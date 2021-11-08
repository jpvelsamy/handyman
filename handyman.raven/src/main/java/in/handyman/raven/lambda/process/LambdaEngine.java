package in.handyman.raven.lambda.process;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.handyman.raven.actor.HandymanActorSystemAccess;
import in.handyman.raven.compiler.RavenParser;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.access.ConfigAccess;
import in.handyman.raven.lambda.action.IActionContext;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.AbstractAudit;
import in.handyman.raven.lambda.doa.Action;
import in.handyman.raven.lambda.doa.ExecutionGroup;
import in.handyman.raven.lambda.doa.ExecutionStatus;
import in.handyman.raven.lambda.doa.Pipeline;
import lombok.extern.log4j.Log4j2;
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

@Log4j2
public class LambdaEngine {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private LambdaEngine() {
    }

    /**
     * Execution starts from here
     */
    public static Pipeline start(final LContext lContext) {
        final String hostName;
        try {
            hostName = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new HandymanException("hostName not found ", e);
        }

        final Pipeline pipeline = Pipeline.builder()
                .relativePath(lContext.getRelativePath())
                .hostName(hostName)
                .modeOfExecution("RavenVM")
                .threadName(Thread.currentThread().getName())
                .build();
        try {
            pipeline.setPipelineLoadType(lContext.getProcessLoadType());
            pipeline.setLambdaName(lContext.getLambdaName());
            pipeline.setParentPipelineId(lContext.getParentPipelineId());
            pipeline.setParentPipelineName(lContext.getParentPipelineName());
            pipeline.setParentActionId(lContext.getParentActionId());
            pipeline.setParentActionName(lContext.getParentActionName());
            pipeline.updateExecutionStatusId(ExecutionStatus.STAGED.getId());

            final RavenParserContext ravenParserContext = lContext.getParentPipelineId() != null
                    ? newInstance(lContext.getRelativePath(), lContext.getLambdaName(), lContext.getInheritedContext(), pipeline)
                    : newInstance(lContext.getProcessLoadType(), pipeline);
            final Map<String, String> context = ravenParserContext.getContext();
            context.put("parent-pipeline-id", String.valueOf(lContext.getParentPipelineId()));
            context.put("pipeline-id", String.valueOf(pipeline.getPipelineId()));
            context.put("process-id", String.valueOf(pipeline.getPipelineId()));

            pipeline.setPipelineName(ravenParserContext.getProcessName());
            pipeline.setContext(context);
            HandymanActorSystemAccess.insert(pipeline);
            pipeline.updateExecutionStatusId(ExecutionStatus.STARTED.getId());
            try {
                run(pipeline, ravenParserContext.getTryContext(), context, ExecutionGroup.TRY);
                pipeline.updateExecutionStatusId(ExecutionStatus.COMPLETED.getId());
            } catch (Exception e) {
                run(pipeline, ravenParserContext.getCatchContext(), context, ExecutionGroup.CATCH);
                pipeline.updateExecutionStatusId(ExecutionStatus.FAILED.getId());
                log.error(e);
                throw new HandymanException("Failed", e);

            } finally {
                run(pipeline, ravenParserContext.getFinallyContext(), context, ExecutionGroup.FINALLY);
                HandymanActorSystemAccess.update(pipeline);
            }
        } catch (Exception e) {
            log.error(e);
            pipeline.updateExecutionStatusId(ExecutionStatus.FAILED.getId());
            HandymanActorSystemAccess.update(pipeline);
            throw new HandymanException("Failed", e);
        }
        return pipeline;
    }

    private static RavenParserContext newInstance(final String relativePath, final String lambdaName, final Map<String, String> inheritedContext, final Pipeline pipeline) {
        final Map<String, String> context = getEContext(lambdaName);
        context.putAll(inheritedContext);
        final String processFile = getProcessFile(HRequestResolver.LoadType.FILE.name(), lambdaName, context, relativePath);
        pipeline.setFileContent(processFile);
        return getRavenParserContext(processFile, lambdaName, context);
    }

