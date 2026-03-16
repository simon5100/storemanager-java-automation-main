package org.example.backend;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.backend.models.*;

public class AuthServiceRequest {

    // Константы для использования в API запросах
    public static final String AUTH_SERVICE_BASE_URL = "http://localhost:8001/";
    public static final String REGISTER_ENDPOINT = "register/";
    public static final String LOGIN_ENDPOINT = "login/";
    public static final String GET_PENDING_PRODUCTS = "get-pending-products/";
    public static final String GET_USER_TOKEN_PRODUCT = "get-user-token/{user_id}";

    public static RegisterResponse getRegisterResponse(RegisterRequest request) {
        return RestAssured
                .given().log().all()
                .baseUri(AUTH_SERVICE_BASE_URL)
                .basePath(REGISTER_ENDPOINT)
                .contentType(ContentType.JSON)
                .body(request)
                .when().log().all()
                .post()
                .then().log().all()
                .extract()
                .as(RegisterResponse.class);
    }

    public static LoginResponse postLogin(LoginRequest request) {
        return RestAssured
                .given().log().all()
                .baseUri(AUTH_SERVICE_BASE_URL)
                .basePath(LOGIN_ENDPOINT)
                .contentType(ContentType.JSON)
                .body(request)
                .when().log().all()
                .post()
                .then().log().all()
                .extract()
                .response().as(LoginResponse.class);
    }

    public static UserTokenResponse getUserToken(String userId) {
        return RestAssured
                .given().log().all()
                .baseUri(AUTH_SERVICE_BASE_URL)
                .basePath(GET_USER_TOKEN_PRODUCT)
                .contentType(ContentType.JSON)
                .pathParam("user_id", userId)
                .when().log().all()
                .get()
                .then().log().all()
                .extract()
                .response().as(UserTokenResponse.class);
    }

    public static Response gePendingProducts(String accessToken) {
        return RestAssured
                .given().log().all()
                .baseUri(AUTH_SERVICE_BASE_URL)
                .basePath(GET_PENDING_PRODUCTS)
                .header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.JSON)
                .when().get()
                .then().log().all()
                .extract().response();
    }
}
