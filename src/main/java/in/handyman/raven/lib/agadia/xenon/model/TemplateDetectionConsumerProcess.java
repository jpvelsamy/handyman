package in.handyman.raven.lib.agadia.xenon.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lambda.doa.audit.ExecutionStatus;
import in.handyman.raven.lib.CoproProcessor;
import in.handyman.raven.lib.TemplateDetectionAction;
import in.handyman.raven.util.ExceptionUtil;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.Marker;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TemplateDetectionConsumerProcess implements CoproProcessor.ConsumerProcess<TemplateDetectionInputTable, TemplateDetectionOutputTable> {

    private final Logger log;
    private final Marker aMarker;
    private final ObjectMapper mapper = new ObjectMapper();
    private static final MediaType MediaTypeJSON = MediaType
            .parse("application/json; charset=utf-8");

    public final ActionExecutionAudit action;
    private final OkHttpClient httpclient;
    private final TemplateDetectionAction aAction;
    private final String STAGE_NAME = "TEMPLATE_DETECTION";

    private final int timeOut;

    public TemplateDetectionConsumerProcess(final Logger log, final Marker aMarker, ActionExecutionAudit action, TemplateDetectionAction aAction) {
        this.log = log;
        this.aMarker = aMarker;
        this.action = action;
        this.aAction = aAction;
        this.timeOut = aAction.getTimeOut();
        this.httpclient = new OkHttpClient.Builder()
                .connectTimeout(this.timeOut, TimeUnit.MINUTES)

                .writeTimeout(this.timeOut, TimeUnit.MINUTES)
                .readTimeout(this.timeOut, TimeUnit.MINUTES)
                .build();
    }


    @Override
    public List<TemplateDetectionOutputTable> process(URL endpoint, TemplateDetectionInputTable entity) throws Exception {

        List<TemplateDetectionOutputTable> outputObjectList = new ArrayList<>();
        List<String> attributes = entity.getQuestions();
        String inputFilePath = entity.getFilePath();
        TemplateDetectionRequest templateDetectionRequest = new TemplateDetectionRequest();
        templateDetectionRequest.setAttributes(attributes);
        templateDetectionRequest.setInputFilePath(inputFilePath);

        log.info(aMarker, "Input request object for template detection filePath is {} and questions size {}", inputFilePath, attributes.size());


        final String body = mapper.writeValueAsString(templateDetectionRequest);
        RequestBody requestBody = RequestBody.create(body, MediaTypeJSON);
        Request request = new Request.Builder()
                .url(endpoint)
                .post(requestBody)
                .build();

        log.info(aMarker, "Request object  endpoint {} and requestbody {}", endpoint, body);

        Long processId = entity.getProcessId();
        String templateId = entity.getTemplateId();
        String tenantId = entity.getTenantId();
        String originId = entity.getOriginId();
        Integer paperNo = entity.getPaperNo();
        Integer groupId = entity.getGroupId();
        Long rootPipelineId = entity.getRootPipelineId();

        try (Response response = httpclient.newCall(request).execute()) {

            Timestamp createdOn = Timestamp.valueOf(LocalDateTime.now());
            if (response.isSuccessful()) {
                TemplateDetectionResponse templateDetectionResponse = mapper.readValue(response.body().string(), new TypeReference<>() {
                });
                Integer imageWidth = templateDetectionResponse.getImageWidth();
                Integer imageHeight = templateDetectionResponse.getImageHeight();
                Integer imageDPI = templateDetectionResponse.getImageDPI();
                String extractedImageUnit = templateDetectionResponse.getExtractedImageUnit();

                if (!templateDetectionResponse.getAttributes().isEmpty()) {
                    templateDetectionResponse.getAttributes().forEach(attribute -> {

                        String string = attribute.getBboxes().toString();
                        String question = attribute.getQuestion();
                        Float scores = attribute.getScores();
                        String predictedAttributionValue = attribute.getPredictedAttributionValue();
                        outputObjectList.add(
                                TemplateDetectionOutputTable.builder()
                                        .processId(processId)
                                        .tenantId(tenantId)
                                        .templateId(templateId)
                                        .predictedAttributionValue(predictedAttributionValue)
                                        .question(question)
                                        .scores(scores)
                                        .bboxes(string)
                                        .imageWidth(imageWidth)
                                        .imageHeight(imageHeight)
                                        .imageDPI(imageDPI)
                                        .extractedImageUnit(extractedImageUnit)
                                        .rootPipelineId(rootPipelineId)
                                        .groupId(groupId)
                                        .originId(originId)
                                        .paperNo(paperNo)
                                        .createdOn(createdOn)
                                        .status(ExecutionStatus.COMPLETED.toString())
                                        .stage(STAGE_NAME)
                                        .message("Template detection completed for group_id " + groupId + "and origin_id  " + originId)
                                        .processedFilePath(inputFilePath).build()
                        );
                    });
                } else {
                    outputObjectList.add(
                            TemplateDetectionOutputTable.builder()
                                    .processId(processId)
                                    .tenantId(tenantId)
                                    .templateId(templateId)
                                    .imageWidth(imageWidth)
                                    .imageHeight(imageHeight)
                                    .imageDPI(imageDPI)
                                    .extractedImageUnit(extractedImageUnit)
                                    .rootPipelineId(rootPipelineId)
                                    .groupId(groupId)
                                    .originId(originId)
                                    .paperNo(paperNo)
                                    .createdOn(createdOn)
                                    .status(ExecutionStatus.COMPLETED.toString())
                                    .stage(STAGE_NAME)
                                    .message("Template detection completed and response is empty for group_id " + groupId + "and origin_id  " + originId)
                                    .processedFilePath(inputFilePath)
                                    .build()
                    );
                }

            } else {
                outputObjectList.add(TemplateDetectionOutputTable.builder()
                        .processId(processId)
                        .tenantId(tenantId)
                        .templateId(templateId)
                        .rootPipelineId(rootPipelineId)
                        .groupId(groupId)
                        .originId(originId)
                        .paperNo(paperNo)
                        .createdOn(createdOn)
                        .status(ExecutionStatus.FAILED.toString())
                        .stage(STAGE_NAME)
                        .message("Template detection failed for group_id " + groupId + "and origin_id  " + originId + " and Exception")
                        .processedFilePath(inputFilePath)
                        .build()
                );
            }
        } catch (Exception e) {
            log.error(aMarker, "The Exception occurred in getting response {}", ExceptionUtil.toString(e));
            HandymanException handymanException = new HandymanException(e);
            HandymanException.insertException("Template detection consumer failed for batch/group " + groupId,
                    handymanException,
                    this.action);
            log.error(aMarker, "The Exception occurred in getting response {}", ExceptionUtil.toString(e));
        }


        return outputObjectList;
    }
}
