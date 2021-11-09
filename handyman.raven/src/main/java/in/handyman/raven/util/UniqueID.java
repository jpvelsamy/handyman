package in.handyman.raven.util;

public class UniqueID {

    private UniqueID() {
    }

    public static Long getId() {
        return System.nanoTime();
    }

}


