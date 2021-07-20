package in.handyman.server;

import in.handyman.server.resource.LambdaResource;
import io.dropwizard.Application;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Environment;

import java.util.Objects;

public class HandymanApp extends Application<DefaultConfiguration> {

    public static void main(final String[] args) throws Exception {
        final HandymanApp handymanApp = new HandymanApp();
        handymanApp.run("server","server/config.yml");
    }

    @Override
    public void run(final DefaultConfiguration configuration, final Environment environment) {
        final JerseyEnvironment jersey = environment.jersey();
        jersey.register(new LambdaResource());
    }
}

