package in.handyman.raven.process;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.handyman.raven.lambda.Lambda;
import in.handyman.raven.lambda.LambdaContext;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

class ProcessExecutorTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void getLambdas() throws JsonProcessingException {
        var lambdas = ProcessExecutor.getLambdas();
        assert !lambdas.isEmpty();
        System.out.println(mapper.writeValueAsString(lambdas));
    }
}
