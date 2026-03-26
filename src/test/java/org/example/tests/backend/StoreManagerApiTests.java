package org.example.tests.backend;

import io.qameta.allure.Feature;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.example.backend.models.LoginRequest;
import org.example.backend.models.LoginResponse;
import org.example.backend.models.RegisterRequest;
import org.example.backend.models.RegisterResponse;
import org.example.db.UsersQueries;
import org.example.db.models.User;
import org.example.tests.BaseTest;
import org.example.tests.extensions.AllureScreenshotExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.example.backend.requests.AuthServiceRequest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@Feature("API tests")
@ExtendWith(AllureScreenshotExtension.class)
public class StoreManagerApiTests extends BaseTest {

    @Test
    void createUserTest() {
        RegisterRequest request = RegisterRequest.generate();
        RegisterResponse registerResponse = executeGetRegister(request);

        assertEquals("User successfully created", registerResponse.getMessage());
        assertEquals(registerResponse.getUser().getEmail(), request.getEmail());
        assertEquals(request.getName(), registerResponse.getUser().getName());

        User userFromDb = UsersQueries.getUserByName(request.getName());

        assertEquals(request.getName(), userFromDb.getName());
        assertEquals(request.getEmail(), userFromDb.getEmail());
    }

    @Test
    void pendingProductsBySuperAdminTest() {
        RegisterRequest request = RegisterRequest.generate();
        executeGetRegister(request);

        UsersQueries.setUserSuperAdminByName(request.getName());

        LoginResponse loginResponse = executePostLogin(LoginRequest.builder()
                .email(request.getEmail()).password(request.getPassword()).build());
        Response pendingProducts = executeGePendingProducts(loginResponse.getAccessToken());
        assertEquals(200, pendingProducts.statusCode());
    }

    @Test
    void pendingProductsByRegularUserTest() {
        RegisterRequest request = RegisterRequest.generate();
        executeGetRegister(request);

        LoginResponse loginResponse = executePostLogin(LoginRequest.builder()
                .email(request.getEmail()).password(request.getPassword()).build());
        Response pendingProducts = executeGePendingProducts(loginResponse.getAccessToken());


        assertEquals(403, pendingProducts.statusCode());
        assertEquals("Insufficient rights to view pending products", pendingProducts.jsonPath().get("detail"));
    }
}