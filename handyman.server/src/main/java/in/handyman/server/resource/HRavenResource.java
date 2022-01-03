package in.handyman.server.resource;

import in.handyman.raven.lambda.doa.audit.PipelineExecutionAudit;
import in.handyman.raven.lambda.process.LContext;
import in.handyman.raven.lambda.process.LambdaEngine;
import io.swagger.annotations.Api;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Api
@Path("/raven-vm")
@Produces(MediaType.APPLICATION_JSON)
public class HRavenResource {

    @POST
    public PipelineExecutionAudit runPipeline(@Valid final LContext lContext) {
        return LambdaEngine.start(lContext);
    }

}
