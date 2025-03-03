package com.webtables.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebTablePage {

    private WebDriver driver;

    private final By ADD_BUTTON = By.id("addNewRecordButton");

    public WebTablePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAddButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_BUTTON));
        WebElement addButtonElement = wait.until(ExpectedConditions.elementToBeClickable(ADD_BUTTON));
        addButtonElement.click();
    }

    public void clickDeleteButton(int rowNumber) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By deleteButton = By.id("delete-record-" + rowNumber);
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteButton));
        WebElement deleteButtonElement = wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        deleteButtonElement.click();
    }

    public void clickEditButton(int rowNumber) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By editButton = By.id("edit-record-" + rowNumber);
        wait.until(ExpectedConditions.visibilityOfElementLocated(editButton));
        WebElement editButtonElement = wait.until(ExpectedConditions.elementToBeClickable(editButton));
        editButtonElement.click();
    }

    public void addNewMember(String firstName, String lastName, String email, int age, int salary, String department) {
        clickAddButton();
        AddNewMemberForm addNewMemberForm = new AddNewMemberForm(driver);
        addNewMemberForm.enterMemberDetails(firstName, lastName, email, age, salary, department);
        addNewMemberForm.submitForm();
    }

    public WebElement getRowByText(String text) {
        try {
            By rowLocator = By.xpath("//div[@class='rt-tr-group']//div[text()='" + text + "']");
            return driver.findElement(rowLocator);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public String getTableCellText(Integer row, Integer column) {
        return driver.findElement(By.xpath("//div[@class='rt-tr-group'][" + row + "]//div[@class='rt-td'][" + column + "]")).getText();
    }

    public String getFirstNameText(Integer row) {
        return getTableCellText(row, 1);
    }

    public String getLastNameText(Integer row) {
        return getTableCellText(row, 2);
    }

    public Integer getAge(Integer row) {
        return Integer.parseInt(getTableCellText(row, 3));
    }

    public String getEmailText(Integer row) {
        return getTableCellText(row, 4);
    }

    public Integer getSalary(Integer row) {
        return Integer.parseInt(getTableCellText(row, 5));
    }

    public String getDepartmentText(Integer row) {
        return getTableCellText(row, 6);
    }
}
