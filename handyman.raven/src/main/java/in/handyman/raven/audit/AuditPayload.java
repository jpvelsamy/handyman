package in.handyman.raven.audit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuditPayload {

    private AuditType auditType;
    private String runMode;
    private String machine;
    private String file;
    private String contextLog;
    private String commandDetail;
    private String statement;
    private Long actionId;
    private Long processId;
    private Long statementId;
    private String actionName;
    private String instanceName;
    private String processName;
    private Integer rowsW;
    private Integer rowsR;
    private Integer rowsProcessed;
    private Integer timeTaken;
    private Integer status;

    public enum AuditType {
        CREATE_INSTANCE_AUDIT, UPDATE_INSTANCE_AUDIT,
        CREATE_COMMAND_AUDIT, UPDATE_COMMAND_AUDIT,
        CREATE_STATEMENT_AUDIT, UPDATE_STATEMENT_AUDIT,
        CREATE_BATCH_AUDIT
    }

}
