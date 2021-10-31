package in.handyman.raven.lambda.access;

import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.doa.ConfigEntity;
import in.handyman.raven.lambda.doa.ConfigID;
import in.handyman.raven.lambda.doa.ResourceConnection;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.UUID;

class ElasticsearchAccessApiTest {


    @Test
    void init() {
        try {
            if (ElasticsearchAccessApi.isExists(ElasticsearchAccessApi.toIndexName(ResourceConnection.class))) {
                ElasticsearchAccessApi.saveIndex(UUID.randomUUID().toString(), ResourceConnection.builder()
                        .configType("")
                        .active(false)
                        .driverClassName("")
                        .name("")
                        .password("")
                        .url("")
                        .userName("")
                        .build());
            }
            if (ElasticsearchAccessApi.isExists(ElasticsearchAccessApi.toIndexName(ConfigEntity.class))) {
                ElasticsearchAccessApi.saveIndex(UUID.randomUUID().toString(), ConfigEntity.builder()
                        .active(false)
                        .id(ConfigID.builder()
                                .name("")
                                .configTypeId(0)
                                .variable("")
                                .build())
                        .value("")
                        .build());
            }
        } catch (IOException e) {
            throw new HandymanException("Init failed", e);
        }
    }

}
