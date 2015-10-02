package gae.oauth2.oidc.schema;

public enum TokenEndpointAuthMethod {

    NONE("none"),
    CLIENT_SECRET_POST("client_secret_post"),
    CLIENT_SECRET_BASIC("client_secret_basic");

    private String method;

    TokenEndpointAuthMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    @Override
    public String toString() {
        return method;
    }
}
