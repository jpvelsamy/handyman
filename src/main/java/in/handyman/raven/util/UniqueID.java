package in.handyman.raven.util;

import com.fasterxml.uuid.Generators;

import java.util.concurrent.atomic.AtomicInteger;

public class UniqueID {
    static final AtomicInteger atomicInteger=new AtomicInteger();
    private UniqueID() {
    }

    public static Long getId() {
        var uuid = Generators.timeBasedGenerator().generate();
        return uuid.timestamp();
    }

    public static Long getAutoIncrementId() {

        return (long) atomicInteger.incrementAndGet();
    }

}


