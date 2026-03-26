package org.example.backend.requests;

import io.qameta.allure.Step;
import io.restassured.http.Method;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.example.backend.ApiClient;
import org.example.backend.models.LoginRequest;
import org.example.backend.models.LoginResponse;
import org.example.backend.models.RegisterRequest;
import org.example.backend.models.RegisterResponse;
import static org.example.config.endpointsproperties.EndpointConfigReaderImpl.*;
import static org.example.config.test_envproperties.TestConfigReaderImpl.getAuthBaseUrl;

@Slf4j
public class AuthServiceRequest {

    // Константы для использования в API запросах
    public static final String AUTH_SERVICE_BASE_URL = getAuthBaseUrl();
    public static final String REGISTER_ENDPOINT = getAuthRegister();
    public static final String LOGIN_ENDPOINT = getAuthLogin();
    public static final String GET_PENDING_PRODUCTS = getAuthPendingProducts();

    @Step("Register user")
    public static RegisterResponse executeGetRegister(RegisterRequest request) {
        log.info("Register user");
        return new ApiClient().serBaseUtl(AUTH_SERVICE_BASE_URL).build()
                .sendRequest(Method.POST, REGISTER_ENDPOINT, request, RegisterResponse.class);
    }

    @Step("Login user")
    public static LoginResponse executePostLogin(LoginRequest request) {
        log.info("Login user");
        return new ApiClient().serBaseUtl(AUTH_SERVICE_BASE_URL).build()
                .sendRequest(Method.POST, LOGIN_ENDPOINT, request, LoginResponse.class);
    }

    @Step("Get pending products")
    public static Response executeGePendingProducts(String accessToken) {
        log.info("Get pending products");
        return new ApiClient().serBaseUtl(AUTH_SERVICE_BASE_URL).addBearerAuthorization(accessToken).build()
                .sendRequest(Method.GET, GET_PENDING_PRODUCTS);
    }
}