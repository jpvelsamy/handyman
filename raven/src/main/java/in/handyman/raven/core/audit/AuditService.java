package in.handyman.raven.core.audit;

import com.zaxxer.hikari.HikariDataSource;
import in.handyman.raven.core.config.ConfigurationService;
import in.handyman.raven.core.connection.ResourceAccess;
import in.handyman.raven.exception.HandymanException;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Log4j2
public class AuditService {

    private static final String INSERT_SPW_AUDIT_INSTANCE_AUDIT = "INSERT INTO spw_audit.instance_audit (instance_id,instance_name, mode, start, hostname, file, status) VALUES (?,?, ?, NOW(), ?, ?, 1);";
    private static final String UPDATE_SPW_AUDIT_INSTANCE_AUDIT = "update spw_audit.instance_audit set end=now(), status=?, context_log=? where instance_id=?";

    private static final String INSERT_SPW_AUDIT_COMMAND_AUDIT = "INSERT INTO spw_audit.command_audit (command_id,instance_id, start, action_name, process_name, status) VALUES (?,?, NOW(), ?, ?, '1')";
    private static final String UPDATE_SPW_AUDIT_COMMAND_AUDIT = "update spw_audit.command_audit set end=now(), status=?, command_config=?  where command_id=?";

    private static final String INSERT_SPW_AUDIT_STATEMENT_AUDIT = "INSERT INTO spw_audit.statement_audit (statement_id,action_id, action_name, process_name, start, status) VALUES (?,?, ?, ?, NOW(),'1');";
    private static final String UPDATE_SPW_AUDIT_STATEMENT_AUDIT = "update spw_audit.statement_audit set end=now(), status=?, statement_config=?, rows_written=?, rows_read=? where statement_id=?";

    private static final String INSERT_SPW_AUDIT_BATCH_AUDIT = "INSERT INTO spw_audit.batch_audit (instance_id, command_name, statement_id, rows_processed, time_taken) VALUES (?, ?, ?, ?, ?);";
    private static final HikariDataSource DATA_SOURCE;

    static  {
        final String commonConfig = ConfigurationService.getCommonConfig("audit");
        DATA_SOURCE = ResourceAccess.rdbmsConn(commonConfig);
    }

