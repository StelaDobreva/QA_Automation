package com.webtables;

import com.webtables.pages.AddNewMemberForm;
import com.webtables.pages.WebTablePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class WebTableTests extends TestUtil {

    private WebTablePage webTablePage;

    @BeforeMethod
    public void beforeMethod() {
        webTablePage = new WebTablePage(driver);
        driver.get(URL_BASE);
    }

    @DataProvider(name = "validNewMemberData")
    public Object[][] provideTestDataValid() {
        return new Object[][] {
                {"Mr.", "Bean", "mr.bean@test.com", 45, 100000, "QA"}
        };
    }

    @Test
    public void addButtonOpensFormTest() {
        webTablePage.clickAddButton();

        AddNewMemberForm addNewMemberForm = new AddNewMemberForm(driver);

        String actualHeaderText = addNewMemberForm.getRegistrationFormHeaderText();

        Assert.assertEquals(actualHeaderText, "Registration Form", "Registration Form header text is incorrect or missing.");

        addNewMemberForm.closeRegistrationForm();
    }

    @Test(dataProvider = "validNewMemberData")
    public void addNewMemberTest(String firstName, String lastName, String email, int age, int salary, String department) {
        webTablePage.clickAddButton();

        AddNewMemberForm addNewMemberForm = new AddNewMemberForm(driver);

        addNewMemberForm.enterMemberDetails(firstName, lastName, email, age, salary, department);
        addNewMemberForm.submitForm();

        int rowNumber = 4; //3 members are present at each webTablePage reload

        Assert.assertEquals(webTablePage.getFirstNameText(rowNumber), firstName);
        Assert.assertEquals(webTablePage.getLastNameText(rowNumber), lastName);
        Assert.assertEquals(webTablePage.getAge(rowNumber), age);
        Assert.assertEquals(webTablePage.getEmailText(rowNumber), email);
        Assert.assertEquals(webTablePage.getSalary(rowNumber), salary);
        Assert.assertEquals(webTablePage.getDepartmentText(rowNumber), department);

        webTablePage.clickDeleteButton(rowNumber);
    }

    @Test(dataProvider = "validNewMemberData")
    public void editButtonOpensFormTest(String firstName, String lastName, String email, int age, int salary, String department) {
        webTablePage.addNewMember(firstName, lastName, email, age, salary, department);
        webTablePage.clickEditButton(4);

        AddNewMemberForm addNewMemberForm = new AddNewMemberForm(driver);

        String actualHeaderText = addNewMemberForm.getRegistrationFormHeaderText();

        Assert.assertEquals(actualHeaderText, "Registration Form", "Registration Form header text is incorrect or missing.");

        addNewMemberForm.closeRegistrationForm();
    }

    @Test(dataProvider = "validNewMemberData")
    public void editFormFieldsContainExpectedInfoTest(String firstName, String lastName, String email, int age, int salary, String department) {
        int rowNumber = 4; //3 members are present at each webTablePage reload

        webTablePage.addNewMember(firstName, lastName, email, age, salary, department);

        webTablePage.clickEditButton(rowNumber);

        AddNewMemberForm addNewMemberForm = new AddNewMemberForm(driver);

        Assert.assertEquals(addNewMemberForm.getFieldValue("firstname"), firstName);
        Assert.assertEquals(addNewMemberForm.getFieldValue("lastname"), lastName);
        Assert.assertEquals(addNewMemberForm.getFieldValue("email"), email);
        Assert.assertEquals(addNewMemberForm.getFieldValue("age"), String.valueOf(age));
        Assert.assertEquals(addNewMemberForm.getFieldValue("salary"), String.valueOf(salary));
        Assert.assertEquals(addNewMemberForm.getFieldValue("department"), department);

        addNewMemberForm.closeRegistrationForm();
    }

    @Test(dataProvider = "validNewMemberData")
    public void editSingleFieldTest(String firstName, String lastName, String email, int age, int salary, String department) {
        int rowNumber = 4; //3 members are present at each webTablePage reload

        webTablePage.addNewMember(firstName, lastName, email, age, salary, department);

        webTablePage.clickEditButton(rowNumber);

        AddNewMemberForm addNewMemberForm = new AddNewMemberForm(driver);

        int newSalary = 120000;

        addNewMemberForm.editSingleField("salary", newSalary);
        addNewMemberForm.submitForm();

        Assert.assertEquals(webTablePage.getFirstNameText(rowNumber), firstName);
        Assert.assertEquals(webTablePage.getLastNameText(rowNumber), lastName);
        Assert.assertEquals(webTablePage.getAge(rowNumber), age);
        Assert.assertEquals(webTablePage.getEmailText(rowNumber), email);
        Assert.assertEquals(webTablePage.getSalary(rowNumber), newSalary);
        Assert.assertEquals(webTablePage.getDepartmentText(rowNumber), department);

        webTablePage.clickDeleteButton(rowNumber);
    }

    @Test(dataProvider = "validNewMemberData")
    public void editAllFieldsTest(String firstName, String lastName, String email, int age, int salary, String department) {
        int rowNumber = 4; //3 members are present at each webTablePage reload

        webTablePage.addNewMember(firstName, lastName, email, age, salary, department);

        webTablePage.clickEditButton(rowNumber);

        AddNewMemberForm addNewMemberForm = new AddNewMemberForm(driver);

        String newFirstName = "UpdatedFirst";
        String newLastName = "UpdatedLast";
        String newEmail = "updated@example.com";
        int newAge = 50;
        int newSalary = 120000;
        String newDepartment = "Engineering";

        addNewMemberForm.enterMemberDetails(newFirstName, newLastName, newEmail, newAge, newSalary, newDepartment);
        addNewMemberForm.submitForm();

        Assert.assertEquals(webTablePage.getFirstNameText(rowNumber), newFirstName);
        Assert.assertEquals(webTablePage.getLastNameText(rowNumber), newLastName);
        Assert.assertEquals(webTablePage.getEmailText(rowNumber), newEmail);
        Assert.assertEquals(webTablePage.getAge(rowNumber), newAge);
        Assert.assertEquals(webTablePage.getSalary(rowNumber), newSalary);
        Assert.assertEquals(webTablePage.getDepartmentText(rowNumber), newDepartment);

        webTablePage.clickDeleteButton(rowNumber);
    }

    @Test(dataProvider = "validNewMemberData")
    public void editSingleFieldAndCloseFormTest(String firstName, String lastName, String email, int age, int salary, String department) {
        int rowNumber = 4; //3 members are present at each webTablePage reload

        webTablePage.addNewMember(firstName, lastName, email, age, salary, department);

        webTablePage.clickEditButton(rowNumber);

        AddNewMemberForm addNewMemberForm = new AddNewMemberForm(driver);

        int newSalary = 120000;

        addNewMemberForm.editSingleField("salary", newSalary);
        addNewMemberForm.closeRegistrationForm();

        Assert.assertEquals(webTablePage.getSalary(rowNumber), salary);

        webTablePage.clickDeleteButton(rowNumber);
    }

    @Test(dataProvider = "validNewMemberData")
    public void deleteMemberTest(String firstName, String lastName, String email, int age, int salary, String department) {
        webTablePage.addNewMember(firstName, lastName, email, age, salary, department);

        webTablePage.clickDeleteButton(4); //3 members are present at each webTablePage reload

        Assert.assertNull(webTablePage.getRowByText(firstName));
    }

    @Test()
    public void invalidRegistrationInputTest() {
        //TODO validation for email, int for age, special symbols validations
    }

    @Test()
    public void validateInputLengthTest() {
        //TODO test for the maximum and minimum allowed length for fields
    }

    @Test()
    public void editSingleFieldAndCloseFormThenOpenAgainTest(){
        //TODO noticed a bug here once edited the info in the form stays edited even if not submitted
    }

    @Test()
    public void sortingByFirstNameTest() {
        //TODO for each column
    }

    @Test()
    public void emptyTableTest() {
        //TODO Ensure that all required fields (e.g., First Name, Last Name, Email, etc.) are validated when left empty.
    }

    @Test()
    public void registrationFormFiledNamesTest() {
        //TODO test registration from field placeholders need to be deleted before entering data
    }
}
