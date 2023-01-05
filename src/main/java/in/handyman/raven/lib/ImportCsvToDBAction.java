package in.handyman.raven.lib;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lambda.doa.config.SpwResourceConfig;
import in.handyman.raven.lib.model.ImportCsvToDB;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.PreparedBatch;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "ImportCsvToDB"
)
public class ImportCsvToDBAction implements IActionExecution {

    private static final String DELIMITER = ",";
    private final ActionExecutionAudit actionExecutionAudit;
    private final Logger log;
    private final ImportCsvToDB importCsvToDB;
    private final Marker aMarker;

    public ImportCsvToDBAction(final ActionExecutionAudit actionExecutionAudit, final Logger log, final Object importCsvToDB) {
        this.importCsvToDB = (ImportCsvToDB) importCsvToDB;
        this.actionExecutionAudit = actionExecutionAudit;
        this.log = log;
        this.aMarker = MarkerFactory.getMarker(" ImportCsvToDB:" + this.importCsvToDB.getName());
    }

    @Override
    public void execute() throws Exception {

        final int threadCount = Optional.ofNullable(importCsvToDB.getWriteThreadCount()).map(Integer::valueOf).orElse(1);
        final Integer batchSize = Optional.ofNullable(importCsvToDB.getBatchSize()).map(Integer::valueOf).orElse(10000);

        final SpwResourceConfig target = importCsvToDB.getTarget();
        if (Objects.isNull(target)) {
            throw new HandymanException("target connection not provided");
        }
        log.info(aMarker, "Resource provided " + target.getConfigName());

        final Jdbi jdbi = Jdbi.create(target.getResourceUrl(), target.getUserName(), target.getPassword());
        final int size = importCsvToDB.getValue().size();
        if (threadCount > 1 && size > 1) {
            final ExecutorService taskList
                    = Executors.newWorkStealingPool(threadCount);
            final CountDownLatch downLatch = new CountDownLatch(size);
            for (var path : importCsvToDB.getValue()) {
                taskList.execute(() -> {
                    doImport(batchSize, jdbi, path);
                    downLatch.countDown();
                });
            }
            downLatch.await();
        } else {
            for (var path : importCsvToDB.getValue()) {
                doImport(batchSize, jdbi, path);
            }
        }


    }

    private void doImport(final Integer batchSize, final Jdbi jdbi, final String path) {
        final File file = new File(path);
        if (!file.exists()) {
            throw new HandymanException("File not exists " + path);
        }
        log.info(aMarker, "Found file " + path);
        try {
            if (file.isFile()) {
                extracted(batchSize, jdbi, file);
            } else if (file.isDirectory()) {
                final Optional<File[]> files = Optional.ofNullable(file.listFiles());
                if (files.isPresent()) {
                    for (var childFile : files.get()) {
                        doImport(batchSize, jdbi, childFile.getAbsolutePath());
                    }
                }
            }
        } catch (Exception e) {
            throw new HandymanException("Exception for file " + path, e);
        }
    }

    private void extracted(final Integer batchSize, final Jdbi jdbi, final File file) throws IOException {
        final List<Map<String, Object>> maps = readObjectsFromCsv(file);

        final int size = maps.size();
        log.info(aMarker, "extracted data from CSV file with size " + size);

        if (size != 0) {
            final Map<String, Object> map = maps.get(0);
            final Set<String> objects = map.keySet();
            final String columnNames = objects.stream().map(String::valueOf).collect(Collectors.joining(DELIMITER));
            final String namedParams = objects.stream().map(String::valueOf).map(s -> ":" + s).collect(Collectors.joining(DELIMITER));


            final Integer rowCount = jdbi.inTransaction(handle -> {

                final String format = String.format(" INSERT INTO %s  (%s) VALUES(%s);", importCsvToDB.getTableName(), columnNames, namedParams);
                var counter = new AtomicInteger();
                try (PreparedBatch batch = handle.prepareBatch(format)) {
                    maps.forEach(map1 -> {
                        batch.bindMap(map1).add();
                        if (counter.getAndIncrement() % batchSize == 0) {
                            log.info(aMarker, "added batch size " + batch.execute().length);
                        }
                    });
                    log.info(aMarker, "added batch size " + batch.execute().length);
                }
                return counter.get();
            });

            log.info(aMarker, "completed batch size " + rowCount);

        }
    }

    private List<Map<String, Object>> readObjectsFromCsv(final File file) throws IOException {
        var bootstrap = CsvSchema.emptySchema().withHeader();
        var csvMapper = new CsvMapper();
        final MappingIterator<Map<String, Object>> mappingIterator = csvMapper.readerFor(new TypeReference<Map<String, Object>>() {
        }).with(bootstrap).readValues(file);
        return mappingIterator.readAll();
    }

    @Override
    public boolean executeIf() throws Exception {
        return importCsvToDB.getCondition();
    }
}
