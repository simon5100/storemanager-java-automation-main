package org.example.backend;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiClient {

    private final RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
            .setContentType(ContentType.JSON);
    private RequestSpecification requestSpec;

    public ApiClient serBaseUtl(String baseUrl) {
        requestSpecBuilder.setBaseUri(baseUrl);
        return this;
    }

    public ApiClient setContentType(ContentType contentType) {
        requestSpecBuilder.setContentType(contentType);
        return this;
    }

    public ApiClient addBearerAuthorization(String token) {
        requestSpecBuilder.addHeader("Authorization", "Bearer " + token);
        return this;
    }

    public ApiClient addHeader(String name, String value) {
        requestSpecBuilder.addHeader(name, value);
        return this;
    }

    public ApiClient addQueryParam(String name, Object value) {
        requestSpecBuilder.addQueryParam(name, value);
        return this;
    }

    public ApiClient addPathParam(String name, Object value) {
        requestSpecBuilder.addPathParam(name, value);
        return this;
    }

    public ApiClient setBody(Object body) {
        requestSpecBuilder.setBody(body);
        return this;
    }

    public ApiClient build() {
        this.requestSpec = this.requestSpecBuilder.build();
        return this;
    }

    // Для получения "сырой" Response (если нужна дополнительная обработка)
    public Response sendRequest(Method method, String path) {
        return innerSendRequest(method, path, null);
    }

    public Response sendRequest(Method method, String path, Object body) {
        return innerSendRequest(method, path, body);
    }

    // Основной метод отправки запроса
    public <T> T sendRequest(Method method, String path, Object body, Class<T> responseClass) {
        Response response = innerSendRequest(method, path, body);
        return response.as(responseClass);
    }

    public Response innerSendRequest(Method method, String path, Object body) {
        RequestSpecification specification = RestAssured.given()
                .filter(new AllureRestAssured()).spec(requestSpec);

        if (body != null) {
            specification.body(body);
        }

        return specification
                .log().all()
                .request(method, path)
                .then().log().all()
                .extract().response();
    }
}
