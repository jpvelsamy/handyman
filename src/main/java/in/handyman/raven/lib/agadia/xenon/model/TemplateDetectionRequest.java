package in.handyman.raven.lib.agadia.xenon.model;

import okhttp3.RequestBody;

import java.util.List;

public class TemplateDetectionRequest {

    private String  inputFilePath;
    private Long rootpipelineId;
    private Long actionId;

    public Long getRootpipelineId() {
        return rootpipelineId;
    }

    public void setRootpipelineId(Long rootpipelineId) {
        this.rootpipelineId = rootpipelineId;
    }

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    public String getProcessname() {
        return processname;
    }

    public void setProcessname(String processname) {
        this.processname = processname;
    }

    private String processname;
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
