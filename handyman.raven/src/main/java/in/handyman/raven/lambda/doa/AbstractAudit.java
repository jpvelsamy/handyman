package in.handyman.raven.lambda.doa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import in.handyman.raven.exception.HandymanException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.Map;
import java.util.Optional;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@MappedSuperclass
public abstract class AbstractAudit extends Auditable {

    protected static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.registerModule(new JavaTimeModule());
    }

    @Column(name = "lambda_name")
    private String lambdaName;
    @Column(name = "pipeline_name")
    private String pipelineName;

    @Column(name = "parent_pipeline_id")
    private Long parentPipelineId = -1L;
    @Column(name = "parent_pipeline_name")
    private String parentPipelineName = "";
    @Column(name = "parent_action_id")
    private Long parentActionId = -1L;
    @Column(name = "parent_action_name")
    private String parentActionName = "";

    @Column(name = "execution_status_id")
    private Integer executionStatusId;

    @Transient
    private Map<String, String> context;


    @JsonIgnore
    @Column(name = "context_node")
    private String contextNode;


    public void setContext(final Map<String, String> context) {
        this.context = context;
        this.contextNode = Optional.ofNullable(context).map(x -> {
            try {
                return MAPPER.writeValueAsString(context);
            } catch (JsonProcessingException e) {
                throw new HandymanException("Failed to convert", e);
            }
        }).orElse(null);
    }

    public String getLambdaName() {
        return lambdaName;
    }

    public void setLambdaName(final String lambdaName) {
        this.lambdaName = lambdaName;
    }

    public String getPipelineName() {
        return pipelineName;
    }

    public void setPipelineName(final String pipelineName) {
        this.pipelineName = pipelineName;
    }

    public Long getParentPipelineId() {
        return parentPipelineId;
    }

    public void setParentPipelineId(final Long parentPipelineId) {
        this.parentPipelineId = parentPipelineId;
    }

    public String getParentPipelineName() {
        return parentPipelineName;
    }

    public void setParentPipelineName(final String parentPipelineName) {
        this.parentPipelineName = parentPipelineName;
    }

    public Long getParentActionId() {
        return parentActionId;
    }

    public void setParentActionId(final Long parentActionId) {
        this.parentActionId = parentActionId;
    }

    public String getParentActionName() {
        return parentActionName;
    }

    public void setParentActionName(final String parentActionName) {
        this.parentActionName = parentActionName;
    }

    public Integer getExecutionStatusId() {
        return executionStatusId;
    }

    public void setExecutionStatusId(final Integer executionStatusId) {
        this.executionStatusId = executionStatusId;
    }

    public Map<String, String> getContext() {
        return context;
    }

    public String getContextNode() {
        return contextNode;
    }

    public void setContextNode(final String contextNode) {
        this.contextNode = contextNode;
    }
}
