package in.handyman.chidori.controller;

import in.handyman.raven.lambda.access.repo.HandymanRepo;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lambda.doa.audit.ExecutionStatus;
import in.handyman.raven.lambda.doa.audit.PipelineExecutionAudit;
import in.handyman.raven.lambda.doa.config.SpwInstanceConfig;
import in.handyman.raven.lambda.process.LContext;
import in.handyman.raven.lambda.process.LambdaEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pipeline")
@RequiredArgsConstructor
public class PipelineController {

    private final HandymanRepo handymanRepo;

    @PostMapping
    public PipelineResponse runPipeline(@RequestBody final PipelineRequest pipelineRequest) {
        final PipelineExecutionAudit pipelineExecutionAudit = LambdaEngine.start(LContext.builder()
                .pipelineName(pipelineRequest.getPipelineName())
                .processLoadType(pipelineRequest.getProcessLoadType())
                .inheritedContext(pipelineRequest.getContext())
                .build());
        return PipelineResponse.builder()
                .rootPipelineId(pipelineExecutionAudit.getRootPipelineId())
                .executionStatus(ExecutionStatus.get(pipelineExecutionAudit.getExecutionStatusId()))
                .build();
    }

    @GetMapping("/{pipelineName}/config")
    public List<SpwInstanceConfig> getConfig(@PathVariable final String pipelineName) {
        return handymanRepo.findAllByInstance(pipelineName);
    }

    @GetMapping("/{rootPipelineId}")
    public List<PipelineExecutionAudit> getPipelineExecutionAudits(@PathVariable final Long rootPipelineId) {
        return handymanRepo.findAllPipelinesByRootPipelineId(rootPipelineId);
    }

    @GetMapping("/{rootPipelineId}/actions")
    public List<ActionExecutionAudit> getActionExecutionAudits(@PathVariable final Long rootPipelineId) {
        return handymanRepo.findAllActionsByRootPipelineId(rootPipelineId);
    }


}
