package in.handyman.raven.util;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zaxxer.hikari.HikariDataSource;
import in.handyman.raven.exception.HandymanException;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
public class CommonQueryUtil {

    public static void addKeyConfig(final Map<String, String> configContext, final ObjectNode detailMap,
                                    final ResultSet rs, final int columnCount, final String prefix) {
        IntStream.range(1, columnCount + 1).forEach(i -> {
            try {
                var key = rs.getMetaData().getColumnLabel(i);
                var value = rs.getString(i);
                configContext.put(((prefix.isBlank() || prefix.isEmpty()) ? "" : (prefix + ".")) + key, value);
                detailMap.put("query.output." + key, value);
            } catch (SQLException e) {
                throw new HandymanException("Fetch config failed", e);
            }
        });
    }

    public static String getResult(final HikariDataSource hikariDataSource, final String value, final ObjectNode detailMap) {
        try (final Connection connection = hikariDataSource.getConnection()) {
            final List<String> formattedQuery = getFormattedQuery(value);
            for (var sqlToExecute : formattedQuery) {
                try (final Statement stmt = connection.createStatement()) {
                    try (var rs = stmt.executeQuery(sqlToExecute)) {
                        if (rs.next()) {
                            return rs.getString(1);
                        }
                    }
                    var warnings = ExceptionUtil.completeSQLWarning(stmt.getWarnings());
                    detailMap.put(sqlToExecute + ".stmtCount", stmt.getUpdateCount());
                    detailMap.put(sqlToExecute + ".warnings", warnings);
                    stmt.clearWarnings();
                } catch (SQLSyntaxErrorException ex) {
                    log.error("Stopping execution, General Error executing sql for {} with for campaign {}", sqlToExecute, ex);
                    detailMap.put(sqlToExecute + ".exception", ExceptionUtil.toString(ex));
                    throw new HandymanException("Process failed", ex);
                } catch (SQLException ex) {
                    log.error("Continuing to execute, even though SQL Error executing sql for {} ", sqlToExecute, ex);
                    detailMap.put(sqlToExecute + ".exception", ExceptionUtil.toString(ex));
                } catch (Throwable ex) {
                    log.error("Stopping execution, General Error executing sql for {} with for campaign {}", sqlToExecute, ex);
                    detailMap.put(sqlToExecute + ".exception", ExceptionUtil.toString(ex));
                    throw new HandymanException("Process failed", ex);
                }
            }
        } catch (Throwable ex) {
            log.error("Stopping execution, General Error executing sql for {} with for campaign {}", value, ex);
            detailMap.put(value + ".exception", ExceptionUtil.toString(ex));
            throw new HandymanException("Process failed", ex);
        }
        return null;
    }

    public static List<String> getFormattedQuery(final String givenQuery) {
        var sqlWithoutQuotes = givenQuery.replaceAll("\"", "");
        var strings = List.of(sqlWithoutQuotes.split(";"));
        return strings.stream().map(String::trim)
                .filter(s -> !s.isEmpty() && !s.isBlank())
                .collect(Collectors.toUnmodifiableList());
    }

}