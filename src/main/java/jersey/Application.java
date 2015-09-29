package jersey;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

public class Application extends ResourceConfig {

    public Application() {
        // property(JspMvcFeature.TEMPLATE_BASE_PATH, "/WEB-INF/classes");
        register(JspMvcFeature.class);
        register(JacksonFeature.class);

        // register classes here
        register(Resource.class);
        register(StatusExceptionMapper.class);

        // disabled jersery resource discovery, faster load times
        property(ServerProperties.METAINF_SERVICES_LOOKUP_DISABLE, true);
        // allows google app engine to honor thrown exception mapper errors over default response errors
        // set this in exception mapper for local resource setting
        // property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
    }
}
