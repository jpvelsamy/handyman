package in.handyman.server.schedular;

import org.jdbi.v3.core.Jdbi;

import java.util.Set;
import java.util.stream.Collectors;

public class QuartzRepo {

    private final Jdbi jdbi;

    public QuartzRepo() {
        jdbi = Jdbi.create("jdbc:mariadb://localhost:3306/handyman_qrtz", "root", "password");
    }

    public Set<String> findAwaitingTriggers() {
        return jdbi.withHandle(handle -> handle.createQuery("select TRIGGER_NAME from QRTZ_TRIGGERS where TRIGGER_STATE='WAITING' ")
                .mapTo(String.class).stream().collect(Collectors.toSet()));
    }
}
