package in.handyman.raven.lambda.access.repo;

import in.handyman.raven.lambda.doa.ConfigEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractAccess {

    protected String getLambdaName(final String pipelineName) {
        if (pipelineName != null) {
            if (pipelineName.contains("#")) {
                return pipelineName.substring(0, pipelineName.lastIndexOf("#"));
            }
        }
        return pipelineName;
    }


    protected Map<String, String> toMap(final List<ConfigEntity> configEntities) {
        return configEntities.stream()
                .collect(Collectors
                        .toMap((configEntity -> Optional.ofNullable(configEntity.getVariable())
                                        .orElse(null)),
                                ConfigEntity::getValue,
                                (p, q) -> p));
    }

}
