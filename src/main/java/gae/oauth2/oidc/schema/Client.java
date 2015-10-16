package gae.oauth2.oidc.schema;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import java.net.URI;
import java.util.Date;
import java.util.UUID;

import com.googlecode.objectify.annotation.OnSave;
import gae.oauth2.oidc.datastore.DatastoreError;
import gae.oauth2.oidc.datastore.DatastoreErrorException;
import gae.oauth2.oidc.datastore.ResourceServerFactoryInterface;
import gae.oauth2.oidc.datastore.objectify.ResourceServerFactoryImpl;
import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.collections4.set.ListOrderedSet;

@Entity
public class Client {
    @Id
    private String id;
    private String secret;
    private Date idIssuedAt;
    private Date secretExpiresAt;

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
    private ClientType type = ClientType.CONFIDENTIAL;
    private ApplicationType applicationType = ApplicationType.WEB;
    private ListOrderedSet<String> contacts = new ListOrderedSet<String>();
    private String name;
    private IterableMap<String, String> names = new HashedMap<String, String>();
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
    private TokenEndpointAuthMethod authMethod = TokenEndpointAuthMethod.CLIENT_SECRET_BASIC; // token_endpoint_auth_signing_alg
    private long authMaxAge = 0;
    private boolean requireAuthTime = false;
    private long accessTokenMaxAge = 3600; // 1 hour
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

    private ListOrderedSet<String> resourceServerIds = new ListOrderedSet<String>();

    private Client() { }

    public Client(String name, ClientType type, ApplicationType applicationType, URI logoUri, URI clientUri, URI policyUri, URI tosUri, TokenEndpointAuthMethod authMethod, long authMaxAge, boolean requireAuthTime, String softwareId, String softwareVersion, Date idIssuedAt, Date secretExpiresAt) {
        this.name = name;
        this.type = type;
        this.applicationType = applicationType;
        this.logoUri = logoUri;
        this.clientUri = clientUri;
        this.policyUri = policyUri;
        this.tosUri = tosUri;
        this.authMethod = authMethod;
        this.authMaxAge = authMaxAge;
        this.requireAuthTime = requireAuthTime;
        this.softwareId = softwareId;
        this.softwareVersion = softwareVersion;

        id = name.toLowerCase().replaceAll(" ", "-").replaceAll("[^a-z0-9_\\x2D]", "");
        secret = UUID.randomUUID().toString();
        this.idIssuedAt = idIssuedAt;
        this.secretExpiresAt = secretExpiresAt;
    }

    public Client(String id, String secret, Date idIssuedAt, Date secretExpiresAt) {
        this.id = id;
        this.secret = secret;
        this.idIssuedAt = idIssuedAt;
        this.secretExpiresAt = secretExpiresAt;
    }

