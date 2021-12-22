package in.handyman.raven.lambda.doa;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum ConfigType {

    COMMON(1), ACTION(2), LAMBDA(3), PIPELINE(4);

    private final Integer id;

    public static ConfigType get(final Integer id) {
        return Stream.of(ConfigType.values()).filter(configType -> Objects.equals(configType.id, id))
                .findFirst().orElse(null);
    }

}
