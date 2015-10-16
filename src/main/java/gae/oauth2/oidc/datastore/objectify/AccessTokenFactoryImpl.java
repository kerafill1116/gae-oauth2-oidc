package gae.oauth2.oidc.datastore.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.googlecode.objectify.Key;
import gae.oauth2.oidc.datastore.AccessTokenFactoryInterface;
import gae.oauth2.oidc.schema.AccessToken;

public class AccessTokenFactoryImpl implements AccessTokenFactoryInterface {
    @Override
    public AccessToken get(String accessToken) {
        return ofy().load().type(AccessToken.class).id(accessToken).now();
    }

    @Override
    public AccessToken save(AccessToken accessToken) {
        Key<AccessToken> key = ofy().save().entity(accessToken).now();
        return ofy().load().key(key).now();
    }
}
