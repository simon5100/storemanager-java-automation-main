package org.example.backend.requests;

import io.restassured.http.Method;
import org.example.backend.ApiClient;
import org.example.backend.models.SupplierCreateModel;
import static org.example.config.endpointsproperties.EndpointConfigReaderImpl.getProductSuppliers;
import static org.example.config.test_envproperties.TestConfigReaderImpl.getProductBaseUrl;

public class ProductServiceRequest {

    // Константы для использования в API запросах
    public static final String PRODUCT_SERVICE_BASE_URL = getProductBaseUrl();
    public static final String SUPPLIER_ENDPOINT = getProductSuppliers();

    public static SupplierCreateModel executePostCreateSupplier(SupplierCreateModel request, String token) {
        return new ApiClient().serBaseUtl(PRODUCT_SERVICE_BASE_URL).addBearerAuthorization(token).build()
                .sendRequest(Method.POST, SUPPLIER_ENDPOINT, request, SupplierCreateModel.class);
    }
}
