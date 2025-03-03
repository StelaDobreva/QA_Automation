package com.bookstore;

import com.bookstore.api.ApiClient;
import com.bookstore.utils.AuthUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestUtils {

    @BeforeClass
    public void setup() throws IOException {
        Properties config = new Properties();
        FileInputStream input = new FileInputStream("src/resources/config.properties");
        config.load(input);

        String username = config.getProperty("username");
        String password = config.getProperty("password");

        String token = AuthUtils.generateToken(username, password);
        ApiClient.setup(token);
    }

    @AfterClass
    public static void tearDown() {
        ApiClient.logout();  // Not implemented
    }

}
