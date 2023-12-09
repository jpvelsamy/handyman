package in.handyman.raven.lib.model.paperitemizer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.CoproProcessor;
import in.handyman.raven.lib.model.triton.TritonInputRequest;
import in.handyman.raven.lib.model.triton.TritonRequest;
import okhttp3.*;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.Marker;

import java.io.File;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class PaperItemizerConsumerProcess implements CoproProcessor.ConsumerProcess<PaperItemizerInputTable, PaperItemizerOutputTable> {

    public static final String TRITON_REQUEST_ACTIVATOR = "triton.request.activator";
    private final Logger log;
    private final Marker aMarker;
    private final ObjectMapper mapper = new ObjectMapper();
    private static final MediaType mediaTypeJson = MediaType
            .parse("application/json; charset=utf-8");
    private final String outputDir;

    public final ActionExecutionAudit action;
    final OkHttpClient httpclient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .readTimeout(10, TimeUnit.MINUTES)
            .build();

    public PaperItemizerConsumerProcess(Logger log, Marker aMarker, String outputDir, ActionExecutionAudit action) {
        this.log = log;
        this.aMarker = aMarker;
        this.outputDir = outputDir;
        this.action = action;
    }

    @Override
    public List<PaperItemizerOutputTable> process(URL endpoint, PaperItemizerInputTable entity) throws Exception {
        log.info(aMarker, "coproProcessor consumer process started with endpoint {} and entity {}", endpoint, entity);
        List<PaperItemizerOutputTable> parentObj = new ArrayList<>();
        String inputFilePath = entity.getFilePath();
        Long rootPipelineId = entity.getRootPipelineId();
        final String paperItemizerProcessName = "PAPER_ITEMIZER";
        Long actionId = action.getActionId();
        ObjectMapper objectMapper = new ObjectMapper();
//payload
        PaperItemizerData paperitemizerData = new PaperItemizerData();
        paperitemizerData.setRootPipelineId(rootPipelineId);
        paperitemizerData.setProcess(paperItemizerProcessName);
        paperitemizerData.setInputFilePath(inputFilePath);
        paperitemizerData.setOutputDir(outputDir);
        paperitemizerData.setActionId(actionId);
        String jsonInputRequest = objectMapper.writeValueAsString(paperitemizerData);

        TritonRequest requestBody = new TritonRequest();
        requestBody.setName("PAPER ITERATOR START");
        requestBody.setShape(List.of(1, 1));
        requestBody.setDatatype("BYTES");
        requestBody.setData(Collections.singletonList(jsonInputRequest));

        TritonInputRequest tritonInputRequest = new TritonInputRequest();
        tritonInputRequest.setInputs(Collections.singletonList(requestBody));


        String jsonRequest = objectMapper.writeValueAsString(tritonInputRequest);

        String tritonRequestActivator = action.getContext().get(TRITON_REQUEST_ACTIVATOR);

        if (Objects.equals("false", tritonRequestActivator)) {
            Request request = new Request.Builder().url(endpoint)
                    .post(RequestBody.create(jsonInputRequest, mediaTypeJson)).build();
            coproRequestBuider(entity, request, objectMapper, parentObj);
        } else {
            Request request = new Request.Builder().url(endpoint)
                    .post(RequestBody.create(jsonRequest, mediaTypeJson)).build();
            tritonRequestBuilder(entity, request, objectMapper, parentObj);
        }


        if (log.isInfoEnabled()) {
            log.info(aMarker, "Request has been build with the parameters \n URI : {}, with inputFilePath {} and outputDir {}", endpoint, inputFilePath, outputDir);
        }


        log.info(aMarker, "coproProcessor consumer process with output entity {}", parentObj);
        return parentObj;
    }

    private void coproRequestBuider(PaperItemizerInputTable entity, Request request, ObjectMapper objectMapper, List<PaperItemizerOutputTable> parentObj) {
        String originId = entity.getOriginId();
        Integer groupId = entity.getGroupId();
        String templateId = entity.getTemplateId();
        Long tenantId = entity.getTenantId();
        Long processId = entity.getProcessId();
        try (Response response = httpclient.newCall(request).execute()) {

            if (log.isInfoEnabled()) {
                log.info(aMarker, "coproProcessor consumer process response with status{}, and message as {}, ", response.isSuccessful(), response.message());
            }
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                extractedOutputRequest(entity, objectMapper, parentObj, "", "", responseBody);

            } else {
                parentObj.add(
                        PaperItemizerOutputTable
                                .builder()
                                .originId(Optional.ofNullable(originId).map(String::valueOf).orElse(null))
                                .groupId(groupId)
                                .processId(processId)
                                .templateId(templateId)
                                .tenantId(tenantId)
                                .status("FAILED")
                                .stage("paperItemizer")
                                .message(response.message())
                                .createdOn(Timestamp.valueOf(LocalDateTime.now()))
                                .rootPipelineId(entity.getRootPipelineId())
                                .build());
                log.error(aMarker, "Error in response {}", response.message());
            }

        } catch (Exception exception) {
            parentObj.add(
                    PaperItemizerOutputTable
                            .builder()
                            .originId(Optional.ofNullable(originId).map(String::valueOf).orElse(null))
                            .groupId(groupId)
                            .processId(processId)
                            .templateId(templateId)
                            .tenantId(tenantId)
                            .status("FAILED")
                            .stage("paperItemizer")
                            .message(exception.getMessage())
                            .createdOn(Timestamp.valueOf(LocalDateTime.now()))
                            .rootPipelineId(entity.getRootPipelineId())
                            .build());
            HandymanException handymanException = new HandymanException(exception);
            HandymanException.insertException("Paper Itemizer  consumer failed for originId " + originId, handymanException, this.action);
            log.error(aMarker, "The Exception occurred in request {}", request, exception);
        }
    }


    private void tritonRequestBuilder(PaperItemizerInputTable entity, Request request, ObjectMapper objectMapper, List<PaperItemizerOutputTable> parentObj) {
        String originId = entity.getOriginId();
        Integer groupId = entity.getGroupId();
        String templateId = entity.getTemplateId();
        Long tenantId = entity.getTenantId();
        Long processId = entity.getProcessId();


        try (Response response = httpclient.newCall(request).execute()) {


            if (log.isInfoEnabled()) {
                log.info(aMarker, "coproProcessor consumer process response with status{}, and message as {}, ", response.isSuccessful(), response.message());
            }
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                PaperItemizerResponse paperItemizerResponse = objectMapper.readValue(responseBody, PaperItemizerResponse.class);
                if (paperItemizerResponse.getOutputs() != null && !paperItemizerResponse.getOutputs().isEmpty()) {
                    paperItemizerResponse.getOutputs().forEach(o -> o.getData().forEach(paperItemizerDataItem ->
                            {
                                extractedOutputRequest(entity, objectMapper, parentObj, paperItemizerResponse.getModelName(), paperItemizerResponse.getModelVersion(), paperItemizerDataItem);

                            }
                    ));
                }

            } else {
                parentObj.add(
                        PaperItemizerOutputTable
                                .builder()
                                .originId(Optional.ofNullable(originId).map(String::valueOf).orElse(null))
                                .groupId(groupId)
                                .processId(processId)
                                .templateId(templateId)
                                .tenantId(tenantId)
                                .status("FAILED")
                                .stage("paperItemizer")
                                .message(response.message())
                                .createdOn(Timestamp.valueOf(LocalDateTime.now()))
                                .rootPipelineId(entity.getRootPipelineId())
                                .build());
                log.error(aMarker, "Error in response {}", response.message());
            }

        } catch (Exception exception) {
            parentObj.add(
                    PaperItemizerOutputTable
                            .builder()
                            .originId(Optional.ofNullable(originId).map(String::valueOf).orElse(null))
                            .groupId(groupId)
                            .processId(processId)
                            .templateId(templateId)
                            .tenantId(tenantId)
                            .status("FAILED")
                            .stage("paperItemizer")
                            .message(exception.getMessage())
                            .createdOn(Timestamp.valueOf(LocalDateTime.now()))
                            .rootPipelineId(entity.getRootPipelineId())
                            .build());
            HandymanException handymanException = new HandymanException(exception);
            HandymanException.insertException("Paper Itemizer  consumer failed for originId " + originId, handymanException, this.action);
            log.error(aMarker, "The Exception occurred in request {}", request, exception);
        }
    }

    private void extractedOutputRequest(PaperItemizerInputTable entity, ObjectMapper objectMapper, List<PaperItemizerOutputTable> parentObj, String modelName, String modelVersion, String paperItemizerDataItem) {
        String originId = entity.getOriginId();
        Integer groupId = entity.getGroupId();
        String templateId = entity.getTemplateId();
        Long tenantId = entity.getTenantId();
        Long processId = entity.getProcessId();
        try {

            PaperItemizerDataItem paperItemizeOutputData = objectMapper.readValue(paperItemizerDataItem, PaperItemizerDataItem.class);
            paperItemizeOutputData.getItemizedPapers().forEach(itemizerPapers -> {
                Long paperNo = getPaperNobyFileName(itemizerPapers);
                parentObj.add(
                        PaperItemizerOutputTable
                                .builder()
                                .processedFilePath(itemizerPapers)
                                .originId(Optional.ofNullable(originId).map(String::valueOf).orElse(null))
                                .groupId(groupId)
                                .templateId(templateId)
                                .tenantId(tenantId)
                                .processId(processId)
                                .paperNo(paperNo)
                                .status("COMPLETED")
                                .stage("paperItemizer")
                                .message("Paper Itemizer macro completed")
                                .createdOn(Timestamp.valueOf(LocalDateTime.now()))
                                .rootPipelineId(entity.getRootPipelineId())
                                .modelName(modelName)
                                .modelVersion(modelVersion)
                                .build());
            });

        } catch (JsonProcessingException e) {
            parentObj.add(
                    PaperItemizerOutputTable
                            .builder()
                            .originId(Optional.ofNullable(originId).map(String::valueOf).orElse(null))
                            .groupId(groupId)
                            .processId(processId)
                            .templateId(templateId)
                            .tenantId(tenantId)
                            .status("FAILED")
                            .stage("paperItemizer")
                            .message(e.getMessage())
                            .createdOn(Timestamp.valueOf(LocalDateTime.now()))
                            .rootPipelineId(entity.getRootPipelineId())
                            .build());
            HandymanException handymanException = new HandymanException(e);
            HandymanException.insertException("Paper Itemizer  consumer failed for originId " + originId, handymanException, this.action);
            log.error(aMarker, "The Exception occurred in request {}", e.toString());
        }
    }


    public static Long getPaperNobyFileName(String filePath) {
        Long extractedNumber = null;
        File file = new File(filePath);

        String fileNameStr = FilenameUtils.removeExtension(file.getName());

        String[] parts = fileNameStr.split("_");

        // Check if there are at least two parts (0 and 1 after the first underscore)
        if (parts.length >= 1) {
            // Extract the second part (index 1 in the array after splitting)
            String number = parts[parts.length - 1];

            // Convert the extracted string to an integer if needed
            extractedNumber = Long.parseLong(number);

            // Print the extracted number
            return extractedNumber + 1;
        }

        return extractedNumber;
    }

}
