package com.bookstore.utils;

import com.bookstore.api.ApiClient;
import io.restassured.response.Response;

public class AuthUtils {

    private static final String AUTH_URL = "https://demoqa.com/Account/v1/GenerateToken";

    public static String generateToken(String username, String password) {

        String payload = String.format("{\"userName\": \"%s\", \"password\": \"%s\"}", username, password);

        Response response = ApiClient.postRequest(AUTH_URL, payload);

        if (response.getStatusCode() == 200) {
            return response.jsonPath().getString("token");
        } else {
            throw new RuntimeException("Authentication failed. Status code: " + response.getStatusCode());
        }
    }
}
