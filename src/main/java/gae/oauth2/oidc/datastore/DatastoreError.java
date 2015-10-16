package gae.oauth2.oidc.datastore;

public enum DatastoreError {
    REQUIRED_RESPONSE_TYPE("required_response_type"),
    REQUIRED_GRANT_TYPE("required_grant_type"),
    UNSUPPORTED_RESPONSE_TYPE("unsupported_response_type"),
    UNSUPPORTED_GRANT_TYPE("unsupported_grant_type");

    private String error;

    DatastoreError(String error) {
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
