package in.handyman.raven.core.server;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class HandymanApplication extends Application<HandymanConfiguration> {

    public static void main(final String... args) throws Exception {
        if (args.length != 0) {
            new HandymanApplication().run(args);
        } else {
            new HandymanApplication().run("server", "/workspace/raven/src/main/resources/handyman.yaml");
        }
    }

    @Override
    public void initialize(final Bootstrap<HandymanConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(final HandymanConfiguration handymanConfiguration) {
                // this would be the preferred way to set up swagger, you can also construct the object here programmatically if you want
                return handymanConfiguration.swaggerBundleConfiguration;
            }
        });
    }

    @Override
    public void run(final HandymanConfiguration configuration, final Environment environment) {
        // add your resources as usual
        environment.jersey().register(new HandymanResource());
    }
}
