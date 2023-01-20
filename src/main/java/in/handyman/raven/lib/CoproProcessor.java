package in.handyman.raven.lib;

import in.handyman.raven.actor.HandymanActorSystemAccess;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lambda.doa.audit.StatementExecutionAudit;
import in.handyman.raven.util.CommonQueryUtil;
import in.handyman.raven.util.UniqueID;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.PreparedBatch;
import org.slf4j.Logger;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class CoproProcessor<T extends CoproProcessor.Entity> {

    private final BlockingQueue<T> queue;
    private final ExecutorService executorService;

    private final Class<T> targetClass;
    private final Jdbi jdbi;

    private final Logger log;

    private final T stoppingSeed;

    private final List<URL> nodes;
    private final Integer nodeSize;

    private final AtomicInteger nodeCount = new AtomicInteger();

    private final ActionExecutionAudit actionExecutionAudit;

    public CoproProcessor(final BlockingQueue<T> queue, final Class<T> targetClass,
                          final Jdbi jdbi, final Logger log,
                          final T stoppingSeed, final List<URL> coproNodes,
                          final ActionExecutionAudit actionExecutionAudit) {
        this.queue = queue;
        this.stoppingSeed = stoppingSeed;
        this.nodes = coproNodes;
        this.executorService = Executors.newWorkStealingPool();
        this.targetClass = targetClass;
        this.jdbi = jdbi;
        this.log = log;
        this.actionExecutionAudit = actionExecutionAudit;
        this.nodeSize = coproNodes.size();
        if (nodeSize > 0) {
            this.log.info("Copro processor created for copro coproNodes {}", nodeSize);
        } else {
            this.log.info("Failed to create Copro processor due to empty copro coproNodes");
            throw new HandymanException("Failed to create Copro processor due to empty copro coproNodes");
        }
        final StatementExecutionAudit audit = StatementExecutionAudit.builder()
                .rootPipelineId(actionExecutionAudit.getRootPipelineId())
                .actionId(actionExecutionAudit.getActionId())
                .statementId(UniqueID.getId())
                .statementContent("CoproProcessor created for " + actionExecutionAudit.getActionName())
                .build();
        addAudit(audit);
    }

    private void addAudit(final StatementExecutionAudit audit) {
        audit.setCreatedBy(actionExecutionAudit.getCreatedBy());
        audit.setLastModifiedBy(actionExecutionAudit.getLastModifiedBy());
        audit.setCreatedDate(LocalDateTime.now());
        audit.setLastModifiedDate(LocalDateTime.now());
        HandymanActorSystemAccess.insert(audit);
    }

    public void startProducer(final String sqlQuery, final Integer readBatchSize) {

        final LocalDateTime startTime = LocalDateTime.now();
        final List<String> formattedQuery = CommonQueryUtil.getFormattedQuery(sqlQuery);
        final StatementExecutionAudit audit = StatementExecutionAudit.builder()
                .rootPipelineId(actionExecutionAudit.getRootPipelineId())
                .actionId(actionExecutionAudit.getActionId())
                .statementId(UniqueID.getId())
                .statementContent("CoproProcessor started producer for " + actionExecutionAudit.getActionName())
                .build();
        addAudit(audit);
        formattedQuery.forEach(sql -> jdbi.useTransaction(handle -> handle.createQuery(sql).mapToBean(targetClass).useStream(stream -> {
            final AtomicInteger counter = new AtomicInteger();
            final Map<Integer, List<T>> partitions = stream.collect(Collectors.groupingBy(it -> counter.getAndIncrement() / readBatchSize));
            log.info("Total no of rows created {}", counter.get());
            partitions.forEach((integer, ts) -> executorService.submit(() -> {
                queue.addAll(ts);
                final StatementExecutionAudit audit2 = StatementExecutionAudit.builder()
                        .rootPipelineId(actionExecutionAudit.getRootPipelineId())
                        .actionId(actionExecutionAudit.getActionId())
                        .statementId(UniqueID.getId())
                        .statementContent("CoproProcessor producer for " + actionExecutionAudit.getActionName())
                        .rowsRead(ts.size())
                        .build();
                addAudit(audit2);
                log.info("Partition {} added to the queue", integer);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    log.error("Error at Producer sleep", e);
                    throw new RuntimeException(e);
                }
            }));
            log.info("Total Partition added to the queue: {} ", partitions.size());
            queue.add(stoppingSeed);
            log.info("Added stopping seed to the queue");
        })));
        final StatementExecutionAudit audit3 = StatementExecutionAudit.builder()
                .rootPipelineId(actionExecutionAudit.getRootPipelineId())
                .actionId(actionExecutionAudit.getActionId())
                .statementId(UniqueID.getId())
                .statementContent("CoproProcessor producer completed " + actionExecutionAudit.getActionName())
                .timeTaken((double) ChronoUnit.SECONDS.between(startTime, LocalDateTime.now()))
                .build();
        addAudit(audit3);

    }

    public void startConsumer(final String insertSql, final Integer consumerCount, final Integer writeBatchSize, final ConsumerProcess<T> callable) {

        final Predicate<T> tPredicate = t -> !Objects.equals(t, stoppingSeed);
        for (int consumer = 0; consumer < consumerCount; consumer++) {
            executorService.submit(() -> {
                final List<T> processedEntity = new ArrayList<>();
                final AtomicInteger counter = new AtomicInteger();
                while (true) {
                    try {
                        final T take = queue.take();
                        if (tPredicate.test(take)) {
                            final int index = nodeCount.incrementAndGet() % nodeSize;//Round robin
                            final T process = callable.process(nodes.get(index), take);
                            processedEntity.add(process);
                            if (counter.getAndIncrement() == writeBatchSize) {
                                jdbi.useTransaction(handle -> {
                                    final PreparedBatch preparedBatch = handle.prepareBatch(insertSql);
                                    final int[] execute = preparedBatch.add(processedEntity).execute();
                                    log.info("Consumer persisted {}", execute);
                                });
                            }
                        } else {
                            log.info("Breaking the consumer");
                            queue.add(take);
                            break;
                        }
                    } catch (InterruptedException e) {
                        log.error("Error at Consumer", e);
                        throw new RuntimeException(e);
                    }
                }
                if (!processedEntity.isEmpty()) {
                    jdbi.useTransaction(handle -> {
                        final PreparedBatch preparedBatch = handle.prepareBatch(insertSql);
                        final int[] execute = preparedBatch.add(processedEntity).execute();
                        log.info("Consumer persisted {}", execute);
                    });
                }
                log.info("Consumer completed the process and persisted {} rows", counter.get());
            });
            log.info("Consumer {} submitted the process", consumer);
        }

    }


    public interface ConsumerProcess<T extends Entity> {

        T process(final URL endpoint, final T entity);

    }

    public interface Entity {

        List<String> getRowData();

    }

}
