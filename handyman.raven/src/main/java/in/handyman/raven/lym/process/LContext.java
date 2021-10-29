package in.handyman.raven.lym.process;

import in.handyman.raven.lym.access.ConfigAccess;
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

    private String processLoadType;
    private String pipelineName;
    private String lambdaName;
    private String relativePath;
    private Long parentPipelineId;
    @Builder.Default
    private Map<String, String> inheritedContext = new HashMap<>();

    public String getLambdaName() {
        return this.lambdaName != null ? this.lambdaName : ConfigAccess.getLambdaName(this.pipelineName);
    }
}
