package in.handyman.raven.access;

import in.handyman.raven.lym.access.ConfigAccess;
import in.handyman.raven.lym.doa.ResourceConnection;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

@Log4j2
class ConfigAccessTest {

    @Test
    void getResourceConfig() {
        final ResourceConnection connection = ConfigAccess.getResourceConfig("dsad");
        assert connection != null;
        log.info(connection);
    }


}
