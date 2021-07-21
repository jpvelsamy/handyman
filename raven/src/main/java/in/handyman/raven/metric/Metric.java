package in.handyman.raven.metric;

import lombok.Data;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.CompilationMXBean;
import java.lang.management.ManagementFactory;

public class Metric {

    @Data
    static class MetricPayload {

        private Integer loadedClassCount;
        private Long unLoadedClassCount;
        private Long totalCompilationTime;

    }

    void classLoaderMetrics(final MetricPayload payload) {
        final ClassLoadingMXBean classLoadingBean = ManagementFactory.getClassLoadingMXBean();
        payload.setLoadedClassCount(classLoadingBean.getLoadedClassCount());
        payload.setUnLoadedClassCount(classLoadingBean.getUnloadedClassCount());
    }

    void jvmCompilationMetrics(final MetricPayload payload) {
        final CompilationMXBean compilationBean = ManagementFactory.getCompilationMXBean();
        if (compilationBean != null && compilationBean.isCompilationTimeMonitoringSupported()) {
            payload.setTotalCompilationTime(compilationBean.getTotalCompilationTime());
        }
    }
}
