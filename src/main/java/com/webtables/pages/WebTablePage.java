package com.webtables.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class WebTablePage {

    private WebDriver driver;

    private By addButton = By.id("addNewRecordButton");
    private By tableRows = By.xpath("//div[@class='rt-tbody']//div[@class='rt-tr-group']");
    private By tableColumns = By.xpath("//div[@class='rt-tbody']//div[@class='rt-tr-group'][last()]//div[@class='rt-td']");
    private By deleteButton = By.xpath("//span[contains(text(), 'Delete')]");


    public WebTablePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAddButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement addButtonElement = wait.until(ExpectedConditions.elementToBeClickable(addButton));
        addButtonElement.click();
    }

    public void deleteMember() {
        driver.findElement(deleteButton).click();
    }

    public String getTableCellText(int row, int column) {
        return driver.findElement(By.xpath("//div[@class='rt-tr-group'][" + row + "]/div[" + column + "]")).getText();
    }
}
