package gae.oauth2.oidc.schema;

public enum TokenValidationEndpointAuthMethod {
    RESOURCE_SERVER_SECRET_BASIC("resource_server_secret_basic", "Basic"),
    RESOURCE_SERVER_SECRET_POST("resource_server_secret_post", "OAuth2-Post"),
    NONE("none", "none");

    private String method;
    private String scheme;

    TokenValidationEndpointAuthMethod(String method, String scheme) {
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
