package in.handyman.raven.lib.model.fileMergerPdf;

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
    private String tenantId;
    private String templateId;
    private Integer groupId;
    private Integer paperNo;
    private String fileId;
    private String status;
    private String stage;
    private String message;
    private String processedFilePath;
    private Timestamp createdOn;
    private Long rootPipelineId;
    private String modelName;
    private String modelVersion;


    @Override
    public List<Object> getRowData() {
        return Stream.of(this.originId, this.tenantId,this.templateId,this.groupId,this.paperNo,this.fileId, this.status, this.stage, this.message,this.processedFilePath,this.createdOn,this.rootPipelineId,this.modelName,this.modelVersion).collect(Collectors.toList());
    }

}





