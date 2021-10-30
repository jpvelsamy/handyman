package in.handyman.raven.lym.doa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import in.handyman.raven.util.UniqueID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.event.SubstituteLoggingEvent;

import java.util.ArrayDeque;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Action extends AbstractAudit {

    @Builder.Default
    private Long actionId = UniqueID.getId();

    private String actionName;

    private Long pipelineId;

    private JsonNode input;

    private String log;

    private Integer executionGroupId;

    @JsonIgnore
    private final ArrayDeque<SubstituteLoggingEvent> eventQueue = new ArrayDeque<>();


}
