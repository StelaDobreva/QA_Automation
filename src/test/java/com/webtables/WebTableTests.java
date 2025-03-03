package com.webtables;

import com.webtables.pages.AddNewMemberForm;
import com.webtables.pages.WebTablePage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class WebTableTests extends TestUtil {

    private WebTablePage webTablePage;

    @Override
    @BeforeTest
    public void beforeTest() {
        super.beforeTest();  // Call the parent class's beforeTest method to initialize WebDriver
        webTablePage = new WebTablePage(driver);  // Initialize the WebTablePage
    }

    @Test
    public void addButtonTest() {
        webTablePage.clickAddButton();

        AddNewMemberForm addNewMemberForm = new AddNewMemberForm(driver);

        String actualHeaderText = addNewMemberForm.getRegistrationFormHeaderText();

        Assert.assertEquals(actualHeaderText, "Registration Form", "Registration Form header text is incorrect or missing.");
    }

    @DataProvider(name = "validNewMemberData")
    public Object[][] provideTestDataValid() {
        return new Object[][] {
                {"Mr.", "Bean", "mr.bean@example.com", "45", "70000", "HR"}
        };
    }

    @Test(dataProvider = "validNewMemberData")
    public void addNewMemberTest(String firstName, String lastName, String email, String age, String salary, String department) {
        webTablePage.clickAddButton();

        AddNewMemberForm addNewMemberForm = new AddNewMemberForm(driver);

        addNewMemberForm.enterMemberDetails(firstName, lastName, email, age, salary, department);
        addNewMemberForm.submitForm();

//        boolean isDataPresent = webTablePage.isDataInTable(firstName, lastName, email, age, salary, department);
//        Assert.assertTrue(isDataPresent, "Data not found in the table.");
    }
}