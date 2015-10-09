package gae.oauth2.oidc;

import com.fasterxml.jackson.core.JsonProcessingException;
import gae.oauth2.oidc.schema.TokenEndpointAuthMethod;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.core.MultivaluedMap;

public class ClientAuthenticationCredentials {
    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String BASIC_AUTH_PREFIX = "Basic ";
    private static final int BASIC_AUTH_PREFIX_LENGTH = BASIC_AUTH_PREFIX.length();
    private static final char SEMI_COLON = ':';

    private String id;
    private String secret;
    private TokenEndpointAuthMethod authMethod;

    public ClientAuthenticationCredentials(String id, String secret, TokenEndpointAuthMethod authMethod) {
        this.id = id;
        this.secret = secret;
        this.authMethod = authMethod;
    }

    public ClientAuthenticationCredentials(String id, String secret) {
        this(id, secret, null);
    }

    public ClientAuthenticationCredentials() {
        this(null, null);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public TokenEndpointAuthMethod getAuthMethod() {
        return authMethod;
    }

    public void setAuthMethod(TokenEndpointAuthMethod authMethod) {
        this.authMethod = authMethod;
    }

    public static ClientAuthenticationCredentials parse(String header, MultivaluedMap<String, String> form)
            throws JsonProcessingException {
        String id = null;
        String secret = null;
        TokenEndpointAuthMethod authMethod = null;

        // parse OAuth2-Post first (strongest)
        if(form.containsKey(CLIENT_ID)) {
            id = form.getFirst(CLIENT_ID);
            secret = form.getFirst(CLIENT_SECRET);
            authMethod = TokenEndpointAuthMethod.CLIENT_SECRET_POST;
        }

        // parse header as last resort
        if(authMethod == null) {
            if(StringUtils.isBlank(header) || !header.startsWith(BASIC_AUTH_PREFIX))
                throw new ErrorException(Error.INVALID_REQUEST);
            String credentials = new String(Base64.decodeBase64(header.substring(BASIC_AUTH_PREFIX_LENGTH).getBytes()));
            int pos = credentials.indexOf(SEMI_COLON);
            if(pos < 1)
                throw new ErrorException(Error.INVALID_REQUEST);
            id = credentials.substring(0, pos);
            secret = credentials.substring(pos + 1);
            authMethod = TokenEndpointAuthMethod.CLIENT_SECRET_BASIC;
        }

        if(authMethod == null)
            throw new ErrorException(Error.INVALID_REQUEST);

        return new ClientAuthenticationCredentials(id, secret, authMethod);
    }
}
