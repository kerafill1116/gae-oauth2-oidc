package gae.oauth2.oidc;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

public class ErrorExceptionMapper implements ExceptionMapper<ErrorException> {
    @Override
    public Response toResponse(ErrorException exception) {
        // disables sendError for status 400+
        new ResourceConfig().property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);

        return exception.getResponse();
    }
}
