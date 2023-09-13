package in.handyman.raven.lib.model.NerAdaptors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class NerAdaptorPayload {

        private List<String> inputString;
        private Long rootPipelineId;
        private Long actionId;
        private String process;
}
