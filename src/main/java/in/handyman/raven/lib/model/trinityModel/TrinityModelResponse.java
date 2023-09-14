package in.handyman.raven.lib.model.trinityModel;

import in.handyman.raven.lib.model.autoRotation.AutoRotationOutput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrinityModelResponse {

        private String model_name;
        private String model_version;
        private List<TrinityModelOutput> outputs;


}
