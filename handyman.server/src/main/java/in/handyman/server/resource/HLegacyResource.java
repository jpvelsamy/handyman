package in.handyman.server.resource;

import in.handyman.server.StartProcess;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/legacy-vm")
@Produces(MediaType.APPLICATION_JSON)
public class HLegacyResource {

    @POST
    public String runPipeline(@Valid final String instanceName) {
        return new StartProcess().start(instanceName);
    }


    @POST
    public String setExprex(@Valid final String instanceName) {
        return new StartProcess().start(instanceName);
    }

}
