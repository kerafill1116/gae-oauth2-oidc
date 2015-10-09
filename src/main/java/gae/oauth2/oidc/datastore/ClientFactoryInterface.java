package gae.oauth2.oidc.datastore;

import gae.oauth2.oidc.schema.Client;

public interface ClientFactoryInterface {
    Client get(String id);
}
