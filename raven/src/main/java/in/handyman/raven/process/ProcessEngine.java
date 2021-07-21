package in.handyman.raven.process;

import in.handyman.raven.actor.HandymanActorSystemAccess;
import in.handyman.raven.audit.AuditPayload;
import in.handyman.raven.compiler.RavenParser;
import in.handyman.raven.config.ConfigurationService;
import in.handyman.raven.context.ActionContext;
import in.handyman.raven.context.ProcessContext;
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

    public static ProcessContext start(final String processLoadType, final String instanceName) {
        log.debug("Handyman Engine start for {}", instanceName);
        final Map<String, String> allConfig = new HashMap<>(ConfigurationService.getAllConfig(instanceName));
        final ProcessContext processContext = ProcessContext.builder()
                .processId(UniqueID.getId())
                .context(allConfig)
                .instanceName(instanceName)
                .build();
        allConfig.put("process-id", String.valueOf(processContext.getProcessId()));
        final String processFile = HRequestResolver.doResolve(processLoadType, processContext);
        if (Objects.isNull(processFile)) {
            throw new HandymanException("Content configuration for process " + instanceName + " is missing, check spw_process_config or spw_instance_config");
        }

        if (processFile.isEmpty()) {
            throw new HandymanException("Content configuration for process " + instanceName + " is missing, check spw_process_config or spw_instance_config");
        }
        final RavenParser.ProcessContext process = ProcessParser.doParse(processFile, processContext);
        return run(processContext, process);
    }

    public static ProcessContext start(final String relativePath, final String instanceName, final Long parentProcessId, final Map<String, String> config) {
        log.debug("Handyman Engine start for {}", instanceName);
        final Map<String, String> allConfig = new HashMap<>(ConfigurationService.getAllConfig(instanceName));
        allConfig.putAll(config);
        final ProcessContext processContext = ProcessContext.builder()
                .processId(UniqueID.getId())
                .parentProcessId(parentProcessId)
                .context(allConfig)
                .instanceName(instanceName)
                .build();
        final String processFile = HRequestResolver.readFile(relativePath, "");
        if (processFile.isEmpty()) {
            throw new HandymanException("Content configuration for process " + instanceName + " is missing, check spw_process_config or spw_instance_config");
        }
        final RavenParser.ProcessContext process = ProcessParser.doParse(processFile, processContext);
        return run(processContext, process);
    }

    private static ProcessContext run(final ProcessContext processContext, final RavenParser.ProcessContext process) {
        final String machine;
        try {
            machine = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new HandymanException("machine not found ", e);
        }

        final AuditPayload auditPayload = AuditPayload.builder()
                .auditType(AuditPayload.AuditType.CREATE_INSTANCE_AUDIT)
                .processId(processContext.getProcessId())
                .instanceName(processContext.getInstanceName())
                .runMode("in.handyman.process.onethread")
                .machine(machine)
                .build();
        HandymanActorSystemAccess.doAudit(auditPayload);
        try {
            tryActionsExecution(processContext, process);
        } catch (HandymanException e) {
            log.error(e);
            processContext.setStatus(ProcessStatus.FAILURE);
            catchActionsExecution(processContext, process);
        } finally {
            finallyActionsExecution(processContext, process);
            auditPayload.setAuditType(AuditPayload.AuditType.UPDATE_INSTANCE_AUDIT);
            auditPayload.setStatus(processContext.getStatus());
            HandymanActorSystemAccess.doAudit(auditPayload);
        }
        MetricUtil.addAfter(processContext);
        return processContext;
    }

    private static void finallyActionsExecution(final ProcessContext processContext, final RavenParser.ProcessContext process) {
        final Set<ActionContext> actionContexts = new HashSet<>();
        try {
            final List<RavenParser.ActionContext> actions = process.finallyBlock.actions;
            final Set<ActionContext> finallyExecution = actions.stream().map(action -> ProcessExecutor.doExecute(processContext, action)).collect(Collectors.toSet());
            actionContexts.addAll(finallyExecution);
        } catch (Exception e) {
            log.error(e);
            processContext.setStatus(ProcessStatus.FAILURE);
        } finally {
            if (actionContexts.stream().allMatch(actionContext -> ProcessStatus.SUCCESS.equals(actionContext.getStatus()))) {
                processContext.setFinallyStatus(ProcessStatus.SUCCESS);
            } else {
                processContext.setFinallyStatus(ProcessStatus.FAILURE);
            }
            processContext.setFinallyActions(actionContexts);
        }
    }

    private static void catchActionsExecution(final ProcessContext processContext, final RavenParser.ProcessContext process) {
        final Set<ActionContext> actionContexts = new HashSet<>();
        try {
            final List<RavenParser.ActionContext> actions = process.catchBlock.actions;
            final Set<ActionContext> catchExecution = actions.stream().map(action -> ProcessExecutor.doExecute(processContext, action)).collect(Collectors.toSet());
            actionContexts.addAll(catchExecution);
        } catch (Exception e) {
            log.error(e);
            processContext.setStatus(ProcessStatus.FAILURE);
        } finally {
            if (actionContexts.stream().allMatch(actionContext -> ProcessStatus.SUCCESS.equals(actionContext.getStatus()))) {
                processContext.setCatchStatus(ProcessStatus.SUCCESS);
            } else {
                processContext.setCatchStatus(ProcessStatus.FAILURE);
            }
            processContext.setCatchActions(actionContexts);
        }
    }

    private static void tryActionsExecution(final ProcessContext processContext, final RavenParser.ProcessContext process) {
        final Set<ActionContext> actionContexts = new HashSet<>();
        try {
            final List<RavenParser.ActionContext> actions = process.tryBlock.actions;
            final Set<ActionContext> tryExecution = actions.stream().map(action -> ProcessExecutor.doExecute(processContext, action)).collect(Collectors.toSet());
            actionContexts.addAll(tryExecution);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
            processContext.setStatus(ProcessStatus.FAILURE);
        } finally {
            if (actionContexts.stream().allMatch(actionContext -> ProcessStatus.SUCCESS.equals(actionContext.getStatus()))) {
                processContext.setTryStatus(ProcessStatus.SUCCESS);
            } else {
                processContext.setTryStatus(ProcessStatus.FAILURE);
            }
            processContext.setTryActions(actionContexts);
        }
    }

}
