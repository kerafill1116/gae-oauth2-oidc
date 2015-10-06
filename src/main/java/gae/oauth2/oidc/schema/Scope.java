package gae.oauth2.oidc.schema;

public enum Scope {

    // required for openid end user authentication
    OPENID("openid"),
    // Section 5.4 http://openid.net/specs/openid-connect-core-1_0.html#ScopeClaims
    PROFILE("profile"),
    EMAIL("email"),
    ADDRESS("address"),
    PHONE("phone");

    private String scope;

    Scope(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return scope;
    }

    @Override
    public String toString() {
        return scope;
    }
}
