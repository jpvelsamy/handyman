package in.handyman.raven.lib;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.access.ResourceAccess;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.CoproStop;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.*;
import org.jdbi.v3.core.Jdbi;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
    actionName = "CoproStop"
)
public class CoproStopAction implements IActionExecution {
  private final ActionExecutionAudit action;

  private final Logger log;

  private final CoproStop coproStop;

  private final Marker aMarker;

  private final ObjectMapper mapper = new ObjectMapper();
  private final String URI;
  private static final MediaType MediaTypeJSON = MediaType
          .parse("application/json; charset=utf-8");

  public CoproStopAction(final ActionExecutionAudit action, final Logger log,
      final Object coproStop) {
    this.coproStop = (CoproStop) coproStop;
    this.action = action;
    this.log = log;
    this.aMarker = MarkerFactory.getMarker(" CoproStop:"+this.coproStop.getName());
    this.URI = action.getContext().get("copro.admin.stop.server.url");

  }

  @Override
  public void execute() throws Exception {
    final Jdbi jdbi = ResourceAccess.rdbmsJDBIConn(coproStop.getResourceConn());
    log.info(aMarker, "<-------copro admin API call for {} has been started------->", coproStop.getName());
    final OkHttpClient httpclient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .readTimeout(10, TimeUnit.MINUTES)
            .build();
    final ObjectNode objectNode = mapper.createObjectNode();
    objectNode.put("processStartCommand", coproStop.getCommand());
    objectNode.put("exportCommand","CORPO_ZERO_SHOT_CLASSIFIER_ENABLE");
    log.info(aMarker, " Input variables id : {}", action.getActionId());
    Request request = new Request.Builder().url(URI)
            .post(RequestBody.create(objectNode.toString(), MediaTypeJSON)).build();
    log.debug(aMarker, "Request has been build with the parameters \n URI : {} \n command : {} ", URI, coproStop.getCommand());
    String name = coproStop.getName() + "_response";
    log.debug(aMarker, "The Request Details: {} ", request);
    try (Response response = httpclient.newCall(request).execute()) {
      String responseBody = response.body().string();
      if (response.isSuccessful()) {
        JSONObject responseObj = new JSONObject(responseBody);
        CoproStopAction.coproStopActionEntity coproStopActionEntity = CoproStopAction.coproStopActionEntity
                .builder()
                .processId(Optional.ofNullable(coproStop.getProcessID()).map(Integer::valueOf).orElse(null))
                .actionId(Optional.ofNullable(action.getActionId()).map(Long::valueOf).orElse(null))
                .rootPipelineId(Optional.ofNullable(action.getRootPipelineId()).map(Long::valueOf).orElse(null))
                .coproActionName(Optional.ofNullable(coproStop.getModuleName()).map(String::valueOf).orElse(null))
                .command(Optional.ofNullable(coproStop.getCommand()).map(String::valueOf).orElse(null))
                .coproProcessId(Optional.ofNullable(responseObj.get("pid")).map(String::valueOf).orElse(null))
//                        .cpu(Optional.ofNullable(responseObj.get("cpu")).map(String::valueOf).orElse(null))
//                        .gpu(Optional.ofNullable(responseObj.get("gpu")).map(String::valueOf).orElse(null))
                .ram(Optional.ofNullable(responseObj.get("ram")).map(String::valueOf).orElse(null))
                .gpuRam(Optional.ofNullable(responseObj.get("gpuRam")).map(String::valueOf).orElse(null))
                .build();
        jdbi.useTransaction(handle -> handle.createUpdate("INSERT INTO info.resource_utilization_details (process_id, action_id, root_pipeline_id, copro_action_name, copro_pid, created_on, command_exec, cpu, gpu, ram, gpu_ram)" +
                        " select :processId,:actionId,:rootPipelineId,:coproActionName,:coproProcessId,now(),:command,:cpu,:gpu,:ram,:gpuRam;")
                .bindBean(coproStopActionEntity)
                .execute());
        log.info(aMarker, "The Successful Response for {} --> {}", name, responseBody);
      } else {
        log.info(aMarker, "The Failure Response {} --> {}", name, responseBody);
      }
      log.info(aMarker, "<--------copro admin API call for {} has been completed------->" , coproStop.getName());
    } catch (Exception e) {
      action.getContext().put(name.concat(".error"), "true");
      action.getContext().put(name.concat(".errorMessage"), e.getMessage());
      log.info(aMarker, "The Exception occurred ", e);
      throw new HandymanException("Failed to execute", e);
    }
  }

  @Override
  public boolean executeIf() throws Exception {
    return coproStop.getCondition();
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class coproStopActionEntity {
    private Integer processId;
    private Long actionId;
    private Long rootPipelineId;
    private String command;
    private String coproActionName;
    private String coproProcessId;
    private String cpu;
    private String gpu;
    private String ram;
    private String gpuRam;

  }
}
