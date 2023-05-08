package in.handyman.raven.lib;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.QrAttribution;
import in.handyman.raven.util.ExceptionUtil;
import in.handyman.raven.util.InstanceUtil;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.Objects;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "QrAttribution"
)
public class QrAttributionAction implements IActionExecution {
    private static final MediaType MediaTypeJSON = MediaType.parse("application/json; charset=utf-8");
    private final ActionExecutionAudit action;
    private final Logger log;
    private final QrAttribution qrAttribution;
    private final ObjectMapper mapper = new ObjectMapper();
    private final String URI;

    private final Marker aMarker;

    public QrAttributionAction(final ActionExecutionAudit action, final Logger log,
                               final Object qrAttribution) {
        this.qrAttribution = (QrAttribution) qrAttribution;
        this.action = action;
        this.log = log;
        this.aMarker = MarkerFactory.getMarker(" QrAttribution:" + this.qrAttribution.getName());
        this.URI = action.getContext().get("copro.qr-attribution.url");
    }

    @Override
    public void execute() throws Exception {
        {
            final OkHttpClient httpclient = InstanceUtil.createOkHttpClient();
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("inputFilePath", qrAttribution.getFilePath());
            // build a request
            Request request = new Request.Builder().url(URI)
                    .post(RequestBody.create(objectNode.toString(), MediaTypeJSON)).build();
            log.info(aMarker, "The request got it successfully the File Path {}", qrAttribution.getFilePath());
            try (Response response = httpclient.newCall(request).execute()) {
                String responseBody = Objects.requireNonNull(response.body()).string();
                String name = "qr-attribution-response";
                if (response.isSuccessful()) {
                    action.getContext().put(name, mapper.readTree(responseBody).toString());
                    log.info(aMarker, "The Successful Response  {} {}", name, responseBody);
                } else {
                    log.info(aMarker, "The Failure Response  {} {}", name, responseBody);
                    throw new HandymanException(responseBody);
                }
            } catch (Exception e) {
                log.info(aMarker, "The Exception occurred {}", ExceptionUtil.toString(e));
                throw new HandymanException("Failed to execute", e, action);
            }
        }
    }

    @Override
    public boolean executeIf() throws Exception {
        return qrAttribution.getCondition();
    }
}
