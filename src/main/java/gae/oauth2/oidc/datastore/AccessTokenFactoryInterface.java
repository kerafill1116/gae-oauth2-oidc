package gae.oauth2.oidc.datastore;

import gae.oauth2.oidc.schema.AccessToken;

public interface AccessTokenFactoryInterface {
    AccessToken get(String accessToken);
    AccessToken save(AccessToken accessToken);
}
