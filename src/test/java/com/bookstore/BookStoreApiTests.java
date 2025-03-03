package com.bookstore;

import com.bookstore.api.ApiClient;
import com.bookstore.model.Book;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.bookstore.model.Book.createBookFromResponse;

public class BookStoreApiTests extends TestUtils{

    @Test
    public void testGetAllBooksPositive() {
        Response response = ApiClient.getRequest("BookStore/v1/Books", null);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.jsonPath().getList("books").size() > 0, "Books should be available.");
    }

    @Test
    public void testGetBookPositive() {
        String isbn = "9781491904244";

        Map<String, String> params = new HashMap<>();
        params.put("ISBN", isbn);

        Response response = ApiClient.getRequest("BookStore/v1/Book", params);

        Map<String, Object> bookData = response.jsonPath().getMap("$");
        Book book = createBookFromResponse(bookData);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(book.getIsbn(), isbn);
    }

    @Test
    public void testGetBookNegative() {
        String isbn = "1000";

        Map<String, String> params = new HashMap<>();
        params.put("ISBN", isbn);

        Response response = ApiClient.getRequest("BookStore/v1/Book", params);

        String errorCode = response.jsonPath().getString("code");
        String errorMessage = response.jsonPath().getString("message");

        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertEquals(errorCode, "1205");
        Assert.assertEquals(errorMessage, "ISBN supplied is not available in Books Collection!");
    }
}
