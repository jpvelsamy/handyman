package in.handyman.raven.lib;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Lists;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.access.ResourceAccess;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.DonutDocQa;
import in.handyman.raven.util.CommonQueryUtil;
import in.handyman.raven.util.InstanceUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.PreparedBatch;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "DonutDocQa"
)
public class DonutDocQaAction implements IActionExecution {
    public static final String ATTRIBUTION_URL = "copro.docnet-attribution.url";
    private final ActionExecutionAudit action;

    private final Logger log;

    private final DonutDocQa donutDocQa;

    private final Marker aMarker;
    private final List<String> nodes;

    private final AtomicInteger counter = new AtomicInteger();

    public DonutDocQaAction(final ActionExecutionAudit action, final Logger log,
                            final Object donutDocQa) {
        this.donutDocQa = (DonutDocQa) donutDocQa;
        this.action = action;
        this.log = log;
        this.aMarker = MarkerFactory.getMarker(" DonutDocQa: " + this.donutDocQa.getName());
        this.nodes = Optional.ofNullable(action.getContext().get(ATTRIBUTION_URL)).map(s -> Arrays.asList(s.split(","))).orElse(Collections.emptyList());
    }

    @Override
    public void execute() throws Exception {
        log.info(aMarker, "<-------Donut Attribution Action for {} has been started------->" + donutDocQa.getName());
        final Jdbi jdbi = ResourceAccess.rdbmsJDBIConn(donutDocQa.getResourceConn());
        final List<DonutQueryResult> donutQueryResults = new ArrayList<>();
        jdbi.useTransaction(handle -> {
            final List<String> formattedQuery = CommonQueryUtil.getFormattedQuery(donutDocQa.getQuestionSql());
            formattedQuery.forEach(sqlToExecute -> donutQueryResults.addAll(handle.createQuery(sqlToExecute)
                    .mapToBean(DonutQueryResult.class)
                    .stream().collect(Collectors.toList())));
        });

        // Create DDL

        jdbi.useTransaction(handle -> handle.execute("create table if not exists macro." + donutDocQa.getResponseAs() + " ( id bigserial not null, file_path text,question text, predicted_attribution_value text, action_id bigint, root_pipeline_id bigint,process_id bigint, created_date timestamp not null default now() );"));
        jdbi.useTransaction(handle -> handle.execute("create table if not exists macro." + donutDocQa.getResponseAs() + "_error ( id bigserial not null, file_path text,error_message text,  action_id bigint, root_pipeline_id bigint,process_id bigint, created_date timestamp not null default now() );"));

        final List<DonutLineItem> donutLineItems = new ArrayList<>();

        donutQueryResults.stream().collect(Collectors.groupingBy(DonutQueryResult::getFilePath))
                .forEach((s, donutQueryResults1) -> donutLineItems.add(DonutLineItem.builder()
                        .filePath(s).questions(donutQueryResults1.stream().map(DonutQueryResult::getQuestion).collect(Collectors.toList()))
                        .build()));

        doProcess(donutLineItems);


    }

    private void doProcess(final List<DonutLineItem> donutLineItems) {
        final int parallelism;
        if (donutDocQa.getForkBatchSize() != null) {
            parallelism = Integer.parseInt(donutDocQa.getForkBatchSize());
        } else {
            parallelism = 1;
        }
        final int size = nodes.size();
        try {
            if (size > 0) {
                final int batchSize = donutLineItems.size() / parallelism;
                if (parallelism > 1 && size > 1 && batchSize > 0) {
                    final List<List<DonutLineItem>> donutLineItemPartitions = Lists.partition(donutLineItems, batchSize);
                    final CountDownLatch countDownLatch = new CountDownLatch(donutLineItemPartitions.size());
                    final ExecutorService executorService = Executors.newFixedThreadPool(parallelism);
                    donutLineItemPartitions.forEach(items -> executorService.submit(() -> {

                        try {
                            computeProcess(size, items);
                        } finally {
                            countDownLatch.countDown();
                        }

                    }));
                    countDownLatch.await();

                } else {
                    computeProcess(size, donutLineItems);
                }

            }
            action.getContext().put(donutDocQa.getResponseAs().concat(".error"), "false");
        } catch (Exception e) {
            log.error(aMarker, "The Failure Response {} --> {}", donutDocQa.getResponseAs(), e.getMessage(), e);
            action.getContext().put(donutDocQa.getResponseAs().concat(".error"), "true");
        }
    }

