package jersey;

import static javax.ws.rs.core.MediaType.*;

import com.googlecode.objectify.ObjectifyService;
import gae.oauth2.oidc.schema.EndUser;
import gae.oauth2.oidc.schema.Client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/jersey")
public class Resource {

    static {
        ObjectifyService.register(EndUser.class);
        ObjectifyService.register(Client.class);
    }

    @GET
    @Produces(TEXT_PLAIN)
    public String index() {
        return "Jersey says Hello World!";
    }

    @GET
    @Path("/error")
    @Produces(TEXT_PLAIN)
    public Response error() {

        throw new StatusException("Jersey says: error!", Response.Status.BAD_REQUEST);
        // return Response.ok().build();
    }

}
