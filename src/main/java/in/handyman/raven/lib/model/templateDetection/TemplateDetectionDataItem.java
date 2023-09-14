package in.handyman.raven.lib.model.templateDetection;

import in.handyman.raven.lib.agadia.xenon.model.XenonResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemplateDetectionDataItem {
    private List<XenonResult> attributes;
    private Integer imageDPI;
    private Integer imageWidth;
    private String extractedImageUnit;
    private List<TemplateDetectionAttributes> attribute;


    public static Integer ImageWidth() {
        return ImageWidth();
    }


    public static Integer ImageDPI() {
        return ImageDPI();
    }

    public static String ExtractedImageUnit() {
        return ExtractedImageUnit();
    }
}