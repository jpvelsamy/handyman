package in.handyman.raven.lambda.process;

import in.handyman.raven.lambda.access.ConfigAccess;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LContext {

    private Long rootPipelineId;
    //Load Enum
    private String processLoadType;
    // Config name
    private String pipelineName;
    // Auto set
    private String lambdaName;
    // Start lambda Engine by path file
    private String relativePath;
    @Builder.Default
    private Map<String, String> inheritedContext = new HashMap<>();

    private Long parentPipelineId;
    private String parentPipelineName;

    private Long parentActionId;
    private String parentActionName;

    private String payload;

    public String getLambdaName() {
        return this.lambdaName != null ? this.lambdaName : ConfigAccess.getLambdaName(this.pipelineName);
    }


}
