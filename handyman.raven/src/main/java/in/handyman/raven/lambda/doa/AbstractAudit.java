package in.handyman.raven.lambda.doa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AbstractAudit extends Auditable {

    protected static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.registerModule(new JavaTimeModule());
    }

    private String lambdaName;
    private String pipelineName;

    @Builder.Default
    private Long parentPipelineId = -1L;
    @Builder.Default
    private String parentPipelineName = "";
    @Builder.Default
    private Long parentActionId = -1L;
    @Builder.Default
    private String parentActionName = "";

    private Integer executionStatusId;
    private Map<String, String> context;


}
