package gae.oauth2.oidc.schema;

public enum GrantType {

    AUTHORIZATION_CODE("authorization_code"),
    IMPLICIT("implicit"),
    PASSWORD("password"),
    CLIENT_CREDENTIALS("client_credentials"),
    REFRESH_TOKEN("refresh_token"),
    JWT_BEARER("urn:ietf:params:oauth:grant-type:jwt-bearer"),
    SAML2_BEARER("urn:ietf:params:oauth:grant-type:saml2-bearer");

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
