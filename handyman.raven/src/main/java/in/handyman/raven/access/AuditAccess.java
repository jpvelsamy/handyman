package in.handyman.raven.access;

import in.handyman.raven.process.doa.ActionAudit;
import in.handyman.raven.process.doa.PipelineAudit;
import in.handyman.raven.process.doa.StatementAudit;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Log4j2
public class AuditAccess {

    public static void insertPipeline(final PipelineAudit pipelineAudit) {
        var connection = DBAccess.getConnection();
        try {
            var statement = connection.createStatement(DBAccess.CONFIG.getString("config.pipeline_audit_insert_stmt"))
                    .bind(0, pipelineAudit.getPipelineId())
                    .bind(1, pipelineAudit.getCreatedBy())
                    .bind(2, pipelineAudit.getCreatedDate())
                    .bind(3, pipelineAudit.getLastModifiedBy())
                    .bind(4, pipelineAudit.getLastModifiedDate())
                    .bind(5, pipelineAudit.getContext())
                    .bind(6, pipelineAudit.getExecutionStatusId())
                    .bind(7, pipelineAudit.getLambdaName())
                    .bind(8, pipelineAudit.getParentPipelineId())
                    .bind(9, pipelineAudit.getParentPipelineName())
                    .bind(10, pipelineAudit.getPipelineName())
                    .bind(11, pipelineAudit.getFileContent())
                    .bind(12, pipelineAudit.getHostName())
                    .bind(13, pipelineAudit.getModeOfExecution())
                    .bind(14, pipelineAudit.getPipelineLoadType())
                    .bind(15, pipelineAudit.getRelativePath())
                    .bind(16, pipelineAudit.getRequestBody())
                    .bind(17, pipelineAudit.getThreadName())
                    .bind(18, pipelineAudit.getParentActionId())
                    .bind(19, pipelineAudit.getParentActionName());
            Mono.from(statement.execute()).subscribe();
        } finally {
            connection.close();
        }
    }

    public static void insertAction(final ActionAudit actionAudit) {
        var connection = DBAccess.getConnection();
        try {
            var statement = connection.createStatement(DBAccess.CONFIG.getString("config.action_audit_insert_stmt"))
                    .bind(0, actionAudit.getPipelineId())
                    .bind(1, actionAudit.getCreatedBy())
                    .bind(2, actionAudit.getCreatedDate())
                    .bind(3, actionAudit.getLastModifiedBy())
                    .bind(4, actionAudit.getLastModifiedDate())
                    .bind(5, actionAudit.getContext())
                    .bind(6, actionAudit.getExecutionStatusId())
                    .bind(7, actionAudit.getLambdaName())
                    .bind(8, actionAudit.getParentPipelineId())
                    .bind(9, actionAudit.getParentPipelineName())
                    .bind(10, actionAudit.getParentActionId())
                    .bind(11, actionAudit.getParentActionName())
                    .bind(12, actionAudit.getPipelineName())
                    .bind(13, actionAudit.getActionName())
                    .bind(14, actionAudit.getInput())
                    .bind(15, actionAudit.getPipelineId())
                    .bind(16, actionAudit.getLog());
            Mono.from(statement.execute()).subscribe();
        } finally {
            connection.close();
        }
    }

    public static void insertStatement(final StatementAudit statementAudit) {
        var connection = DBAccess.getConnection();
        try {
            var statement = connection.createStatement(DBAccess.CONFIG.getString("config.statement_audit_insert_stmt"))
                    .bind(0, statementAudit.getStatementId())
                    .bind(1, statementAudit.getActionId())
                    .bind(2, statementAudit.getCreatedBy())
                    .bind(3, statementAudit.getCreatedDate())
                    .bind(4, statementAudit.getLastModifiedBy())
                    .bind(5, statementAudit.getLastModifiedDate())
                    .bind(6, statementAudit.getRowsProcessed())
                    .bind(7, statementAudit.getRowsRead())
                    .bind(8, statementAudit.getRowsWritten())
                    .bind(9, statementAudit.getTimeTaken())
                    .bind(10, statementAudit.getStatement());
            Mono.from(statement.execute()).subscribe();
        } finally {
            connection.close();
        }
    }

}
