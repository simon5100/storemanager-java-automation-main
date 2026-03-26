package org.example.config.test_envproperties;

import org.aeonbits.owner.Config;

@Config.Sources("file:./src/test/resources/test_env.properties")
public interface TestConfigReader extends Config {

    @Key("app.ui.url")
    String appUiUrl();

    @Key("auth.base.url")
    String authBaseUrl();

    @Key("product.base.url")
    String productBaseUrl();

    @Key("db.host")
    String dbHost();

    @Key("db.port")
    Integer dbPort();

    @Key("db.name")
    String dbName();

    @Key("db.user")
    String dbUser();

    @Key("db.password")
    String dbPassword();
}
