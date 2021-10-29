package in.handyman.raven.lym.doa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import in.handyman.raven.util.UniqueID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pipeline extends AbstractAudit {


    @Builder.Default
    private Long pipelineId = UniqueID.getId();

    private String modeOfExecution;
    private String hostName;
    private String threadName;
    private String pipelineLoadType;
    private String fileContent;
    private String requestBody;
    private String relativePath;

}