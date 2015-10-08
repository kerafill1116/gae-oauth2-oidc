package gae.oauth2.oidc.schema;

public enum TokenValidationEndpointAuthMethod {

    RESOURCE_SERVER_SECRET_BASIC("resource_server_secret_basic"),
    RESOURCE_SERVER_SECRET_POST("resource_server_secret_post"),
    NONE("none");

    private String method;

    TokenValidationEndpointAuthMethod(String method) {
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
