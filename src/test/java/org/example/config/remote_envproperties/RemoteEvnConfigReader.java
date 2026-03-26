package org.example.config.remote_envproperties;

import org.aeonbits.owner.Config;

@Config.Sources("file:./src/main/resources/remote_env.properties")
public interface RemoteEvnConfigReader extends Config {

    @Key("app.ui.url")
    String appUiUrl();

    @Key("auth.base.url")
    String authBaseUrl();

    @Key("product.base.url")
    String productBaseUrl();

    @Key("db.host")
    String dbHost();

    @Key("db.port")
    String dbPort();

    @Key("db.name")
    String dbName();

    @Key("db.user")
    String dbUser();

    @Key("db.password")
    String dbPassword();
}
