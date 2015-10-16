package gae.oauth2.oidc;

public enum Error {
    INVALID_REQUEST("invalid_request"),
    INVALID_CLIENT("invalid_client"),
    INVALID_GRANT("invalid_grant"),
    UNAUTHORIZED_CLIENT("unauthorized_client"),
    UNSUPPORTED_GRANT_TYPE("unsupported_grant_type"),
    INVALID_SCOPE("invalid_scope");

    private String error;

    Error(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return error;
    }
}
