package in.handyman.raven.lib.model.templateDetection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemplateDetectionRequest {

    private List<Input> inputs;

    public void setInputs(List<Input> inputs) {
        this.inputs = inputs;

    }

    public void setRootPipelineId(Long aLong) {
    }

    public void setActionId(Long actionId) {
    }

    public void setProcess(String process) {
    }

    public void setInputFilePath(String filePath) {
    }

    public void getAttributes(String attributes) {
    }



    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Input {
        private String name;
        private List<Integer> shape;
        private String datatype;
        private List<Data> data;
        private TemplateDetectionData TemplateDetectiondata = new TemplateDetectionData();}
}

