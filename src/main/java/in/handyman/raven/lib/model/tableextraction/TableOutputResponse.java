package in.handyman.raven.lib.model.tableextraction;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableOutputResponse {
    private String csvTablesPath;
    private String croppedImage;
    private JsonNode bboxes;
    private JsonNode tableResponse;

}
