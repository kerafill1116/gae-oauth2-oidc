package gae.oauth2.oidc.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import gae.oauth2.oidc.ClientAuthenticationCredentials;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

@Path("/oauth2")
public class OAuth2Resource {

    @POST
    @Path("/token")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response token(@HeaderParam("Authorization") String authorizationHeader, MultivaluedMap<String, String> form)
            throws JsonProcessingException {
        ClientAuthenticationCredentials clientCredentials = ClientAuthenticationCredentials.parse(authorizationHeader, form);

        return Response.ok("{ \"id\" : \"" + clientCredentials.getId() + "\", \"secret\" : \"" + clientCredentials.getSecret() + "\" }").build();
    }



}
