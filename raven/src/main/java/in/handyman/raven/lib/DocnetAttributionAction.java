package in.handyman.raven.lib;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.DocnetAttribution;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.util.*;
import java.util.concurrent.TimeUnit;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
    actionName = "DocnetAttribution"
)
public class DocnetAttributionAction implements IActionExecution {
  private final ActionExecutionAudit action;

  private final Logger log;

  private final DocnetAttribution docnetAttribution;

  private final Marker aMarker;
  private final String URI;
  private final ObjectMapper mapper = new ObjectMapper();
  private static final MediaType MediaTypeJSON = MediaType.parse("application/json; charset=utf-8");

  public DocnetAttributionAction(final ActionExecutionAudit action, final Logger log,
      final Object docnetAttribution) {
    this.docnetAttribution = (DocnetAttribution) docnetAttribution;
    this.action = action;
    this.log = log;
    this.aMarker = MarkerFactory.getMarker(" DocnetAttribution:"+this.docnetAttribution.getName());
    this.URI=action.getContext().get("copro.docnet-attribution.url");
  }

  @Override
  public void execute() throws Exception {
    log.info(aMarker,"<-------Docnet Attribution Action for {} has been started------->"+docnetAttribution.getName());
    final OkHttpClient httpclient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .readTimeout(10, TimeUnit.MINUTES).build();
    log.info(aMarker, " Input variables id : {}", action.getActionId());

    JsonNode questionList=mapper.readTree(docnetAttribution.getQuestionList());
    JsonNode keyName = questionList.get("f1");
    log.info(aMarker, "Question List for {} are {}", docnetAttribution.getQuestionList(), keyName);

    final ObjectNode objectNode = mapper.createObjectNode();
    objectNode.put("inputFilePath",docnetAttribution.getInputFilePath());
    objectNode.set("attributes",questionList.get("f2"));

    Request request = new Request.Builder().url(URI)
            .post(RequestBody.create(objectNode.toString(),MediaTypeJSON)).build();

    log.info(aMarker, "The Request Details : {}", request);
    try (Response response = httpclient.newCall(request).execute()) {
      String responseBody = response.body().string();
      String name = docnetAttribution.getName() + "_response";
      JsonNode actualObj = mapper.readTree(responseBody);
      List<String> attributionResult=new ArrayList<String>();
      for (JsonNode fieldName : questionList.get("f2")) {
        JsonNode value = actualObj.get("attributionValue").get(fieldName.asText());
        attributionResult.add(value.asText());
      }

      ObjectNode resultNode = mapper.createObjectNode();
      resultNode.put("keyName", keyName.asText());
      resultNode.putPOJO("attributionResult",  attributionResult);
      if (response.isSuccessful()) {
        log.info(aMarker, "The Successful Response for {} --> {}", name, responseBody);
        action.getContext().put(name, resultNode.toString());
        action.getContext().put(name.concat(".error"), "false");
      } else {
        log.info(aMarker, "The Failure Response {} --> {}", name, responseBody);
        action.getContext().put(name.concat(".error"), "true");
      }
      log.info(aMarker,"<-------Docnet Attribution Action for {} has been completed------->"+docnetAttribution.getName());
    }catch (Exception e){
      log.info(aMarker, "The Exception occurred ",e);
      throw new HandymanException("Failed to execute", e);
    }
  }

  @Override
  public boolean executeIf() throws Exception {
    return docnetAttribution.getCondition();
  }
}
