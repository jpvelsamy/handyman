package in.handyman.raven.lib.model.utModel;

import in.handyman.raven.lib.model.TextExtraction.DataExtractionOutput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UrgencyTriageModelResponse {
    private String model_name;
    private String model_version;
    private List<UrgencyTriageModelOutput> outputs;
}