    public String getId() {
        return id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Date getIdIssuedAt() {
        return idIssuedAt;
    }

    public Date getSecretExpiresAt() {
        return secretExpiresAt;
    }

    public void setSecretExpiresAt(Date secretExpiresAt) {
        this.secretExpiresAt = secretExpiresAt;
    }

    public ListOrderedSet<URI> getRedirectUris() {
        return redirectUris;
    }

    public void setRedirectUris(ListOrderedSet<URI> redirectUris) {
        this.redirectUris = redirectUris;
    }

    public ListOrderedSet<ResponseType> getResponseTypes() {
        return responseTypes;
    }

    public void setResponseTypes(ListOrderedSet<ResponseType> responseTypes) {
        this.responseTypes = responseTypes;
    }

    public ListOrderedSet<GrantType> getGrantTypes() {
        return grantTypes;
    }

    public void setGrantTypes(ListOrderedSet<GrantType> grantTypes) {
        this.grantTypes = grantTypes;
    }

    public ClientType getType() {
        return type;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    public ApplicationType getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(ApplicationType applicationType) {
        this.applicationType = applicationType;
    }

    public ListOrderedSet<String> getContacts() {
        return contacts;
    }

    public void setContacts(ListOrderedSet<String> contacts) {
        this.contacts = contacts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(String lang) {
        return names.get(lang);
    }

    public void setName(String lang, String name) {
        names.put(lang, name);
    }

    public URI getLogoUri() {
        return logoUri;
    }

    public void setLogoUri(URI logoUri) {
        this.logoUri = logoUri;
    }

    public URI getLogoUri(String lang) {
        return logoUris.get(lang);
    }

    public void setLogoUri(String lang, URI logoUri) {
        logoUris.put(lang, logoUri);
    }

    public URI getClientUri() {
        return clientUri;
    }

    public void setClientUri(URI clientUri) {
        this.clientUri = clientUri;
    }

    public URI getClientUri(String lang) {
        return clientUris.get(lang);
    }

    public void setClientUri(String lang, URI clientUri) {
        clientUris.put(lang, clientUri);
    }

    public URI getPolicyUri() {
        return policyUri;
    }

    public void setPolicyUri(URI policyUri) {
        this.policyUri = policyUri;
    }

    public URI getPolicyUri(String lang) {
        return policyUris.get(lang);
    }

    public void setPolicyUri(String lang, URI policyUri) {
        policyUris.put(lang, policyUri);
    }

    public URI getTosUri() {
        return tosUri;
    }

    public void setTosUri(URI tosUri) {
        this.tosUri = tosUri;
    }

    public URI getTosUri(String lang) {
        return tosUris.get(lang);
    }

    public void setTosUri(String lang, URI tosUri) {
        tosUris.put(lang, tosUri);
    }

    public TokenEndpointAuthMethod getAuthMethod() {
        return authMethod;
    }

    public void setAuthMethod(TokenEndpointAuthMethod authMethod) {
        this.authMethod = authMethod;
    }

    public long getAuthMaxAge() {
        return authMaxAge;
    }

    public void setAuthMaxAge(long authMaxAge) {
        this.authMaxAge = authMaxAge;
    }

    public boolean isRequireAuthTime() {
        return requireAuthTime;
    }

    public void setRequireAuthTime(boolean requireAuthTime) {
        this.requireAuthTime = requireAuthTime;
    }

    public long getAccessTokenMaxAge() {
        return accessTokenMaxAge;
    }

    public void setAccessTokenMaxAge(long accessTokenMaxAge) {
        this.accessTokenMaxAge = accessTokenMaxAge;
    }

    public ListOrderedSet<Scope> getScope() {
        return scope;
    }

    public void setScope(ListOrderedSet<Scope> scope) {
        this.scope = scope;
    }

    public String getSoftwareId() {
        return softwareId;
    }

    public void setSoftwareId(String softwareId) {
        this.softwareId = softwareId;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public ListOrderedSet<String> getResourceServerIds() {
        return resourceServerIds;
    }

    public void setResourceServerIds(ListOrderedSet<String> resourceServerIds) {
        this.resourceServerIds = resourceServerIds;
    }

    @OnSave
    private void checkResponseGrantTypes() {
        ListOrderedSet<ResponseType> allowedResponseTypes = new ListOrderedSet<>();
        ListOrderedSet<GrantType> allowedGrantTypes = new ListOrderedSet<>();
        ResourceServerFactoryInterface rsFactory = new ResourceServerFactoryImpl();
        for(String rsId : resourceServerIds) {
            ResourceServer rs = rsFactory.get(rsId);
            allowedResponseTypes.addAll(rs.getResponseTypes());
            allowedGrantTypes.addAll(rs.getGrantTypes());
        }
        for(ResponseType rt : responseTypes) {
            if(!allowedResponseTypes.contains(rt))
                throw new DatastoreErrorException(DatastoreError.UNSUPPORTED_RESPONSE_TYPE);
        }
        for(GrantType gt : grantTypes) {
            if(!allowedGrantTypes.contains(gt))
                throw new DatastoreErrorException(DatastoreError.UNSUPPORTED_GRANT_TYPE);
        }
        if(responseTypes.contains(ResponseType.CODE) && !grantTypes.contains(GrantType.AUTHORIZATION_CODE))
            throw new DatastoreErrorException(DatastoreError.REQUIRED_GRANT_TYPE);
        if((responseTypes.contains(ResponseType.ID_TOKEN) || responseTypes.contains(ResponseType.TOKEN)) && !grantTypes.contains(GrantType.IMPLICIT))
            throw new DatastoreErrorException(DatastoreError.REQUIRED_GRANT_TYPE);
        if(grantTypes.contains(GrantType.AUTHORIZATION_CODE) && !responseTypes.contains(ResponseType.CODE))
            throw new DatastoreErrorException(DatastoreError.REQUIRED_RESPONSE_TYPE);
        if(grantTypes.contains(GrantType.IMPLICIT) && !(responseTypes.contains(ResponseType.TOKEN) || responseTypes.contains(ResponseType.ID_TOKEN)))
            throw new DatastoreErrorException(DatastoreError.REQUIRED_RESPONSE_TYPE);
    }
}
