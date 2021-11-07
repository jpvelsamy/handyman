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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "action_execution_audit")
public class ActionExecutionAudit extends Auditable {

    @Builder.Default
    @Id
    private Long id = UniqueID.getId();
    @Column(name = "action_id")
    private Long actionId;
    @Column(name = "pipeline_id")
    private Long pipelineId;
    @Column(name = "execution_status_id")
    private Integer executionStatusId;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(final Long actionId) {
        this.actionId = actionId;
    }

    public Long getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(final Long pipelineId) {
        this.pipelineId = pipelineId;
    }

    public Integer getExecutionStatusId() {
        return executionStatusId;
    }

    public void setExecutionStatusId(final Integer executionStatusId) {
        this.executionStatusId = executionStatusId;
    }
}
