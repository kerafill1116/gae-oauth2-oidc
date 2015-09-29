package jersey;

import static javax.ws.rs.core.MediaType.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/jersey")
public class Resource {

    @GET
    @Path("/")
    @Produces(TEXT_PLAIN)
    public String index() {
        return "Jersey says: Hello world!";
    }
}
