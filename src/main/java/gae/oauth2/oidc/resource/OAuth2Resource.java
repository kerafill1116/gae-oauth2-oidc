package gae.oauth2.oidc.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gae.oauth2.oidc.*;
import gae.oauth2.oidc.Error;
import gae.oauth2.oidc.datastore.AccessTokenFactoryInterface;
import gae.oauth2.oidc.datastore.ClientFactoryInterface;
import gae.oauth2.oidc.datastore.ResourceServerFactoryInterface;
import gae.oauth2.oidc.datastore.objectify.AccessTokenFactoryImpl;
import gae.oauth2.oidc.datastore.objectify.ClientFactoryImpl;
import gae.oauth2.oidc.datastore.objectify.ResourceServerFactoryImpl;
import gae.oauth2.oidc.schema.AccessToken;
import gae.oauth2.oidc.schema.Client;
import gae.oauth2.oidc.schema.GrantType;
import gae.oauth2.oidc.schema.ResourceServer;
import gae.oauth2.oidc.schema.TokenType;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.Date;

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
        if(!client.getGrantTypes().contains(accessTokenRequest.getGrantType()))
            throw new ErrorException(Error.UNAUTHORIZED_CLIENT);

        String resourceServerId = accessTokenRequest.getResourceServerId();
        if(StringUtils.isBlank(resourceServerId))
            resourceServerId = client.getResourceServerIds().get(0);
        ResourceServerFactoryInterface resourceServerFactory = new ResourceServerFactoryImpl();
        ResourceServer resourceServer = resourceServerFactory.get(resourceServerId);
        if(resourceServer == null)
            throw new ErrorException(Error.UNAUTHORIZED_CLIENT);
        if(!resourceServer.getScope().containsAll(accessTokenRequest.getScope()))
            throw new ErrorException(Error.INVALID_SCOPE);

        AccessTokenFactoryInterface accessTokenFactory = new AccessTokenFactoryImpl();
        AccessToken accessToken = null;
        switch(accessTokenRequest.getGrantType()) {
            case AUTHORIZATION_CODE:
                // to be implemented
                break;
            case REFRESH_TOKEN:
                // to be implemented
                break;
            case PASSWORD:
                // to be implemented
                break;
            case CLIENT_CREDENTIALS:
                // generate access token with particular resource server and client id
                accessToken = new AccessToken(TokenType.BEARER, client.getAccessTokenMaxAge(), false, accessTokenRequest.getScope(), client.getId(), resourceServerId, new Date());
                accessTokenFactory.save(accessToken);
                break;
        }

        // "{ \"id\" : \"" + clientCredentials.getId() + "\", \"secret\" : \"" + clientCredentials.getSecret() + "\" }"
        ObjectMapper mapper = new ObjectMapper();
        Response.ResponseBuilder rb = Response.ok();
        rb.type(MediaType.APPLICATION_JSON_TYPE);
        rb.header(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
        rb.header("Pragma", "no-cache");
        rb.header(HttpHeaders.EXPIRES, "0");
        rb.entity(mapper.writeValueAsString(accessToken));
        return rb.build();

    }



}
