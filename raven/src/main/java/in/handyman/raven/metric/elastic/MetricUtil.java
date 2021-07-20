package in.handyman.raven.metric.elastic;

import in.handyman.raven.context.ProcessContext;
import in.handyman.raven.exception.HandymanException;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.FunctionTimer;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.binder.jvm.DiskSpaceMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import io.micrometer.core.instrument.binder.logging.Log4j2Metrics;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.core.instrument.binder.system.UptimeMetrics;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Log4j2
public class MetricUtil {

    private static final JvmGcMetrics jvmGcMetrics = new JvmGcMetrics();
    private static final JvmMemoryMetrics jvmMemoryMetrics = new JvmMemoryMetrics();
    private static final JvmThreadMetrics jvmThreadMetrics = new JvmThreadMetrics();
    private static final ProcessorMetrics processorMetrics = new ProcessorMetrics();
    private static final Log4j2Metrics log4j2Metrics = new Log4j2Metrics();
    private static final MariaDBMeterRegistry registry;

    static {
        try {
            registry = new MariaDBMeterRegistry(new MariaDBConfig() {

                @Override
                public String get(final String key) {
                    return null;
                }

                @Override
                public String host() {
                    return "jdbc:mysql://pazzmysaz.cherry.in:3306/spw_config?useUnicode=true&characterEncoding=utf8&dumpQueriesOnException=true";
                }

                @Override
                public String tableName() {
                    return "spw_config.`micrometer-metrics`";
                }

                @Override
                public String driver() {
                    return "org.mariadb.jdbc.Driver";
                }

                @Override
                public String userName() {
                    return "root";
                }

                @Override
                public String password() {
                    return "password";
                }

                @Override
                public Duration step() {
                    return Duration.ofSeconds(5);
                }

            }, Clock.SYSTEM);
        } catch (ClassNotFoundException e) {
            throw new HandymanException("", e);
        }
        registry.config().commonTags("instance", "Handyman");
        jvmGcMetrics.bindTo(registry);
        jvmMemoryMetrics.bindTo(registry);
        jvmThreadMetrics.bindTo(registry);
        processorMetrics.bindTo(registry);
        log4j2Metrics.bindTo(registry);
        new DiskSpaceMetrics(new File(System.getProperty("user.dir"))).bindTo(registry);
        new UptimeMetrics().bindTo(registry);
    }

    public static void add(final String lambdaName, final ProcessContext context) {
        Counter.builder("Counter-Handyman").tag("lambdaName", lambdaName).tags("processId", String.valueOf(context.getProcessId()))
                .tags("processName", context.getProcessName())
                .tags("status", String.valueOf(context.getStatus()))
                .register(registry);
        FunctionTimer.builder("FunctionTimer-Handyman", context, ProcessContext::getProcessId, p -> System.nanoTime() - p.getStart(), TimeUnit.NANOSECONDS)
                .tags("processId", String.valueOf(context.getProcessId()))
                .tags("processName", context.getProcessName())
                .tags("status", String.valueOf(context.getStatus()))
                .register(registry);
        registry.publish();
        Timer.builder("Timer-Handyman")
                .tags("processId", String.valueOf(context.getProcessId()))
                .tags("processName", context.getProcessName())
                .tags("status", String.valueOf(context.getStatus()))
                .publishPercentiles(0.5, 0.95)
                .register(registry);
    }

    public static MeterRegistry getCompositeMeterRegistry() {
        return registry;
    }
}
