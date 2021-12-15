package in.handyman.raven.lambda.process;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import in.handyman.raven.actor.HandymanActorSystemAccess;
import in.handyman.raven.compiler.RavenParser;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.access.ConfigAccess;
import in.handyman.raven.lambda.access.repo.HandymanRepo;
import in.handyman.raven.lambda.access.repo.HandymanRepoR2Impl;
import in.handyman.raven.lambda.action.IActionContext;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.AbstractAudit;
import in.handyman.raven.lambda.doa.Action;
import in.handyman.raven.lambda.doa.ExecutionGroup;
import in.handyman.raven.lambda.doa.ExecutionStatus;
import in.handyman.raven.lambda.doa.Pipeline;
import in.handyman.raven.util.UniqueID;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.helpers.SubstituteLogger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class LambdaEngine {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final HandymanRepo REPO = new HandymanRepoR2Impl();
    private static final String RAVEN_VM = "RavenVM";

    static {
        MAPPER.registerModule(new JavaTimeModule());
    }

    private LambdaEngine() {
    }

    /**
     * Execution starts from here
     */
    public static Pipeline start(final LContext lContext) throws HandymanException {
        if (lContext.getRootPipelineId() != null) {
            lContext.setRootPipelineId(UniqueID.getId());
            log.info("LContext rootID => {} ", lContext.getRootPipelineId());
        }
        log.info("LContext => " + lContext);
        final String hostName;
        try {
            hostName = InetAddress.getLocalHost().getHostAddress();
            log.info("Execution Engine Starts in host {}", hostName);
        } catch (UnknownHostException e) {
            throw new HandymanException("hostName not found ", e);
        }
        final Pipeline pipeline = Pipeline.builder()
                .hostName(hostName)
                .modeOfExecution(RAVEN_VM)
                .threadName(Thread.currentThread().getName())
                .build();
        pipeline.setRootPipelineId(lContext.getRootPipelineId());

        HandymanActorSystemAccess.insert(pipeline);
        pipeline.updateExecutionStatusId(ExecutionStatus.STAGED.getId());
        log.info("Started building the pipeline context");
        try {
            pipeline.updateExecutionStatusId(ExecutionStatus.STARTED.getId());
            final Map<String, String> context = getEContext(lContext.getPipelineName());
            log.info("Raven Context has been initialized");
            final String processFile = getProcessFile(lContext, context);
            context.put("parent-pipeline-id", String.valueOf(lContext.getParentPipelineId()));
            context.put("pipeline-id", String.valueOf(pipeline.getPipelineId()));
            context.put("process-id", String.valueOf(pipeline.getPipelineId()));
            context.putAll(lContext.getInheritedContext());
//            final Map<String, String> other = getOther(pipeline);
//            context.putAll(other);
            log.info("Raven context has been populated with inheritedContext");
            final RavenParserContext ravenParserContext = getRavenParserContext(processFile, lContext.getLambdaName(), context);

            toPipeline(lContext, pipeline);

            final String relativePath = context.get(HRequestResolver.LoadType.FILE.getVariable());
//            if (relativePath != null) {
//                final int i = relativePath.indexOf(pipeline.getPipelineName());
//                pipeline.setLambdaName(relativePath.substring(i ).replace(pipeline.getPipelineName() + "/", ""));
//            }
            pipeline.setProcessName(ravenParserContext.getProcessName());
            pipeline.setContext(context);

            log.info("Initial Pipeline context has been build successfully");
            try {
                log.info("Pipeline execution has been started");
                pipeline.updateExecutionStatusId(ExecutionStatus.RUNNING.getId());
                run(pipeline, ravenParserContext.getTryContext(), context, ExecutionGroup.TRY);
                log.info("Pipeline execution has been completed successfully");
            } catch (Exception e) {
                log.info("Started Executing the catch block");
                run(pipeline, ravenParserContext.getCatchContext(), context, ExecutionGroup.CATCH);
                pipeline.updateExecutionStatusId(ExecutionStatus.FAILED.getId());
                log.error("try section failed", e);
                log.error("Completed Execution catch block");
                throw new HandymanException("Failed", e);
            } finally {
                log.info("Executing Finally Block");
                run(pipeline, ravenParserContext.getFinallyContext(), context, ExecutionGroup.FINALLY);
                HandymanActorSystemAccess.update(pipeline);
                log.info("Completed execution finally block");
                if (ExecutionStatus.get(pipeline.getExecutionStatusId()) == ExecutionStatus.RUNNING) {
                    final List<Action> actions = REPO.findActions(pipeline.getPipelineId());
                    final Integer executionStatusId = actions.stream().filter(action -> ExecutionStatus.get(pipeline.getExecutionStatusId()) != ExecutionStatus.COMPLETED)
                            .findFirst().map(Action::getExecutionStatusId).orElse(ExecutionStatus.COMPLETED.getId());
                    pipeline.updateExecutionStatusId(executionStatusId);
                }
                HandymanActorSystemAccess.update(pipeline);
            }
        } catch (Exception e) {
            log.error("Process section failed", e);
            pipeline.updateExecutionStatusId(ExecutionStatus.FAILED.getId());
            HandymanActorSystemAccess.update(pipeline);
            throw new HandymanException("Failed for " + pipeline.getPipelineName(), e);
        }
        return pipeline;
    }

    private static HashMap<String, String> getEContext(final String lambdaName) {
        log.info("Configuration for the lambda {}", lambdaName);
        return new HashMap<>(ConfigAccess.getAllConfig(lambdaName));
    }

    private static String getProcessFile(final LContext lContext, final Map<String, String> context) {
        final String lambdaName = lContext.getLambdaName();
        final String relativePath = lContext.getRelativePath();
        log.info("Started initializing raven context ");
        final String processLoadType = lContext.getProcessLoadType();
        final String processFile;
        if (lContext.getParentPipelineId() != null) {
            processFile = getProcessFile(HRequestResolver.LoadType.FILE.name(), lambdaName, context, relativePath);
        } else if (HRequestResolver.LoadType.valueOf(processLoadType) == HRequestResolver.LoadType.REST) {
            processFile = lContext.getPayload();
        } else {
            processFile = getProcessFile(processLoadType, lambdaName, context, null);
        }
        return processFile;
    }

    private static RavenParserContext getRavenParserContext(final String processFile, final String lambdaName,
                                                            final Map<String, String> context) {
        log.debug("Handyman Engine start for {}", lambdaName);
        final RavenParser.ProcessContext ravenParser = LambdaParser.doParse(processFile);
        return RavenParserContext.builder()
                .processName(CommandProxy.getString(ravenParser.name, context))
                .tryContext(ravenParser.tryBlock.actions)
                .catchContext(ravenParser.catchBlock.actions)
                .finallyContext(ravenParser.finallyBlock.actions)
                .build();
    }

    private static void toPipeline(final LContext lContext, final Pipeline pipeline) {
        pipeline.setRelativePath(lContext.getRelativePath());
        pipeline.setPipelineLoadType(lContext.getProcessLoadType());
        pipeline.setLambdaName(lContext.getLambdaName());
        pipeline.setPipelineName(lContext.getPipelineName());
        pipeline.setParentPipelineId(lContext.getParentPipelineId());
        pipeline.setParentPipelineName(lContext.getParentPipelineName());
        pipeline.setParentActionId(lContext.getParentActionId());
        pipeline.setParentActionName(lContext.getParentActionName());
        pipeline.setRequestBody(lContext.getPayload());
    }

    private static void run(final Pipeline pipeline,
                            final List<RavenParser.ActionContext> contexts,
                            final Map<String, String> context,
                            final ExecutionGroup executionGroup) {
        contexts.forEach(actionContext -> {
            var action = Action.builder()
                    .executionGroupId(executionGroup.getId())
                    .pipelineId(pipeline.getPipelineId())
                    .build();
            action.setContext(context);
            action.setPipelineId(pipeline.getPipelineId());
            toAction(action, pipeline);
            log.info("");
            log.info("Action context has been set successfully for action {}", action.getActionId());
            doAction(action, actionContext);
            log.info("Action context has been executed successfully for action {}", action.getActionId());
            log.info("");
        });
    }

    private static String getProcessFile(final String processLoadType, final String lambdaName, final Map<String, String> context, final String relativePath) {
        final String processFile = relativePath != null ? HRequestResolver.readFile(relativePath, "") :
                HRequestResolver.doResolve(lambdaName, processLoadType, context);
        if (Objects.isNull(processFile)) {
            throw new HandymanException("Content configuration for process " + lambdaName + " is missing, check spw_process_config or spw_instance_config");
        }
        if (processFile.isEmpty()) {
            throw new HandymanException("Content configuration for process " + lambdaName + " is missing, check spw_process_config or spw_instance_config");
        }
        return processFile;
    }

    public static void toAction(final Action action, final AbstractAudit abstractAudit) {
        action.setPipelineName(abstractAudit.getPipelineName());
        action.setLambdaName(abstractAudit.getLambdaName());
        action.setParentActionId(abstractAudit.getParentActionId());
        action.setParentActionName(abstractAudit.getParentActionName());
        action.setParentPipelineId(abstractAudit.getParentPipelineId());
        action.setParentPipelineName(abstractAudit.getParentPipelineName());
        action.setRootPipelineId(abstractAudit.getRootPipelineId());
    }

    public static void doAction(final Action action, final RavenParser.ActionContext actionContext) {
        final SubstituteLogger logger = getLogger(action);
        HandymanActorSystemAccess.insert(action);
        action.updateExecutionStatusId(ExecutionStatus.STAGED.getId());
        HandymanActorSystemAccess.update(action);
        logger.info("\n");
        try {
            logger.info("Action execution has been started");
            logger.info("Given context {}", action.getContext());
            final IActionExecution execution = load(actionContext, action);
            execute(execution, action);
            logger.info("Execution has been completed successfully");
        } catch (Exception e) {
            logger.error("Exception " + action.getActionName(), e);
            action.updateExecutionStatusId(ExecutionStatus.FAILED.getId());
            throw new HandymanException("Failed to convert", e);
        } finally {
            HandymanActorSystemAccess.update(action);
            final StringBuilder stringBuilder = new StringBuilder();
            logger.info("Started collecting Lambda engine logs {}", action.getActionName());
            stringBuilder.append("\n");
            action.getEventQueue().forEach(event -> {
                append(stringBuilder, Instant.ofEpochMilli(event.getTimeStamp()));
                stringBuilder.append(" ");
                append(stringBuilder, event.getThreadName());
                stringBuilder.append(" ");
                append(stringBuilder, event.getLevel());
                stringBuilder.append(" ");
                append(stringBuilder, event.getMarker());
                stringBuilder.append(" ");
                append(stringBuilder, MessageFormatter.arrayFormat(event.getMessage(), event.getArgumentArray()).getMessage());
                if (event.getThrowable() != null) {
                    var sw = new StringWriter();
                    var pw = new PrintWriter(sw);
                    event.getThrowable().printStackTrace(pw);
                    var sStackTrace = sw.toString();
                    stringBuilder.append("\n");
                    stringBuilder.append(sStackTrace);
                }
                stringBuilder.append("\n");
            });
            logger.info("\n");
            log.info("Completed collecting LambdaEngine logs");
            action.setLog(stringBuilder.toString());
            log.info(stringBuilder.toString());
            HandymanActorSystemAccess.update(action);
        }
    }

    public static SubstituteLogger getLogger(final Action action) {
        return new SubstituteLogger(action.getActionName(), action.getEventQueue(), false);
    }

    private static IActionExecution load(final RavenParser.ActionContext actionContext, final Action action) {
        final ParseTree child = actionContext.getChild(0);
        if (child != null) {
            final String actionName = child.getClass().getSimpleName().replace(ProcessExecutor.CONTEXT, "");
            action.setActionName(actionName);
            final Logger logger = getLogger(action);
            logger.info("Execution class {} staged", actionName);
            if (ProcessExecutor.ACTION_CONTEXT_MAP.containsKey(actionName) && ProcessExecutor.ACTION_EXECUTION_MAP.containsKey(actionName)) {
                try {
                    action.updateExecutionStatusId(ExecutionStatus.STARTED.getId());
                    return initialization(child, action);
                } catch (Exception e) {
                    logger.trace("Error at Initialization " + actionName, e);
                }
            } else {
                logger.trace("Error Not found action " + actionName);
            }
        }
        throw new HandymanException("Unknown ActionContext");
    }

    public static void execute(final IActionExecution execution, final Action action) {
        final Logger logger = getLogger(action);
        final String actionName = action.getActionName();
        try {
            action.setThreadName(Thread.currentThread().getName());
            action.updateExecutionStatusId(ExecutionStatus.RUNNING.getId());
            logger.info("Execution class {} loaded", actionName);
            if (execution.executeIf()) {
                logger.info("Execution class {} condition passed", actionName);
                execution.execute();
                logger.info("Execution class {} executed", actionName);
            } else {
                logger.info("Execution class {} condition failed", actionName);
            }
            action.updateExecutionStatusId(ExecutionStatus.COMPLETED.getId());
        } catch (Exception e) {
            logger.trace("Error at Execution " + actionName, e);
            action.updateExecutionStatusId(ExecutionStatus.FAILED.getId());
            throw new HandymanException("Execution failed for " + actionName, e);
        }
    }

    private static void append(final StringBuilder stringBuilder, final Object value) {
        if (value != null) {
            stringBuilder.append(String.format("%s", value));
        }
    }

    //TODO preload actions for staging
    private static IActionExecution initialization(
            final ParseTree child,
            final Action action)
            throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final Logger logger = getLogger(action);
        final String actionName = action.getActionName();
        final IActionContext actionContext = (IActionContext) ProcessExecutor.ACTION_CONTEXT_MAP.get(actionName).getConstructor().newInstance();
        logger.debug("actionContext Execution class {} instance created", actionName);
        CommandProxy.setTarget(actionContext, child, action.getContext());
        action.setActionName(actionContext.getName());
        logger.debug("actionContext Execution context {}", actionContext);
        action.setInput(MAPPER.convertValue(actionContext, JsonNode.class));
        HandymanActorSystemAccess.update(action);
        return (IActionExecution) ProcessExecutor.ACTION_EXECUTION_MAP.get(actionName)
                .getConstructor(Action.class, Logger.class, Object.class)
                .newInstance(action, logger, actionContext);
    }

    public static Action getAction(final String actionName, final Action action) {
        var vAction = Action.builder()
                .pipelineId(action.getPipelineId())
                .executionGroupId(ExecutionGroup.ACTION.getId())
                .actionName(actionName)
                .build();
        vAction.setContext(action.getContext());
        LambdaEngine.toAction(vAction, action);
        vAction.setParentPipelineId(action.getPipelineId());
        vAction.setParentPipelineName(action.getPipelineName());
        vAction.setParentActionId(action.getActionId());
        vAction.setParentActionName(action.getActionName());
        return vAction;
    }

}
