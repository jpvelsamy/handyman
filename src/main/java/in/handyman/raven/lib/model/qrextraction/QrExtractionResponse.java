package in.handyman.raven.lib.model.qrextraction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QrExtractionResponse {
        @JsonProperty("model_name")
        private String modelName;

        @JsonProperty("model_version")
        private String modelVersion;
        private List<QrExtractionOutput> outputs;
}
