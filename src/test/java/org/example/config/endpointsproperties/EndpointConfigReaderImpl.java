package org.example.config.endpointsproperties;

import org.aeonbits.owner.ConfigFactory;

public class EndpointConfigReaderImpl {
    private static final EndpointConfigReader ENDPOINT_CONFIG_READER =
            ConfigFactory.create(EndpointConfigReader.class);

    public static String getAuthRegister() {
        return ENDPOINT_CONFIG_READER.authRegister();
    }

    public static String getAuthLogin() {
        return ENDPOINT_CONFIG_READER.authLogin();
    }

    public static String getAuthPendingProducts() {
        return ENDPOINT_CONFIG_READER.authPendingProducts();
    }

    public static String getProductSuppliers() {
        return ENDPOINT_CONFIG_READER.productSuppliers();
    }
}
