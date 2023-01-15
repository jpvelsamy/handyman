package in.handyman.raven.access;

import in.handyman.raven.lambda.access.ConfigAccess;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;

@Slf4j
class ConfigAccessDTest {

    @Test
    void getResourceConfig() {
        final Map<String, String> commonConfig = ConfigAccess.getCommonConfig();
        assert commonConfig != null;
        log.info(commonConfig.toString());
    }


}
