package in.handyman.raven.lambda.access;

import in.handyman.raven.lambda.access.repo.DBAccess;
import org.junit.jupiter.api.Test;

class DBAccessTest {

    @Test
    void init() throws ClassNotFoundException {
        DBAccess.getConnection();
        DBAccess.init();
        assert !ConfigAccess.getCommonConfig().isEmpty();
    }

}
