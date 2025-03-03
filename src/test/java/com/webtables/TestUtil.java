package com.webtables;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public abstract class TestUtil {

    private static final String URL_BASE = "https://demoqa.com/webtables";
    protected WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));

        driver.get(URL_BASE);
    }

    @AfterTest
    public void afterTest() {
        if (driver != null) {
            driver.quit();
        }
    }
}


