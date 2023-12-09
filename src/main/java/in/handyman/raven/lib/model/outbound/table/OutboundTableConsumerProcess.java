package in.handyman.raven.lib.model.outbound.table;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.CoproProcessor;
import in.handyman.raven.lib.OutboundTableResponseAction;
import in.handyman.raven.lib.alchemy.common.AlchemyApiPayload;
import in.handyman.raven.lib.model.outbound.AlchemyKvpInputEntity;
import in.handyman.raven.lib.model.outbound.AlchemyKvpOutputEntity;
import in.handyman.raven.util.ExceptionUtil;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.Marker;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OutboundTableConsumerProcess implements CoproProcessor.ConsumerProcess<AlchemyTableInputEntity, AlchemyTableOutputEntity> {

    private final Logger log;
    private final Marker aMarker;
    private final ObjectMapper mapper = new ObjectMapper();
    private static final MediaType MediaTypeJSON = MediaType
            .parse("application/json; charset=utf-8");

    public final ActionExecutionAudit action;
    private final OutboundTableResponseAction aaction;
    private final OkHttpClient httpclient;
    private final String authToken;
    private final String STAGE_NAME = "PRODUCT_OUTBOUND";

    public OutboundTableConsumerProcess(Logger log, Marker aMarker, ActionExecutionAudit action, OutboundTableResponseAction aaction) {
        this.log = log;
        this.aMarker = aMarker;
        this.action = action;
        this.aaction = aaction;
        this.authToken = action.getContext().get("alchemyAuth.token");
        this.httpclient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.MINUTES)
                .writeTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES)
                .build();
    }

    @Override
    public List<AlchemyTableOutputEntity> process(URL endpoint, AlchemyTableInputEntity entity) throws Exception {

        log.info(aMarker, "Alchemy cosumer process Started for origin id {}", entity.getAlchemyOriginId());

        List<AlchemyTableOutputEntity> parentObj = new ArrayList<>();
        String originId = entity.getAlchemyOriginId();
        Long rootPipelineId = entity.getRootPipelineId();
        Long pageNo = entity.getPaperNo();


        log.info(aMarker, "Request object  endpoint {} ", endpoint);

        Long tenantId = entity.getTenantId();
        String endPointFinalUrl = endpoint + "/" + originId + "/" + pageNo + "?tenantId=" + tenantId;

        Request request = new Request.Builder()
                .url(endPointFinalUrl)
                .addHeader("accept", "*/*")
                .addHeader("Authorization", "Bearer " + authToken)
                .build();

        try (Response response = httpclient.newCall(request).execute()) {

            Timestamp createdOn = Timestamp.valueOf(LocalDateTime.now());
            if (response.isSuccessful()) {
                AlchemyApiPayload alchemyApiPayload = mapper.readValue(response.body().string(), AlchemyApiPayload.class);

                if (!alchemyApiPayload.getPayload().isEmpty() && !alchemyApiPayload.getPayload().isNull() && alchemyApiPayload.isSuccess()) {

                    parentObj.add(AlchemyTableOutputEntity
                            .builder()
                            .processId(entity.getProcessId())
                            .tenantId(tenantId)
                            .groupId(entity.getGroupId())
                             .paperNo(entity.getPaperNo())
                            .tableResponse(String.valueOf(alchemyApiPayload.getPayload()))
                            .alchemyOriginId(entity.getAlchemyOriginId())
                            .pipelineOriginId(entity.getPipelineOriginId())
                            .rootPipelineId(rootPipelineId)
                            .stage("PRODUCT_OUBOUND").status("COMPLETED").message("alchemy kvp response completed for origin_id - " + entity.getAlchemyOriginId())
                            .build());
                }
            } else {
                parentObj.add(AlchemyTableOutputEntity
                        .builder()
                        .processId(entity.getProcessId())
                        .tenantId(tenantId)
                        .groupId(entity.getGroupId())
                        .alchemyOriginId(entity.getAlchemyOriginId())
                        .pipelineOriginId(entity.getPipelineOriginId())
                        .rootPipelineId(rootPipelineId)
                        .stage("PRODUCT_OUBOUND").status("FAILED").message("alchemy kvp response failed for origin_id - " + entity.getAlchemyOriginId())
                        .build());
            }


        } catch (Exception e) {
            log.error(aMarker, "The Exception occurred in getting response {}", ExceptionUtil.toString(e));
            HandymanException handymanException = new HandymanException(e);
            HandymanException.insertException("Alchemy kvp api consumer failed for origin_id - " + originId,
                    handymanException,
                    this.action);
            log.error(aMarker, "The Exception occurred in getting response {}", ExceptionUtil.toString(e));
        }


        return parentObj;
    }
}


