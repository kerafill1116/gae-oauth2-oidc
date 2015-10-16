package gae.oauth2.oidc.datastore;

public class DatastoreErrorException extends RuntimeException {
    private DatastoreError error;

    public DatastoreErrorException(DatastoreError error) {
        this.error = error;
    }

    public DatastoreError getError() {
        return error;
    }
}
