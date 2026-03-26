package org.example.config.endpointsproperties;

import org.aeonbits.owner.Config;

@Config.Sources("file:./src/test/resources/endpoints.properties")
public interface EndpointConfigReader extends Config {

    @Key("auth.register")
    String authRegister();

    @Key("auth.login")
    String authLogin();

    @Key("auth.pending.products")
    String authPendingProducts();

    @Key("product.suppliers")
    String productSuppliers();
}
