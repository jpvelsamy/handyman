package in.handyman.raven.util;

import java.util.List;
import java.util.stream.Collectors;

public class CommonQueryUtil {

    public static List<String> getFormattedQuery(final String givenQuery) {
        var sqlWithoutQuotes = givenQuery.replaceAll("\"", "");
        var strings = List.of(sqlWithoutQuotes.split(";"));
        return strings.stream().map(String::trim)
                .filter(s -> !s.isEmpty() && !s.isBlank())
                .collect(Collectors.toUnmodifiableList());
    }

}
