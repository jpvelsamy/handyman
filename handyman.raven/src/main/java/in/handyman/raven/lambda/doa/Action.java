package in.handyman.raven.lambda.doa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import in.handyman.raven.lambda.access.AuditAccess;
import in.handyman.raven.util.UniqueID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.event.SubstituteLoggingEvent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayDeque;
import java.util.Optional;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "action")
public class Action extends AbstractAudit {

    @JsonIgnore
    @Transient
    private final ArrayDeque<SubstituteLoggingEvent> eventQueue = new ArrayDeque<>();
    @Builder.Default
    @Id
    @Column(name = "action_id")
    private Long actionId = UniqueID.getId();
    @Column(name = "action_name")
    private String actionName;
    @Column(name = "pipeline_id")
    private Long pipelineId;
    @Transient
    private JsonNode input;
    private String log;
    @Column(name = "execution_group_id")
    private Integer executionGroupId;

    @JsonIgnore
    @Column(name = "input_node")
    private String inputNode;

    public void setInput(final JsonNode input) {
        this.input = input;
        this.inputNode = Optional.ofNullable(input).map(JsonNode::toString).orElse(null);
    }

    public void updateExecutionStatusId(final Integer executionStatusId) {
        this.setExecutionStatusId(executionStatusId);
        AuditAccess.insert(ActionExecutionAudit.builder().actionId(actionId)
                .pipelineId(this.pipelineId).executionStatusId(executionStatusId).build());
    }

    public ArrayDeque<SubstituteLoggingEvent> getEventQueue() {
        return eventQueue;
    }

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(final Long actionId) {
        this.actionId = actionId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(final String actionName) {
        this.actionName = actionName;
    }

    public Long getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(final Long pipelineId) {
        this.pipelineId = pipelineId;
    }

    public JsonNode getInput() {
        return input;
    }

    public String getLog() {
        return log;
    }

    public void setLog(final String log) {
        this.log = log;
    }

    public Integer getExecutionGroupId() {
        return executionGroupId;
    }

    public void setExecutionGroupId(final Integer executionGroupId) {
        this.executionGroupId = executionGroupId;
    }

    public String getInputNode() {
        return inputNode;
    }

    public void setInputNode(final String inputNode) {
        this.inputNode = inputNode;
    }
}
