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

public class PharseMatchKeyFilter {


    private List<String> Drug;
    private List<String> Member;
}
