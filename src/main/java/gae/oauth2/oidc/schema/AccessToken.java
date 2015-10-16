package gae.oauth2.oidc.schema;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.set.ListOrderedSet;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.joda.time.DateTime;

@Entity
@JsonSerialize(using = AccessToken.Serializer.class)
public class AccessToken {
    @Id
    private String accessToken;
    private TokenType type = TokenType.BEARER;
    private long expiresIn;
    private String refreshToken;
    private ListOrderedSet<Scope> scope = new ListOrderedSet<Scope>();

    private String clientId;
    private String resourceServerId;
    private Date issuedAt;
    private Date expiresAt;

    private AccessToken() { }

    public AccessToken(TokenType type, long expiresIn, boolean hasRefreshToken, ListOrderedSet<Scope> scope, String clientId, String resourceServerId, Date issuedAt) {
        // generate access token
        accessToken = DigestUtils.sha256Hex(UUID.randomUUID().toString());
        this.type = type;
        this.expiresIn = expiresIn;
        // generate refresh token
        if(hasRefreshToken)
            refreshToken = DigestUtils.sha256Hex(UUID.randomUUID().toString());
        this.scope = scope;
        this.clientId = clientId;
        this.resourceServerId = resourceServerId;
        this.issuedAt = issuedAt;
        DateTime jodaExpiresAt = new DateTime(issuedAt).plusSeconds((int) expiresIn);
        expiresAt = jodaExpiresAt.toDate();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public TokenType getType() {
        return type;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public ListOrderedSet<Scope> getScope() {
        return scope;
    }

    public String getClientId() {
        return clientId;
    }

    public String getResourceServerId() {
        return resourceServerId;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public static class Serializer extends JsonSerializer<AccessToken> {
        @Override
        public void serialize(AccessToken value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException, JsonProcessingException {
            gen.writeStartObject();
            gen.writeStringField("access_token", value.accessToken);
            gen.writeStringField("token_type", value.type.getType());
            gen.writeNumberField("expires_in", value.expiresIn);
            if(!StringUtils.isBlank(value.refreshToken))
                gen.writeStringField("refresh_token", value.refreshToken);
            if(!CollectionUtils.isEmpty(value.scope)) {
                StrBuilder scopeSb = new StrBuilder();
                for(Scope scope : value.scope) {
                    scopeSb.append(scope.getScope());
                    scopeSb.append(' ');
                }
                gen.writeStringField("scope", scopeSb.leftString(scopeSb.length() - 1));
            }
            gen.writeEndObject();
        }
    }

}
