package in.handyman.raven.process;

import in.handyman.raven.actor.HandymanActorSystemAccess;
import in.handyman.raven.audit.AuditPayload;
import in.handyman.raven.compiler.RavenParser;
import in.handyman.raven.config.ConfigurationService;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.action.Action;
import in.handyman.raven.action.ActionContext;
import in.handyman.raven.action.IAction;
import in.handyman.raven.action.IActionExecution;
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
    private static final Map<String, ? extends Class<?>> actionExecutionContextMap;
    private static final Map<String, ? extends Class<?>> actionExecutionMap;

    static {
        try {
            log.info("IAction Execution classes loader initialized");
            final Set<String> lambdaPackageNames = getPackageLambda();
            final Set<Class<?>> lambdaContext = getLambdaContext(lambdaPackageNames);
            final Set<Class<?>> lambdaAutowire = lambdaPackageNames.stream().flatMap(packageName -> {
                try {
                    final Reflections reflections = new Reflections(packageName);
                    return reflections.getTypesAnnotatedWith(Action.class).stream();
                } catch (Exception e) {
                    log.error("Action failed for the package name {}", packageName, e);
                    return null;
                }
            }).filter(Objects::nonNull).collect(Collectors.toSet());

            if (lambdaAutowire.isEmpty() || lambdaContext.isEmpty() || lambdaAutowire.size() != lambdaContext.size()) {
                throw new HandymanException("Size mismatched or Empty IAction initialization");
            }

            actionExecutionContextMap = getActionExecutionContextMap(lambdaContext);
            actionExecutionMap = lambdaAutowire.stream()
                    .filter(aClass -> {
                        final Action annotation = aClass.getAnnotation(Action.class);
                        return IActionExecution.class.isAssignableFrom(aClass) &&
                                Objects.nonNull(annotation) && !annotation.actionName().isEmpty() && !annotation.actionName().isBlank();
                    }).collect(Collectors.toMap(aClass -> aClass.getAnnotation(Action.class).actionName(),
                            aClass -> aClass, (p, q) -> p));
            if (!actionExecutionMap.keySet().containsAll(actionExecutionContextMap.keySet())) {
                throw new HandymanException("Mismatched lambdaName");
            }
            log.info("IAction Execution classes successfully loaded");
        } catch (Exception e) {
            throw new HandymanException(e.getMessage(), e);
        }
    }

    protected static Context doExecute(final Process process, final RavenParser.ActionContext context) {
        if (context.getChild(0) != null) {
            final String lambdaName = context.getChild(0).getClass().getSimpleName().replace(CONTEXT, "");
            log.debug("IAction Execution class {} started", lambdaName);
            if (actionExecutionContextMap.containsKey(lambdaName) && actionExecutionMap.containsKey(lambdaName)) {
                final Context actionContext = Context.builder()
                        .lambdaName(lambdaName)
                        .processId(process.getProcessId())
                        .processName(process.getProcessName())
                        .context(process.getContext())
                        .build();
                try {
                    final IActionExecution IActionExecution = initialization(actionContext, context, lambdaName);
                    log.debug("IAction Execution class {} initialized", lambdaName);
                    execution(IActionExecution);
                    log.debug("IAction Execution class {} executed", lambdaName);
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
        throw new HandymanException("Unknown IAction");
    }

    private static IActionExecution initialization(
            final Context actionContext,
            final RavenParser.ActionContext context,
            final String lambdaName)
            throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final IAction IAction = (IAction) actionExecutionContextMap.get(lambdaName).getConstructor().newInstance();
        log.debug("IAction Execution class {} instance created", lambdaName);
        CommandProxy.setTarget(IAction, context.getChild(0), actionContext.getContext());
        log.debug("IAction Execution class {} context mapped", lambdaName);
        actionContext.setName(lambdaName + "->" + IAction.getName());
        final AuditPayload auditPayload = getAuditContext(actionContext);
        HandymanActorSystemAccess.doAudit(auditPayload);
        return (IActionExecution) actionExecutionMap.get(lambdaName)
                .getConstructor(Context.class, Object.class)
                .newInstance(actionContext, IAction);
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

    public static Map<String, Object> getLambdas() {
        final Set<String> lambdaPackageNames = getPackageLambda();
        final Set<Class<?>> lambdaContext = getLambdaContext(lambdaPackageNames);
        var lambdas = new HashMap<String, Object>();
        getActionExecutionContextMap(lambdaContext).forEach((s, aClass) -> {
            try {
                final Object o = aClass.getConstructor().newInstance();
                lambdas.put(s, o);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new HandymanException(aClass.getName() + " new instance failed", e);
            }
        });
        return lambdas;
    }

    private static Set<String> getPackageLambda() {
        return ConfigurationService.getPackageLambda();
    }

    private static Set<Class<?>> getLambdaContext(final Set<String> lambdaPackageNames) {
        return lambdaPackageNames.stream().flatMap(packageName -> {
            try {
                final Reflections reflections = new Reflections(packageName);
                return reflections.getTypesAnnotatedWith(ActionContext.class).stream();
            } catch (Exception e) {
                log.error("Context failed for the package name {}", packageName, e);
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    private static Map<String, ? extends Class<?>> getActionExecutionContextMap(final Set<Class<?>> lambdaContext) {
        return lambdaContext.stream()
                .filter(aClass -> {
                    final ActionContext annotation = aClass.getAnnotation(ActionContext.class);
                    return IAction.class.isAssignableFrom(aClass) &&
                            Objects.nonNull(annotation) && !annotation.actionName().isEmpty() && !annotation.actionName().isBlank();
                }).collect(Collectors.toMap(aClass -> aClass.getAnnotation(ActionContext.class).actionName(),
                        aClass -> aClass, (p, q) -> p));
    }

}
