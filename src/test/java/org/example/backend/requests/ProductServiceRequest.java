package org.example.backend.requests;

import io.restassured.http.Method;
import org.example.backend.ApiClient;
import org.example.backend.models.SupplierCreateModel;

public class ProductServiceRequest {

    // Константы для использования в API запросах
    public static final String PRODUCT_SERVICE_BASE_URL = "http://localhost:8002/";
    public static final String SUPPLIER_ENDPOINT = "suppliers/";

    public static SupplierCreateModel executePostCreateSupplier(SupplierCreateModel request, String token) {
        return new ApiClient().serBaseUtl(PRODUCT_SERVICE_BASE_URL).addBearerAuthorization(token).build()
                .sendRequest(Method.POST, SUPPLIER_ENDPOINT, request, SupplierCreateModel.class);
    }
}
