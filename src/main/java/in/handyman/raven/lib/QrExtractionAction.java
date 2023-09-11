package in.handyman.raven.lib;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.access.ResourceAccess;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.QrExtraction;
import in.handyman.raven.util.ExceptionUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.*;
import org.jdbi.v3.core.Jdbi;
import org.mariadb.jdbc.message.client.LongDataPacket;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "QrExtraction"
)
public class QrExtractionAction implements IActionExecution {
    private final ActionExecutionAudit action;

    private final Logger log;

    private final QrExtraction qrExtraction;
  private static String httpClientTimeout = new String();
    private final Marker aMarker;

    public QrExtractionAction(final ActionExecutionAudit action, final Logger log,
                              final Object qrExtraction) {
        this.qrExtraction = (QrExtraction) qrExtraction;
        this.action = action;
        this.log = log;
        this.aMarker = MarkerFactory.getMarker(" QrExtraction:" + this.qrExtraction.getName());
      this.httpClientTimeout = action.getContext().get("okhttp.client.timeout");
    }

    @Override
    public void execute() throws Exception {
        try {
            log.info(aMarker, "Qr extraction Action for {} with group by eoc-id has started", qrExtraction.getName());
            final Jdbi jdbi = ResourceAccess.rdbmsJDBIConn(qrExtraction.getResourceConn());

            //3. initiate copro processor and copro urls
            final List<URL> urls = Optional.ofNullable(action.getContext().get("copro.qr-attribution.url")).map(s -> Arrays.stream(s.split(",")).map(s1 -> {
                try {
                    return new URL(s1);
                } catch (MalformedURLException e) {
                    log.error("Error in processing the URL {}", s1, e);
                    throw new HandymanException("Error in processing the URL", e, action);
                }
            }).collect(Collectors.toList())).orElse(Collections.emptyList());

            log.info("Urls for the qr attribution for sor grouping {}", urls);

            //5. build insert prepare statement with output table columns
            final String insertQuery = "INSERT INTO " + qrExtraction.getOutputTable() +
                    "            (origin_id, group_id, paper_no, created_on,   qr_format, qr_format_id, extracted_value,   file_id, b_box, angle, confidence_score, status, stage, message,decode_type)" +
                    "VALUES(?,?,?,?,  ?,?,? ,?,?,  ?,?,?, ?,?  ,?)";
            final CoproProcessor<QrInputEntity, QrOutputEntity> coproProcessor =
                    new CoproProcessor<>(new LinkedBlockingQueue<>(),
                            QrOutputEntity.class,
                            QrInputEntity.class,
                            jdbi, log,
                            new QrInputEntity(), urls, action);
            //4. call the method start producer from coproprocessor
            coproProcessor.startProducer(qrExtraction.getQuerySet(), Integer.valueOf(action.getContext().get("read.batch.size")));
            log.info("start producer method from copro processor ");
            Thread.sleep(1000);
            //8. call the method start consumer from coproprocessor
            coproProcessor.startConsumer(insertQuery, Integer.valueOf(action.getContext().get("qr.consumer.API.count")), Integer.valueOf(action.getContext().get("write.batch.size")), new QrConsumerProcess(log, aMarker, action));
            log.info("start consumer method from copro processor ");

        } catch (Exception e) {
            log.error("Error in the Qr extraction action {}", ExceptionUtil.toString(e));
            throw new HandymanException("QR extraction action failed ", e, action);

        }

    }


    //6. write consumer process class which implements CoproProcessor.ConsumerProcess
    public static class QrConsumerProcess implements CoproProcessor.ConsumerProcess<QrInputEntity, QrOutputEntity> {

        private final Logger log;
        private final Marker aMarker;
        private final ObjectMapper mapper = new ObjectMapper();
        private static final MediaType MediaTypeJSON = MediaType
                .parse("application/json; charset=utf-8");

        public final ActionExecutionAudit action;

      final OkHttpClient httpclient = new OkHttpClient.Builder()
              .connectTimeout(Long.parseLong(httpClientTimeout), TimeUnit.MINUTES)
              .writeTimeout(Long.parseLong(httpClientTimeout), TimeUnit.MINUTES)
              .readTimeout(Long.parseLong(httpClientTimeout), TimeUnit.MINUTES)
              .build();


        public QrConsumerProcess(Logger log, Marker aMarker, ActionExecutionAudit action) {
            this.log = log;
            this.aMarker = aMarker;
            this.action = action;

        }

