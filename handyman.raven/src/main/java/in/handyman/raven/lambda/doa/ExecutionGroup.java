package in.handyman.raven.lambda.doa;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum ExecutionGroup {

    TRY(1), CATCH(2), FINALLY(3);

    private final Integer id;

    public static ExecutionGroup get(final Integer id) {
        return Stream.of(ExecutionGroup.values()).filter(status -> Objects.equals(status.id, id))
                .findFirst().orElse(null);
    }

}
