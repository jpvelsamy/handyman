package in.handyman.raven.lib.model.hwDectection;

import in.handyman.raven.lib.CoproProcessor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HwClassificationOutputTable implements CoproProcessor.Entity{

    private String createdUserId;
    private String lastUpdatedUserId;
    private String tenantId;
    private String originId;
    private Integer groupId;
    private Integer paperNo;
    private String templateId;
    private Integer modelRegistryId;
    private String documentType;
    private Long confidenceScore;
    private String status;
    private String stage;
    private String message;
    private Long rootPipelineId;

    @Override
    public List<Object> getRowData() {
        return Stream.of(this.createdUserId,this.lastUpdatedUserId, this.tenantId,
                this.originId, this.paperNo, this.templateId, this.modelRegistryId,
                this.documentType, this.status, this.stage, this.message, this.groupId,this.rootPipelineId, this.confidenceScore).collect(Collectors.toList());

    }
}

