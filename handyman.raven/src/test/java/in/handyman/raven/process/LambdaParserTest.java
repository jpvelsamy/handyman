package in.handyman.raven.process;

import in.handyman.raven.lym.process.HRequestResolver;
import org.junit.jupiter.api.Test;

class LambdaParserTest {

    @Test
    void doProcess() {
        final Process start = ProcessEngine.start(HRequestResolver.LoadType.FILE.name(), "cub.test");
        System.out.println("Hello " + start);
        assert start.getProcessId() != null;
    }


}
