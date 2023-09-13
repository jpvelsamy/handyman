package in.handyman.raven.lib.model.zeroShotClassifier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ZeroShotClassifierDataItem {
    private Long rootPipelineId;
    private String process;
    private String inputFilePath;
    private Long actionId;
    private String page_content;
    private List <String>Entity_confidence_score;
    private List<ZeroShotClassifierDataEntityConfidenceScore>entityConfidenceScores;


}
