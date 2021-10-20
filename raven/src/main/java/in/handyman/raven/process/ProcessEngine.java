package in.handyman.raven.process;

import in.handyman.raven.actor.HandymanActorSystemAccess;
import in.handyman.raven.audit.AuditPayload;
import in.handyman.raven.compiler.RavenParser;
import in.handyman.raven.config.ConfigurationService;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.metric.MetricUtil;
import in.handyman.raven.util.UniqueID;
import lombok.extern.log4j.Log4j2;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
public class ProcessEngine {

    public static Process start(final String processLoadType, final String lambdaName) {
        final Map<String, String> context = new HashMap<>(ConfigurationService.getAllConfig(lambdaName));
        final Process process = getProcess(lambdaName, null, context);
        final String processFile = HRequestResolver.doResolve(processLoadType, process);
        if (Objects.isNull(processFile)) {
            throw new HandymanException("Content configuration for process " + lambdaName + " is missing, check spw_process_config or spw_instance_config");
        }
        if (processFile.isEmpty()) {
            throw new HandymanException("Content configuration for process " + lambdaName + " is missing, check spw_process_config or spw_instance_config");
        }
        log.debug("Handyman Engine start for {}", lambdaName);
        final RavenParser.ProcessContext ravenParser = ProcessParser.doParse(processFile, process.getContext());
        process.setRavenParser(ravenParser);
        return run(process);
    }

    private static Process getProcess(final String lambdaName, final Long parentPipelineId, final Map<String, String> context) {
        final Process process = Process.builder()
                .processId(UniqueID.getId())
                .parentPipelineId(parentPipelineId)
                .context(context)
                .lambdaName(lambdaName)
                .build();
        context.put("process-id", String.valueOf(process.getProcessId()));
        return process;
    }

    public static Process run(final Process process) {
        final RavenParser.ProcessContext ravenParser = process.getRavenParser();
        if (Objects.isNull(ravenParser)) {
            throw new HandymanException(RavenParser.ProcessContext.class.getSimpleName() + " not found ");
        }
        final String machine;
        try {
            machine = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new HandymanException("machine not found ", e);
        }

        final AuditPayload auditPayload = AuditPayload.builder()
                .auditType(AuditPayload.AuditType.CREATE_INSTANCE_AUDIT)
                .processId(process.getProcessId())
                .processName(process.getLambdaName())
                .runMode("RAVEN")
                .machine(machine)
                .build();
        HandymanActorSystemAccess.doAudit(auditPayload);
        try {
            tryActionsExecution(process, ravenParser);
        } catch (HandymanException e) {
            log.error(e);
            process.setStatus(ProcessStatus.FAILURE);
            catchActionsExecution(process, ravenParser);
        } finally {
            finallyActionsExecution(process, ravenParser);
            auditPayload.setAuditType(AuditPayload.AuditType.UPDATE_INSTANCE_AUDIT);
            auditPayload.setStatus(process.getStatus());
            HandymanActorSystemAccess.doAudit(auditPayload);
        }
        MetricUtil.addAfter(process);
        return process;
    }

    private static void tryActionsExecution(final Process processContext, final RavenParser.ProcessContext process) {
        final Set<Context> contexts = new HashSet<>();
        try {
            final List<RavenParser.ActionContext> actions = process.tryBlock.actions;
            final Set<Context> tryExecution = actions.stream().map(action -> ProcessExecutor.doExecute(processContext, action)).collect(Collectors.toSet());
            contexts.addAll(tryExecution);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
            processContext.setStatus(ProcessStatus.FAILURE);
        } finally {
            if (contexts.stream().allMatch(actionContext -> ProcessStatus.SUCCESS.equals(actionContext.getStatus()))) {
                processContext.setTryStatus(ProcessStatus.SUCCESS);
            } else {
                processContext.setTryStatus(ProcessStatus.FAILURE);
            }
            processContext.setTryActions(contexts);
        }
    }

    private static void catchActionsExecution(final Process processContext, final RavenParser.ProcessContext process) {
        final Set<Context> contexts = new HashSet<>();
        try {
            final List<RavenParser.ActionContext> actions = process.catchBlock.actions;
            final Set<Context> catchExecution = actions.stream().map(action -> ProcessExecutor.doExecute(processContext, action)).collect(Collectors.toSet());
            contexts.addAll(catchExecution);
        } catch (Exception e) {
            log.error(e);
            processContext.setStatus(ProcessStatus.FAILURE);
        } finally {
            if (contexts.stream().allMatch(actionContext -> ProcessStatus.SUCCESS.equals(actionContext.getStatus()))) {
                processContext.setCatchStatus(ProcessStatus.SUCCESS);
            } else {
                processContext.setCatchStatus(ProcessStatus.FAILURE);
            }
            processContext.setCatchActions(contexts);
        }
    }

    private static void finallyActionsExecution(final Process processContext, final RavenParser.ProcessContext process) {
        final Set<Context> contexts = new HashSet<>();
        try {
            final List<RavenParser.ActionContext> actions = process.finallyBlock.actions;
            final Set<Context> finallyExecution = actions.stream().map(action -> ProcessExecutor.doExecute(processContext, action)).collect(Collectors.toSet());
            contexts.addAll(finallyExecution);
        } catch (Exception e) {
            log.error(e);
            processContext.setStatus(ProcessStatus.FAILURE);
        } finally {
            if (contexts.stream().allMatch(actionContext -> ProcessStatus.SUCCESS.equals(actionContext.getStatus()))) {
                processContext.setFinallyStatus(ProcessStatus.SUCCESS);
            } else {
                processContext.setFinallyStatus(ProcessStatus.FAILURE);
            }
            processContext.setFinallyActions(contexts);
        }
    }

    public static Process start(final String relativePath, final String lambdaName, final Long parentPipelineId, final Map<String, String> inheritedContext) {
        final Process process = newInstanceProcess(relativePath, lambdaName, parentPipelineId, inheritedContext);
        return run(process);
    }

    public static Process newInstanceProcess(final String relativePath, final String lambdaName, final Long parentPipelineId, final Map<String, String> inheritedContext) {
        log.debug("Handyman Engine start for {}", lambdaName);
        final Map<String, String> context = new HashMap<>(ConfigurationService.getAllConfig(lambdaName));
        context.putAll(inheritedContext);
        final RavenParser.ProcessContext ravenParser = doRavenParser(relativePath, lambdaName, context);
        final Process process = getProcess(lambdaName, parentPipelineId, context);
        process.setRavenParser(ravenParser);
        return process;
    }

    private static RavenParser.ProcessContext doRavenParser(final String relativePath, final String lambdaName, final Map<String, String> context) {
        final String processFile = HRequestResolver.readFile(relativePath, "");
        if (processFile.isEmpty()) {
            throw new HandymanException("Content configuration for process " + lambdaName + " is missing, check spw_process_config or spw_instance_config");
        }
        return ProcessParser.doParse(processFile, context);
    }

}
