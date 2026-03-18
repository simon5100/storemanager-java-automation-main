package org.example.tests.backend;

import io.restassured.response.Response;
import org.example.backend.models.*;
import org.example.db.UsersQueries;
import org.example.db.models.User;
import org.example.tests.BaseTest;
import org.junit.jupiter.api.Test;
import static org.example.backend.requests.AuthServiceRequest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoreManagerApiTests extends BaseTest {

    @Test
    void createUserTest() {
        RegisterRequest request = RegisterRequest.generate();
        RegisterResponse registerResponse = getRegisterResponse(request);

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
        getRegisterResponse(request);

        UsersQueries.setUserSuperAdminByName(request.getName());

        LoginResponse loginResponse = postLogin(LoginRequest.builder()
                .email(request.getEmail()).password(request.getPassword()).build());
        Response pendingProducts = gePendingProducts(loginResponse.getAccessToken());
        assertEquals(200, pendingProducts.statusCode());
    }

    @Test
    void pendingProductsByRegularUserTest() {
        RegisterRequest request = RegisterRequest.generate();
        getRegisterResponse(request);

        LoginResponse loginResponse = postLogin(LoginRequest.builder()
                .email(request.getEmail()).password(request.getPassword()).build());
        Response pendingProducts = gePendingProducts(loginResponse.getAccessToken());


        assertEquals(403, pendingProducts.statusCode());
        assertEquals("Insufficient rights to view pending products", pendingProducts.jsonPath().get("detail"));
    }
}