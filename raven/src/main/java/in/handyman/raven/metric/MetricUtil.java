package in.handyman.raven.metric;

import in.handyman.raven.connection.DataSource;
import in.handyman.raven.context.ProcessContext;
import io.github.mweirauch.micrometer.jvm.extras.ProcessMemoryMetrics;
import io.github.mweirauch.micrometer.jvm.extras.ProcessThreadMetrics;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.LongTaskTimer;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.binder.jvm.ClassLoaderMetrics;
import io.micrometer.core.instrument.binder.jvm.DiskSpaceMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmCompilationMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmHeapPressureMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmInfoMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import io.micrometer.core.instrument.binder.system.FileDescriptorMetrics;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.core.instrument.binder.system.UptimeMetrics;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.joining;

@Log4j2
public class MetricUtil {

    private static final PrometheusMeterRegistry registry;
    private static final String cQuery = "CREATE TABLE if not exists %s ( `name` text NOT NULL, `count` float8 NULL DEFAULT 0, `value` float8 NULL DEFAULT 0, `sum` float8 NULL DEFAULT 0, `mean` float8 NULL DEFAULT 0, `duration` float8 NULL DEFAULT 0, `max` float8 NULL DEFAULT 0, `total` float8 NULL DEFAULT 0, `unknown` float8 NULL DEFAULT 0, `active` float8 NULL DEFAULT 0, `created_on` timestamp DEFAULT current_timestamp() , tags text NULL DEFAULT '[]' ) ENGINE = InnoDB DEFAULT CHARSET = utf8;";
    private static final String iQuery = "INSERT INTO %s (name, count, value, sum, mean, duration, max, active, created_on,tags) VALUES(?,?,?,?,?,?,?,?,current_timestamp(),?);";

    static {
        registry = new PrometheusMeterRegistry(new PrometheusConfig() {

            @Override
            public String get(final String key) {
                return null;
            }

            @Override
            public Duration step() {
                return Duration.ofSeconds(5);
            }

        });
        registry.config().commonTags("instance", "Handyman");
        new JvmCompilationMetrics().bindTo(registry);
        new JvmGcMetrics().bindTo(registry);
        new JvmHeapPressureMetrics().bindTo(registry);
        new JvmInfoMetrics().bindTo(registry);
        new JvmMemoryMetrics().bindTo(registry);
        new JvmThreadMetrics().bindTo(registry);
        new ProcessMemoryMetrics().bindTo(registry);
        new ProcessThreadMetrics().bindTo(registry);
        new DiskSpaceMetrics(new File(".")).bindTo(registry);
        new UptimeMetrics().bindTo(registry);
        new ProcessorMetrics().bindTo(registry);
        new ClassLoaderMetrics().bindTo(registry);
        new FileDescriptorMetrics().bindTo(registry);
    }

    public static void addAfter(final ProcessContext context) {
        final String name = "process." + context.getProcessName();
        Counter.builder(name)
                .tag("instanceName", context.getInstanceName())
                .tag("status", String.valueOf(context.getStatus()))
                .register(registry).increment();
        Counter.builder(name + ".try")
                .description("Number of Try Actions")
                .tag("instanceName", context.getInstanceName())
                .tags("status", String.valueOf(context.getTryStatus()))
                .register(registry).increment();
        Counter.builder(name + ".catch")
                .description("Number of Catch Actions")
                .tag("instanceName", context.getInstanceName())
                .tags("status", String.valueOf(context.getCatchStatus()))
                .register(registry).increment();
        Counter.builder(name + ".finally")
                .description("Number of Finally Actions")
                .tag("instanceName", context.getInstanceName())
                .tags("status", String.valueOf(context.getFinallyStatus()))
                .register(registry).increment();
        final long amount = System.nanoTime() - context.getStart();
        Timer.builder(name + ".requests")
                .tag("instanceName", context.getInstanceName())
                .register(registry)
                .record(amount, TimeUnit.NANOSECONDS);
        LongTaskTimer.builder(name + ".requests.slow")
                .tag("instanceName", context.getInstanceName())
                .register(registry);
        DistributionSummary.builder(name + ".detail")
                .tag("instanceName", context.getInstanceName())
                .tag("processId", String.valueOf(context.getProcessId()))
                .register(registry).record(amount);
        persist();
    }