        //7. overwrite the method process in coproprocessor, write copro api logic inside this method
        @Override
        public List<QrOutputEntity> process(URL endpoint, QrInputEntity entity) throws Exception {
            log.info("copro consumer process started");

            List<QrOutputEntity> qrOutputEntities = new ArrayList<>();
            final ObjectNode objectNode = mapper.createObjectNode();
            String filePath = entity.filePath;
            final String qrExtractionProcess="QR_EXTRACTION";
            Long actionId= action.getActionId();
            Long rootpipelineId=entity.getRootPipelineId();
            objectNode.put("inputFilePath", filePath);
            objectNode.put("process",qrExtractionProcess);
            objectNode.put("actionId",actionId);
            objectNode.put("rootPipelineId",rootpipelineId);

            if (log.isInfoEnabled()) {
                log.info("input object node in the consumer process  inputFilePath {}", filePath);
            }

            Request request = new Request.Builder().url(endpoint).post(RequestBody.create(objectNode.toString(), MediaTypeJSON)).build();

            if (log.isInfoEnabled()) {
                log.info("input object node in the consumer process coproURL {}, inputFilePath {}", endpoint, filePath);
            }
            String originId = entity.getOriginId();
            Integer paperNo = entity.getPaperNo();
            Integer groupId = entity.getGroupId();
            String fileId = entity.getFileId();
            try (Response response = httpclient.newCall(request).execute()) {
                String responseBody = Objects.requireNonNull(response.body()).string();
                if (response.isSuccessful()) {
                    List<QrReader> qrLineItems = mapper.readValue(responseBody, new TypeReference<>() {
                    });

                    AtomicInteger atomicInteger = new AtomicInteger();
                    if (!qrLineItems.isEmpty()) {
                        qrLineItems.forEach(qrReader -> {

                            qrOutputEntities.add(QrOutputEntity.builder()
                                    .angle(qrReader.getAngle())
                                    .originId(originId)
                                    .paperNo(paperNo)
                                    .groupId(groupId)
                                    .fileId(fileId)
                                    .decodeType(qrReader.getDecode_type())
                                    .qrFormat(qrReader.getType())
                                    .qrFormatId(atomicInteger.incrementAndGet())
                                    .extractedValue(qrReader.getValue())
                                    .confidenceScore(qrReader.getConfidenceScore())
                                    .createdOn(Timestamp.valueOf(LocalDateTime.now()))
                                    .b_box(qrReader.getBoundingBox().toString())
                                    .status("COMPLETED")
                                    .stage("QR_EXTRACTION")
                                    .message("qr extraction completed")
                                    .build());

                        });
                    } else {
                        qrOutputEntities.add(QrOutputEntity.builder()
                                .originId(originId)
                                .paperNo(paperNo)
                                .groupId(groupId)
                                .fileId(fileId)
                                .createdOn(Timestamp.valueOf(LocalDateTime.now()))
                                .status("ABSENT")
                                .stage("QR_EXTRACTION")
                                .message("qr code absent in the given file")
                                .build());
                    }

                } else {
                    qrOutputEntities.add(QrOutputEntity.builder()
                            .originId(originId)
                            .paperNo(paperNo)
                            .groupId(groupId)
                            .fileId(fileId)
                            .createdOn(Timestamp.valueOf(LocalDateTime.now()))
                            .status("FAILED")
                            .stage("QR_EXTRACTION")
                            .message(response.message())
                            .build());

                    log.error(aMarker, "The Exception occurred in episode of coverage in response {}", response);
                }

            } catch (Exception e) {
                qrOutputEntities.add(QrOutputEntity.builder()
                        .originId(originId)
                        .paperNo(paperNo)
                        .groupId(groupId)
                        .fileId(fileId)
                        .createdOn(Timestamp.valueOf(LocalDateTime.now()))
                        .status("FAILED")
                        .stage("QR_EXTRACTION")
                        .message(e.getMessage())
                        .build());
                log.error("Error in the copro process api hit {}", request);
                HandymanException handymanException = new HandymanException(e);
                HandymanException.insertException("Error in qr extraction action for group id - " + groupId, handymanException, this.action);
            }
            return qrOutputEntities;
        }
    }


    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class QrReader {
        private String value;
        private String type;
        private String decode_type;
        private Integer confidenceScore;
        private JsonNode boundingBox;
        private Integer angle;
    }

    //1. input pojo from select query, which implements CoproProcessor.Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class     QrInputEntity implements CoproProcessor.Entity {
        private String filePath;
        private Integer groupId;
        private String originId;
        private Integer paperNo;
        private String fileId;
        private Long rootPipelineId;

        @Override
        public List<Object> getRowData() {
            return null;
        }
    }

    //2. output pojo for table, which implements CoproProcessor.Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class QrOutputEntity implements CoproProcessor.Entity {
        private String originId;
        private Integer groupId;
        private Integer paperNo;
        private String fileId;
        private String qrFormat;
        private Integer qrFormatId;
        private String extractedValue;
        private Timestamp createdOn;
        private Integer confidenceScore;
        private Integer angle;
        private String b_box;
        private String status;
        private String stage;
        private String message;
        private String decodeType;

        @Override
        public List<Object> getRowData() {
            return Stream.of(this.originId, this.groupId, this.paperNo, this.createdOn, this.qrFormat, this.qrFormatId, this.extractedValue, this.fileId, this.b_box, this.angle, this.confidenceScore, this.status, this.stage, this.message,this.decodeType).collect(Collectors.toList());
        }

    }

    @Override
    public boolean executeIf() throws Exception {
        return qrExtraction.getCondition();
    }
}
