package in.handyman.raven.lib.adapters;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lib.interfaces.AdapterInterface;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class NameAdapter implements AdapterInterface {

    @Override
    public boolean getValidationModel(String sentence, String uri) throws Exception {
        try{

            ObjectMapper mapper = new ObjectMapper();
            MediaType MediaTypeJSON = MediaType.parse("application/json; charset=utf-8");
            String[] patientName = new String[]{sentence};
            OkHttpClient httpclient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.MINUTES)
                    .writeTimeout(10, TimeUnit.MINUTES)
                    .readTimeout(10, TimeUnit.MINUTES).build();
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.putPOJO("inputString", patientName);
            Request request = new Request.Builder().url(uri)
                    .post(RequestBody.create(objectNode.toString(), MediaTypeJSON)).build();
            try (Response response = httpclient.newCall(request).execute()) {
                String responseBody = Objects.requireNonNull(response.body()).string();
                JsonNode actualObj = mapper.readTree(responseBody);
                if (actualObj.get("prediction").get(0) != null) {
                    return actualObj.get("prediction").get(0).get("predictedLabel").asBoolean();
                } else {
                    return false;
                }
            }

        } catch (Exception ex) {
            throw new HandymanException("Failed to execute", ex);
        }

    }

    @Override
    public boolean getDateValidationModel(String sentence, int comparableYear, String[] dateFormats) throws Exception {
        return false;
    }

    @Override
    public int getThresholdScore(String sentence) throws Exception {
        return 0;
    }
}
