package in.handyman.raven.lib;

import in.handyman.raven.exception.HandymanException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ProductResponseActionTest {

    final OkHttpClient httpclient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .readTimeout(10, TimeUnit.MINUTES)
            .build();

    @Test
    void execute() throws MalformedURLException {

        URL url = new URL("http://localhost:8189/alchemy/api/v1/transaction/product/response/TRZ-3/ORIGIN-11?tenantId=1");
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "");

        Request request = new Request.Builder().url(url)
                .addHeader("accept", "*/*")
                .addHeader("Authorization", "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyIERldGFpbHMiLCJpc3MiOiJJbnRpY3NBSSBBbGNoZW15IiwiZXhwIjoxNjk0NDg3NTU0LCJpYXQiOjE2OTQ0NDQzNTQsImVtYWlsIjoidGVzdEBpbnRpY3MuYWkifQ.oDFhoOQDBAlGLjYFvOvXNzdiXelwxNCeJfweSL0MGSs")
                .addHeader("Content-Type", "application/json")
                .post(requestBody)
                .build();

        try (Response response = httpclient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                log.info("Response Details: {}", response);
            }
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }
}