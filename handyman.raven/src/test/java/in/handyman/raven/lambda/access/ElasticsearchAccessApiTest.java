package in.handyman.raven.lambda.access;

import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.access.repo.ElasticsearchAccessApi;
import in.handyman.raven.lambda.doa.config.SpwResourceConfig;

import java.io.IOException;
import java.util.UUID;

class ElasticsearchAccessApiTest {


    //    @Test
    void init() {
        try {
            if (ElasticsearchAccessApi.isExists(ElasticsearchAccessApi.toIndexName(SpwResourceConfig.class))) {
                ElasticsearchAccessApi.saveIndex(UUID.randomUUID().toString(), SpwResourceConfig.builder()
                        .active(false)
                        .driverClassName("")
                        .configName("")
                        .password("")
                        .url("")
                        .userName("")
                        .build());
            }
//            if (ElasticsearchAccessApi.isExists(ElasticsearchAccessApi.toIndexName(SpwResourceConfig.class))) {
//                ElasticsearchAccessApi.saveIndex(UUID.randomUUID().toString(), SpwResourceConfig.builder()
//                        .active(false)
//                        .name("")
//                        .configTypeId(0)
//                        .variable("")
//                        .id(1L)
//                        .value("")
//                        .build());
//            }
        } catch (IOException e) {
            throw new HandymanException("Init failed", e);
        }
    }

}
