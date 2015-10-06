package gae.oauth2.oidc.schema;

public enum ApplicationType {

    // must not use localhost, must use https: scheme
    WEB("web"),
    // can use http: scheme if localhost, otherwise must use https: scheme
    NATIVE("native");

    private String type;

    ApplicationType(String type) {
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
