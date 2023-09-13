package in.handyman.raven.lib.model.paperItemizer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaperItemizerResponse {
    private String model_name;
    private String model_version;
    private List<PaperItemizerOutput> outputs;
}
