package in.handyman.raven.lib.model.outbound;

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

public class AlchemyKvpInputEntity implements CoproProcessor.Entity {
    private Integer processId;
    private Long groupId;
    private Long tenant_id;
    private Long rootPipelineId;
    private String alchemyOriginId;
    private String pipelineOriginId;
    private String fileName;

    @Override
    public List<Object> getRowData() {
        return null;
    }
}
