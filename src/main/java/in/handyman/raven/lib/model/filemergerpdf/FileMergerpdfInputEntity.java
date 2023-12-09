package in.handyman.raven.lib.model.filemergerpdf;


import in.handyman.raven.lib.CoproProcessor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileMergerpdfInputEntity implements CoproProcessor.Entity {

    private String originId;
    private List<String> filePaths;
    private Long rootPipelineId;
    private Long tenantId;
    private Long processId;
    private Long groupId;
    private String fileId;
    private String outputFileName;


    @Override
    public List<Object> getRowData() {
        return null;
    }
}
