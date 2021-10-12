package in.handyman.raven.process;

import in.handyman.raven.actor.HandymanActorSystemAccess;
import in.handyman.raven.audit.AuditPayload;
import in.handyman.raven.compiler.RavenParser;
import in.handyman.raven.config.ConfigurationService;
import in.handyman.raven.context.ActionContext;
import in.handyman.raven.context.ProcessContext;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.Lambda;
import in.handyman.raven.lambda.LambdaAutowire;
import in.handyman.raven.lambda.LambdaContext;
import in.handyman.raven.lambda.LambdaExecution;
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
            log.info("Lambda Execution classes loader initialized");
            final Set<String> lambdaPackageNames = getPackageLambda();
            final Set<Class<?>> lambdaContext = getLambdaContext(lambdaPackageNames);
            final Set<Class<?>> lambdaAutowire = lambdaPackageNames.stream().flatMap(packageName -> {
                try {
                    final Reflections reflections = new Reflections(packageName);
                    return reflections.getTypesAnnotatedWith(LambdaAutowire.class).stream();
                } catch (Exception e) {
                    log.error("LambdaAutowire failed for the package name {}", packageName, e);
                    return null;
                }
            }).filter(Objects::nonNull).collect(Collectors.toSet());

            if (lambdaAutowire.isEmpty() || lambdaContext.isEmpty() || lambdaAutowire.size() != lambdaContext.size()) {
                throw new HandymanException("Size mismatched or Empty Lambda initialization");
            }

            actionExecutionContextMap = getActionExecutionContextMap(lambdaContext);
            actionExecutionMap = lambdaAutowire.stream()
                    .filter(aClass -> {
                        final LambdaAutowire annotation = aClass.getAnnotation(LambdaAutowire.class);
                        return LambdaExecution.class.isAssignableFrom(aClass) &&
                                Objects.nonNull(annotation) && !annotation.lambdaName().isEmpty() && !annotation.lambdaName().isBlank();
                    }).collect(Collectors.toMap(aClass -> aClass.getAnnotation(LambdaAutowire.class).lambdaName(),
                            aClass -> aClass, (p, q) -> p));
            if (!actionExecutionMap.keySet().containsAll(actionExecutionContextMap.keySet())) {
                throw new HandymanException("Mismatched lambdaName");
            }
            log.info("Lambda Execution classes successfully loaded");
        } catch (Exception e) {
            throw new HandymanException(e.getMessage(), e);
        }
    }

    protected static ActionContext doExecute(final ProcessContext processContext, final RavenParser.ActionContext context) {
        if (context.getChild(0) != null) {
            final String lambdaName = context.getChild(0).getClass().getSimpleName().replace(CONTEXT, "");
            log.debug("Lambda Execution class {} started", lambdaName);
            if (actionExecutionContextMap.containsKey(lambdaName) && actionExecutionMap.containsKey(lambdaName)) {
                final ActionContext actionContext = ActionContext.builder()
                        .lambdaName(lambdaName)
                        .processId(processContext.getProcessId())
                        .processName(processContext.getProcessName())
                        .context(processContext.getContext())
                        .build();
                try {
                    final LambdaExecution lambdaExecution = initialization(actionContext, context, lambdaName);
                    log.debug("Lambda Execution class {} initialized", lambdaName);
                    execution(lambdaExecution);
                    log.debug("Lambda Execution class {} executed", lambdaName);
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
        throw new HandymanException("Unknown Action");
    }

    private static LambdaExecution initialization(
            final ActionContext actionContext,
            final RavenParser.ActionContext context,
            final String lambdaName)
            throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final Lambda lambda = (Lambda) actionExecutionContextMap.get(lambdaName).getConstructor().newInstance();
        log.debug("Lambda Execution class {} instance created", lambdaName);
        CommandProxy.setTarget(lambda, context.getChild(0), actionContext.getContext());
        log.debug("Lambda Execution class {} context mapped", lambdaName);
        actionContext.setName(lambdaName + "->" + lambda.getName());
        final AuditPayload auditPayload = getAuditContext(actionContext);
        HandymanActorSystemAccess.doAudit(auditPayload);
        return (LambdaExecution) actionExecutionMap.get(lambdaName)
                .getConstructor(ActionContext.class, Object.class)
                .newInstance(actionContext, lambda);
    }

    private static void execution(final LambdaExecution lambdaExecution) throws Exception {
        if (lambdaExecution.executeIf()) {
            lambdaExecution.execute();
        }
    }

    private static AuditPayload getAuditContext(final ActionContext actionContext) {
        return AuditPayload.builder()
                .auditType(AuditPayload.AuditType.CREATE_COMMAND_AUDIT)
                .processId(actionContext.getProcessId())
                .actionId(actionContext.getLambdaId())
                .instanceName(actionContext.getProcessName())
                .processName(actionContext.getProcessName())
                .actionName(actionContext.getName())
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
                return reflections.getTypesAnnotatedWith(LambdaContext.class).stream();
            } catch (Exception e) {
                log.error("LambdaContext failed for the package name {}", packageName, e);
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    private static Map<String, ? extends Class<?>> getActionExecutionContextMap(final Set<Class<?>> lambdaContext) {
        return lambdaContext.stream()
                .filter(aClass -> {
                    final LambdaContext annotation = aClass.getAnnotation(LambdaContext.class);
                    return Lambda.class.isAssignableFrom(aClass) &&
                            Objects.nonNull(annotation) && !annotation.lambdaName().isEmpty() && !annotation.lambdaName().isBlank();
                }).collect(Collectors.toMap(aClass -> aClass.getAnnotation(LambdaContext.class).lambdaName(),
                        aClass -> aClass, (p, q) -> p));
    }

}
