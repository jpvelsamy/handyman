package in.handyman.server;

import in.handyman.server.config.ApplicationHealthCheck;
import in.handyman.server.resource.HRavenResource;
import in.handyman.server.schedular.InstanceJobCreator;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.extern.log4j.Log4j2;
import org.quartz.SchedulerException;

@Log4j2
public class HandymanApplication extends Application<Configuration> {


    public static void main(final String[] args) throws Exception {
        new HandymanApplication().run("server", "config.yaml");
    }

    @Override
    public void run(final Configuration configuration, final Environment environment) throws SchedulerException {
        final HRavenResource hRavenResource = new HRavenResource();
        environment
                .jersey()
                .register(hRavenResource);
        var scheduler = InstanceJobCreator.init();
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



    @Override
    public void initialize(final Bootstrap<Configuration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        super.initialize(bootstrap);
    }
}
