package jersey;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static javax.ws.rs.core.MediaType.*;

import com.googlecode.objectify.ObjectifyService;
import gae.oauth2.oidc.schema.EndUser;
import gae.oauth2.oidc.schema.Gender;
import gae.oauth2.oidc.schema.OAuth2Client;
import org.apache.commons.collections4.set.ListOrderedSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;

@Path("/jersey")
public class Resource {

    static {
        ObjectifyService.register(EndUser.class);
        ObjectifyService.register(OAuth2Client.class);
    }

    @GET
    @Path("/")
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
