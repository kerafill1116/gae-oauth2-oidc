package gae.oauth2.oidc.schema;

public enum TokenEndpointAuthMethod {
    CLIENT_SECRET_BASIC("client_secret_basic", "Basic"),
    CLIENT_SECRET_POST("client_secret_post", "OAuth2-Post"),
    NONE("none", "none");

    private String method;
    private String scheme;

    TokenEndpointAuthMethod(String method, String scheme) {
        this.method = method;
        this.scheme = scheme;
    }

    public String getMethod() {
        return method;
    }

    public String getScheme() {
        return scheme;
    }

    @Override
    public String toString() {
        return String.format("%s; %s", method, scheme);
    }
}
