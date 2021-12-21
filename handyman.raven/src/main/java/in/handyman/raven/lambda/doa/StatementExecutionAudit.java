package in.handyman.raven.lambda.doa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class StatementExecutionAudit extends Auditable {

    @Builder.Default
    private Long statementId = UniqueID.getId();

    private Long actionId;

    private String statementContent;
    private Long rootPipelineId;

    private Integer rowsWritten;
    private Integer rowsRead;
    private Integer rowsProcessed;
    private Double timeTaken;

}
