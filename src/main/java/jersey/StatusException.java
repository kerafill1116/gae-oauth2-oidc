package jersey;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class StatusException extends WebApplicationException {

    public StatusException(String message, Response.Status status) {

        super(message, status);
    }
}
