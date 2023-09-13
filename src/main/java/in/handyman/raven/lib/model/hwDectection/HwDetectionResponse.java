package in.handyman.raven.lib.model.hwDectection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HwDetectionResponse {
    private String model_name;
    private String model_version;
    private List<HwDetectionOutput> outputs;
}
