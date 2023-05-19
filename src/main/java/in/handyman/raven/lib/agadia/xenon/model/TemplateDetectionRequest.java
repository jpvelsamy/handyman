package in.handyman.raven.lib.agadia.xenon.model;

import okhttp3.RequestBody;

import java.util.List;

public class TemplateDetectionRequest {

    private String  inputFilePath;
    private List<String> attributes;

    public String getInputFilePath() {
        return inputFilePath;
    }

    public void setInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }
}
