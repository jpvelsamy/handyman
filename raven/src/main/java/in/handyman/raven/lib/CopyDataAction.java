package in.handyman.raven.lib;

import in.handyman.raven.audit.AuditService;
import in.handyman.raven.config.ConfigurationService;
import in.handyman.raven.connection.ResourceAccess;
import in.handyman.raven.process.Context;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.action.ActionExecution;
import in.handyman.raven.action.IActionExecution;
import in.handyman.raven.lib.model.CopyData;
import in.handyman.raven.util.Table;
import in.handyman.raven.util.UniqueID;
import lombok.extern.log4j.Log4j2;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.insert.Insert;
import org.apache.logging.log4j.MarkerManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Auto Generated By Raven
 */
@ActionExecution(actionName = "CopyData")
@Log4j2
public class CopyDataAction implements IActionExecution {

    private final Context context;
    private final CopyData copyData;
    private final MarkerManager.Log4jMarker aMarker;

    public CopyDataAction(final Context context, final Object copyData) {
        this.copyData = (CopyData) copyData;
        this.context = context;
        this.aMarker = new MarkerManager.Log4jMarker("CopyData");
        this.context.getDetailMap().putPOJO("context", copyData);
    }

    @Override
    public void execute() throws Exception {
        //Retrieving the global config map for default value
        var configMap = ConfigurationService.getGlobalConfig();
        var instanceId = context.getProcessId();
        var name = copyData.getName();
        var source = Optional.ofNullable(copyData.getSource()).map(String::trim)
                .filter(s -> !s.isEmpty() && !s.isBlank())
                .orElseThrow(() -> new HandymanException("source data source cannot be empty for copyData for " + name));
        var target = Optional.ofNullable(copyData.getTo()).map(String::trim)
                .filter(s -> !s.isEmpty() && !s.isBlank())
                .orElseThrow(() -> new HandymanException("target data source cannot be empty for copyData for " + name));
        var fetchSize = Optional.ofNullable(copyData.getFetchBatchSize())
                .map(String::trim)
                .map(Integer::valueOf)
                .filter(integer -> integer > 0)
                .orElseGet(() -> Integer.valueOf(configMap.getOrDefault(Constants.READ_SIZE, Constants.DEFAULT_READ_SIZE).trim()));
        var writeSize = Optional.ofNullable(copyData.getWriteBatchSize())
                .map(String::trim)
                .map(Integer::valueOf)
                .filter(integer -> integer > 0)
                .orElseGet(() -> Integer.valueOf(configMap.getOrDefault(Constants.WRITE_SIZE, Constants.DEFAULT_WRITE_SIZE).trim()));
        var upperThreadCount = Optional.ofNullable(copyData.getWriteThreadCount())
                .map(String::trim)
                .map(Integer::valueOf)
                .filter(integer -> integer > 0)
                .orElseGet(() -> Integer.valueOf(configMap.getOrDefault(Constants.WRITER_THREAD, Constants.DEFAULT_WRITER_COUNT).trim()));
        var lowerThreadCount = 1;
        //retrieving the insert into sql statement
        var insertStatement = Optional.ofNullable(copyData.getValue()).map(String::trim)
                .map(s -> s.replaceAll("\"", ""))
                .filter(s -> !s.isEmpty() && !s.isBlank())
                .orElseThrow(() -> new HandymanException("INSERT INTO SELECT .... cannot be empty for copyData for " + name));

        var givenStatement = CCJSqlParserUtil.parse(insertStatement);
        if (givenStatement instanceof Insert) {
            var insert = (Insert) givenStatement;
            var select = insert.getSelect();

            log.info("CopyData action input variables id:{},name: {}, source-database:{}, target-database:{}, fetchSize:{}, writeSize:{},threadCount:{} ", instanceId, name, source, target, fetchSize, writeSize, upperThreadCount);
            log.info("CopyData Insert Sql input post parameter ingestion \n : {}", insert);
            log.info("CopyData Select Sql input post parameter ingestion \n : {}", insert);
            //initializing the connection related statement
            var hikariDataSource = ResourceAccess.rdbmsConn(source);
            final Long statementId = UniqueID.getId();
            final ExecutorService executor = Executors.newWorkStealingPool();
            var rand = new Random();
            var rowQueueMap = new LinkedHashMap<Integer, BlockingQueue<Table.Row>>();
            var rowsProcessed = new AtomicInteger(0);

            try (final Connection sourceConnection = hikariDataSource.getConnection()) {
                AuditService.insertStatementAudit(statementId, context.getLambdaId(),
                        copyData.getName(), context.getProcessName());
                try (final Statement stmt = sourceConnection.createStatement()) {
                    stmt.setFetchSize(fetchSize);
                    var countDownLatch = new CountDownLatch(upperThreadCount);
                    IntStream.range(lowerThreadCount, upperThreadCount + 1).forEach(i -> {
                        var rowQueue = new LinkedBlockingDeque<Table.Row>();
                        var poisonPill = new Table.Row(i, null);
                        log.info(aMarker, " action is prepping up writer thread with poison pill {}", poisonPill);
                        final CopyDataJdbcWriter jdbcWriter = new CopyDataJdbcWriter(configMap, insert, poisonPill, copyData,
                                context, rowQueue, countDownLatch);
                        executor.submit(jdbcWriter);
                        rowQueueMap.put(poisonPill.getRowId(), rowQueue);
                    });
                    //Retrieving the data from the source
                    var selectStatement = select.toString();
                    try (var rs = stmt.executeQuery(selectStatement)) {
                        var nrCols = rs.getMetaData().getColumnCount();
                        while (rs.next()) {
                            var startTime = System.currentTimeMillis();
                            var row = getRow(rs, nrCols);
                            addRowToQueue(upperThreadCount, lowerThreadCount, rand, rowQueueMap, row);
                            addAudit(instanceId, name, fetchSize, statementId, rowsProcessed, startTime);
                            rowQueueMap.forEach((integer, rows) -> rows.add(new Table.Row(integer, null)));
                            try {
                                countDownLatch.await();
                            } catch (final InterruptedException ex) {
                                log.error(aMarker, "{} error during waiting for worker threads to finish their job", instanceId, ex);
                                throw ex;
                            }
                        }
                    }

                    AuditService.updateStatementAudit(statementId, -1, 0, insertStatement, 1);
                }
            } catch (Exception ex) {
                log.error(aMarker, "{} error closing source connection for database {}", instanceId, source, ex);
                throw ex;
            }
        } else {
            throw new HandymanException("Insert stmt not found");
        }
    }

