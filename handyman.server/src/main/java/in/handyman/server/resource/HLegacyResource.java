package in.handyman.server.resource;

import in.handyman.server.StartProcess;
import io.swagger.annotations.Api;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Api
@Path("/legacy-vm")
@Produces(MediaType.APPLICATION_JSON)
public class HLegacyResource {

    @POST
    @Path("/{instanceName}")
    public String runPipeline(@PathParam("instanceName") final String instanceName) {
        return new StartProcess().start(instanceName);
    }


    @POST
    @Path("/{instanceName}/schedule")
    public String setCronExpression(@PathParam("instanceName") final String instanceName,
                                     final String expression) {
        return new StartProcess().start(instanceName);
    }

}
