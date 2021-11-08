package in.handyman.server;

import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.access.repo.HandymanRepo;
import in.handyman.raven.lambda.access.repo.HandymanRepoR2Impl;
import in.handyman.raven.lambda.doa.ConfigStore;
import in.handyman.raven.lambda.doa.ConfigType;
import in.handyman.server.config.ApplicationHealthCheck;
import in.handyman.server.resource.HRavenResource;
import in.handyman.server.schedular.InstanceJob;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.extern.log4j.Log4j2;
import org.quartz.CronScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Collections;
import java.util.Optional;

@Log4j2
public class HandymanApplication extends Application<Configuration> {

    private static final HandymanRepo HANDYMAN_REPO = new HandymanRepoR2Impl();

    public static void main(final String[] args) throws Exception {
        new HandymanApplication().run("server", "config.yaml");
    }

    @Override
    public void run(final Configuration configuration, final Environment environment) throws SchedulerException {
        final HRavenResource hRavenResource = new HRavenResource();
        environment
                .jersey()
                .register(hRavenResource);
        var scheduler = getScheduler();
        environment.lifecycle().manage(new Managed() {
            @Override
            public void start() throws SchedulerException {
                scheduler.start();
            }

            @Override
            public void stop() throws Exception {
                scheduler.shutdown();
            }
        });
        final ApplicationHealthCheck healthCheck = new ApplicationHealthCheck();
        environment
                .healthChecks()
                .register("application", healthCheck);
    }

    private static Scheduler getScheduler() throws SchedulerException {
        var schedulerFactory = new StdSchedulerFactory();
        var scheduler = schedulerFactory.getScheduler();

        //RavenVM
        HANDYMAN_REPO.findConfigEntitiesByVariable(ConfigType.PIPELINE, InstanceJob.CRON)
                .forEach(configStore -> {
                    final String configStoreName = configStore.getName();
                    var triggerNew = TriggerBuilder.newTrigger().withIdentity(configStoreName,
                                    Optional.ofNullable(ConfigType.get(configStore.getConfigTypeId()))
                                            .map(Enum::name).orElse(null))
                            .usingJobData(InstanceJob.NAME, configStoreName)
                            .usingJobData(InstanceJob.VM, InstanceJob.HandymanVM.RAVEN_VM.name())
                            .usingJobData(InstanceJob.LOAD_TYPE, HANDYMAN_REPO.findConfigEntities(ConfigType.PIPELINE,
                                    configStoreName, InstanceJob.LOAD_TYPE_VARIABLE).map(ConfigStore::getValue).orElse(null))
                            .withSchedule(CronScheduleBuilder.cronSchedule(configStore.getValue())).build();
                    try {
                        scheduler.scheduleJob(triggerNew);
                    } catch (SchedulerException e) {
                        throw new HandymanException("scheduler Failed",e);
                    }
                });
        return scheduler;
    }

    @Override
    public void initialize(final Bootstrap<Configuration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        super.initialize(bootstrap);
    }
}
