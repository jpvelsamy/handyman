package in.handyman.raven.lib;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import in.handyman.raven.lambda.process.HRequestResolver;
import in.handyman.raven.lambda.process.LContext;
import in.handyman.raven.lambda.process.LambdaEngine;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

class SharePointTest {
    // urgency trige
    @Test
    void execute() {
        LambdaEngine.start(LContext.builder()
                .pipelineName("root.test")
                .processLoadType(HRequestResolver.LoadType.FILE.name())
                .build());
    }


    @Test
    void docnetResult() throws JsonProcessingException {

        String jsonString = "[{\"sorKey\":\"date_of_birth\",\"sorId\":\"12\",\"attributionResult\":[{\"question\":\"Date of Birth\",\"predictedAttributionValue\":\"12/16/1963\",\"confidence_score\":\"\"}]},{\"sorKey\":\"date_of_birth\",\"sorId\":\"12\",\"attributionResult\":[{\"question\":\"Date of Birth\",\"predictedAttributionValue\":\"12/16/1963\",\"confidence_score\":\"\"}]},{\"sorKey\":\"date_of_birth\",\"sorId\":\"12\",\"attributionResult\":[{\"question\":\"Date of Birth\",\"predictedAttributionValue\":\"12/16/1963\",\"confidence_score\":\"\"}]},{\"sorKey\":\"date_of_birth\",\"sorId\":\"12\",\"attributionResult\":[{\"question\":\"Date of Birth\",\"predictedAttributionValue\":\"12/16/1963\",\"confidence_score\":\"\"}]}]";

        JSONArray jObj = new JSONArray(jsonString);

        jObj.forEach(resultObject -> {
            JSONObject obj = (JSONObject) resultObject;
            obj.getString("sorKey");
            obj.getString("sorId");
            JSONArray result = obj.getJSONArray("attributionResult");
            for (int i = 0; i < result.length(); i++) {
                JSONObject object = (JSONObject) result.get(i);
                object.getString("question");
                object.getString("predictedAttributionValue");
                System.out.println(object.getString("question"));
                System.out.println(object.getString("predictedAttributionValue"));

            }
        });


    }

    @Data
    @AllArgsConstructor
    class DocnetQuestions {
        private JsonNode question;
    }

}