    public static void insertInstanceAudit(final Long instanceId, final String instanceName,
                                           final String runMode, final String machine, final String file) {
        log.info("Obtained Connection for handle for inserting the audit for process {}", instanceName);
        try (final Connection connection = DATA_SOURCE.getConnection()) {
            try (final PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SPW_AUDIT_INSTANCE_AUDIT)) {
                preparedStatement.setLong(1, instanceId);
                preparedStatement.setString(2, instanceName);
                preparedStatement.setString(3, runMode);
                preparedStatement.setString(4, machine);
                preparedStatement.setString(5, file);
                preparedStatement.executeUpdate();
                return;
            } catch (SQLException e) {
                log.error("PreparedStatement failed", e);
            }
        } catch (SQLException e) {
            log.error("Connection failed", e);
        }
        throw new HandymanException("Instance audit create failed");
    }

    public static void updateInstanceAudit(final Long instanceId, final Integer status, final String contextLog, final String instanceName) {
        log.info("Obtained Connection for handle for updating the audit for instanceId #{} for process #{} ", instanceId, instanceName);
        if (execute(instanceId, status, contextLog, UPDATE_SPW_AUDIT_INSTANCE_AUDIT)) return;
        throw new HandymanException("Instance audit update failed");
    }

    private static boolean execute(final Long instanceId, final Integer status, final String contextLog, final String query) {
        try (final Connection connection = DATA_SOURCE.getConnection()) {
            try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, status);
                preparedStatement.setString(2, contextLog);
                preparedStatement.setLong(3, instanceId);
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException e) {
                log.error("PreparedStatement failed", e);
            }
        } catch (SQLException e) {
            log.error("Connection failed", e);
        }
        return false;
    }

    public static void insertCommandAudit(final Long commandId, final Long instanceId, final String actionName, final String processName) {
        log.info("Insert for command #{} for process #{} for processId# {}", actionName, processName, instanceId.toString());
        try (final Connection connection = DATA_SOURCE.getConnection()) {
            try (final PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SPW_AUDIT_COMMAND_AUDIT)) {
                preparedStatement.setLong(1, commandId);
                preparedStatement.setLong(2, instanceId);
                preparedStatement.setString(3, actionName);
                preparedStatement.setString(4, processName);
                preparedStatement.executeUpdate();
                return;
            } catch (SQLException e) {
                log.error("PreparedStatement failed", e);
            }
        } catch (SQLException e) {
            log.error("Connection failed", e);
        }
        throw new HandymanException("Command Audit create failed");
    }

    public static void updateCommandAudit(final Long actionId, final Integer status, final String commandDetail) {
        log.info("Obtained Connection for actionId #{} with status #{} ", actionId, status);
        if (execute(actionId, status, commandDetail, UPDATE_SPW_AUDIT_COMMAND_AUDIT)) return;
        throw new HandymanException("Command Audit update failed");
    }

    public static void insertStatementAudit(final Long statementId, final Long actionId, final String actionName, final String processName) {
        log.info("Insert for Statement #{} for process #{} for actionId# {}", actionName, processName, actionId);
        try (final Connection connection = DATA_SOURCE.getConnection()) {
            try (final PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SPW_AUDIT_STATEMENT_AUDIT,
                    Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setLong(1, statementId);
                preparedStatement.setLong(2, actionId);
                preparedStatement.setString(3, actionName);
                preparedStatement.setString(4, processName);
                preparedStatement.executeUpdate();
                return;
            } catch (SQLException e) {
                log.error("PreparedStatement failed", e);
            }
        } catch (SQLException e) {
            log.error("Connection failed", e);
        }
        throw new HandymanException("Statement Audit create failed");
    }

    public static void updateStatementAudit(final Long statementId, final Integer rowsW, final Integer rowsR, final String statement, final Integer status) {
        log.info("Obtained Connection for statementId #{} with status #{} ", statementId, status);
        try (final Connection connection = DATA_SOURCE.getConnection()) {
            try (final PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SPW_AUDIT_STATEMENT_AUDIT)) {
                preparedStatement.setInt(1, status);
                preparedStatement.setString(2, statement);
                preparedStatement.setInt(3, rowsW);
                preparedStatement.setInt(4, rowsR);
                preparedStatement.setLong(5, statementId);
                preparedStatement.executeUpdate();
                return;
            } catch (SQLException e) {
                log.error("PreparedStatement failed", e);
            }
        } catch (SQLException e) {
            log.error("Connection failed", e);
        }
        throw new HandymanException("Statement Audit update failed");
    }

    public static Integer insertBatchAudit(final Long statementId, final String actionName, final Long instanceId, final Integer rowsProcessed, final Long timeTaken) {
        log.info("Insert for Batch #{} for statement #{} for actionId# {}", statementId, actionName, instanceId);
        try (final Connection connection = DATA_SOURCE.getConnection()) {
            try (final PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SPW_AUDIT_BATCH_AUDIT,
                    Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setLong(1, instanceId);
                preparedStatement.setString(2, actionName);
                preparedStatement.setLong(3, statementId);
                preparedStatement.setInt(4, rowsProcessed);
                preparedStatement.setLong(5, timeTaken);
                preparedStatement.executeUpdate();
                try (final ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    resultSet.next();
                    final int pk = resultSet.getInt(1);
                    log.info("Completed Insert Batch audit for statement # {} of rows processed # {} with id# {}", actionName, rowsProcessed, pk);
                    return pk;
                } catch (SQLException e) {
                    log.error("ResultSet failed", e);
                }
            } catch (SQLException e) {
                log.error("PreparedStatement failed", e);
            }
        } catch (SQLException e) {
            log.error("Connection failed", e);
        }
        throw new HandymanException("Batch Audit create failed");
    }


}
