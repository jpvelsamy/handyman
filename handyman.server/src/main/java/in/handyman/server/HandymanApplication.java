package in.handyman.server;

import in.handyman.server.config.ApplicationHealthCheck;
import in.handyman.server.resource.HRavenResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HandymanApplication extends Application<Configuration> {

    public static void main(final String[] args) throws Exception {
        new HandymanApplication().run("server", "config.yaml");
    }

    @Override
    public void run(final Configuration configuration, final Environment environment) {
        final HRavenResource hRavenResource = new HRavenResource();
        environment
                .jersey()
                .register(hRavenResource);

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
