package com.bookstore.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class ApiClient {

    private static final String BASE_URL = "https://demoqa.com";
    private static String token;

    public static void setup(String authToken) {
        RestAssured.baseURI = BASE_URL;
        token = authToken;
    }

    public static Response getRequest(String endpoint, Map<String, String> params) {
        var request = RestAssured.given();

        if (token != null && !token.isEmpty()) {
            request.header("Authorization", "Bearer " + token);
        } else {
            throw new RuntimeException("Authorization token is missing.");
        }

        request.header("Accept", "application/json");

        if (params != null && !params.isEmpty()) {
            request.queryParams(params);
        }

        try {
            return request.get(endpoint);
        } catch (Exception e) {
            throw new RuntimeException("Request failed: " + e.getMessage(), e);
        }
    }

    public static Response postRequest(String endpoint, String body) {
        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(body)
                .post(endpoint);
    }

    public static Response putRequest(String endpoint, String body) {
        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(body)
                .put(endpoint);
    }

    public static Response deleteRequest(String endpoint) {
        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .delete(endpoint);
    }

    public static void logout() {
        //TODO
    }
}
