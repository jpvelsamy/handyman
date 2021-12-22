package in.handyman.raven.lambda.doa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import in.handyman.raven.actor.HandymanActorSystemAccess;
import in.handyman.raven.util.UniqueID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Slf4j
public class Pipeline extends AbstractAudit implements IPipeline {

    @Builder.Default
    private Long pipelineId = UniqueID.getId();

    private String modeOfExecution;
    private String hostName;
    private String threadName;
    private String pipelineLoadType;
    // Input audit
    @Builder.Default
    private String fileContent = "";
    @Builder.Default
    private String requestBody = "";
    @Builder.Default
    private String relativePath = "";


    public void updateExecutionStatusId(final Integer executionStatusId) {
        this.setExecutionStatusId(executionStatusId);
        HandymanActorSystemAccess.insert(LambdaExecutionAudit.builder().pipelineId(this.pipelineId).executionStatusId(executionStatusId).rootPipelineId(this.getRootPipelineId()).build());
        log.info("Pipeline audit has been inserted with PipelineId {} as {}", this.getPipelineName(), ExecutionStatus.get(executionStatusId));
    }

    @Override
    public String getCurrentTime() {
        return IPipeline.super.getCurrentTime();
    }

    @Override
    public String getCurrentDate() {
        return IPipeline.super.getCurrentDate();
    }
}
