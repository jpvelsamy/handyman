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

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayDeque;
import java.util.Optional;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Action extends AbstractAudit {

    @JsonIgnore
    @Transient
    private final ArrayDeque<SubstituteLoggingEvent> eventQueue = new ArrayDeque<>();
    @Builder.Default
    @Id
    private Long actionId = UniqueID.getId();
    private String actionName;
    private Long pipelineId;
    @Transient
    private JsonNode input;
    private String log;
    private Integer executionGroupId;

    @JsonIgnore
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
}
