package in.handyman.server.schedular;

import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.access.repo.HandymanRepo;
import in.handyman.raven.lambda.access.repo.HandymanRepoR2Impl;
import in.handyman.raven.lambda.doa.ConfigStore;
import in.handyman.raven.lambda.doa.ConfigType;
import lombok.extern.log4j.Log4j2;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Properties;
import java.util.Set;

@Log4j2
public class InstanceJobCreator implements Job {

    private static final QuartzRepo REPO = new QuartzRepo();
    private static final HandymanRepo HANDYMAN_REPO = new HandymanRepoR2Impl();
    private static final String INSTANCE_JOB_CREATOR = "InstanceJobCreator";

    public static Scheduler init() throws SchedulerException {
        final Scheduler scheduler = getScheduler();
        final Set<String> awaitingTriggers = REPO.findAwaitingTriggers();
        if (!awaitingTriggers.contains(INSTANCE_JOB_CREATOR)) {

            // define the job and tie it to our HelloJob class
            var jobBuilder = JobBuilder.newJob(InstanceJobCreator.class);

            var jobDetail = jobBuilder
                    .withIdentity(INSTANCE_JOB_CREATOR)
                    .build();

            var trigger = TriggerBuilder.newTrigger()
                    .withIdentity(INSTANCE_JOB_CREATOR)
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/20 * 1/1 * ? *")).build();
            scheduler.scheduleJob(jobDetail, trigger);

        }

        return scheduler;
    }

    private static Scheduler getScheduler() throws SchedulerException {
        final Properties properties = getProperties();
        var schedulerFactory = new StdSchedulerFactory(properties);
        return schedulerFactory.getScheduler();
    }

    private static Properties getProperties() {
        var prop = new Properties();
        //RMI configuration to make the client to connect to the
//            Quartz server
        prop.put("org.quartz.scheduler.rmi.export", "true");
        prop.put("org.quartz.scheduler.rmi.createRegistry", "true");
        prop.put("org.quartz.scheduler.rmi.registryHost", "localhost");
        prop.put("org.quartz.scheduler.rmi.registryPort", "1099");
        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        prop.put("org.quartz.threadPool.threadCount", "2");
        //Quartz Server Properties
        prop.put("quartz.scheduler.instanceName", "ServerScheduler");
        prop.put("org.quartz.scheduler.instanceId", "AUTO");
        prop.put("org.quartz.scheduler.skipUpdateCheck", "true");
        prop.put("org.quartz.scheduler.jobFactory.class", "org.quartz.simpl.SimpleJobFactory");
        prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        prop.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
        prop.put("org.quartz.jobStore.dataSource", "quartzDataSource");
        prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
        prop.put("org.quartz.jobStore.isClustered", "false");
        //MYSQL DATABASE CONFIGURATION
        //If we do not specify this configuration, QUARTZ will
//            use RAM(in-memory) to store jobs
        //Once we restart QUARTZ, the jobs will not be persisted
        // Configure your MySQL properties
        prop.put("org.quartz.dataSource.quartzDataSource.driver", "org.mariadb.jdbc.Driver");
        prop.put("org.quartz.dataSource.quartzDataSource.URL", "jdbc:mariadb://localhost:3306/handyman_qrtz");
        prop.put("org.quartz.dataSource.quartzDataSource.user", "root");
        prop.put("org.quartz.dataSource.quartzDataSource.password", "password");
        return prop;
    }

    @Override
    public void execute(final JobExecutionContext jobExecutionContext) {
        log.info("Initiated InstanceJobCreator {}", jobExecutionContext);
        final Set<String> awaitingTriggers = REPO.findAwaitingTriggers();
        //RavenVM
        HANDYMAN_REPO.findConfigEntitiesByVariable(ConfigType.PIPELINE, InstanceJob.CRON)
                .stream()
                .filter(configStore -> !awaitingTriggers.contains(configStore.getName()))
                .forEach(configStore -> {
                    final String configStoreName = configStore.getName();
                    var triggerNew = TriggerBuilder.newTrigger()
                            .withIdentity(configStoreName)
                            .withSchedule(CronScheduleBuilder.cronSchedule(configStore.getValue())).build();
                    var jobBuilder = JobBuilder.newJob(InstanceJob.class);
                    var data = new JobDataMap();
                    data.put(InstanceJob.NAME, configStoreName);
                    data.put(InstanceJob.VM, InstanceJob.HandymanVM.RAVEN_VM.name());
                    data.put(InstanceJob.LOAD_TYPE, HANDYMAN_REPO.findConfigEntities(ConfigType.PIPELINE,
                            configStoreName, InstanceJob.LOAD_TYPE_VARIABLE).map(ConfigStore::getValue).orElse(null));

                    var jobDetail = jobBuilder
                            .usingJobData(data)
                            .withIdentity(configStoreName)
                            .build();
                    try {

                        jobExecutionContext.getScheduler().scheduleJob(jobDetail, triggerNew);
                        log.info("added {}", jobDetail);

                    } catch (SchedulerException e) {
                        throw new HandymanException("scheduler Failed", e);
                    }
                });
    }
}
