package in.handyman.raven.process;

import org.junit.jupiter.api.Test;

class ProcessParserTest {

    @Test
    void doProcess() {
        final Process start = ProcessEngine.start(HRequestResolver.LoadType.FILE.name(), "cub.test");
        System.out.println("Hello " + start);
        assert start.getProcessId() != null;
    }


}
