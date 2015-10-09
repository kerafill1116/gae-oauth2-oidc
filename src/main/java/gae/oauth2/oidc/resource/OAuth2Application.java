package gae.oauth2.oidc.resource;

import gae.oauth2.oidc.ErrorExceptionMapper;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

public class OAuth2Application extends ResourceConfig {

    public OAuth2Application() {
        // property(JspMvcFeature.TEMPLATE_BASE_PATH, "/WEB-INF/classes");
        register(JspMvcFeature.class);
        register(JacksonFeature.class);

        // register classes here
        register(OAuth2Resource.class);
        register(ErrorExceptionMapper.class);

        // disabled jersery resource discovery, faster load times
        property(ServerProperties.METAINF_SERVICES_LOOKUP_DISABLE, true);
        // allows google app engine to honor thrown exception mapper errors over default response errors
        // set this in exception mapper for local resource setting
        // property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
    }
}
