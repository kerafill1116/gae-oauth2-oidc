package gae.oauth2.oidc.schema;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.nimbusds.jose.jwk.JWKSet;
import java.net.URL;
import java.util.Date;
import org.apache.commons.collections4.set.ListOrderedSet;

@Entity
public class OAuth2Client {

    @Id
    private String clientId;
    private String clientSecret;
    private Date clientIdIssuedAt;
    private Date clientSecretExpiresAt;
    private ListOrderedSet<URL> redirectUris;
    private TokenEndpointAuthMethod tokenEndpointAuthMethod;
    private ListOrderedSet<GrantType> grantTypes;
    private ListOrderedSet<ResponseType> responseTypes;
    private String clientName;
    private URL clientUri;
    private URL logoUri;
    private ListOrderedSet<String> scopes;
    private ListOrderedSet<String> contacts;
    private URL tosUri;
    private URL policyUri;
    private URL jwksUri;
    private JWKSet jwks;
    private String softwareId;
    private String softwareVersion;

    private OAuth2Client() {
        redirectUris = new ListOrderedSet<URL>();
        grantTypes = new ListOrderedSet<GrantType>();
        responseTypes = new ListOrderedSet<ResponseType>();
        scopes = new ListOrderedSet<String>();
        contacts = new ListOrderedSet<String>();
    }

    public ListOrderedSet<URL> getRedirectUris() {
        return redirectUris;
    }
}
