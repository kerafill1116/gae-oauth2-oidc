package gae.oauth2.oidc;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@JsonSerialize(using = ErrorException.Serializer.class)
public class ErrorException extends RuntimeException {
    private Error error;
    private ClientAuthenticationCredentials clientCredentials;
    private Response.ResponseBuilder rb;

    public ErrorException(Error error, ClientAuthenticationCredentials clientCredentials)
            throws JsonProcessingException {
        this.error = error;
        this.clientCredentials = clientCredentials;
        rb = Response.noContent();
        rb.type(MediaType.APPLICATION_JSON_TYPE);
        rb.header(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
        rb.header("Pragma", "no-cache");
        rb.header(HttpHeaders.EXPIRES, "0");
        switch(error) {
            case INVALID_CLIENT:
                rb.status(Response.Status.UNAUTHORIZED);
                rb.header(HttpHeaders.WWW_AUTHENTICATE, clientCredentials.getAuthMethod().getScheme());
                break;
            default:
                rb.status(Response.Status.BAD_REQUEST);
        }
        ObjectMapper mapper = new ObjectMapper();
        rb.entity(mapper.writeValueAsString(this));
    }

    public ErrorException(Error error)
            throws JsonProcessingException {
        this(error, null);
    }

    public Error getError() {
        return error;
    }

    public Response.ResponseBuilder getResponseBuilder() {
        return rb;
    }

    public Response getResponse() {
        return rb.build();
    }

    public static class Serializer extends JsonSerializer<ErrorException> {
        @Override
        public void serialize(ErrorException value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException, JsonProcessingException {
            gen.writeStartObject();
            gen.writeStringField("error", value.error.getError());
            gen.writeEndObject();
        }
    }
}
