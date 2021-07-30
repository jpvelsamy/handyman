package in.handyman.raven.lib;

import in.handyman.raven.audit.AuditService;
import in.handyman.raven.connection.ResourceAccess;
import in.handyman.raven.context.ActionContext;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lib.model.CopyData;
import in.handyman.raven.util.Table;
import in.handyman.raven.util.UniqueID;
import lombok.extern.log4j.Log4j2;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.insert.Insert;

import java.sql.Connection;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;

@Log4j2
public class CopyDataJdbcWriter implements Callable<Void> {

    private final Insert insert;
    private final Table.Row poisonPill;
    private final ActionContext actionContext;
    private final LinkedBlockingDeque<Table.Row> rowQueue;
    private final CountDownLatch countDownLatch;

    private final Set<String> writeBuffer = new HashSet<>();
    private final String target;
    private final Integer writeSize;
    private final String columnList;

    public CopyDataJdbcWriter(final Map<String, String> configMap, final Insert insert,
                              final Table.Row poisonPill, final CopyData context, final ActionContext actionContext,
                              final LinkedBlockingDeque<Table.Row> rowQueue, final CountDownLatch countDownLatch) {
        this.insert = insert;
        this.poisonPill = poisonPill;
        this.actionContext = actionContext;
        this.rowQueue = rowQueue;
        this.countDownLatch = countDownLatch;

        this.target = Optional.ofNullable(context.getTo()).map(String::trim)
                .filter(s -> !s.isEmpty() && !s.isBlank())
                .orElseThrow(() -> new HandymanException("target data source cannot be empty for copyData for " + context.getName()));
        this.writeSize = Optional.ofNullable(context.getWriteBatchSize())
                .map(String::trim)
                .map(Integer::valueOf)
                .filter(integer -> integer > 0)
                .orElseGet(() -> Integer.valueOf(configMap.getOrDefault(Constants.WRITE_SIZE, Constants.DEFAULT_WRITE_SIZE).trim()));
        this.columnList = insert.getColumns().stream().map(Column::getColumnName).collect(Collectors.joining(","));

    }


    @Override
    public Void call() throws Exception {

        while (true) {
            final Table.Row row = rowQueue.take();
            if (poisonPill.equals(row)) {
                if (!writeBuffer.isEmpty()) {
                    log.info("CopyDataWriter(After poison pill) flushing to database rows:{}", writeBuffer.size());
                    writeToDb();
                }
                countDownLatch.countDown();
                break;
            } else {
                final String dataFrame = generateDataFrame(row);
                if (Objects.nonNull(dataFrame)) {
                    writeBuffer.add(dataFrame);
                    if (writeBuffer.size() % writeSize == 0) {
                        log.info("CopyDataWriter(Before poison pill) flushing to database rows:{}", writeBuffer.size());
                        writeToDb();
                    }
                }
            }
        }

        return null;
    }

    private String generateDataFrame(final Table.Row row) {

        log.info("CopyDataWriter: Writer generating dataframe for row: {}", row);
        var columnSet = row.getColumnSet();
        var dataFrameBuilder = new StringBuilder();

        var targetTable = insert.getTable();

        dataFrameBuilder.append("INSERT INTO ").append(targetTable).append(" (").
                append(columnList).append(") VALUES").append(Constants.INSERT_STMT_VALUE_START);

        if (Objects.nonNull(columnSet)) {
            for (final Table.ColumnInARow column : columnSet) {
                final String columnTypeName = column.getColumnTypeName();

                if (Objects.nonNull(columnTypeName) && (Objects.equals(columnTypeName, Constants.STRING_DATATYPE)
                        || Objects.equals(columnTypeName.toLowerCase(), "java.lang.string")
                        || Objects.equals(columnTypeName.toLowerCase(), "datetime")
                        || Objects.equals(columnTypeName.toLowerCase(), "timestamp"))) {
                    dataFrameBuilder.append(Constants.STRING_ENCLOSER).
                            append(column.getValue()).append(Constants.STRING_ENCLOSER);
                } else {
                    dataFrameBuilder.append(column.getValue());
                }
                if (!column.getIsLastColumn()) {
                    dataFrameBuilder.append(Constants.FIELD_SEPARATOR);
                }
            }
            dataFrameBuilder.append(Constants.INSERT_STMT_VALUE_END);
            return dataFrameBuilder.toString();

        }
        return null;
    }

    private void writeToDb() {

        var hikariDataSource = ResourceAccess.rdbmsConn(target);
        try (final Connection sourceConnection = hikariDataSource.getConnection()) {
            sourceConnection.setAutoCommit(false);
            log.info("Writing to database using conn: {}", target);
            final Long statementId = UniqueID.getId();
            AuditService.insertStatementAudit(statementId, actionContext.getLambdaId(),
                    actionContext.getName(), actionContext.getProcessName());
            try (final Statement stmt = sourceConnection.createStatement()) {
                for (var s : writeBuffer) {
                    stmt.addBatch(s);
                }
                stmt.executeBatch();
                sourceConnection.commit();
                AuditService.updateStatementAudit(statementId, -1, 0, writeBuffer.toString(), 1);
                writeBuffer.clear();
            }
        } catch (Throwable ex) {
            log.error("CopyDataWriter: {} error closing source connection for database: {} ", actionContext.getProcessId(), target, ex);
            throw new HandymanException("writeToDb failed", ex);
        }
    }

}
