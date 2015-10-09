package gae.oauth2.oidc.schema;

public enum ClientType {
    CONFIDENTIAL("confidential"),
    PUBLIC("public");

    private String type;

    ClientType(String type) {
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
