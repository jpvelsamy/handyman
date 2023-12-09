package in.handyman.raven.lib.model.filemergerpdf;

import in.handyman.raven.lib.CoproProcessor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileMergerpdfOutputEntity implements CoproProcessor.Entity {
    private String originId;
    private Long tenantId;
    private Long processId;
    private Long groupId;
    private String status;
    private String stage;
    private String message;
    private String processedFilePath;
    private String fileName;
    private Timestamp createdOn;
    private Timestamp lastUpdatedOn;
    private Long rootPipelineId;
    private String modelName;
    private String modelVersion;


    @Override
    public List<Object> getRowData() {
        return Stream.of(this.originId, this.tenantId,this.groupId,this.processedFilePath,this.createdOn,this.lastUpdatedOn,this.rootPipelineId,this.status, this.stage, this.message,this.modelName,this.modelVersion,this.fileName,this.processId).collect(Collectors.toList());
    }

}





