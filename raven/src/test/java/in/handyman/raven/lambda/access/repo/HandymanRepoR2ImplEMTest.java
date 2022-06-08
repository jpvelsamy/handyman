package in.handyman.raven.lambda.access.repo;

import org.junit.jupiter.api.Test;

import java.util.Map;

class HandymanRepoR2ImplEMTest {

    @Test
    void getAllConfig() {
        var info = new HandymanRepoR2Impl();
        final Map<String, String> restApiActionTest = info.getAllConfig("RestApiActionTest");
        assert restApiActionTest != null && !restApiActionTest.isEmpty();
    }
}
