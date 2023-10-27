package in.handyman.raven.lib.model.utmodel;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("paper_type")
    private String paperType;
    @JsonProperty("confidence_score")
    private Long confidenceScore;
    private UrgencyTriageModelBoundingBox bboxes;

}

