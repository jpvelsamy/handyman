package in.handyman.raven.lib.model.templateDetection;

import in.handyman.raven.lib.model.NerAdaptors.NerAdapterOutput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TemplateDetectionResponse {

        private String model_name;
        private String model_version;
        private List<TemplateDetectionOutput> outputs;
    }


