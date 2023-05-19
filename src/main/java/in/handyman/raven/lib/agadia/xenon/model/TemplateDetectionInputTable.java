package in.handyman.raven.lib.agadia.xenon.model;

import in.handyman.raven.lib.CoproProcessor;
import lombok.Builder;

import java.util.List;

@Builder
public class TemplateDetectionInputTable implements CoproProcessor.Entity {

    private String originId;
    private Integer paperNo;
    private Integer groupId;
    private String filePath;
    private String tenantId;
    private String templateId;
    private Long processId;
    private Long rootPipelineId;
    private String questions;

    public TemplateDetectionInputTable() {
    }

    public TemplateDetectionInputTable(String originId, Integer paperNo, Integer groupId, String filePath, String tenantId, String templateId, Long processId, String outputDir, Long rootPipelineId, String questions) {
        this.originId = originId;
        this.paperNo = paperNo;
        this.groupId = groupId;
        this.filePath = filePath;
        this.tenantId = tenantId;
        this.templateId = templateId;
        this.processId = processId;
        this.rootPipelineId = rootPipelineId;
        this.questions = questions;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public Integer getPaperNo() {
        return paperNo;
    }

    public void setPaperNo(Integer paperNo) {
        this.paperNo = paperNo;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public Long getRootPipelineId() {
        return rootPipelineId;
    }

    public void setRootPipelineId(Long rootPipelineId) {
        this.rootPipelineId = rootPipelineId;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    @Override
    public List<Object> getRowData() {
        return null;
    }
}
