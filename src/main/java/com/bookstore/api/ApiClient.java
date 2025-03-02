package com.bookstore.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiClient {

    private static final String BASE_URL = "https://demoqa.com/login";
    private static String token;

    public static void setup(String authToken) {
        RestAssured.baseURI = BASE_URL;
        token = authToken;
    }

    public static Response get(String endpoint) {
        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .get(endpoint);
    }

    public static Response post(String endpoint, String body) {
        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(body)
                .post(endpoint);
    }

    public static Response put(String endpoint, String body) {
        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(body)
                .put(endpoint);
    }

    public static Response delete(String endpoint) {
        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .delete(endpoint);
    }
}
