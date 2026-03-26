package org.example.config.remote_envproperties;

import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;

@Getter
public class RemoteEvnConfigReaderImpl implements RemoteEvnConfigReader {
    public static final RemoteEvnConfigReader REMOTE_EVN_CONFIG_READER = ConfigFactory.create(RemoteEvnConfigReader.class);

    @Override
    public String appUiUrl() {
        return REMOTE_EVN_CONFIG_READER.appUiUrl();
    }

    @Override
    public String authBaseUrl() {
        return REMOTE_EVN_CONFIG_READER.authBaseUrl();
    }

    @Override
    public String productBaseUrl() {
        return REMOTE_EVN_CONFIG_READER.productBaseUrl();
    }

    @Override
    public String dbHost() {
        return REMOTE_EVN_CONFIG_READER.dbHost();
    }

    @Override
    public String dbPort() {
        return REMOTE_EVN_CONFIG_READER.dbPort();
    }

    @Override
    public String dbName() {
        return REMOTE_EVN_CONFIG_READER.dbName();
    }

    @Override
    public String dbUser() {
        return REMOTE_EVN_CONFIG_READER.dbUser();
    }

    @Override
    public String dbPassword() {
        return REMOTE_EVN_CONFIG_READER.dbPassword();
    }
}
