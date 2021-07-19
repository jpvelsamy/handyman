package in.handyman.raven.core.server;


import in.handyman.raven.core.context.ProcessContext;
import in.handyman.raven.core.process.HRequestResolver;
import in.handyman.raven.core.process.ProcessEngine;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.util.Optional;

@Path("/process")
@Api("/process")
public class HandymanResource {

    @POST
    @Path("/{instance}/{alias}/start")
    @ApiOperation(
            value = "Initiate a process",
            response = ProcessContext.class
    )
    public ProcessContext doProcess(@QueryParam("processLoadType") final Optional<String> processLoadType,
                                    @PathParam("instance") final String instance,
                                    @PathParam("alias") final String alias) {
        return ProcessEngine.start(processLoadType.orElse(HRequestResolver.LoadType.FILE.name()), String.format("%s#%s", instance, alias));
    }
}
