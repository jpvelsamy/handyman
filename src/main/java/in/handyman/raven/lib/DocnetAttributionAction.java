package in.handyman.raven.lib;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.access.ResourceAccess;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.DocnetAttribution;
import in.handyman.raven.util.CommonQueryUtil;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
        try {
            log.info(aMarker, "Docnut Attribution Action for {} has been started" , docnetAttribution.getName());
            final OkHttpClient httpclient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.MINUTES)
                    .writeTimeout(10, TimeUnit.MINUTES)
                    .readTimeout(10, TimeUnit.MINUTES).build();
            final Jdbi jdbi = ResourceAccess.rdbmsJDBIConn(docnetAttribution.getResourceConn());
            final List<Map<String, Object>> questions = new ArrayList<>();

            jdbi.useTransaction(handle -> {
                final List<String> formattedQuery = CommonQueryUtil.getFormattedQuery(docnetAttribution.getAttributeQuestionSql());
                formattedQuery.forEach(sqlToExecute -> questions.addAll(handle.createQuery(sqlToExecute).mapToMap().stream().collect(Collectors.toList())));
            });
            final String docnetAttributionAsResponse = docnetAttribution.getDocnetAttributionAsResponse();
            final ArrayNode finalRes = mapper.createArrayNode();
            for (var question : questions) {

                final String questionsJsonString = Optional.ofNullable(question.get("questions")).map(String::valueOf).orElse("[]");
                final String sorKey = Optional.ofNullable(question.get("sor_key")).map(String::valueOf).orElse("[]");
                final String sorId = Optional.ofNullable(question.get("sor_id")).map(String::valueOf).orElse("[]");
                final String sorItemId = Optional.ofNullable(question.get("sor_item_id")).map(String::valueOf).orElse("[]");

                final JsonNode questionNodes = mapper.readTree(questionsJsonString);

                if (!questionNodes.isEmpty()) {
                    final ObjectNode objectNode = mapper.createObjectNode();
                    objectNode.put("inputFilePath", docnetAttribution.getInputFilePath());
                    objectNode.set("attributes", questionNodes);
                    objectNode.put("outputDir", docnetAttribution.getOutputDir());
                    log.info(aMarker, "Question List for {} are {}", sorKey, questionNodes);

                    final Request request = new Request.Builder().url(URI)
                            .post(RequestBody.create(objectNode.toString(), MediaTypeJSON)).build();
                    log.info(aMarker, "The Request Details : {}", request);
                    try (Response response = httpclient.newCall(request).execute()) {
                        String responseBody = Objects.requireNonNull(response.body()).string();
                        if (response.isSuccessful()) {
                            JsonNode actualObj = mapper.readTree(responseBody);
                            ObjectNode resultNode = mapper.createObjectNode();
                            resultNode.put("sorKey", sorKey);
                            resultNode.put("sorId", sorId);
                            resultNode.put("sorItemId", sorItemId);
                            resultNode.putPOJO("attributionResult", actualObj);
                            finalRes.add(resultNode);
                            log.info(aMarker, "The Successful Response for {} --> {}", docnetAttributionAsResponse, responseBody);
                        } else {
                            log.error(aMarker, "The Failure Response {} --> {}", docnetAttributionAsResponse, responseBody);
                            action.getContext().put(docnetAttributionAsResponse.concat(".error"), "true");
                            throw new HandymanException(responseBody);
                        }
                    } catch (Exception e) {
                        log.error(aMarker, "The Exception occurred ", e);
                        action.getContext().put(docnetAttributionAsResponse.concat(".error"), "true");
                        throw new HandymanException("Failed to execute", e, action);
                    }
                }

            }
            action.getContext().put(docnetAttributionAsResponse.concat(".error"), "false");
            action.getContext().put(docnetAttributionAsResponse.concat(".response"), finalRes.toString().replace("'", "''"));
        } catch (Exception e) {
            action.getContext().put(docnetAttribution.getName().concat(".error"), "true");
            log.info(aMarker, "The Exception occurred ", e);
            throw new HandymanException("Failed to execute", e, action);
        }
        log.info(aMarker, "Docnut Attribution Action for {} has been completed" , docnetAttribution.getName());

    }

    @Override
    public boolean executeIf() throws Exception {
        return docnetAttribution.getCondition();
    }
}
