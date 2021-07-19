package in.handyman.raven.core.process;

import in.handyman.raven.core.context.ProcessContext;
import org.junit.jupiter.api.Test;

class ProcessParserTest {

    @Test
    void doProcess() {
        final ProcessContext start = ProcessEngine.start(HRequestResolver.LoadType.FILE.name(), "cub.test");
    }



}
