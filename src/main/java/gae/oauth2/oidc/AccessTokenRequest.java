package gae.oauth2.oidc;

import com.fasterxml.jackson.core.JsonProcessingException;
import gae.oauth2.oidc.schema.GrantType;
import gae.oauth2.oidc.schema.Scope;
import java.net.URI;
import java.net.URISyntaxException;
import javax.ws.rs.core.MultivaluedMap;
import org.apache.commons.collections4.set.ListOrderedSet;
import org.apache.commons.lang3.StringUtils;


public class AccessTokenRequest {
    private GrantType grantType;
    // authorization_code
    private String code;
    private URI redirectUri;
    // password
    private String username;
    private String password;
    // refresh_token
    private String refreshToken;
    // common password, client_credentials, refresh_token
    private ListOrderedSet<Scope> scope = new ListOrderedSet<>();

    private AccessTokenRequest() { }

    public AccessTokenRequest(GrantType grantType, String code, URI redirectUri, String username, String password, String refreshToken, ListOrderedSet<Scope> scope) {
        this.grantType = grantType;
        this.code = code;
        this.redirectUri = redirectUri;
        this.username = username;
        this.password = password;
        this.refreshToken = refreshToken;
        this.scope = scope;
    }

    public static AccessTokenRequest parse(MultivaluedMap<String, String> form)
            throws JsonProcessingException {
        String grantTypeStr = form.getFirst("grant_type");
        if(StringUtils.isBlank(grantTypeStr))
            throw new ErrorException(Error.INVALID_REQUEST);
        GrantType grantType = null;
        try {
            grantType = GrantType.valueOf(grantTypeStr.toUpperCase());
        }
        catch(IllegalArgumentException e) { // null pointer check already done above by StringUtils.isBlank
            throw new ErrorException(Error.UNSUPPORTED_GRANT_TYPE);
        }

        // parse grant_type parameters
        String code = null;
        URI redirectUri = null;
        String refreshToken = null;
        String username = null;
        String password = null;
        switch(grantType) {
            case AUTHORIZATION_CODE:
                code = form.getFirst("code");
                if(StringUtils.isBlank(code))
                    throw new ErrorException(Error.INVALID_REQUEST);
                try {
                    redirectUri = new URI(form.getFirst("redirect_uri"));
                }
                catch(URISyntaxException e) {
                    throw new ErrorException(Error.INVALID_REQUEST);
                }
                break;
            case REFRESH_TOKEN:
                refreshToken = form.getFirst("refresh_token");
                if(StringUtils.isBlank(refreshToken))
                    throw new ErrorException(Error.INVALID_REQUEST);
                break;
            case PASSWORD:
                username = form.getFirst("username");
                if(StringUtils.isBlank(username))
                    throw new ErrorException(Error.INVALID_REQUEST);
                break;
        }

        // parse scope
        ListOrderedSet<Scope> scope = new ListOrderedSet<>();
        String scopeStr = form.getFirst("scope");
        if(!StringUtils.isBlank(scopeStr)) {
            String[] scopesStr = StringUtils.split(scopeStr);
            for(String str : scopesStr)
                scope.add(new Scope(str));
        }

        return new AccessTokenRequest(grantType, code, redirectUri, username, password, refreshToken, scope);
    }


}
