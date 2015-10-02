package gae.oauth2.oidc.schema;

public enum ResponseType {

    CODE("code"),
    TOKEN("token"),
    ID_TOKEN("id_token");

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
