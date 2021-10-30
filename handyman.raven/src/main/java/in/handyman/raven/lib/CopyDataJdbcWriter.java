package in.handyman.raven.lib;

import in.handyman.raven.connection.ResourceAccess;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lib.model.CopyData;
import in.handyman.raven.lambda.doa.Action;
import in.handyman.raven.lambda.process.LambdaEngine;
import in.handyman.raven.util.Table;
import in.handyman.raven.util.UniqueID;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.insert.Insert;
import org.slf4j.Logger;

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

public class CopyDataJdbcWriter implements Callable<Void> {

    private final Insert insert;
    private final Table.Row poisonPill;
    private final Action action;
    private final LinkedBlockingDeque<Table.Row> rowQueue;
    private final CountDownLatch countDownLatch;

    private final Set<String> writeBuffer = new HashSet<>();
    private final String target;
    private final Integer writeSize;
    private final String columnList;
    private final Logger log;

    public CopyDataJdbcWriter(final Map<String, String> configMap, final Insert insert,
                              final Table.Row poisonPill, final CopyData copyData, final Action action,
                              final LinkedBlockingDeque<Table.Row> rowQueue, final CountDownLatch countDownLatch) {
        this.insert = insert;
        this.poisonPill = poisonPill;
        this.action = action;
        this.rowQueue = rowQueue;
        this.countDownLatch = countDownLatch;

        this.target = Optional.ofNullable(copyData.getTo()).map(String::trim)
                .filter(s -> !s.isEmpty() && !s.isBlank())
                .orElseThrow(() -> new HandymanException("target data source cannot be empty for copyData for " + copyData.getName()));
        this.writeSize = Optional.ofNullable(copyData.getWriteBatchSize())
                .map(String::trim)
                .map(Integer::valueOf)
                .filter(integer -> integer > 0)
                .orElseGet(() -> Integer.valueOf(configMap.getOrDefault(Constants.WRITE_SIZE, Constants.DEFAULT_WRITE_SIZE).trim()));
        this.columnList = insert.getColumns().stream().map(Column::getColumnName).collect(Collectors.joining(","));

        this.log = LambdaEngine.getLogger(action);
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

    private void writeToDb() {

        var hikariDataSource = ResourceAccess.rdbmsConn(target);
        try (final Connection sourceConnection = hikariDataSource.getConnection()) {
            sourceConnection.setAutoCommit(false);
            log.info("Writing to database using conn: {}", target);
            final Long statementId = UniqueID.getId();
            //TODO audit
            try (final Statement stmt = sourceConnection.createStatement()) {
                for (var s : writeBuffer) {
                    stmt.addBatch(s);
                }
                stmt.executeBatch();
                sourceConnection.commit();
                writeBuffer.clear();
            }
        } catch (Throwable ex) {
            log.error("CopyDataWriter: {} error closing source connection for database: {} ", action.getActionId(), target, ex);
            throw new HandymanException("writeToDb failed", ex);
        }
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

                if (Objects.nonNull(columnTypeName) && (Objects.equals(columnTypeName.toLowerCase(), Constants.STRING_DATATYPE)
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

}
