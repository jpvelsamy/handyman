package in.handyman.raven.lib;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AutoRotationInputTable implements CoproProcessor.Entity {
    private String originId;
    private Integer paperNo;
    private Integer groupId;
    private String filePath;
    private String tenantId;
    private String templateId;
    private Long processId;
    private String outputDir;
    private Long rootPipelineId;

    @Override
    public List<Object> getRowData() {
        return null;
    }
}
