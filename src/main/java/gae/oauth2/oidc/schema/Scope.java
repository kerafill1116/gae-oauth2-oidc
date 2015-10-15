package gae.oauth2.oidc.schema;

public class Scope {
    // required for openid end user authentication
    public static Scope OPENID = new Scope("openid");
    // Section 5.4 http://openid.net/specs/openid-connect-core-1_0.html#ScopeClaims
    public static Scope PROFILE = new Scope("profile");
    public static Scope EMAIL = new Scope("email");
    public static Scope ADDRESS = new Scope("address");
    public static Scope PHONE = new Scope("phone");

    private String scope;

    public Scope(String scope) {
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
