package in.handyman.raven.process;

import in.handyman.raven.context.ProcessContext;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class ProcessEngineTest {

    @Test
    void start() {
        final ProcessContext start = ProcessEngine
                .start(HRequestResolver.LoadType.FILE.name(),
                        "cub.test");
        assert start.getTryActions().size() == 3;
    }
}
