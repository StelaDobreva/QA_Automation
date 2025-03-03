package com.webtables.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddNewMemberForm {

    private WebDriver driver;

    private final By REGISTRATION_FORM_HEADER = By.id("registration-form-modal");
    private final By FIRST_NAME_FIELD = By.id("firstName");
    private final By Last_NAME_FIELD = By.id("lastName");
    private final By AGE_FIELD = By.id("age");
    private final By EMAIL_FIELD = By.id("userEmail");
    private final By SALARY_FIELD = By.id("salary");
    private final By DEPARTMENT_FIELD = By.id("department");
    private final By SUBMIT_BUTTON = By.id("submit");
    private final By CLOSE_BUTTON = By.className("close");

    public AddNewMemberForm(WebDriver driver) {
        this.driver = driver;
    }

    public String getRegistrationFormHeaderText() {
        return driver.findElement(REGISTRATION_FORM_HEADER).getText();
    }

    public void enterMemberDetails(String firstName, String lastName, String email, int age, int salary, String department) {
        driver.findElement(FIRST_NAME_FIELD).clear();
        driver.findElement(FIRST_NAME_FIELD).sendKeys(firstName);

        driver.findElement(Last_NAME_FIELD).clear();
        driver.findElement(Last_NAME_FIELD).sendKeys(lastName);

        driver.findElement(EMAIL_FIELD).clear();
        driver.findElement(EMAIL_FIELD).sendKeys(email);

        driver.findElement(AGE_FIELD).clear();
        driver.findElement(AGE_FIELD).sendKeys(String.valueOf(age));

        driver.findElement(SALARY_FIELD).clear();
        driver.findElement(SALARY_FIELD).sendKeys(String.valueOf(salary));

        driver.findElement(DEPARTMENT_FIELD).clear();
        driver.findElement(DEPARTMENT_FIELD).sendKeys(department);
    }

    public void submitForm() {
        driver.findElement(SUBMIT_BUTTON).click();
    }

    public void closeRegistrationForm() {
        driver.findElement(CLOSE_BUTTON).click();
    }

    public String getFieldValue(String field) {
        By fieldLocator = switch (field.toLowerCase()) {
            case "firstname" -> FIRST_NAME_FIELD;
            case "lastname" -> Last_NAME_FIELD;
            case "email" -> EMAIL_FIELD;
            case "age" -> AGE_FIELD;
            case "salary" -> SALARY_FIELD;
            case "department" -> DEPARTMENT_FIELD;
            default -> throw new IllegalArgumentException("Invalid field name: " + field);
        };

        return driver.findElement(fieldLocator).getDomProperty("value");
    }

    public void editSingleField(String field, Object newInput) {
        By fieldLocator = switch (field.toLowerCase()) {
            case "firstname" -> FIRST_NAME_FIELD;
            case "lastname" -> Last_NAME_FIELD;
            case "email" -> EMAIL_FIELD;
            case "age" -> AGE_FIELD;
            case "salary" -> SALARY_FIELD;
            case "department" -> DEPARTMENT_FIELD;
            default -> throw new IllegalArgumentException("Invalid field name: " + field);
        };

        String inputToEnter = newInput instanceof String ? (String) newInput : String.valueOf(newInput);

        driver.findElement(fieldLocator).clear();
        driver.findElement(fieldLocator).sendKeys(inputToEnter);
    }
}
