package in.handyman.raven.lib.model.pharsematch;

import in.handyman.raven.lib.CoproProcessor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhraseMatchOutputTable implements CoproProcessor.Entity {
    private String originId;
    private Integer paperNo;
    private String groupId;
    private String isKeyPresent;
    private String entity;
    private String truthEntity;

    private String status;

    private String stage;

    private String message;
    private Long rootPipelineId;
    private String modelName;
    private String modelVersion;
    private Long tenantId;



    @Override
    public List<Object> getRowData() {
        return Stream.of(this.originId, this.groupId, this.paperNo, this.truthEntity, this.entity,
                this.isKeyPresent, this.status, this.stage, this.message,this.rootPipelineId,this.modelName, this.modelVersion,this.tenantId).collect(Collectors.toList());
    }
}


