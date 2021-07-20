package in.handyman.raven.metric.elastic;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.step.StepMeterRegistry;
import io.micrometer.core.instrument.util.MeterPartition;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.joining;

@Log4j2
public class MariaDBMeterRegistry extends StepMeterRegistry {

    private static final String cQuery = "CREATE TABLE if not exists %s ( `name` text NOT NULL, `count` float8 NULL DEFAULT 0, `value` float8 NULL DEFAULT 0, `sum` float8 NULL DEFAULT 0, `mean` float8 NULL DEFAULT 0, `duration` float8 NULL DEFAULT 0, `max` float8 NULL DEFAULT 0, `total` float8 NULL DEFAULT 0, `unknown` float8 NULL DEFAULT 0, `active` float8 NULL DEFAULT 0, `created_on` timestamp DEFAULT current_timestamp() , tags text NULL DEFAULT '[]' ) ENGINE = InnoDB DEFAULT CHARSET = utf8;";
    private static final String iQuery = "INSERT INTO %s (name, count, value, sum, mean, duration, max, active, created_on,tags) VALUES(?,?,?,?,?,?,?,?,current_timestamp(),?);";
    private final HikariDataSource dataSource;
    private final MariaDBConfig config;

    public MariaDBMeterRegistry(final MariaDBConfig config, final Clock clock) throws ClassNotFoundException {
        super(config, clock);
        this.config = config;
        Class.forName(config.driver());
        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(config.host());
        hikariConfig.setUsername(config.userName());
        hikariConfig.setPassword(config.password());
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(hikariConfig);
    }

    private void createTableIfNeeded() {
        if (!config.autoCreateTable()) {
            return;
        }
        try (var conn = this.dataSource.getConnection()) {
            try (var stmt = conn.createStatement()) {
                stmt.execute(String.format(cQuery, config.tableName()));
            }
        } catch (SQLException e) {
            log.error("Metric table failed to create", e);
        }
    }

    private String getTags(final Meter meter) {
        return getConventionTags(meter.getId()).stream()
                .map(t -> String.format("{\"%s\":\"%s\"}", t.getKey(), t.getValue()))
                .collect(joining(",", "[", "]"));
    }


    @Override
    protected void publish() {
        if (config.enabled()) {
            createTableIfNeeded();
            try (var conn = this.dataSource.getConnection()) {
                final String iFormattedQuery = String.format(iQuery, config.tableName());
                MeterPartition.partition(this, config.batchSize()).forEach(meters -> {
                    try (var stmt = conn.prepareStatement(iFormattedQuery)) {
                        for (var meter : meters) {
                            final Payload payload = new Payload();
                            meter.use(gauge -> payload.setValue(gauge.value()),
                                    counter -> payload.setCount(counter.count()), timer -> {
                                        payload.setCount((double) timer.count());
                                        payload.setSum(timer.totalTime(getBaseTimeUnit()));
                                        payload.setMean(timer.mean(getBaseTimeUnit()));
                                        payload.setMax(timer.max(getBaseTimeUnit()));
                                    }, distributionSummary -> {
                                        payload.setCount((double) distributionSummary.count());
                                        payload.setSum(distributionSummary.totalAmount());
                                        payload.setMean(distributionSummary.mean());
                                        payload.setMax(distributionSummary.max());
                                    }, longTaskTimer -> {
                                        payload.setActive((double) longTaskTimer.activeTasks());
                                        payload.setDuration(longTaskTimer.duration(getBaseTimeUnit()));
                                    }, timeGauge -> payload.setValue(timeGauge.value(getBaseTimeUnit())),
                                    functionCounter -> payload.setCount(functionCounter.count()), functionTimer -> {
                                        payload.setSum(functionTimer.totalTime(getBaseTimeUnit()));
                                        payload.setMean(functionTimer.mean(getBaseTimeUnit()));
                                        payload.setCount(functionTimer.count());
                                    }, meter1 -> {
                                    });

                            //name, count, value, sum, mean, duration, max, total, `unknown`, active, created_on,tags
                            stmt.setString(1, meter.getId().getName());
                            stmt.setDouble(2, payload.getCount());
                            stmt.setDouble(3, payload.getValue());
                            stmt.setDouble(4, payload.getSum());
                            stmt.setDouble(5, payload.getMean());
                            stmt.setDouble(6, payload.getDuration());
                            stmt.setDouble(7, payload.getMax());
                            stmt.setDouble(8, payload.getActive());
                            stmt.setString(9, getTags(meter));
                            stmt.addBatch();
                        }
                        stmt.executeBatch();
                    } catch (SQLException e) {
                        log.error("Metric table failed to Insert", e);
                    }
                });
            } catch (SQLException e) {
                log.error("Metric table failed to Insert", e);
            }
        }
    }

    @Override
    protected TimeUnit getBaseTimeUnit() {
        return TimeUnit.MILLISECONDS;
    }

    static class Payload {

        private String name;
        private Double count = 0d;
        private Double value = 0d;
        private Double sum = 0d;
        private Double mean = 0d;
        private Double duration = 0d;
        private Double max = 0d;
        private Double active = 0d;

        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
        }

        public Double getCount() {
            return count;
        }

        public void setCount(final Double count) {
            this.count = count;
        }

        public Double getValue() {
            return value;
        }

        public void setValue(final Double value) {
            this.value = value;
        }

        public Double getSum() {
            return sum;
        }

        public void setSum(final Double sum) {
            this.sum = sum;
        }

        public Double getMean() {
            return mean;
        }

        public void setMean(final Double mean) {
            this.mean = mean;
        }

        public Double getDuration() {
            return duration;
        }

        public void setDuration(final Double duration) {
            this.duration = duration;
        }

        public Double getMax() {
            return max;
        }

        public void setMax(final Double max) {
            this.max = max;
        }


        public Double getActive() {
            return active;
        }

        public void setActive(final Double active) {
            this.active = active;
        }

    }


}


