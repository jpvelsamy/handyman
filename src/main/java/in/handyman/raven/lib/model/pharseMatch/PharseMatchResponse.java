package in.handyman.raven.lib.model.pharseMatch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PharseMatchResponse {
        private String model_name;
        private String model_version;
        private List<PharseMatchOutput> outputs;
}
