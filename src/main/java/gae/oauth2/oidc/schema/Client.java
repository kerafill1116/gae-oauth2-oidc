package gae.oauth2.oidc.schema;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import java.net.URI;
import java.util.Date;
import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.collections4.set.ListOrderedSet;

@Entity
public class Client {

    @Id
    private String clientId;
    private String clientSecret;
    private Date clientIdIssuedAt;
    private Date clientSecretExpiresAt;

    private ListOrderedSet<URI> redirectUris = new ListOrderedSet<URI>();
    // default code only
    private ListOrderedSet<ResponseType> responseTypes = new ListOrderedSet<ResponseType>();
    // ResponseType GrantType correspondence:
    // code: authorization_code
    // id_token: implicit
    // token id_token: implicit
    // code id_token: authorization_code, implicit
    // code token: authorization_code, implicit
    // code token id_token: authorization_code, implicit
    // default authorization_code only
    private ListOrderedSet<GrantType> grantTypes = new ListOrderedSet<GrantType>();
    private ApplicationType applicationType = ApplicationType.WEB;
    private ListOrderedSet<String> contacts = new ListOrderedSet<String>();
    private String clientName;
    private IterableMap<String, String> clientNames = new HashedMap<String, String>();
    private URI logoUri;
    private IterableMap<String, URI> logoUris = new HashedMap<String, URI>();
    private URI clientUri;
    private IterableMap<String, URI> clientUris = new HashedMap<String, URI>();
    private URI policyUri;
    private IterableMap<String, URI> policyUris = new HashedMap<String, URI>();
    private URI tosUri;
    private IterableMap<String, URI> tosUris = new HashedMap<String, URI>();
    // jwks_uri
    // jwks
    // sector_identifier_uri
    // subject_type
    // id_token_signed_response_alg
    // id_token_encrypted_response_alg
    // id_token_encrypted_response_enc
    // userinfo_signed_response_alg
    // userinfo_encrypted_response_alg
    // userinfo_encrypted_response_enc
    // request_object_signing_alg
    // request_object_encryption_alg
    // request_object_encryption_enc
    private TokenEndpointAuthMethod tokenEndpointAuthMethod = TokenEndpointAuthMethod.CLIENT_SECRET_BASIC;
    // token_endpoint_auth_signing_alg
    private long defaultMaxAge = 0;
    private boolean requireAuthTime = false;
    // default_acr_values
    // initiate_login_uri
    // request_uris

    // dynamic response params upon successful dynamic registration
    // registration_access_token
    // registration_client_uri

    // must include openid for end user authentication
    private ListOrderedSet<Scope> scope = new ListOrderedSet<Scope>();
    private String softwareId;
    private String softwareVersion;

    public ListOrderedSet<URI> getRedirectUris() {
        return redirectUris;
    }
}
