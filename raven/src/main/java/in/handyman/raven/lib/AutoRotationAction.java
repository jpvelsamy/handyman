package in.handyman.raven.lib;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.AutoRotation;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.util.Map;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
    actionName = "AutoRotation"
)
public class AutoRotationAction implements IActionExecution {
  private final ActionExecutionAudit action;

  private final Logger log;

  private final AutoRotation autoRotation;

  private final Marker aMarker;

  private final ObjectMapper mapper = new ObjectMapper();
  private static final MediaType MediaTypeJSON = MediaType
          .parse("application/json; charset=utf-8");

  private final String URI  ;

  public AutoRotationAction(final ActionExecutionAudit action, final Logger log,
      final Object autoRotation) {
    this.autoRotation = (AutoRotation) autoRotation;
    this.action = action;
    this.log = log;
    this.aMarker = MarkerFactory.getMarker(" AutoRotation:"+this.autoRotation.getName());
    this.URI=action.getContext().get("copro.autorotation.url");

  }

  @Override
  public void execute() throws Exception {
    OkHttpClient httpclient = new OkHttpClient();

    final ObjectNode objectNode = mapper.createObjectNode();

    objectNode.put("inputFilePath",autoRotation.getFilePath());
    objectNode.put("outputDir",autoRotation.getOutputDir());

    // build a request
    Request request = new Request.Builder().url(URI)
            .post(RequestBody.create( objectNode.toString(),MediaTypeJSON)).build();
    log.info(aMarker, "The request got it successfully the File Path and outputDir {} {}",autoRotation.getFilePath(),autoRotation.getOutputDir());

    try (Response response = httpclient.newCall(request).execute()) {
      String responseBody = response.body().string();
      String name = autoRotation.getName() + "-auto rotation-response";
      if (response.isSuccessful()) {
        Map<String, String> responseMap = mapper.readValue(responseBody, new TypeReference<>() {
        });
        responseMap.forEach((s, s2) -> action.getContext().put(String.format("%s.%s", autoRotation.getName(), s), s2));        log.info(aMarker, "The Successful Response  {} {}", name, responseBody);
      }else {
        log.info(aMarker, "The Failure Response  {} {}", name, responseBody);
      }
    }catch (Exception e){
      log.info(aMarker, "The Exception occurred ",e);
      throw new HandymanException("Failed to execute", e);
    }

  }

  @Override
  public boolean executeIf() throws Exception {
    return autoRotation.getCondition();
  }
}
