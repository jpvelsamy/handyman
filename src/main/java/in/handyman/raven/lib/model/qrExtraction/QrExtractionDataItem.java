package in.handyman.raven.lib.model.qrExtraction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QrExtractionDataItem {

        private String value;
        private String type;
        private String confidenceScore;
        private QrExtractionBoundingBox boundingBox;
        private int angle;
        private String decode_type;

}

