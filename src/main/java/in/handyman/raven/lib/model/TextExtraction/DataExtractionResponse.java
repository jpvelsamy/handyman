package in.handyman.raven.lib.model.TextExtraction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataExtractionResponse {
        private String model_name;
        private String model_version;
        private List<DataExtractionOutput> outputs;
    }

