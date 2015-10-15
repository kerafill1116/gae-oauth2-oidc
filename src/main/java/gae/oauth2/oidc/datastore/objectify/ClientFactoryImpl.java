package gae.oauth2.oidc.datastore.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.googlecode.objectify.Key;
import gae.oauth2.oidc.datastore.ClientFactoryInterface;
import gae.oauth2.oidc.schema.Client;

public class ClientFactoryImpl implements ClientFactoryInterface {
    @Override
    public Client get(String id) {
        return ofy().load().type(Client.class).id(id).now();
    }

    @Override
    public Client save(Client client) {
        Key<Client> key = ofy().save().entity(client).now();
        return ofy().load().key(key).now();
    }
}
