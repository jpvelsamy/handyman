package in.handyman.raven.lambda.access;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.doa.Action;
import in.handyman.raven.lambda.doa.Pipeline;
import in.handyman.raven.lambda.doa.Statement;
import io.r2dbc.spi.Connection;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

@Log4j2
public class AuditAccess {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @SneakyThrows
    public static void insertPipeline(final Pipeline audit) {
        final Connection conn = DBAccess.getConnection();
        final String value;
        try {
            value = MAPPER.writeValueAsString(wrap(audit.getContext()));
        } catch (JsonProcessingException e) {
            throw new HandymanException("Persist failed", e);
        }

        Mono.just(conn).map(connection -> connection.createStatement(DBAccess.CONFIG.getString("config.pipeline_audit_insert_stmt"))
                .bind(0, wrap(audit.getPipelineId()))
                .bind(1, audit.getCreatedBy())
                .bind(2, audit.getCreatedDate())
                .bind(3, audit.getLastModifiedBy())
                .bind(4, LocalDateTime.now())
                .bind(5, value)
                .bind(6, wrap(audit.getExecutionStatusId()))
                .bind(7, wrap(audit.getLambdaName()))
                .bind(8, wrap(audit.getParentPipelineId()))
                .bind(9, wrap(audit.getParentPipelineName()))
                .bind(10, wrap(audit.getPipelineName()))
                .bind(11, wrap(audit.getFileContent()))
                .bind(12, wrap(audit.getHostName()))
                .bind(13, wrap(audit.getModeOfExecution()))
                .bind(14, wrap(audit.getPipelineLoadType()))
                .bind(15, wrap(audit.getRelativePath()))
                .bind(16, wrap(audit.getRequestBody()))
                .bind(17, wrap(audit.getThreadName()))
                .bind(18, wrap(audit.getParentActionId()))
                .bind(19, wrap(audit.getParentActionName())).execute()).log()
                .doOnNext(data -> log.info("created: {}", data))
                .doOnError(e -> {
                    log.error("Pipeline persist failed", e);
                    throw new HandymanException("Persist failed", e);
                }).doFinally(signalType -> conn.close())
                .wait();

    }

    public static void insertAction(final Action audit) {
        var connection = DBAccess.getConnection();
        try {
            var statement = connection.createStatement(DBAccess.CONFIG.getString("config.action_audit_insert_stmt"))
                    .bind(0, wrap(audit.getPipelineId()))
                    .bind(1, audit.getCreatedBy())
                    .bind(2, audit.getCreatedDate())
                    .bind(3, audit.getLastModifiedBy())
                    .bind(4, LocalDateTime.now())
                    .bind(5, MAPPER.writeValueAsString(wrap(audit.getContext())))
                    .bind(6, wrap(audit.getExecutionStatusId()))
                    .bind(7, wrap(audit.getLambdaName()))
                    .bind(8, wrap(audit.getParentPipelineId()))
                    .bind(9, wrap(audit.getParentPipelineName()))
                    .bind(10, wrap(audit.getParentActionId()))
                    .bind(11, wrap(audit.getParentActionName()))
                    .bind(12, wrap(audit.getPipelineName()))
                    .bind(13, wrap(audit.getActionName()))
                    .bind(14, wrap(audit.getInput()).toString())
                    .bind(15, wrap(audit.getPipelineId()))
                    .bind(16, wrap(audit.getLog()))
                    .bind(17, wrap(audit.getExecutionGroupId()));
            execute(statement);
        } catch (Exception e) {
            log.error("Action persist failed", e);
            throw new HandymanException("Persist failed", e);
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
                    .bind(4, LocalDateTime.now())
                    .bind(6, wrap(statementAudit.getRowsProcessed()))
                    .bind(7, wrap(statementAudit.getRowsRead()))
                    .bind(8, wrap(statementAudit.getRowsWritten()))
                    .bind(9, wrap(statementAudit.getTimeTaken()))
                    .bind(10, wrap(statementAudit.getStatement()));
            execute(statement);
        } catch (Exception e) {
            log.error("Statement persist failed", e);
            throw new HandymanException("Persist failed", e);
        } finally {
            connection.close();
        }
    }

    private static void execute(final io.r2dbc.spi.Statement statement) {
        final Disposable subscribe = Mono.from(statement.execute())
                .log()
                .doOnNext(data -> log.info("created: {}", data))
                .subscribe();
    }

    private static Object wrap(final Object obj) {
        return Optional.ofNullable(obj).orElse("");
    }

}
