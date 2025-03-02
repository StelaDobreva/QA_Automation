package com.bookstore.tests;

import com.bookstore.api.ApiClient;
import com.bookstore.utils.AuthUtils;
import groovyjarjarasm.asm.util.Printer;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BookStoreApiTests {

    private String token;

    @BeforeClass
    public void setup() throws IOException {
        Properties config = new Properties();
        FileInputStream input = new FileInputStream("src/main/resources/config.properties");
        config.load(input);

        String username = config.getProperty("username");
        String password = config.getProperty("password");

        token = AuthUtils.generateToken(username, password);
        ApiClient.setup(token);
    }

    @Test
    public void testGetBooksPositive() {
        Response response = ApiClient.get("/Books/v1/Books");

        System.out.println("Response Body:\n" + response.prettyPrint());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.jsonPath().getList("books").size() > 0, "Books should be available.");
    }

    @Test
    public void testAddBookNegative() {
        String invalidPayload = "{\"invalidField\": \"value\"}";

        Response response = ApiClient.post("/Books/v1/Books", invalidPayload);

        Assert.assertEquals(response.getStatusCode(), 400, "Invalid payload should return 400.");
    }
}
