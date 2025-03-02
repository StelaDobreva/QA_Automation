package com.bookstore.utils;

import com.bookstore.api.ApiClient;
import io.restassured.response.Response;

public class AuthUtils {

    public static String generateToken(String username, String password) {
        String authEndpoint = "/Account/v1/GenerateToken";
        String payload = String.format("{\"userName\": \"%s\", \"password\": \"%s\"}", username, password);

        Response response = ApiClient.post(authEndpoint, payload);

        if (response.getStatusCode() == 200) {
            return response.jsonPath().getString("token");
        } else {
            throw new RuntimeException("Authentication failed. Status code: " + response.getStatusCode());
        }
    }
}
