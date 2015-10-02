package jersey;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

public class StatusExceptionMapper implements ExceptionMapper<StatusException> {

    @Override
    public Response toResponse(StatusException exception) {

        new ResourceConfig().property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
        Response exceptionResponse = exception.getResponse();
        return Response.status(exceptionResponse.getStatus())
                .entity(String.format("Status: %d\nMessage: %s", exceptionResponse.getStatus(), exception.getMessage()))
                .build();
    }
}
