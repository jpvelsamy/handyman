package in.handyman.raven.lib.model.pharseMatch;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.CoproProcessor;
import in.handyman.raven.lib.model.fileMergerPdf.FileMergerpdfInputEntity;
import in.handyman.raven.lib.model.fileMergerPdf.FileMergerpdfOutputEntity;
import in.handyman.raven.lib.model.triton.TritonInputRequest;
import in.handyman.raven.lib.model.triton.TritonRequest;
import in.handyman.raven.lib.model.zeroShotClassifier.ZeroShotClassifierDataItem;
import in.handyman.raven.util.ExceptionUtil;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.Marker;

import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class PhraseMatchConsumerProcess implements CoproProcessor.ConsumerProcess<PhraseMatchInputTable, PhraseMatchOutputTable> {
    private final Logger log;
    private final Marker aMarker;
    private final ObjectMapper mapper = new ObjectMapper();
    private static final MediaType MediaTypeJSON = MediaType
            .parse("application/json; charset=utf-8");
    public final ActionExecutionAudit action;
    private static final String actionName = "PHRASE_MATCH";

    final OkHttpClient httpclient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .readTimeout(10, TimeUnit.MINUTES)
            .build();

    public PhraseMatchConsumerProcess(final Logger log, final Marker aMarker, ActionExecutionAudit action) {
        this.log = log;
        this.aMarker = aMarker;
        this.action = action;
    }

    @Override
    public List<PhraseMatchOutputTable> process(URL endpoint, PhraseMatchInputTable entity) throws JsonProcessingException {
        List<PhraseMatchOutputTable> parentObj = new ArrayList<>();
        String originId = entity.getOriginId();
        String groupId = entity.getGroupId();
        String pipelineId = String.valueOf(entity.getRootPipelineId());
        String processId = String.valueOf(entity.getProcessId());
        String paperNo = String.valueOf(entity.getPaperNo());
        Long actionId = action.getActionId();
        ObjectMapper objectMapper = new ObjectMapper();



        //payload
        PharseMatchData data = new PharseMatchData();
        data.setRootPipelineId("1");
        data.setActionId(actionId);
        data.setProcess(entity.getProcessId());
        data.setOriginId(originId);
        data.setPaperNo(paperNo);
        data.setGroupId(groupId);

        String jsonInputRequest = objectMapper.writeValueAsString(data);

        PharseMatchRequest requests = new PharseMatchRequest();
        TritonRequest requestBody = new TritonRequest();
        requestBody.setName("PM START");
        requestBody.setShape(List.of(1, 1));
        requestBody.setDatatype("BYTES");
        requestBody.setData(Collections.singletonList(jsonInputRequest));


        TritonInputRequest tritonInputRequest=new TritonInputRequest();
        tritonInputRequest.setInputs(Collections.singletonList(requestBody));

        String jsonRequest = objectMapper.writeValueAsString(requests);

        String tritonRequestActivator = action.getContext().get("triton.request.activator");


        if (Objects.equals("true", tritonRequestActivator)) {
            Request request = new Request.Builder().url(endpoint)
                    .post(RequestBody.create(data.toString(), MediaTypeJSON)).build();
            coproRequestBuilder(entity,parentObj, request,objectMapper);
        } else {
            Request request = new Request.Builder().url(endpoint)
                    .post(RequestBody.create(jsonRequest, MediaTypeJSON)).build();
            tritonRequestBuilder(entity,parentObj,request);
        }

        return parentObj;
    }

    private void coproRequestBuilder(PhraseMatchInputTable entity, List<PhraseMatchOutputTable> parentObj, Request request, ObjectMapper objectMapper) {
        final Integer paperNo = Optional.ofNullable(entity.getPaperNo()).map(String::valueOf).map(Integer::parseInt).orElse(null);
        Long rootPipelineId = entity.getRootPipelineId();
        try (Response response = httpclient.newCall(request).execute()) {
            String responseBody = Objects.requireNonNull(response.body()).string();
            if (response.isSuccessful()) {

                            extractedOutputDataRequest(entity, parentObj, responseBody, objectMapper, "", "");

                } else {
                    parentObj.add(
                            PhraseMatchOutputTable
                                    .builder()
                                    .originId(Optional.ofNullable(entity.getOriginId()).map(String::valueOf).orElse(null))
                                    .groupId(Optional.ofNullable(entity.getGroupId()).map(String::valueOf).orElse(null))
                                    .status("FAILED")
                                    .paperNo(paperNo)
                                    .stage(actionName)
                                    .message(Optional.of(responseBody).map(String::valueOf).orElse(null))
                                    .rootPipelineId(rootPipelineId)
                                    .build());
                    log.info(aMarker, "The Exception occurred in Phrase match API call");
                }

        } catch (Exception exception) {
            parentObj.add(
                    PhraseMatchOutputTable
                            .builder()
                            .originId(Optional.ofNullable(entity.getOriginId()).map(String::valueOf).orElse(null))
                            .groupId(Optional.ofNullable(entity.getGroupId()).map(String::valueOf).orElse(null))
                            .status("FAILED")
                            .paperNo(paperNo)
                            .stage(actionName)
                            .message(exception.getMessage())
                            .rootPipelineId(rootPipelineId)
                            .build());
            log.error(aMarker, "Exception occurred in the phrase match paper filter action {}", ExceptionUtil.toString(exception));
            HandymanException handymanException = new HandymanException(exception);
            HandymanException.insertException("Error in inserting Intellimatch result table", handymanException, this.action);

        }
    }




    private void tritonRequestBuilder( PhraseMatchInputTable entity, List<PhraseMatchOutputTable> parentObj, Request request) {

        final Integer paperNo = Optional.ofNullable(entity.getPaperNo()).map(String::valueOf).map(Integer::parseInt).orElse(null);
        Long rootPipelineId = entity.getRootPipelineId();
        try (Response response = httpclient.newCall(request).execute()) {
            String responseBody = Objects.requireNonNull(response.body()).string();

            if (response.isSuccessful()) {
                ObjectMapper objectMapper = new ObjectMapper();
                PharseMatchResponse modelResponse = objectMapper.readValue(responseBody, PharseMatchResponse.class);
                if (modelResponse.getOutputs() != null && !modelResponse.getOutputs().isEmpty()) {
                    modelResponse.getOutputs().forEach(o -> {
                        o.getData().forEach(pharseMatchDataItem -> {
                            extractedOutputDataRequest(entity, parentObj, pharseMatchDataItem, objectMapper, "", "");
                        });
                    });
                } else {
                parentObj.add(
                        PhraseMatchOutputTable
                                .builder()
                                .originId(Optional.ofNullable(entity.getOriginId()).map(String::valueOf).orElse(null))
                                .groupId(Optional.ofNullable(entity.getGroupId()).map(String::valueOf).orElse(null))
                                .status("FAILED")
                                .paperNo(paperNo)
                                .stage(actionName)
                                .message(Optional.of(responseBody).map(String::valueOf).orElse(null))
                                .rootPipelineId(rootPipelineId)
                                .build());
                log.info(aMarker, "The Exception occurred in Phrase match API call");
            }
            }
        } catch (Exception exception) {
            parentObj.add(
                    PhraseMatchOutputTable
                            .builder()
                            .originId(Optional.ofNullable(entity.getOriginId()).map(String::valueOf).orElse(null))
                            .groupId(Optional.ofNullable(entity.getGroupId()).map(String::valueOf).orElse(null))
                            .status("FAILED")
                            .paperNo(paperNo)
                            .stage(actionName)
                            .message(exception.getMessage())
                            .rootPipelineId(rootPipelineId)
                            .build());
            log.error(aMarker, "Exception occurred in the phrase match paper filter action {}", ExceptionUtil.toString(exception));
            HandymanException handymanException = new HandymanException(exception);
            HandymanException.insertException("Error in inserting Intellimatch result table", handymanException, this.action);

        }
    }

    private static void extractedOutputDataRequest(PhraseMatchInputTable entity, List<PhraseMatchOutputTable> parentObj, String pharseMatchDataItem, ObjectMapper objectMapper, String modelName, String modelVersion) {
        String originId = entity.getOriginId();
        String groupId = entity.getGroupId();


        final Integer paperNo = Optional.ofNullable(entity.getPaperNo()).map(String::valueOf).map(Integer::parseInt).orElse(null);
        Long rootPipelineId = entity.getRootPipelineId();
        try {
            PharseMatchDataItem pharseMatchOutputData = objectMapper.readValue(pharseMatchDataItem, PharseMatchDataItem.class);

            parentObj.add(
                    PhraseMatchOutputTable
                            .builder()
                            .originId(Optional.ofNullable(originId).map(String::valueOf).orElse(null))
                            .groupId(Optional.ofNullable(groupId).map(String::valueOf).orElse(null))
                            .paperNo(paperNo)
                            .status("COMPLETED")
                            .stage(actionName)
                            .message("Completed API call zero shot classifier")
                            .rootPipelineId(rootPipelineId)
                            .modelName(modelName)
                            .truthEntity(pharseMatchOutputData.getTruthEntity())
                            .isKeyPresent(pharseMatchOutputData.getIsKeyPresent())
                            .entity(pharseMatchOutputData.getEntity())
                            .modelVersion(modelVersion)
                            .build());

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

