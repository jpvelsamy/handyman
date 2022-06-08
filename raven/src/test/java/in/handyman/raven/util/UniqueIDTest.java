package in.handyman.raven.util;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

class UniqueIDTest {

    @Test
    void getId() {
        IntStream.range(1, 10).forEach(value -> System.out.println(System.nanoTime() + "   " + UniqueID.getId()));
    }
}
