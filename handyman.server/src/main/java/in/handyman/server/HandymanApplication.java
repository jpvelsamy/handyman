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
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
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
        initScheduler(environment);
        final ApplicationHealthCheck healthCheck = new ApplicationHealthCheck();
        environment
                .healthChecks()
                .register("application", healthCheck);
    }

    private void initScheduler(final Environment environment) throws SchedulerException {
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
    }


    @Override
    public void initialize(final Bootstrap<Configuration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        bootstrap.addBundle(new SwaggerBundle<>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(final Configuration swaggerConfiguration) {
                final SwaggerBundleConfiguration swaggerBundleConfiguration = new SwaggerBundleConfiguration();
                swaggerBundleConfiguration.setResourcePackage(this.getClass().getPackageName());
                return swaggerBundleConfiguration;
            }

        });
        super.initialize(bootstrap);
    }
}
