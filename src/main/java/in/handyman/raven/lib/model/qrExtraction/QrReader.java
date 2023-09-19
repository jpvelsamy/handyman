package in.handyman.raven.lib.model.qrExtraction;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QrReader {
    private String value;
    private String type;
    private String decode_type;
    private Integer confidenceScore;
    private JsonNode boundingBox;
    private Integer angle;
}
