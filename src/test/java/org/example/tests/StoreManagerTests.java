package org.example.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.db.UsersQueries;
import org.example.models.RegisterRequest;
import org.example.models.RegisterResponse;
import org.example.models.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoreManagerTests extends BaseTest {

    @Test
    void firstApiTest() {
        RegisterRequest request = RegisterRequest.generate();
        RegisterResponse registerResponse = getRegisterResponse(request);

        assertEquals("User successfully created", registerResponse.getMessage());
        assertEquals(registerResponse.getUser().getEmail(), request.getEmail());
        assertEquals(request.getName(), registerResponse.getUser().getName());

        User userFromDb = UsersQueries.getUserByName(request.getName());

        assertEquals(registerResponse.getUser().getName(), userFromDb.getName());
    }

    private static RegisterResponse getRegisterResponse(RegisterRequest request) {
        return RestAssured
                .given().log().all()
                .baseUri(BASE_URL)
                .basePath(REGISTER_ENDPOINT)
                .contentType(ContentType.JSON)
                .body(request)
                .when().log().all()
                .post()
                .then()
                .log().all()
                .extract()
                .as(RegisterResponse.class);
    }
}
