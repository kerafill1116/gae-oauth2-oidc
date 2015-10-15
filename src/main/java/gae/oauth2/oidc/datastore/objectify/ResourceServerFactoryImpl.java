package gae.oauth2.oidc.datastore.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.googlecode.objectify.Key;
import gae.oauth2.oidc.datastore.ResourceServerFactoryInterface;
import gae.oauth2.oidc.schema.ResourceServer;

public class ResourceServerFactoryImpl implements ResourceServerFactoryInterface {
    @Override
    public ResourceServer get(String id) {
        return ofy().load().type(ResourceServer.class).id(id).now();
    }

    @Override
    public ResourceServer save(ResourceServer resourceServer) {
        Key<ResourceServer> key = ofy().save().entity(resourceServer).now();
        return ofy().load().key(key).now();
    }
}
