package in.handyman.raven.process;

import in.handyman.raven.context.ProcessContext;
import org.junit.jupiter.api.Test;

class ProcessParserTest {

    @Test
    void doProcess() {
        final ProcessContext start = ProcessEngine.start(HRequestResolver.LoadType.FILE.name(), "cub.test");

        System.out.println("Hello");

    }



}
