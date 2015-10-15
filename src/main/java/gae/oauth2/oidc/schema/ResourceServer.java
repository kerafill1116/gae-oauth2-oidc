package gae.oauth2.oidc.schema;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import java.net.URI;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.collections4.set.ListOrderedSet;

@Entity
public class ResourceServer {
    @Id
    private String id;
    private String secret;
    private Date idIssuedAt;
    private Date secretExpiresAt;

    private ListOrderedSet<ResponseType> responseTypes = new ListOrderedSet<>();
    private ListOrderedSet<GrantType> grantTypes = new ListOrderedSet<>();

    private String name;
    private IterableMap<String, String> names = new HashedMap<>();
    private URI logoUri;
    private IterableMap<String, URI> logoUris = new HashedMap<>();

    private TokenValidationEndpointAuthMethod authMethod = TokenValidationEndpointAuthMethod.RESOURCE_SERVER_SECRET_BASIC;

    private ListOrderedSet<Scope> scope = new ListOrderedSet<>();
    private String softwareId;
    private String softwareVersion;

    private ListOrderedSet<String> clientIds = new ListOrderedSet<>();

    private ResourceServer() { }

    public ResourceServer(String name, URI logoUri, TokenValidationEndpointAuthMethod authMethod, String softwareId, String softwareVersion) {
        this.name = name;
        this.logoUri = logoUri;
        this.authMethod = authMethod;
        this.softwareId = softwareId;
        this.softwareVersion = softwareVersion;

        id = name.toLowerCase().replaceAll(" ", "-").replaceAll("[^a-z0-9_\\x2D]", "");
        secret = UUID.randomUUID().toString();
        idIssuedAt = new Date();
        secretExpiresAt = null;
    }

    public ResourceServer(String id, String secret, Date idIssuedAt, Date secretExpiresAt) {
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

    public TokenValidationEndpointAuthMethod getAuthMethod() {
        return authMethod;
    }

    public void setAuthMethod(TokenValidationEndpointAuthMethod authMethod) {
        this.authMethod = authMethod;
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
}
