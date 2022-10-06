package in.handyman.raven.lib;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.ProviderAttribution;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.util.Collections;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
    actionName = "ProviderAttribution"
)
public class ProviderAttributionAction implements IActionExecution {
  private final ActionExecutionAudit action;

  private final Logger log;

  private final ProviderAttribution providerAttribution;
  private final ObjectMapper mapper = new ObjectMapper();
  private static final MediaType MediaTypeJSON = MediaType.parse("application/json; charset=utf-8");
  private final String URI;

  private final Marker aMarker;

  public ProviderAttributionAction(final ActionExecutionAudit action, final Logger log,
      final Object providerAttribution) {
    this.providerAttribution = (ProviderAttribution) providerAttribution;
    this.action = action;
    this.log = log;
    this.aMarker = MarkerFactory.getMarker(" ProviderAttribution:"+this.providerAttribution.getName());
    this.URI=action.getContext().get("copro.kvp-attribution.url");
  }

  @Override
  public void execute() throws Exception {
    OkHttpClient httpclient = new OkHttpClient();
    final ObjectNode objectNode = mapper.createObjectNode();
    objectNode.put("inputFilePath",providerAttribution.getFilePath());
    objectNode.put("outputDir",providerAttribution.getOutputDir());
    objectNode.putPOJO("attributes", Collections.singletonList(providerAttribution.getProviderKeywords()));
// build a request
    Request request = new Request.Builder().url(URI)
            .post(RequestBody.create(objectNode.toString(),MediaTypeJSON)).build();
    log.info(aMarker, "The request got it successfully the File Path and outputDir {} {}",providerAttribution.getFilePath()
            ,providerAttribution.getOutputDir());
    try (Response response = httpclient.newCall(request).execute()) {
      String responseBody = response.body().string();
      String name = providerAttribution.getName() + "-provider-attribution-response";
      if (response.isSuccessful()) {
        action.getContext().put(name, responseBody);
        log.info(aMarker, "The Successful Response  {} {}", name, responseBody);
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
    return providerAttribution.getCondition();
  }
}