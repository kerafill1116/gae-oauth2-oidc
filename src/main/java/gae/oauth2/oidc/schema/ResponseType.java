package gae.oauth2.oidc.schema;

public enum ResponseType {

    CODE("code"),
    ID_TOKEN("id_token"),
    TOKEN("token");

    private String type;

    ResponseType(String type) {
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
