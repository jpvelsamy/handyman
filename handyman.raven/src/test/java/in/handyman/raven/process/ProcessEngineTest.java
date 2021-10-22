package in.handyman.raven.process;

import org.junit.jupiter.api.Test;

class ProcessEngineTest {

    @Test
    void start() {
        final Process start = ProcessEngine
                .start(HRequestResolver.LoadType.FILE.name(),
                        "cub.test");
        assert start.getTryActions().size() == 3;
    }
}
