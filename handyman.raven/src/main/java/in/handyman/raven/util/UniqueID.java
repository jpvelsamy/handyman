package in.handyman.raven.util;

import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Enumeration;

public class UniqueID {

    private static final SequenceGenerator GENERATOR = new SequenceGenerator();

    private UniqueID() {
    }

    public static Long getId() {
        return GENERATOR.nextId();
    }

}

class SequenceGenerator {


    private static final int NODE_ID_BITS = 10;
    private static final int SEQUENCE_BITS = 12;

    private static final int MAX_NODE_ID = (int) (Math.pow(2, NODE_ID_BITS) - 1);
    private static final int MAX_SEQUENCE = (int) (Math.pow(2, SEQUENCE_BITS) - 1);

    private static final long CUSTOM_EPOCH = 1420070400000L;

    private final int nodeId;

    private volatile long lastTimestamp = -1L;
    private volatile long sequence = 0L;


    public SequenceGenerator() {
        this.nodeId = createNodeId();
    }

    private int createNodeId() {
        int nodeId;
        try {
            final StringBuilder sb = new StringBuilder();
            final Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                final NetworkInterface networkInterface = networkInterfaces.nextElement();
                final byte[] mac = networkInterface.getHardwareAddress();
                if (mac != null) {
                    for (final byte b : mac) {
                        sb.append(String.format("%02X", b));
                    }
                }
            }
            nodeId = sb.toString().hashCode();
        } catch (Exception ex) {
            nodeId = (new SecureRandom().nextInt());
        }
        return nodeId & MAX_NODE_ID;
    }

    public synchronized long nextId() {
        long currentTimestamp = timestamp();

        if (currentTimestamp < lastTimestamp) {
            throw new IllegalStateException("Invalid System Clock!");
        }

        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                // Sequence Exhausted, wait till next millisecond.
                currentTimestamp = waitNextMillis(currentTimestamp);
            }
        } else {
            // reset sequence to start with zero for the next millisecond
            sequence = 0;
        }

        lastTimestamp = currentTimestamp;

        long id = currentTimestamp << (NODE_ID_BITS + SEQUENCE_BITS);
        id |= (nodeId << SEQUENCE_BITS);
        id |= sequence;
        return id;
    }

    // Get current timestamp in milliseconds, adjust for the custom epoch.
    private static long timestamp() {
        return Instant.now().toEpochMilli() - CUSTOM_EPOCH;
    }

    // Block and wait till next millisecond
    private long waitNextMillis(long currentTimestamp) {
        while (currentTimestamp == lastTimestamp) {
            currentTimestamp = timestamp();
        }
        return currentTimestamp;
    }
}


