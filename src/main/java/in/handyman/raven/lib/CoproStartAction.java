package in.handyman.raven.lib;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.access.ResourceAccess;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.CoproStart;
import in.handyman.raven.util.ExceptionUtil;
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

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "CoproStart"
)
public class CoproStartAction implements IActionExecution {
  private final ActionExecutionAudit action;

  private final Logger log;

  private final CoproStart coproStart;

  private final Marker aMarker;

  private final ObjectMapper mapper = new ObjectMapper();
  //    private final String URI;
  private static final MediaType MediaTypeJSON = MediaType
          .parse("application/json; charset=utf-8");

  public CoproStartAction(final ActionExecutionAudit action, final Logger log,
                          final Object coproStart) {
    this.coproStart = (CoproStart) coproStart;
    this.action = action;
    this.log = log;
    this.aMarker = MarkerFactory.getMarker(" CoproStart:" + this.coproStart.getName());
//        this.URI = action.getContext().get("copro.admin.start.server.url");

  }

  @Override
  public void execute() throws Exception {
    final Jdbi jdbi = ResourceAccess.rdbmsJDBIConn(coproStart.getResourceConn());
    log.info(aMarker, "copro admin API call for {} has been started------->", coproStart.getName());
    final OkHttpClient httpclient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .readTimeout(10, TimeUnit.MINUTES)
            .build();
    String process="COPRO_START";
    Long rootPipelineId=action.getRootPipelineId();
    Long actionId=action.getActionId();
    final ObjectNode objectNode = mapper.createObjectNode();
    String coproCommand = coproStart.getCommand();
    String exportCommand = coproStart.getExportCommand();
    objectNode.put("processStartCommand", coproCommand);
    objectNode.put("exportCommand", exportCommand);
    objectNode.put("processUp","1");
    objectNode.put("process",process);
    objectNode.put("rootPipelineId",rootPipelineId);
    objectNode.put("actionId",actionId);

    String coproServerUrl = coproStart.getCoproServerUrl();
    Request request = new Request.Builder().url(coproServerUrl)
            .post(RequestBody.create(objectNode.toString(), MediaTypeJSON)).build();

    if(log.isInfoEnabled()) {
      log.info(aMarker, "Request has been build with the parameters \n coproUrl  {} ,coproCommand : {} exportCommand {} ", coproServerUrl,coproCommand,exportCommand);
    }
    String name = coproStart.getName() + "_response";
    log.debug(aMarker, "The Request Details: {} ", request);
    try (Response response = httpclient.newCall(request).execute()) {
      String responseBody = Objects.requireNonNull(response.body()).string();
      if (response.isSuccessful()) {
        JSONObject responseObj = new JSONObject(responseBody);
        CoproStartAction.coproStartActionEntity coproStartActionEntity = CoproStartAction.coproStartActionEntity
                .builder()
                .processId(Optional.ofNullable(coproStart.getProcessID()).map(Long::valueOf).orElse(null))
                .actionId(action.getActionId())
                .rootPipelineId(action.getRootPipelineId())
                .coproActionName(Optional.ofNullable(coproStart.getModuleName()).map(String::valueOf).orElse(null))
                .command(Optional.ofNullable(coproCommand).map(String::valueOf).orElse(null))
                .coproProcessId(Optional.ofNullable(responseObj.get("Pid")).map(String::valueOf).orElse(null))
                        .ram(Optional.ofNullable(responseObj.get("cpuUtilize")).map(String::valueOf).orElse(null))
                        .gpuRam(Optional.ofNullable(responseObj.get("gpuUtilize")).map(String::valueOf).orElse(null))
                        .build();
                Thread.sleep(Long.parseLong(action.getContext().get("macro.copro.stop.action")));
                jdbi.useTransaction(handle -> handle.createUpdate("INSERT INTO info.resource_utilization_details (process_id, action_id, root_pipeline_id, copro_action_name, copro_pid, created_on, command_exec, cpu, gpu, ram, gpu_ram)" +
                                " select :processId,:actionId,:rootPipelineId,:coproActionName,:coproProcessId,now(),:command,:cpu,:gpu,:ram,:gpuRam;")
                        .bindBean(coproStartActionEntity)
                        .execute());
                log.info(aMarker, "The Successful Response for {} --> {}", name, responseBody);
            } else {
                log.error(aMarker, "The Failure Response {} --> {}", name, responseBody);
            }
            log.info(aMarker, "copro admin API call for {} has been completed" , coproStart.getName());
        } catch (Exception e) {
            action.getContext().put(name.concat(".error"), "true");
            action.getContext().put(name.concat(".errorMessage"), e.getMessage());
            log.error(aMarker, "The Exception occurred {}", ExceptionUtil.toString(e));
            throw new HandymanException("Failed to execute", e, action);
        }
    }

  @Override
  public boolean executeIf() throws Exception {
    return coproStart.getCondition();
  }


  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class coproStartActionEntity {
    private Long processId;
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