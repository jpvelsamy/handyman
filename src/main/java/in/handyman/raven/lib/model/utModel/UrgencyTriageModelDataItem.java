package in.handyman.raven.lib.model.utModel;

import in.handyman.raven.lib.model.qrExtraction.QrExtractionBoundingBox;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UrgencyTriageModelDataItem {
    private String model;
    private String paper_type;
    private String confidence_score;
    private UrgencyTriageModelBoundingBox bboxes;

}

