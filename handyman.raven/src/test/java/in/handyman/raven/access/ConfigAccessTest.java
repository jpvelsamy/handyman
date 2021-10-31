package in.handyman.raven.access;

import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.access.ConfigAccess;
import in.handyman.raven.lambda.access.ElasticsearchAccessApi;
import in.handyman.raven.lambda.doa.ConfigEntity;
import in.handyman.raven.lambda.doa.ConfigID;
import in.handyman.raven.lambda.doa.ResourceConnection;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.UUID;

@Log4j2
class ConfigAccessTest {

    @Test
    void getResourceConfig() {
        final ResourceConnection connection = ConfigAccess.getResourceConfig("dsad");
        assert connection != null;
        log.info(connection);
    }


}
