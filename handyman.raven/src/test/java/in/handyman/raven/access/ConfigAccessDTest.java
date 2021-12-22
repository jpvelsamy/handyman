package in.handyman.raven.access;

import in.handyman.raven.lambda.access.ConfigAccess;
import in.handyman.raven.lambda.doa.ResourceConnection;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class ConfigAccessDTest {

    @Test
    void getResourceConfig() {
        final ResourceConnection connection = ConfigAccess.getResourceConfig("dsad");
        assert connection != null;
        log.info(connection.toString());
    }


}
