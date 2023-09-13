package in.handyman.raven.lib.model.qrExtraction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QrExtractionBoundingBox {
    private int topLeftX;
    private int topLeftY;
    private int bottomRightX;
    private int bottomRightY;
}
