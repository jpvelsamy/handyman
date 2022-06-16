package in.handyman.raven.util;

import com.zaxxer.hikari.HikariDataSource;
import in.handyman.raven.exception.HandymanException;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class CommonQueryUtil {

    public static void addKeyConfig(final Map<String, String> configContext, final Logger logger,
                                    final ResultSet rs, final int columnCount, final String prefix) {
        IntStream.range(1, columnCount + 1).forEach(i -> {
            try {
                var key = rs.getMetaData().getColumnLabel(i);
                var value = rs.getString(i);
                configContext.put(((prefix.isBlank() || prefix.isEmpty()) ? "" : (prefix + ".")) + key, value);
                logger.info("query.output." + key, value);
            } catch (SQLException e) {
                logger.error("failed to set value", e);
                throw new HandymanException("Fetch config failed", e);
            }
        });
    }


    public static String getResult(final Jdbi jdbi, final String value) {
        return jdbi.withHandle(handle -> {
            final List<String> formattedQuery = getFormattedQuery(value);
            return formattedQuery.stream().flatMap(s -> handle.createQuery(s).map((rs, ctx) -> rs.getString(1)).stream()).findFirst().orElse(null);
        });
    }

    public static String getResult(final HikariDataSource hikariDataSource, final String value, final Logger logger) {
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
                    logger.info(sqlToExecute + ".stmtCount", stmt.getUpdateCount());
                    logger.info(sqlToExecute + ".warnings", warnings);
                    stmt.clearWarnings();
                } catch (SQLSyntaxErrorException ex) {
                    log.error("Stopping execution, General Error executing sql for {} with for campaign {}", sqlToExecute, ex);
                    logger.info(sqlToExecute + ".exception", ExceptionUtil.toString(ex));
                    throw new HandymanException("Process failed", ex);
                } catch (SQLException ex) {
                    log.error("Continuing to execute, even though SQL Error executing sql for {} ", sqlToExecute, ex);
                    logger.info(sqlToExecute + ".exception", ExceptionUtil.toString(ex));
                } catch (Exception ex) {
                    log.error("Stopping execution, General Error executing sql for {} with for campaign {}", sqlToExecute, ex);
                    logger.info(sqlToExecute + ".exception", ExceptionUtil.toString(ex));
                    throw new HandymanException("Process failed", ex);
                }
            }
        } catch (Exception ex) {
            log.error("Stopping execution, General Error executing sql for {} with for campaign {}", value, ex);
            logger.info(value + ".exception", ExceptionUtil.toString(ex));
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
