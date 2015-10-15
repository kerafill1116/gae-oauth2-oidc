package gae.oauth2.oidc.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gae.oauth2.oidc.*;
import gae.oauth2.oidc.Error;
import gae.oauth2.oidc.datastore.ClientFactoryInterface;
import gae.oauth2.oidc.datastore.objectify.ClientFactoryImpl;
import gae.oauth2.oidc.schema.Client;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
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

        ClientFactoryInterface clientFactory = new ClientFactoryImpl();
        Client client = clientFactory.get(clientCredentials.getId());
        if(client == null || !client.getSecret().equals(clientCredentials.getSecret())) {
            // copy auth method of client if not null
            if(client != null)
                clientCredentials.setAuthMethod(client.getAuthMethod());
            throw new ErrorException(Error.INVALID_CLIENT, clientCredentials);
        }

        AccessTokenRequest accessTokenRequest = AccessTokenRequest.parse(form);

        // "{ \"id\" : \"" + clientCredentials.getId() + "\", \"secret\" : \"" + clientCredentials.getSecret() + "\" }"
        ObjectMapper mapper = new ObjectMapper();
        Response.ResponseBuilder rb = Response.ok();
        rb.type(MediaType.APPLICATION_JSON_TYPE);
        rb.header(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
        rb.header("Pragma", "no-cache");
        rb.header(HttpHeaders.EXPIRES, "0");
        rb.entity(mapper.writeValueAsString(clientCredentials));
        return rb.build();
    }



}
