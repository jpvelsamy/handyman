package in.handyman.raven.lambda.doa.audit;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import in.handyman.raven.lambda.doa.Auditable;
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
public class PipelineExecutionStatusAudit extends Auditable {

    public static final String SCHEMA_NAME = "handyman_audit";
    public static final String TABLE_NAME = "pipeline_execution_status_audit";

    @Builder.Default
    private Long id = UniqueID.getId();
    private Long pipelineId;
    private Integer executionStatusId;
    private Long rootPipelineId;

}
