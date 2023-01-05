package in.handyman.raven.lambda.access.repo;

import org.junit.jupiter.api.Test;

import java.util.Map;

class HandymanRepoImplEMTest {

    @Test
    void getAllConfig() {
        var info = new HandymanRepoImpl();
        final Map<String, String> restApiActionTest = info.getAllConfig("RestApiActionTest");
        assert restApiActionTest != null && !restApiActionTest.isEmpty();
    }
}