    private void computeProcess(final int nodeSize, final List<DonutLineItem> donutLineItems) {
        final Jdbi jdbi = ResourceAccess.rdbmsJDBIConn(donutDocQa.getResourceConn());

        donutLineItems.forEach(donutLineItem -> {
            final String filePath = donutLineItem.getFilePath();

            try {
                final List<String> questions = donutLineItem.getQuestions();
                final String node = nodes.get(counter.incrementAndGet() % nodeSize);

                log.info(aMarker, "preparing {} for rest api call", questions.size());
                final List<DonutResultLineItem> lineItems = new DonutApiCaller(node).compute(filePath, donutDocQa.getOutputDir(), questions);
                log.info(aMarker, "completed {}", lineItems.size());

                jdbi.useTransaction(handle -> {
                    final PreparedBatch batch = handle.prepareBatch("INSERT INTO macro." + donutDocQa.getResponseAs() + " (process_id,file_path,question, predicted_attribution_value, action_id, root_pipeline_id) VALUES(" + action.getPipelineId() + ",:filePath,:question,:predictedAttributionValue, " + action.getActionId() + ", " + action.getRootPipelineId() + ");");
                    Lists.partition(lineItems, 100).forEach(resultLineItems -> {
                        log.info(aMarker, "inserting into donut_docqa_action {}", resultLineItems.size());
                        resultLineItems.forEach(resultLineItem -> {
                            batch.bind("filePath", filePath)
                                    .bind("question", resultLineItem.question)
                                    .bind("predictedAttributionValue", resultLineItem.predictedAttributionValue)
                                    .add();
                        });
                        int[] counts = batch.execute();
                        log.info(aMarker, " persisted {} in donut_docqa_action", counts);
                    });
                });
            } catch (Exception e) {
                jdbi.useTransaction(handle -> {
                    handle.createUpdate("INSERT INTO macro." + donutDocQa.getResponseAs() + "_error (file_path,error_message, action_id, root_pipeline_id,process_id) VALUES(:filePath,:errorMessage, " + action.getActionId() + ", " + action.getRootPipelineId() + "," + action.getPipelineId() + ");")
                            .bind("filePath", filePath)
                            .bind("errorMessage", e.getMessage())
                            .execute();
                });
            }

        });

    }

    @Override
    public boolean executeIf() throws Exception {
        return donutDocQa.getCondition();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DonutLineItem {

        private String filePath;
        private List<String> questions;

    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DonutQueryResult {

        private String filePath;
        private String question;

    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DonutResultLineItem {
        private String question;
        private String predictedAttributionValue;

    }


    @Slf4j
    public static class DonutApiCaller {

        private static final MediaType MediaTypeJSON = MediaType.parse("application/json; charset=utf-8");
        private static final ObjectMapper MAPPER = new ObjectMapper();
        private final OkHttpClient httpclient = InstanceUtil.createOkHttpClient();
        private final String node;

        public DonutApiCaller(final String node) {
            this.node = node;
        }

        protected List<DonutResultLineItem> compute(final String inputPath, final String outputDir, final List<String> questions) {
            final ObjectNode objectNode = MAPPER.createObjectNode();
            objectNode.put("inputFilePath", inputPath);
            objectNode.putPOJO("attributes", questions);
            objectNode.put("outputDir", outputDir);
            final Request request = new Request.Builder().url(node)
                    .post(RequestBody.create(objectNode.toString(), MediaTypeJSON)).build();
            log.info("Request URL : {} Question List size {}", node, questions.size());
            try (Response response = httpclient.newCall(request).execute()) {
                String responseBody = Objects.requireNonNull(response.body()).string();
                if (response.isSuccessful()) {
                    List<DonutResultLineItem> donutLineItems = MAPPER.readValue(responseBody, new TypeReference<>() {
                    });
                    log.info("DonutLineItem size {}", donutLineItems.size());
                    return donutLineItems;
                } else {
                    throw new HandymanException(responseBody);
                }
            } catch (Exception e) {
                throw new HandymanException("Failed to execute the rest api call " + node, e);
            }
        }
    }

}
