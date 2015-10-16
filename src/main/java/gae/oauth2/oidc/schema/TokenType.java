package gae.oauth2.oidc.schema;

public enum TokenType {
    BEARER("Bearer");

    private String type;

    TokenType(String type) {
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
