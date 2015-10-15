package gae.oauth2.oidc.datastore.objectify;

import com.googlecode.objectify.ObjectifyService;
import gae.oauth2.oidc.schema.Client;
import gae.oauth2.oidc.schema.EndUser;
import gae.oauth2.oidc.schema.ResourceServer;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

public class ObjectifyApplication extends ResourceConfig {
    static {
        ObjectifyService.register(Client.class);
        ObjectifyService.register(EndUser.class);
        ObjectifyService.register(ResourceServer.class);
    }

    public ObjectifyApplication() {
        property(ServerProperties.METAINF_SERVICES_LOOKUP_DISABLE, true);
    }
}
