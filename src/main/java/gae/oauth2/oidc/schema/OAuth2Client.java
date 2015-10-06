package gae.oauth2.oidc.schema;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import java.net.URI;
import java.util.Date;
import org.apache.commons.collections4.set.ListOrderedSet;

@Entity
public class OAuth2Client {

    @Id
    private String clientId;
    private String clientSecret;
    private Date clientIdIssuedAt;
    private Date clientSecretExpiresAt;

    private ListOrderedSet<URI> redirectUris;
    private TokenEndpointAuthMethod tokenEndpointAuthMethod;
    private ListOrderedSet<GrantType> grantTypes;
    private ListOrderedSet<ResponseType> responseTypes;
    private String clientName;
    private URI clientUri;
    private URI logoUri;
    private String scope;
    private ListOrderedSet<String> contacts;
    private URI tosUri;
    private URI policyUri;
    // private URI jwksUri;
    // private JWKSet jwks;
    private String softwareId;
    private String softwareVersion;

    private OAuth2Client() {
        redirectUris = new ListOrderedSet<URI>();
        grantTypes = new ListOrderedSet<GrantType>();
        responseTypes = new ListOrderedSet<ResponseType>();
        contacts = new ListOrderedSet<String>();
    }

    public ListOrderedSet<URI> getRedirectUris() {
        return redirectUris;
    }
}
