package in.handyman.raven.process;

import in.handyman.raven.action.ActionContext;
import in.handyman.raven.action.ActionExecution;
import in.handyman.raven.action.IActionContext;
import in.handyman.raven.action.IActionExecution;
import in.handyman.raven.actor.HandymanActorSystemAccess;
import in.handyman.raven.audit.AuditPayload;
import in.handyman.raven.compiler.RavenParser;
import in.handyman.raven.config.ConfigurationService;
import in.handyman.raven.exception.HandymanException;
import lombok.extern.log4j.Log4j2;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
public class ProcessExecutor {

    private static final String CONTEXT = "Context";
    private static final Map<String, ? extends Class<?>> actionContextMap;
    private static final Map<String, ? extends Class<?>> actionExecutionMap;

    static {
        try {
            log.info("IActionContext Execution classes loader initialized");
            final Set<String> actionPackageNames = getPackageAction();
            final Set<Class<?>> actionContexts = getActionContextClass(actionPackageNames);
            final Set<Class<?>> actionExecutions = getActionExecutionClass(actionPackageNames);

            if (actionExecutions.isEmpty() || actionContexts.isEmpty() || actionExecutions.size() != actionContexts.size()) {
                throw new HandymanException("Size mismatched or Empty IActionContext initialization");
            }

            actionContextMap = getActionContext(actionContexts);
            actionExecutionMap = getActionExecution(actionExecutions);
            if (!actionExecutionMap.keySet().containsAll(actionContextMap.keySet())) {
                throw new HandymanException("Mismatched lambdaName");
            }
            log.info("IActionContext Execution classes successfully loaded");
        } catch (Exception e) {
            throw new HandymanException(e.getMessage(), e);
        }
    }

    private static Set<Class<?>> getActionExecutionClass(final Set<String> packageNames) {
        return packageNames.stream().flatMap(packageName -> {
            try {
                final Reflections reflections = new Reflections(packageName);
                return reflections.getTypesAnnotatedWith(ActionExecution.class).stream();
            } catch (Exception e) {
                log.error("ActionExecution failed for the package name {}", packageName, e);
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    private static Map<String, ? extends Class<?>> getActionExecution(final Set<Class<?>> classes) {
        return classes.stream()
                .filter(aClass -> {
                    final ActionExecution annotation = aClass.getAnnotation(ActionExecution.class);
                    return IActionExecution.class.isAssignableFrom(aClass) &&
                            Objects.nonNull(annotation) && !annotation.actionName().isEmpty() && !annotation.actionName().isBlank();
                }).collect(Collectors.toMap(aClass -> aClass.getAnnotation(ActionExecution.class).actionName(),
                        aClass -> aClass, (p, q) -> p));
    }

    protected static Context doExecute(final Process process, final RavenParser.ActionContext context) {
        if (context.getChild(0) != null) {
            final String lambdaName = context.getChild(0).getClass().getSimpleName().replace(CONTEXT, "");
            log.debug("IActionContext Execution class {} started", lambdaName);
            if (actionContextMap.containsKey(lambdaName) && actionExecutionMap.containsKey(lambdaName)) {
                final Context actionContext = Context.builder()
                        .lambdaName(lambdaName)
                        .processId(process.getProcessId())
                        .processName(process.getProcessName())
                        .context(process.getContext())
                        .build();
                try {
                    final IActionExecution IActionExecution = initialization(actionContext, context, lambdaName);
                    log.debug("IActionContext Execution class {} initialized", lambdaName);
                    execution(IActionExecution);
                    log.debug("IActionContext Execution class {} executed", lambdaName);
                    actionContext.setStatus(ProcessStatus.SUCCESS);
                } catch (Exception e) {
                    actionContext.setStatus(ProcessStatus.FAILURE);
                    throw new HandymanException(e.getMessage(), e);
                } finally {
                    final AuditPayload auditPayload = getAuditContext(actionContext);
                    auditPayload.setStatus(actionContext.getStatus());
                    auditPayload.setAuditType(AuditPayload.AuditType.UPDATE_COMMAND_AUDIT);
                    auditPayload.setCommandDetail(actionContext.getDetailMap().toString());
                    HandymanActorSystemAccess.doAudit(auditPayload);
                }
                return actionContext;
            }
        }
        throw new HandymanException("Unknown IActionContext");
    }

    private static IActionExecution initialization(
            final Context actionContext,
            final RavenParser.ActionContext context,
            final String lambdaName)
            throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final IActionContext IActionContext = (IActionContext) actionContextMap.get(lambdaName).getConstructor().newInstance();
        log.debug("IActionContext Execution class {} instance created", lambdaName);
        CommandProxy.setTarget(IActionContext, context.getChild(0), actionContext.getContext());
        log.debug("IActionContext Execution class {} context mapped", lambdaName);
        actionContext.setName(lambdaName + "->" + IActionContext.getName());
        final AuditPayload auditPayload = getAuditContext(actionContext);
        HandymanActorSystemAccess.doAudit(auditPayload);
        return (IActionExecution) actionExecutionMap.get(lambdaName)
                .getConstructor(Context.class, Object.class)
                .newInstance(actionContext, IActionContext);
    }

    private static void execution(final IActionExecution IActionExecution) throws Exception {
        if (IActionExecution.executeIf()) {
            IActionExecution.execute();
        }
    }

    private static AuditPayload getAuditContext(final Context context) {
        return AuditPayload.builder()
                .auditType(AuditPayload.AuditType.CREATE_COMMAND_AUDIT)
                .processId(context.getProcessId())
                .actionId(context.getLambdaId())
                .instanceName(context.getProcessName())
                .processName(context.getProcessName())
                .actionName(context.getName())
                .build();
    }

    private static Set<String> getPackageAction() {
        return ConfigurationService.getPackageAction();
    }

    private static Set<Class<?>> getActionContextClass(final Set<String> packageNames) {
        return packageNames.stream().flatMap(packageName -> {
            try {
                final Reflections reflections = new Reflections(packageName);
                return reflections.getTypesAnnotatedWith(ActionContext.class).stream();
            } catch (Exception e) {
                log.error("Context failed for the package name {}", packageName, e);
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    private static Map<String, ? extends Class<?>> getActionContext(final Set<Class<?>> classes) {
        return classes.stream()
                .filter(aClass -> {
                    final ActionContext annotation = aClass.getAnnotation(ActionContext.class);
                    return IActionContext.class.isAssignableFrom(aClass) &&
                            Objects.nonNull(annotation) && !annotation.actionName().isEmpty() && !annotation.actionName().isBlank();
                }).collect(Collectors.toMap(aClass -> aClass.getAnnotation(ActionContext.class).actionName(),
                        aClass -> aClass, (p, q) -> p));
    }

}
