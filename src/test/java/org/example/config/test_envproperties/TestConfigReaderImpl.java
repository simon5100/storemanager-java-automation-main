package org.example.config.test_envproperties;

import org.aeonbits.owner.ConfigFactory;

public class TestConfigReaderImpl {
    private static final TestConfigReader TEST_CONFIG_READER =
            ConfigFactory.create(TestConfigReader.class);

    public static String getAppUiUrl() {
        return TEST_CONFIG_READER.appUiUrl();
    }

    public static String getAuthBaseUrl() {
        return TEST_CONFIG_READER.authBaseUrl();
    }

    public static String getProductBaseUrl() {
        return TEST_CONFIG_READER.productBaseUrl();
    }

    public static String getDbHost() {
        return TEST_CONFIG_READER.dbHost();
    }

    public static Integer getDbPort() {
        return TEST_CONFIG_READER.dbPort();
    }

    public static String getDbName() {
        return TEST_CONFIG_READER.dbName();
    }

    public static String getDbUser() {
        return TEST_CONFIG_READER.dbUser();
    }

    public static String getDbPassword() {
        return TEST_CONFIG_READER.dbPassword();
    }
}
