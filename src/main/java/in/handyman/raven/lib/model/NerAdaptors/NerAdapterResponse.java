package in.handyman.raven.lib.model.NerAdaptors;

import com.fasterxml.jackson.annotation.JsonProperty;
import in.handyman.raven.lib.model.hwDectection.HwDetectionOutput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NerAdapterResponse {
        @JsonProperty("model_name")
        private String modelName;
        @JsonProperty("model_version")
        private String modelVersion;
        private List<NerAdapterOutput> outputs;
    }

