package gae.oauth2.oidc.schema;

public enum GrantType {
    // OpenID Connect only supported
    AUTHORIZATION_CODE("authorization_code"),
    IMPLICIT("implicit"),
    REFRESH_TOKEN("refresh_token"),
    // OAuth2
    PASSWORD("password"),
    CLIENT_CREDENTIALS("client_credentials");

    private String type;

    GrantType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
