package gae.oauth2.oidc;

public enum Error {
    INVALID_REQUEST("invalid_request"),
    INVALID_CLIENT("invalid_client"),
    UNSUPPORTED_GRANT_TYPE("unsupported_grant_type");

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
