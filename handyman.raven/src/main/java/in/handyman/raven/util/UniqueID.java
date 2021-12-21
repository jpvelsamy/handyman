package in.handyman.raven.util;

import com.fasterxml.uuid.Generators;

public class UniqueID {

    private UniqueID() {
    }

    public static Long getId() {
        var uuid = Generators.timeBasedGenerator().generate();
        return  uuid.timestamp();
    }

}


