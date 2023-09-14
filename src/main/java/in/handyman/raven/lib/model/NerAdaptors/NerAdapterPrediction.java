package in.handyman.raven.lib.model.NerAdaptors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NerAdapterPrediction {

    private String inputName;
    private int predictionScore;
    private String predictionTag;
    private String predictedLabel;

}
