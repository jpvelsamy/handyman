package in.handyman.raven.lym.process;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.handyman.raven.action.IActionContext;
import in.handyman.raven.action.IActionExecution;
import in.handyman.raven.compiler.RavenParser;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lym.access.ConfigAccess;
import in.handyman.raven.lym.doa.Action;
import in.handyman.raven.lym.doa.ExecutionStatus;
import in.handyman.raven.lym.doa.Pipeline;
import in.handyman.raven.process.Context;
import lombok.extern.log4j.Log4j2;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.helpers.SubstituteLogger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
public class LambdaEngine {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private LambdaEngine() {
    }

    public static void start(final LContext lContext) {
        final Pipeline pipeline = Pipeline.builder()
                .relativePath(lContext.getRelativePath())
                .build();
        final RavenParserContext ravenParserContext = lContext.getParentPipelineId() != null ? newInstance(lContext.getProcessLoadType(), lContext.getLambdaName())
                : newInstance(lContext.getRelativePath(), lContext.getLambdaName(), lContext.getInheritedContext());
        final Map<String, String> context = ravenParserContext.getContext();
        context.put("parent-pipeline-id", String.valueOf(lContext.getParentPipelineId()));
        context.put("pipeline-id", String.valueOf(pipeline.getPipelineId()));
        context.put("process-id", String.valueOf(pipeline.getPipelineId()));
        try {
            final List<Action> actions = extracted(pipeline, ravenParserContext.getTryContext(), context);
            final boolean allMatch = actions.stream()
                    .allMatch(action -> ExecutionStatus.get(action.getExecutionStatusId()) == ExecutionStatus.COMPLETED);
            pipeline.setPipelineId();
        } catch (Exception e) {
            final List<Action> actions = extracted(pipeline, ravenParserContext.getCatchContext(), context);
        } finally {
            final List<Action> actions = extracted(pipeline, ravenParserContext.getFinallyContext(), context);
        }
    }

    private static List<Action> extracted(final Pipeline pipeline,
                                          final List<RavenParser.ActionContext> contexts,
                                          final Map<String, String> context) {
        return contexts.stream().map(actionContext -> {
            var action = Action.builder()
                    .pipelineId(pipeline.getPipelineId())
                    .build();
            action.setContext(context);
            toAction(action, pipeline);
            action.setExecutionStatusId(ExecutionStatus.STAGED.getId());
            //TODO action
            try {
                final IActionExecution execution = load(actionContext, action);
                execute(execution, action);
                return action;
            } finally {
                final StringBuilder stringBuilder = new StringBuilder();
                action.getEventQueue().forEach(event -> {
                    //TODO format this
                    stringBuilder.append(String.format("Level {%s} Marker {%s} ThreadName {%s} Time {%s} Message {%s}", event.getLevel(),
                            event.getMarkers(), event.getThreadName(),
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
            }
        }).collect(Collectors.toList());
    }

    private static void toAction(final Action action, final Pipeline pipeline) {
        action.setPipelineId(pipeline.getPipelineId());
        action.setPipelineName(pipeline.getPipelineName());
        action.setLambdaName(action.getLambdaName());
        action.setParentActionId(pipeline.getParentActionId());
        action.setParentActionName(pipeline.getParentActionName());
        action.setParentPipelineId(pipeline.getParentPipelineId());
        action.setParentPipelineName(pipeline.getParentPipelineName());
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
                    action.setExecutionStatusId(ExecutionStatus.STARTED.getId());
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
            action.setExecutionStatusId(ExecutionStatus.RUNNING.getId());
            logger.info("Execution class {} loaded", actionName);
            if (execution.executeIf()) {
                logger.info("Execution class {} condition passed", actionName);
                execution.execute();
                logger.info("Execution class {} executed", actionName);
            }
            action.setExecutionStatusId(ExecutionStatus.COMPLETED.getId());
        } catch (Exception e) {
            logger.trace("Error at Execution " + actionName, e);
            action.setExecutionStatusId(ExecutionStatus.FAILED.getId());
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
        logger.debug("actionContext Execution class {} actionContext mapped", actionName);
        action.setInput(MAPPER.convertValue(actionContext, JsonNode.class));
        return (IActionExecution) ProcessExecutor.ACTION_EXECUTION_MAP.get(actionName)
                .getConstructor(Context.class, Object.class)
                .newInstance(action, logger, actionContext);
    }

    private static SubstituteLogger getLogger(final Action action) {
        return new SubstituteLogger(action.getActionName(), action.getEventQueue(), false);
    }

    private static RavenParserContext newInstance(final String processLoadType, final String lambdaName) {
        final Map<String, String> context = getEContext(lambdaName);
        final String processFile = getProcessFile(processLoadType, lambdaName, context, null);
        return getRavenParserContext(processFile, lambdaName, context);
    }

    private static RavenParserContext newInstance(final String relativePath, final String lambdaName, final Map<String, String> inheritedContext) {
        final Map<String, String> context = getEContext(lambdaName);
        context.putAll(inheritedContext);
        final String processFile = getProcessFile(HRequestResolver.LoadType.FILE.name(), lambdaName, context, relativePath);
        return getRavenParserContext(processFile, lambdaName, context);
    }

    private static RavenParserContext getRavenParserContext(final String processFile, final String lambdaName, final Map<String, String> context) {
        log.debug("Handyman Engine start for {}", lambdaName);
        final RavenParser.ProcessContext ravenParser = LambdaParser.doParse(processFile, context);
        return RavenParserContext.builder()
                .tryContext(ravenParser.tryBlock.actions)
                .catchContext(ravenParser.catchBlock.actions)
                .finallyContext(ravenParser.finallyBlock.actions)
                .context(context)
                .build();
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

    private static HashMap<String, String> getEContext(final String lambdaName) {
        return new HashMap<>(ConfigAccess.getAllConfig(lambdaName));
    }

}