package in.handyman.raven.lib;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.DocnetAttribution;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "DocnetAttribution"
)
public class DocnetAttributionAction implements IActionExecution {
    private static final MediaType MediaTypeJSON = MediaType.parse("application/json; charset=utf-8");
    private final ActionExecutionAudit action;
    private final Logger log;
    private final DocnetAttribution docnetAttribution;
    private final Marker aMarker;
    private final String URI;

    private final boolean SOR_FLAG;
    private final ObjectMapper mapper = new ObjectMapper();

    public DocnetAttributionAction(final ActionExecutionAudit action, final Logger log,
                                   final Object docnetAttribution) {
        this.docnetAttribution = (DocnetAttribution) docnetAttribution;
        this.action = action;
        this.log = log;
        this.aMarker = MarkerFactory.getMarker(" DocnetAttribution:" + this.docnetAttribution.getName());
        this.URI = action.getContext().get("copro.docnet-attribution.url");
        this.SOR_FLAG = Boolean.parseBoolean(action.getContext().get("sor-key-filtering-flag"));
    }

    @Override
    public void execute() throws Exception {
        log.info(aMarker, "<-------Docnet Attribution Action for {} has been started------->" + docnetAttribution.getName());
        final OkHttpClient httpclient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.MINUTES)
                .writeTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES).build();
        log.info(aMarker, " Input variables id : {}", action.getActionId());

//        JsonNode absentKeyList = mapper.readTree(docnetAttribution.getAbsentKeyFilterList());
        JsonNode questionList = mapper.readTree(docnetAttribution.getQuestionList());
        JsonNode keyName = questionList.get("f1");
        String name = docnetAttribution.getName() + "_response";
//        for (JsonNode fieldList : absentKeyList) {
//            JSONObject jsonValue = new JSONObject(fieldList.asText());
//            String sorName = jsonValue.getString("key_name");
//            if (keyName.asText().equalsIgnoreCase(sorName)) {
                if (SOR_FLAG) {
                    final ObjectNode objectNode = mapper.createObjectNode();
                    objectNode.put("inputFilePath", docnetAttribution.getInputFilePath());
                    objectNode.set("attributes", questionList.get("f2"));
                    objectNode.put("outputDir", docnetAttribution.getOutputDir());
                    log.info(aMarker, "Question List for {} are {}", questionList.get("f2"), keyName);

                    Request request = new Request.Builder().url(URI)
                            .post(RequestBody.create(objectNode.toString(), MediaTypeJSON)).build();
                    log.info(aMarker, "The Request Details : {}", request);
                    try (Response response = httpclient.newCall(request).execute()) {
                        String responseBody = Objects.requireNonNull(response.body()).string();

                        if (response.isSuccessful()) {
                            JsonNode actualObj = mapper.readTree(responseBody);
                            ObjectNode resultNode = mapper.createObjectNode();
                            resultNode.put("keyName", keyName.asText());
                            resultNode.putPOJO("attributionResult", actualObj);
                            log.info(aMarker, "The Successful Response for {} --> {}", name, responseBody);
                            action.getContext().put(name, resultNode.toString());
                            action.getContext().put(name.concat(".error"), "false");
                            action.getContext().put(name.concat(".value"), "true");
                        } else {
                            log.info(aMarker, "The Failure Response {} --> {}", name, responseBody);
                            action.getContext().put(name.concat(".error"), "true");
                        }
                        log.info(aMarker, "<-------Docnet Attribution Action for {} has been completed------->" + docnetAttribution.getName());
                    } catch (Exception e) {
                        log.info(aMarker, "The Exception occurred ", e);
                        throw new HandymanException("Failed to execute", e);
                    }
                } else {
                    action.getContext().put(name.concat(".value"), "false");
                }
//                break;
//            }
        }
//    }

    @Override
    public boolean executeIf() throws Exception {
        return docnetAttribution.getCondition();
    }
}
