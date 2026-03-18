package org.example.backend.requests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.backend.models.*;

public class ProductServiceRequest {

    // Константы для использования в API запросах
    public static final String PRODUCT_SERVICE_BASE_URL = "http://localhost:8002/";
    public static final String SUPPLIER_ENDPOINT = "suppliers/";

    public static SupplierCreateModel executePostCreateSupplier(SupplierCreateModel request, String accessToken) {
        return RestAssured
                .given()
                .baseUri(PRODUCT_SERVICE_BASE_URL)
                .basePath(SUPPLIER_ENDPOINT)
                .header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.JSON)
                .body(request).log().all()
                .when().post()
                .then().log().all()
                .extract().response().as(SupplierCreateModel.class);
    }
}
