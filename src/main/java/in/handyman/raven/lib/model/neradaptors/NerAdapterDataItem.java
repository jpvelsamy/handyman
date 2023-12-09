package in.handyman.raven.lib.model.neradaptors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NerAdapterDataItem {
    private List<NerAdapterPrediction> prediction;

}

