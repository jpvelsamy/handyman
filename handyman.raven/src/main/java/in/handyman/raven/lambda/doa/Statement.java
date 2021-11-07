package in.handyman.raven.lambda.doa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import in.handyman.raven.util.UniqueID;
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
@Table(name="statement")
public class Statement extends Auditable {

    @Id
    @Builder.Default
    @Column(name = "statement_id")
    private Long statementId = UniqueID.getId();

    @Column(name = "action_id")
    private Long actionId;

    @Column(name = "statement_content")
    private String statementContent;

    @Column(name = "rows_written")
    private Integer rowsWritten;
    @Column(name = "rows_read")
    private Integer rowsRead;
    @Column(name = "rows_processed")
    private Integer rowsProcessed;
    @Column(name = "time_taken")
    private Double timeTaken;

    public Long getStatementId() {
        return statementId;
    }

    public void setStatementId(final Long statementId) {
        this.statementId = statementId;
    }

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(final Long actionId) {
        this.actionId = actionId;
    }

    public String getStatementContent() {
        return statementContent;
    }

    public void setStatementContent(final String statementContent) {
        this.statementContent = statementContent;
    }

    public Integer getRowsWritten() {
        return rowsWritten;
    }

    public void setRowsWritten(final Integer rowsWritten) {
        this.rowsWritten = rowsWritten;
    }

    public Integer getRowsRead() {
        return rowsRead;
    }

    public void setRowsRead(final Integer rowsRead) {
        this.rowsRead = rowsRead;
    }

    public Integer getRowsProcessed() {
        return rowsProcessed;
    }

    public void setRowsProcessed(final Integer rowsProcessed) {
        this.rowsProcessed = rowsProcessed;
    }

    public Double getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(final Double timeTaken) {
        this.timeTaken = timeTaken;
    }
}
