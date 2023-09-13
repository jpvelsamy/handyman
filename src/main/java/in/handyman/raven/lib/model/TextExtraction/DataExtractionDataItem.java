package in.handyman.raven.lib.model.TextExtraction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataExtractionDataItem {

        private String filePath;
        private String fileName;
        private String PageContent;
    }


