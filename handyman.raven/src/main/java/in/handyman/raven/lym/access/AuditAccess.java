package in.handyman.raven.lym.access;

import in.handyman.raven.lym.doa.Action;
import in.handyman.raven.lym.doa.Pipeline;
import in.handyman.raven.lym.doa.Statement;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Log4j2
public class AuditAccess {

    public static void insertPipeline(final Pipeline pipeline) {
        var connection = DBAccess.getConnection();
        try {
            var statement = connection.createStatement(DBAccess.CONFIG.getString("config.pipeline_audit_insert_stmt"))
                    .bind(0, pipeline.getPipelineId())
                    .bind(1, pipeline.getCreatedBy())
                    .bind(2, pipeline.getCreatedDate())
                    .bind(3, pipeline.getLastModifiedBy())
                    .bind(4, pipeline.getLastModifiedDate())
                    .bind(5, pipeline.getContext())
                    .bind(6, pipeline.getExecutionStatusId())
                    .bind(7, pipeline.getLambdaName())
                    .bind(8, pipeline.getParentPipelineId())
                    .bind(9, pipeline.getParentPipelineName())
                    .bind(10, pipeline.getPipelineName())
                    .bind(11, pipeline.getFileContent())
                    .bind(12, pipeline.getHostName())
                    .bind(13, pipeline.getModeOfExecution())
                    .bind(14, pipeline.getPipelineLoadType())
                    .bind(15, pipeline.getRelativePath())
                    .bind(16, pipeline.getRequestBody())
                    .bind(17, pipeline.getThreadName())
                    .bind(18, pipeline.getParentActionId())
                    .bind(19, pipeline.getParentActionName());
            Mono.from(statement.execute()).subscribe();
        } finally {
            connection.close();
        }
    }

    public static void insertAction(final Action action) {
        var connection = DBAccess.getConnection();
        try {
            var statement = connection.createStatement(DBAccess.CONFIG.getString("config.action_audit_insert_stmt"))
                    .bind(0, action.getPipelineId())
                    .bind(1, action.getCreatedBy())
                    .bind(2, action.getCreatedDate())
                    .bind(3, action.getLastModifiedBy())
                    .bind(4, action.getLastModifiedDate())
                    .bind(5, action.getContext())
                    .bind(6, action.getExecutionStatusId())
                    .bind(7, action.getLambdaName())
                    .bind(8, action.getParentPipelineId())
                    .bind(9, action.getParentPipelineName())
                    .bind(10, action.getParentActionId())
                    .bind(11, action.getParentActionName())
                    .bind(12, action.getPipelineName())
                    .bind(13, action.getActionName())
                    .bind(14, action.getInput())
                    .bind(15, action.getPipelineId())
                    .bind(16, action.getLog())
                    .bind(17, action.getExecutionGroupId());
            Mono.from(statement.execute()).subscribe();
        } finally {
            connection.close();
        }
    }

    public static void insertStatement(final Statement statementAudit) {
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
