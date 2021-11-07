package in.handyman.raven.lambda.doa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import in.handyman.raven.lambda.access.AuditAccess;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "pipeline")
public class Pipeline extends AbstractAudit {

    @Builder.Default
    @Id
    @Column(name = "pipeline_id")
    private Long pipelineId = System.nanoTime();

    @Column(name = "mode_of_execution")
    private String modeOfExecution;
    @Column(name = "host_name")
    private String hostName;
    @Column(name = "thread_name")
    private String threadName;
    @Column(name = "pipeline_load_type")
    private String pipelineLoadType;
    // Input audit
    @Builder.Default
    @Column(name = "file_content")
    private String fileContent = "";
    @Builder.Default
    @Column(name = "request_body")
    private String requestBody = "";
    @Builder.Default
    @Column(name = "relative_path")
    private String relativePath = "";


    public void updateExecutionStatusId(final Integer executionStatusId) {
        this.setExecutionStatusId(executionStatusId);
        AuditAccess.insert(LambdaExecutionAudit.builder().pipelineId(this.pipelineId).executionStatusId(executionStatusId).build());
    }

    public Long getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(final Long pipelineId) {
        this.pipelineId = pipelineId;
    }

    public String getModeOfExecution() {
        return modeOfExecution;
    }

    public void setModeOfExecution(final String modeOfExecution) {
        this.modeOfExecution = modeOfExecution;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(final String hostName) {
        this.hostName = hostName;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(final String threadName) {
        this.threadName = threadName;
    }

    public String getPipelineLoadType() {
        return pipelineLoadType;
    }

    public void setPipelineLoadType(final String pipelineLoadType) {
        this.pipelineLoadType = pipelineLoadType;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(final String fileContent) {
        this.fileContent = fileContent;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(final String requestBody) {
        this.requestBody = requestBody;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(final String relativePath) {
        this.relativePath = relativePath;
    }
}