    private Table.Row getRow(final ResultSet rs, final int nrCols) throws SQLException {
        var columnSet = new LinkedHashSet<Table.ColumnInARow>();
        var id = rs.getRow();
        IntStream.range(1, nrCols + 1).forEach(i -> {
            final Table.ColumnInARow column = createColumn(i, rs);
            columnSet.add(column);
        });
        return new Table.Row(id, columnSet);
    }

    private void addRowToQueue(final Integer upperThreadCount, final int lowerThreadCount, final Random rand, final LinkedHashMap<Integer, BlockingQueue<Table.Row>> rowQueueMap, final Table.Row row) {
        var queueNumber = rand.nextInt((upperThreadCount - lowerThreadCount) + 1) + lowerThreadCount;
        var rowQueue = rowQueueMap.get(queueNumber);
        rowQueue.add(row);
    }

    private void addAudit(final Long instanceId, final String name, final Integer fetchSize, final Long statementId, final AtomicInteger rowsProcessed, final long startTime) {
        if (rowsProcessed.incrementAndGet() % fetchSize == 0) {
            var endTime = System.currentTimeMillis();
            var timeTaken = endTime - startTime;
            //Taken care of batch audit
            AuditService.insertBatchAudit(statementId, name, instanceId, rowsProcessed.get(), timeTaken);
        }
    }

    private Table.ColumnInARow createColumn(final int i, final ResultSet rs) {
        try {
            final ResultSetMetaData rsMetaData = rs.getMetaData();
            var columnType = rsMetaData.getColumnType(i);
            var columnTypeName = rsMetaData.getColumnTypeName(i);
            var columnName = rsMetaData.getColumnName(i);
            var columnLabel = rsMetaData.getColumnLabel(i);
            var scale = rsMetaData.getScale(i);
            var value = rs.getObject(i);
            var isLastColumn = i == rsMetaData.getColumnCount();
            return new Table.ColumnInARow(columnType, columnTypeName, columnName, columnLabel,
                    scale, value, null, isLastColumn);
        } catch (Exception ex) {
            throw new HandymanException("Column mapping failed", ex);
        }
    }

    @Override
    public boolean executeIf() {
        return copyData.getCondition();
    }
}
