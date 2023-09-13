package in.handyman.raven.lib.model.autoRotation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AutoRotationModelResponse {
    private String model_name;
    private String model_version;
    private List<AutoRotationOutput> outputs;
}
