package in.handyman.raven.lambda.doa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import in.handyman.raven.lambda.access.AuditAccess;
import in.handyman.raven.util.UniqueID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pipeline extends AbstractAudit {

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
        AuditAccess.insert(LambdaExecutionAudit.builder().pipelineId(this.pipelineId).executionStatusId(executionStatusId).build());
    }


}
