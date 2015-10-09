package gae.oauth2.oidc.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import gae.oauth2.oidc.datastore.ClientFactoryInterface;
import gae.oauth2.oidc.schema.Client;

public class ClientFactoryImpl implements ClientFactoryInterface {
    @Override
    public Client get(String id) {
        return ofy().load().type(Client.class).id(id).now();
    }
}
