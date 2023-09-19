package in.handyman.raven.lib.model.TextExtraction;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.CoproProcessor;
import in.handyman.raven.lib.model.triton.TritonInputRequest;
import in.handyman.raven.lib.model.triton.TritonRequest;
import in.handyman.raven.util.ExceptionUtil;
import okhttp3.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.Marker;

import java.io.File;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static in.handyman.raven.lib.HwDetectionAction.httpClientTimeout;

public class DataExtractionConsumerProcess implements CoproProcessor.ConsumerProcess<DataExtractionInputTable, DataExtractionOutputTable> {
    private final Logger log;
    private final Marker aMarker;
    private final ObjectMapper mapper = new ObjectMapper();
    private static final MediaType MediaTypeJSON = MediaType.parse("application/json; charset=utf-8");

    public final ActionExecutionAudit action;
    final OkHttpClient httpclient = new OkHttpClient.Builder()
            .connectTimeout(Long.parseLong(httpClientTimeout), TimeUnit.MINUTES)
            .writeTimeout(Long.parseLong(httpClientTimeout), TimeUnit.MINUTES)
            .readTimeout(Long.parseLong(httpClientTimeout), TimeUnit.MINUTES)
            .build();
    public DataExtractionConsumerProcess(final Logger log, final Marker aMarker, ActionExecutionAudit action) {
        this.log = log;
        this.aMarker = aMarker;
        this.action = action;
    }


    @Override
    public List<DataExtractionOutputTable> process(URL endpoint, DataExtractionInputTable entity) throws JsonProcessingException {
        List<DataExtractionOutputTable> parentObj = new ArrayList<>();
        final ObjectNode objectNode = mapper.createObjectNode();
        String inputFilePath = entity.getFilePath();
        final String DATA_EXTRACTION_PROCESS="DATA_EXTRACTION";
        Long rootpipelineId=entity.getRootPipelineId();
        Long actionId=action.getActionId();
        String process = String.valueOf(entity.process);
        String filePath = String.valueOf(entity.getFilePath());
        ObjectMapper objectMapper = new ObjectMapper();


        //payload
        DataExtractionData DataExtractiondata = new DataExtractionData();
        DataExtractiondata.setRootPipelineId(Long.valueOf(rootpipelineId));
        DataExtractiondata.setActionId(actionId);
        DataExtractiondata.setProcess(process);
        DataExtractiondata.setInputFilePath(filePath);
        String jsonInputRequest = objectMapper.writeValueAsString(DataExtractiondata);



        DataExtractionRequest requests = new  DataExtractionRequest();
        TritonRequest requestBody = new TritonRequest();
        requestBody.setName("TEXT EXTRACTOR START");
        requestBody.setShape(List.of(1, 1));
        requestBody.setDatatype("BYTES");
        requestBody.setData(Collections.singletonList(jsonInputRequest));

        // requestBody.setData(Collections.singletonList(jsonNodeRequest));

        //  requestBody.setData(Collections.singletonList(DataExtractiondata));

        TritonInputRequest tritonInputRequest=new TritonInputRequest();
        tritonInputRequest.setInputs(Collections.singletonList(requestBody));

        String jsonRequest = objectMapper.writeValueAsString(requests);

        Request request = new Request.Builder().url(endpoint).post(RequestBody.create(jsonRequest.toString(), MediaTypeJSON)).build();

        if(log.isInfoEnabled()) {
            log.info(aMarker, "Request has been build with the parameters \n URI : {}, with inputFilePath {} ", endpoint, inputFilePath);
        }

        String originId = entity.getOriginId();
        Integer groupId = entity.getGroupId();

        try (Response response = httpclient.newCall(request).execute()) {
            String responseBody = Objects.requireNonNull(response.body()).string();
            if (response.isSuccessful()) {
                ObjectMapper objectMappers = new ObjectMapper();
                DataExtractionResponse modelResponse = objectMappers.readValue(responseBody, DataExtractionResponse.class);
                if (modelResponse.getOutputs() != null && !modelResponse.getOutputs().isEmpty()) {
                    modelResponse.getOutputs().forEach(o -> {
                        o.getData().forEach(DataExtractionDataItem -> {

                            JSONObject parentResponseObject = new JSONObject(responseBody);
                            final String contentString= Optional.ofNullable(parentResponseObject.get("pageContent")).map(String::valueOf).orElse(null);
                            final String flag=(!Objects.isNull(contentString) && contentString.length() > 5 ) ? "no" : "yes";

                            parentObj.add(DataExtractionOutputTable.builder()
                                    .filePath(new File(entity.getFilePath()).getAbsolutePath())
                                    .extractedText(contentString)
                                    .originId(Optional.ofNullable(originId).map(String::valueOf).orElse(null))
                                    .groupId(groupId)
                                    .fileName(Optional.ofNullable(parentResponseObject.get("fileName")).map(String::valueOf).orElse(null))
                                    .paperNo(entity.getPaperNo())
                                    .status("COMPLETED")
                                    .stage("DATA_EXTRACTION")
                                    .message("Data extraction macro completed")
                                    .createdOn(Timestamp.valueOf(LocalDateTime.now()))
                                    .tenantId(entity.getTenantId())
                                    .templateId(entity.getTemplateId())
                                    .processId(entity.getProcessId())
                                    .isBlankPage(flag)
                                    .templateName(entity.getTemplateName())
                                    .rootPipelineId(entity.getRootPipelineId())
                                    .modelName(modelResponse.getModelName())
                                    .modelVersion(modelResponse.getModelVersion())
                                    .build());
                        });
                    });
                }
            }else{
                parentObj.add(DataExtractionOutputTable.builder()
                        .originId(Optional.ofNullable(originId).map(String::valueOf).orElse(null))
                        .groupId(groupId)
                        .paperNo(entity.getPaperNo())
                        .status("FAILED")
                        .stage("DATA_EXTRACTION")
                        .tenantId(entity.getTenantId())
                        .templateId(entity.getTemplateId())
                        .processId(entity.getProcessId())
                        .message(response.message())
                        .createdOn(Timestamp.valueOf(LocalDateTime.now()))
                        .rootPipelineId(entity.getRootPipelineId())
                        .templateName(entity.getTemplateName())
                        .build());
                log.error(aMarker, "The Exception occurred ");
            }
        } catch (Exception e) {
            parentObj.add(DataExtractionOutputTable.builder()
                    .originId(Optional.ofNullable(originId).map(String::valueOf).orElse(null))
                    .groupId(groupId)
                    .paperNo(entity.getPaperNo())
                    .status("FAILED")
                    .stage("DATA_EXTRACTION")
                    .tenantId(entity.getTenantId())
                    .templateId(entity.getTemplateId())
                    .processId(entity.getProcessId())
                    .createdOn(Timestamp.valueOf(LocalDateTime.now()))
                    .message(ExceptionUtil.toString(e))
                    .rootPipelineId(entity.getRootPipelineId())
                    .templateName(entity.getTemplateName())
                    .build());

            log.error(aMarker, "The Exception occurred ", e);
            HandymanException handymanException = new HandymanException(e);
            HandymanException.insertException("test extraction consumer failed for batch/group "+ groupId, handymanException, this.action);

        }
        return parentObj;
    }

}