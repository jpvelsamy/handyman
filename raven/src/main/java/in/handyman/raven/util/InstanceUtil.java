package in.handyman.raven.util;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public class InstanceUtil {

    private InstanceUtil() {

    }


    public static OkHttpClient createOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.MINUTES)
                .writeTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES)
                .build();
    }

}
