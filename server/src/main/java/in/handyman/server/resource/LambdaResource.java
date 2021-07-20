package in.handyman.server.resource;

import in.handyman.raven.context.ProcessContext;
import in.handyman.raven.process.HRequestResolver;
import in.handyman.raven.process.ProcessEngine;
import io.micrometer.core.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/handyman/lambda")
@Produces(MediaType.APPLICATION_JSON)
public class LambdaResource {

    @GET
    @Timed
    public ProcessContext start() {
        return ProcessEngine.start(HRequestResolver.LoadType.FILE.name(), "cub.test");
    }

}
