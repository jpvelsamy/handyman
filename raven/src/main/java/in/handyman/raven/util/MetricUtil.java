package in.handyman.raven.util;

import com.sun.net.httpserver.HttpServer;
import in.handyman.raven.context.ProcessContext;
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
import io.micrometer.core.instrument.search.Search;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

@Log4j2
public class MetricUtil {

    private static final JvmGcMetrics jvmGcMetrics = new JvmGcMetrics();
    private static final JvmMemoryMetrics jvmMemoryMetrics = new JvmMemoryMetrics();
    private static final JvmThreadMetrics jvmThreadMetrics = new JvmThreadMetrics();
    private static final ProcessorMetrics processorMetrics = new ProcessorMetrics();
    private static final Log4j2Metrics log4j2Metrics = new Log4j2Metrics();
    private static final PrometheusMeterRegistry registry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);

    static {
        registry.config().commonTags("instance", "Handyman");
        jvmGcMetrics.bindTo(registry);
        jvmMemoryMetrics.bindTo(registry);
        jvmThreadMetrics.bindTo(registry);
        processorMetrics.bindTo(registry);
        log4j2Metrics.bindTo(registry);
        new DiskSpaceMetrics(new File(System.getProperty("user.dir"))).bindTo(registry);
        new UptimeMetrics().bindTo(registry);


        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(9099), 0);
            server.createContext("/prometheus", httpExchange -> {
                String response = registry.scrape();
                final String openMetricsScrape = registry.scrape(TextFormat.CONTENT_TYPE_OPENMETRICS_100);

                httpExchange.sendResponseHeaders(200, openMetricsScrape.getBytes().length);
                try (OutputStream os = httpExchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            });
            new Thread(server::start).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void add(final String lambdaName, final ProcessContext context) {
        Counter.builder("Counter-Handyman").tag("lambdaName", lambdaName).tags("processId", String.valueOf(context.getProcessId()))
                .tags("processName", context.getProcessName())
                .tags("status", String.valueOf(context.getStatus())).register(registry);
        FunctionTimer.builder("FunctionTimer-Handyman", context, ProcessContext::getProcessId, p -> System.nanoTime() - p.getStart(), TimeUnit.NANOSECONDS)
                .tags("processId", String.valueOf(context.getProcessId()))
                .tags("processName", context.getProcessName())
                .tags("status", String.valueOf(context.getStatus()))
                .register(registry);
        Timer.builder("Timer-Handyman")
                .tags("processId", String.valueOf(context.getProcessId()))
                .tags("processName", context.getProcessName())
                .tags("status", String.valueOf(context.getStatus()))
                .publishPercentiles(0.5, 0.95)
                .register(registry);
        final Search search = registry.find("Handyman");
        final Counter counter1 = search.counter();
        if (counter1 != null) {
            counter1.increment();
        }
    }

    public static MeterRegistry getCompositeMeterRegistry() {
        return registry;
    }
}
