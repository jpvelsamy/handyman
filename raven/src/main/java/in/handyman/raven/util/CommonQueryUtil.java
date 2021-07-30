package in.handyman.raven.util;

import com.fasterxml.jackson.databind.node.ObjectNode;
import in.handyman.raven.exception.HandymanException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CommonQueryUtil {

    public static List<String> getFormattedQuery(final String givenQuery) {
        var sqlWithoutQuotes = givenQuery.replaceAll("\"", "");
        var strings = List.of(sqlWithoutQuotes.split(";"));
        return strings.stream().map(String::trim)
                .filter(s -> !s.isEmpty() && !s.isBlank())
                .collect(Collectors.toUnmodifiableList());
    }

    public static void addKeyConfig(final Map<String, String> configContext, final ObjectNode detailMap,
                                    final ResultSet rs, final int columnCount, final String prefix) {
        IntStream.range(1, columnCount + 1).forEach(i -> {
            try {
                var key = rs.getMetaData().getColumnLabel(i);
                var value = rs.getString(i);
                configContext.put(prefix + "." + key, value);
                detailMap.put("query.output." + key, value);
            } catch (SQLException e) {
                throw new HandymanException("Fetch config failed", e);
            }
        });
    }

}
