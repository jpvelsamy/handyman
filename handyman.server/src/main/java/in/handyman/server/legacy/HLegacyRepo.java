package in.handyman.server.legacy;

import org.jdbi.v3.core.Jdbi;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HLegacyRepo {

    private final Jdbi spwConfig;

    public HLegacyRepo() {
        spwConfig = Jdbi.create("jdbc:mariadb://localhost:3306/spw_config", "root", "password");
    }

    public List<InstanceConfig> findInstanceConfigsByVariable(final String variable) {
        return spwConfig.withHandle(handle -> handle.createQuery("SELECT * FROM spw_instance_config where variable = ? and active ='Y' ")
                .bind(0,variable)
                .mapToBean(InstanceConfig.class).list());
    }
}
