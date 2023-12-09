package in.handyman.raven.lib.model.qrextraction;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.CoproProcessor;
import in.handyman.raven.lib.model.triton.TritonInputRequest;
import in.handyman.raven.lib.model.triton.TritonRequest;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.Marker;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class QrConsumerProcess implements CoproProcessor.ConsumerProcess<QrInputEntity, QrOutputEntity> {
    public static final String TRITON_REQUEST_ACTIVATOR = "triton.request.activator";
    public static final String QR_EXTRACTION_PROCESS = "QR_EXTRACTION";
    public static final String OKHTTP_CLIENT_TIMEOUT = "okhttp.client.timeout";
    private final Logger log;
    private final Marker aMarker;
    private final ObjectMapper mapper = new ObjectMapper();
    private static final MediaType MediaTypeJSON = MediaType
            .parse("application/json; charset=utf-8");

    public final ActionExecutionAudit action;
    public static String httpClientTimeout = new String();
    final OkHttpClient httpclient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .readTimeout(10, TimeUnit.MINUTES)
            .build();


    public QrConsumerProcess(Logger log, Marker aMarker, ActionExecutionAudit action) {
        this.log = log;
        this.aMarker = aMarker;
        this.action = action;
        this.httpClientTimeout = action.getContext().get(OKHTTP_CLIENT_TIMEOUT);
    }

    //7. overwrite the method process in coproprocessor, write copro api logic inside this method
    @Override
    public List<QrOutputEntity> process(URL endpoint, QrInputEntity entity) throws Exception {
        log.info("copro consumer process started");
        List<QrOutputEntity> qrOutputEntities = new ArrayList<>();

        String filePath = entity.getFilePath();
        Long rootPipelineId = entity.getRootPipelineId();
        Long actionId = action.getActionId();


        ObjectMapper objectMapper = new ObjectMapper();

        //payload
        QrExtractionData qrExtractionData = new QrExtractionData();
        qrExtractionData.setRootPipelineId(rootPipelineId);
        qrExtractionData.setProcess(QR_EXTRACTION_PROCESS);
        qrExtractionData.setInputFilePath(filePath);
        qrExtractionData.setActionId(actionId);
        String jsonInputRequest = objectMapper.writeValueAsString(qrExtractionData);


        TritonRequest requestBody = new TritonRequest();
        requestBody.setName("QR START");
        requestBody.setShape(List.of(1, 1));
        requestBody.setDatatype("BYTES");
        requestBody.setData(Collections.singletonList(jsonInputRequest));


        TritonInputRequest tritonInputRequest = new TritonInputRequest();
        tritonInputRequest.setInputs(Collections.singletonList(requestBody));

        String jsonRequest = objectMapper.writeValueAsString(tritonInputRequest);
        String tritonRequestActivator = action.getContext().get(TRITON_REQUEST_ACTIVATOR);
        if (log.isInfoEnabled()) {
            log.info("input object node in the consumer process  inputFilePath {}", filePath);
        }

        if (log.isInfoEnabled()) {
            log.info("input object node in the consumer process coproURL {}, inputFilePath {}", endpoint, filePath);
        }


        if (Objects.equals("false", tritonRequestActivator)) {
            Request request = new Request.Builder().url(endpoint)
                    .post(RequestBody.create(jsonInputRequest, MediaTypeJSON)).build();
            coproRequestBuilder(entity, request, objectMapper, qrOutputEntities, rootPipelineId);
        } else {
            Request request = new Request.Builder().url(endpoint)
                    .post(RequestBody.create(jsonRequest, MediaTypeJSON)).build();
            tritonRequestBuilder(entity, request, objectMapper, qrOutputEntities, rootPipelineId);
        }


        return qrOutputEntities;
    }

    private void tritonRequestBuilder(QrInputEntity entity, Request request, ObjectMapper objectMapper, List<QrOutputEntity> qrOutputEntities, Long rootPipelineId) {
        String originId = entity.getOriginId();
        Integer paperNo = entity.getPaperNo();
        Integer groupId = entity.getGroupId();
        String fileId = entity.getFileId();
        Long tenantId = entity.getTenantId();

        try (Response response = httpclient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = Objects.requireNonNull(response.body()).string();
                QrExtractionResponse modelResponse = objectMapper.readValue(responseBody, QrExtractionResponse.class);
                if (modelResponse.getOutputs() != null && !modelResponse.getOutputs().isEmpty()) {
                    modelResponse.getOutputs().forEach(o -> {
                        o.getData().forEach(qrDataItem -> {
                            extractedOutputRequest(qrOutputEntities, rootPipelineId, qrDataItem, originId, paperNo, groupId, fileId, tenantId, modelResponse.getModelName(),modelResponse.getModelVersion());
                        });
                    });
                } else {
                    qrOutputEntities.add(QrOutputEntity.builder()
                            .originId(originId)
                            .paperNo(paperNo)
                            .groupId(groupId)
                            .fileId(fileId)
                            .tenantId(tenantId)
                            .createdOn(Timestamp.valueOf(LocalDateTime.now()))
                            .rootPipelineId(rootPipelineId)
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
                        .tenantId(tenantId)
                        .fileId(fileId)
                        .createdOn(Timestamp.valueOf(LocalDateTime.now()))
                        .rootPipelineId(rootPipelineId)
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
                    .tenantId(tenantId)
                    .createdOn(Timestamp.valueOf(LocalDateTime.now()))
                    .status("FAILED")
                    .rootPipelineId(rootPipelineId)
                    .stage("QR_EXTRACTION")
                    .message(e.getMessage())
                    .build());
            log.error("Error in the copro process api hit {}", request);
            HandymanException handymanException = new HandymanException(e);
            HandymanException.insertException("Error in qr extraction action for group id - " + groupId, handymanException, this.action);
        }
    }

    private void coproRequestBuilder(QrInputEntity entity, Request request, ObjectMapper objectMapper, List<QrOutputEntity> qrOutputEntities, Long rootPipelineId) {
        String originId = entity.getOriginId();
        Integer paperNo = entity.getPaperNo();
        Integer groupId = entity.getGroupId();
        String fileId = entity.getFileId();
        Long tenantId = entity.getTenantId();

        try (Response response = httpclient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = Objects.requireNonNull(response.body()).string();
                extractedOutputRequest(qrOutputEntities, rootPipelineId, responseBody, originId, paperNo, groupId, fileId, tenantId, "","");

            } else {
                qrOutputEntities.add(QrOutputEntity.builder()
                        .originId(originId)
                        .paperNo(paperNo)
                        .groupId(groupId)
                        .fileId(fileId)
                        .tenantId(tenantId)
                        .createdOn(Timestamp.valueOf(LocalDateTime.now()))
                        .rootPipelineId(rootPipelineId)
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
                    .tenantId(tenantId)
                    .createdOn(Timestamp.valueOf(LocalDateTime.now()))
                    .status("FAILED")
                    .rootPipelineId(rootPipelineId)
                    .stage("QR_EXTRACTION")
                    .message(e.getMessage())
                    .build());
            log.error("Error in the copro process api hit {}", request);
            HandymanException handymanException = new HandymanException(e);
            HandymanException.insertException("Error in the copro process api hit for group id - " + groupId, handymanException, this.action);
        }
    }

    private void extractedOutputRequest(List<QrOutputEntity> qrOutputEntities, Long rootPipelineId, String qrDataItem, String originId, Integer paperNo, Integer groupId, String fileId, Long tenantId, String modelName,String modelVersion) {

        List<QrReader> qrLineItems = null;
        try {
            qrLineItems = mapper.readValue(qrDataItem, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        AtomicInteger atomicInteger = new AtomicInteger();
        if (!qrLineItems.isEmpty()) {
            qrLineItems.forEach(qrReader -> {
                JsonNode qrBoundingBox=mapper.valueToTree(qrReader.getBoundingBox());
                qrOutputEntities.add(QrOutputEntity.builder()
                        .angle(qrReader.getAngle())
                        .originId(originId)
                        .paperNo(paperNo)
                        .groupId(groupId)
                        .fileId(fileId)
                        .decodeType(qrReader.getDecodeType())
                        .qrFormat(qrReader.getType())
                        .rootPipelineId(rootPipelineId)
                        .qrFormatId(atomicInteger.incrementAndGet())
                        .extractedValue(qrReader.getValue())
                        .confidenceScore(qrReader.getConfidenceScore())
                        .createdOn(Timestamp.valueOf(LocalDateTime.now()))
                        .b_box(qrBoundingBox.toString())
                        .status("COMPLETED")
                        .stage("QR_EXTRACTION")
                        .message("qr extraction completed")
                        .tenantId(tenantId)
                        .modelName(modelName)
                        .modelVersion(modelVersion)
                        .build());
            });
        }else{
            qrOutputEntities.add(QrOutputEntity.builder()
                    .originId(originId)
                    .paperNo(paperNo)
                    .groupId(groupId)
                    .tenantId(tenantId)
                    .fileId(fileId)
                    .createdOn(Timestamp.valueOf(LocalDateTime.now()))
                    .rootPipelineId(rootPipelineId)
                    .status("ABSENT")
                    .stage("QR_EXTRACTION")
                    .message("qr code absent in the given file")
                    .build());
        }
    }
}



