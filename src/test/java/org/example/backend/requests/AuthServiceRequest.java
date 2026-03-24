package org.example.backend.requests;

import io.restassured.http.Method;
import io.restassured.response.Response;
import org.example.backend.ApiClient;
import org.example.backend.models.LoginRequest;
import org.example.backend.models.LoginResponse;
import org.example.backend.models.RegisterRequest;
import org.example.backend.models.RegisterResponse;

public class AuthServiceRequest {

    // Константы для использования в API запросах
    public static final String AUTH_SERVICE_BASE_URL = "http://localhost:8001/";
    public static final String REGISTER_ENDPOINT = "register/";
    public static final String LOGIN_ENDPOINT = "login/";
    public static final String GET_PENDING_PRODUCTS = "get-pending-products/";
    public static final String GET_USER_TOKEN_PRODUCT = "get-user-token/{user_id}";

    public static RegisterResponse executeGetRegister(RegisterRequest request) {
        return new ApiClient().serBaseUtl(AUTH_SERVICE_BASE_URL).build()
                .sendRequest(Method.POST, REGISTER_ENDPOINT, request, RegisterResponse.class);
    }

    public static LoginResponse executePostLogin(LoginRequest request) {
        return new ApiClient().serBaseUtl(AUTH_SERVICE_BASE_URL).build()
                .sendRequest(Method.POST, LOGIN_ENDPOINT, request, LoginResponse.class);
    }

    public static Response executeGePendingProducts(String accessToken) {
        return new ApiClient().serBaseUtl(AUTH_SERVICE_BASE_URL).addBearerAuthorization(accessToken).build()
                .sendRequest(Method.GET, GET_PENDING_PRODUCTS);
    }
}