    private static RavenParserContext newInstance(final String processLoadType, final Pipeline pipeline) {
        final Map<String, String> context = getEContext(pipeline.getPipelineName());
        final String lambdaName = pipeline.getPipelineName();
        var processFile = getProcessFile(processLoadType, lambdaName, context, null);
        pipeline.setFileContent(processFile);
        return getRavenParserContext(processFile, lambdaName, context);
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
            doAction(action, actionContext);
        });
    }

    private static HashMap<String, String> getEContext(final String lambdaName) {
        return new HashMap<>(ConfigAccess.getAllConfig(lambdaName));
    }

    private static String getProcessFile(final String processLoadType, final String lambdaName, final Map<String, String> context, final String relativePath) {
        final String processFile = relativePath != null ? HRequestResolver.readFile(relativePath, "") : HRequestResolver.doResolve(lambdaName, processLoadType, context);
        if (Objects.isNull(processFile)) {
            throw new HandymanException("Content configuration for process " + lambdaName + " is missing, check spw_process_config or spw_instance_config");
        }
        if (processFile.isEmpty()) {
            throw new HandymanException("Content configuration for process " + lambdaName + " is missing, check spw_process_config or spw_instance_config");
        }
        return processFile;
    }

    private static RavenParserContext getRavenParserContext(final String processFile, final String lambdaName,
                                                            final Map<String, String> context) {
        log.debug("Handyman Engine start for {}", lambdaName);
        final RavenParser.ProcessContext ravenParser = LambdaParser.doParse(processFile, context);
        return RavenParserContext.builder()
                .processName(String.valueOf(ravenParser.name))
                .tryContext(ravenParser.tryBlock.actions)
                .catchContext(ravenParser.catchBlock.actions)
                .finallyContext(ravenParser.finallyBlock.actions)
                .context(context)
                .build();
    }

    public static void toAction(final Action action, final AbstractAudit abstractAudit) {
        action.setPipelineName(abstractAudit.getPipelineName());
        action.setLambdaName(action.getLambdaName());
        action.setParentActionId(abstractAudit.getParentActionId());
        action.setParentActionName(abstractAudit.getParentActionName());
        action.setParentPipelineId(abstractAudit.getParentPipelineId());
        action.setParentPipelineName(abstractAudit.getParentPipelineName());
    }

    public static void doAction(final Action action, final RavenParser.ActionContext actionContext) {
        HandymanActorSystemAccess.insert(action);
        action.updateExecutionStatusId(ExecutionStatus.STAGED.getId());
        HandymanActorSystemAccess.update(action);
        try {
            final IActionExecution execution = load(actionContext, action);
            execute(execution, action);
//                return action;
        } catch (Exception e) {
            log.error("Failed " + action, e);
            final SubstituteLogger logger = getLogger(action);
            logger.error("Exception", e);
            throw new HandymanException("Failed to convert", e);
        } finally {
            HandymanActorSystemAccess.update(action);
            final StringBuilder stringBuilder = new StringBuilder();
            action.getEventQueue().forEach(event -> {
                //TODO format this
                stringBuilder.append(String.format("Level %s Marker %s ThreadName %s Time %s Message %s", event.getLevel(),
                        event.getMarker(), event.getThreadName(),
                        Instant.ofEpochMilli(event.getTimeStamp()),
                        MessageFormatter.arrayFormat(event.getMessage(), event.getArgumentArray()).getMessage()));
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
            action.setLog(stringBuilder.toString());
            log.info(stringBuilder);
            HandymanActorSystemAccess.update(action);
        }
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

    private static void execute(final IActionExecution execution, final Action action) {
        final Logger logger = getLogger(action);
        final String actionName = action.getActionName();
        try {
            action.updateExecutionStatusId(ExecutionStatus.RUNNING.getId());
            logger.info("Execution class {} loaded", actionName);
            if (execution.executeIf()) {
                logger.info("Execution class {} condition passed", actionName);
                execution.execute();
                logger.info("Execution class {} executed", actionName);
            }
            action.updateExecutionStatusId(ExecutionStatus.COMPLETED.getId());
        } catch (Exception e) {
            logger.trace("Error at Execution " + actionName, e);
            action.updateExecutionStatusId(ExecutionStatus.FAILED.getId());
        }
    }

    public static SubstituteLogger getLogger(final Action action) {
        return new SubstituteLogger(action.getActionName(), action.getEventQueue(), false);
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
        logger.debug("actionContext Execution class {} actionContext mapped", actionName);
        action.setInput(MAPPER.convertValue(actionContext, JsonNode.class));
        HandymanActorSystemAccess.update(action);
        return (IActionExecution) ProcessExecutor.ACTION_EXECUTION_MAP.get(actionName)
                .getConstructor(Action.class, Logger.class, Object.class)
                .newInstance(action, logger, actionContext);
    }

}
