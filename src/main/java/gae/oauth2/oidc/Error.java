package gae.oauth2.oidc;

public enum Error {

    INVALID_REQUEST("invalid_request");

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