    private static void persist() {
        try (var conn = DataSource.getConnection()) {
            try (var stmt = conn.createStatement()) {
                stmt.execute(String.format(cQuery, "spw_config.`micrometer-metrics`"));
            }
            final String iFormattedQuery = String.format(iQuery, "spw_config.`micrometer-metrics`");
            try (var stmt = conn.prepareStatement(iFormattedQuery)) {
                final List<Meter> meters = registry.getMeters();
                for (var meter : meters) {
                    final Payload payload = new Payload();
                    meter.use(gauge -> payload.setValue(gauge.value()),
                            counter -> payload.setCount(counter.count()),
                            timer -> {
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
                                payload.setMean(longTaskTimer.mean(getBaseTimeUnit()));
                                payload.setMax(longTaskTimer.max(getBaseTimeUnit()));
                                payload.setActive((double) longTaskTimer.activeTasks());
                                payload.setDuration(longTaskTimer.duration(getBaseTimeUnit()));
                            }, timeGauge -> payload.setValue(timeGauge.value(getBaseTimeUnit())),
                            functionCounter -> payload.setCount(functionCounter.count()), functionTimer -> {
                                payload.setSum(functionTimer.totalTime(getBaseTimeUnit()));
                                payload.setMean(functionTimer.mean(getBaseTimeUnit()));
                                payload.setCount(functionTimer.count());
                            }, log::debug);
                    final String tags = meter.getId().getTags().stream().map(t -> String.format("{\"%s\":\"%s\"}", t.getKey(), t.getValue()))
                            .collect(joining(",", "[", "]"));
                    stmt.setString(1, meter.getId().getName());
                    stmt.setDouble(2, payload.getCount());
                    stmt.setDouble(3, payload.getValue());
                    stmt.setDouble(4, payload.getSum());
                    stmt.setDouble(5, payload.getMean());
                    stmt.setDouble(6, payload.getDuration());
                    stmt.setDouble(7, payload.getMax());
                    stmt.setDouble(8, payload.getActive());
                    stmt.setString(9, tags);
                    stmt.addBatch();
                }
                stmt.executeBatch();
            }
        } catch (SQLException e) {
            log.error("Metric table failed to create", e);
        }


    }

    private static TimeUnit getBaseTimeUnit() {
        return TimeUnit.NANOSECONDS;
    }

    @ToString
    public static class Payload {

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
            return Optional.ofNullable(count).filter(aDouble -> !aDouble.isInfinite() && !aDouble.isNaN()).orElse(0d);
        }

        public void setCount(final Double count) {
            this.count = count;
        }

        public Double getValue() {
            return Optional.ofNullable(value).filter(aDouble -> !aDouble.isInfinite() && !aDouble.isNaN()).orElse(0d);
        }

        public void setValue(final Double value) {
            this.value = value;
        }

        public Double getSum() {
            return Optional.ofNullable(sum).filter(aDouble -> !aDouble.isInfinite() && !aDouble.isNaN()).orElse(0d);
        }

        public void setSum(final Double sum) {
            this.sum = sum;
        }

        public Double getMean() {
            return Optional.ofNullable(mean).filter(aDouble -> !aDouble.isInfinite() && !aDouble.isNaN()).orElse(0d);
        }

        public void setMean(final Double mean) {
            this.mean = mean;
        }

        public Double getDuration() {
            return Optional.ofNullable(duration).filter(aDouble -> !aDouble.isInfinite() && !aDouble.isNaN()).orElse(0d);
        }

        public void setDuration(final Double duration) {
            this.duration = duration;
        }

        public Double getMax() {
            return Optional.ofNullable(max).filter(aDouble -> !aDouble.isInfinite() && !aDouble.isNaN()).orElse(0d);
        }

        public void setMax(final Double max) {
            this.max = max;
        }


        public Double getActive() {
            return Optional.ofNullable(active).filter(aDouble -> !aDouble.isInfinite() && !aDouble.isNaN()).orElse(0d);
        }

        public void setActive(final Double active) {
            this.active = active;
        }

    }

}