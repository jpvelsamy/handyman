package in.handyman.raven.process;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class ProcessExecutorTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void getLambdas() throws JsonProcessingException {
        var lambdas = ProcessExecutor.getLambdas();
        assert !lambdas.isEmpty();
        System.out.println(mapper.writeValueAsString(lambdas));
    }
}
