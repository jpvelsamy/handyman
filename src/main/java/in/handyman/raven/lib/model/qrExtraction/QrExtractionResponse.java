package in.handyman.raven.lib.model.qrExtraction;

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

        private String model_name;
        private String model_version;
        private List<QrExtractionOutput> outputs;
}
