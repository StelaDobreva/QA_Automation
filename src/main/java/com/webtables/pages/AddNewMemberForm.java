package com.webtables.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddNewMemberForm {

    private WebDriver driver;

    private By registrationFormHeader = By.id("registration-form-modal");
    private By firstNameField = By.id("firstName");
    private By lastNameField = By.id("lastName");
    private By ageField = By.id("age");
    private By emailField = By.id("userEmail");
    private By salaryField = By.id("salary");
    private By departmentField = By.id("department");
    private By submitButton = By.id("submit");

    public AddNewMemberForm(WebDriver driver) {
        this.driver = driver;
    }

    public String getRegistrationFormHeaderText() {
        return driver.findElement(registrationFormHeader).getText();
    }

    public void enterMemberDetails(String firstName, String lastName, String email, String age, String salary, String department) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(ageField).sendKeys(age);
        driver.findElement(salaryField).sendKeys(salary);
        driver.findElement(departmentField).sendKeys(department);
    }

    public void submitForm() {
        driver.findElement(submitButton).click();
    }
}
