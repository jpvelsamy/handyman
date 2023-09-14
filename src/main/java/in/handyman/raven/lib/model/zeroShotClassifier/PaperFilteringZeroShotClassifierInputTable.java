package in.handyman.raven.lib.model.zeroShotClassifier;

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
    public class PaperFilteringZeroShotClassifierInputTable implements CoproProcessor.Entity {

        public String pipelineId;
        private String originId;
        private Integer paperNo;
        private String groupId;
        private String pageContent;
        private String truthPlaceholder;
        private String processId;
        private Long rootPipelineId;

        @Override
        public List<Object> getRowData() {
            return null;
        }
    }

