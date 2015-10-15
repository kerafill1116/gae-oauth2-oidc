package gae.oauth2.oidc.datastore;

import gae.oauth2.oidc.schema.ResourceServer;

public interface ResourceServerFactoryInterface {
    ResourceServer get(String id);
    ResourceServer save(ResourceServer resourceServer);
}
