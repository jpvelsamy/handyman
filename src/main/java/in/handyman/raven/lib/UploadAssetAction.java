package in.handyman.raven.lib;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.UploadAsset;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "UploadAsset"
)
public class UploadAssetAction implements IActionExecution {
    private final ActionExecutionAudit action;
    private final Logger log;
    private final UploadAsset uploadAsset;
    private final ObjectMapper mapper = new ObjectMapper();
    private final String URI;
    private final Marker aMarker;
    MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png/pdf");

    public UploadAssetAction(final ActionExecutionAudit action, final Logger log,
                             final Object uploadAsset) {
        this.uploadAsset = (UploadAsset) uploadAsset;
        this.action = action;
        this.log = log;
        this.aMarker = MarkerFactory.getMarker(" UploadAsset:" + this.uploadAsset.getName());
        this.URI = action.getContext().get("copro.upload-asset.url");
    }

    @Override
    public void execute() throws Exception {
        OkHttpClient httpclient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.MINUTES)
                .writeTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES).build();

        File file = new File(uploadAsset.getFilePath());
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("multipartFile", file.getName(), RequestBody.create(file, MEDIA_TYPE_PNG))
                .addFormDataPart("templateId", uploadAsset.getTemplateId())
                .build();

        Request request = new Request.Builder().url(URI)
                .addHeader("Authorization", "Bearer " + uploadAsset.getToken())
                .post(requestBody).build();
        log.info(aMarker, "The request got it successfully to upload Asset {}",
                uploadAsset.getFilePath());
        try (Response response = httpclient.newCall(request).execute()) {
            assert response.body() != null;
            String responseBody = response.body().string();
            JsonNode jsonResult = mapper.readTree(responseBody);
            String name = uploadAsset.getName();
            log.info(aMarker, "The response got it successfully for upload Asset as ==> {}",
                    responseBody);
            if (response.isSuccessful()) {
                action.getContext().put(name, jsonResult.get("asset").get("assetId").asText());
                log.info(aMarker, "The Successful Response  {} {}", name, responseBody);
            } else {
                log.info(aMarker, "The Failure Response  {} {}", name, responseBody);
            }
            log.info(aMarker, "The response got it successfully for upload Asset as ==> {}",
                    action);
        } catch (Exception e) {
            log.info(aMarker, "The Exception occurred ", e);
            throw new HandymanException("Failed to execute", e);
        }
    }

    @Override
    public boolean executeIf() throws Exception {
        return uploadAsset.getCondition();
    }
}
