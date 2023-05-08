package in.handyman.raven.lib;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.access.ResourceAccess;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.EocJsonGenerator;
import in.handyman.raven.util.InstanceUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "EocJsonGenerator"
)
public class EocJsonGeneratorAction implements IActionExecution {
  private static final MediaType MediaTypeJSON = MediaType
          .parse("application/json; charset=utf-8");
  private final ActionExecutionAudit action;

  private final Logger log;

  private final EocJsonGenerator eocJsonGenerator;

  private final Marker aMarker;
  private final ObjectMapper mapper = new ObjectMapper();
  private final String URI;

  public EocJsonGeneratorAction(final ActionExecutionAudit action, final Logger log,
                                final Object eocJsonGenerator) {
    this.eocJsonGenerator = (EocJsonGenerator) eocJsonGenerator;
    this.action = action;
    this.log = log;
    this.aMarker = MarkerFactory.getMarker(" EocJsonGenerator:" + this.eocJsonGenerator.getName());
    this.URI = action.getContext().get("gatekeeper.url");
  }

  @Override
  public void execute() throws Exception {
    log.info(aMarker, "<-------Eoc Json Generation Action for {} has been started------->" + eocJsonGenerator.getName());
    final Jdbi jdbi = ResourceAccess.rdbmsJDBIConn(eocJsonGenerator.getResourceConn());
    final OkHttpClient httpclient = InstanceUtil.createOkHttpClient();

    Request request = new Request.Builder().url(URI + "api/v1/" + eocJsonGenerator.getDocumentId() + "/docdetaillineitem/" + eocJsonGenerator.getEocId())
            .header("Authorization", "Bearer " + eocJsonGenerator.getAuthtoken()).build();

    String name = eocJsonGenerator.getName();
    log.info(aMarker, "The Request Details : {}", request);
    try (Response response = httpclient.newCall(request).execute()) {
      assert response.body() != null;
      String responseBody = response.body().string();
      if (response.isSuccessful()) {
        log.info(aMarker, "The Successful Response for {} --> {}", name, responseBody);


        EocResponse eocResponse = EocResponse.builder()
                .documentId(eocJsonGenerator.getDocumentId())
                .eocId(eocJsonGenerator.getEocId())
                .originId(eocJsonGenerator.getOriginId())
                .groupId(Integer.valueOf(eocJsonGenerator.getGroupId()))
                .eocResponse(responseBody).rootPipelineId(action.getRootPipelineId()).build();

        jdbi.useTransaction(handle -> {
          handle.createUpdate("INSERT INTO outbound.eoc_response_details (document_id, eoc_id, origin_id, group_id, eoc_response, root_pipeline_id) " +
                          "VALUES( :documentId, :eocId, :originId, :groupId, :eocResponse::json, :rootPipelineId);")
                  .bindBean(eocResponse).execute();
          log.debug(aMarker, "inserted {} into eoc response details", eocResponse);
        });
      } else {
        log.info(aMarker, "The Failure Response {} --> {}", name, responseBody);
        action.getContext().put(name.concat(".error"), "true");
        action.getContext().put(name.concat(".errorMessage"), responseBody);
      }
      action.getContext().put(name + ".isSuccessful", String.valueOf(response.isSuccessful()));
    } catch (Exception e) {
      log.error(aMarker, "The Exception occurred ", e);
      action.getContext().put(name + ".isSuccessful", "false");
      throw new HandymanException("Failed to execute", e);
    }
    log.info(aMarker, "<-------Eoc Json Generation Action for {} has been completed------->" + eocJsonGenerator.getName());

  }

  @Override
  public boolean executeIf() throws Exception {
    return eocJsonGenerator.getCondition();
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class EocResponse {
    private String documentId;
    private String eocId;
    private String originId;
    private Integer groupId;
    private String eocResponse;
    private Long rootPipelineId;
  }
}