package stepDefinitions;

import base.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.NoSuchElementException;
import pages.LoginPage;
import utils.ExcelReaderManager;

import java.io.IOException;
import java.util.*;

public class LoginSteps extends BaseTest {


    private final BaseTest baseTest;
    private LoginPage loginPage;

    public LoginSteps(BaseTest baseTest) {
        this.baseTest = baseTest;
    }


    @Given("the app is launched")
    public void launchApp() throws Exception {

        loginPage = new LoginPage(baseTest.getDriver());

        try {
            boolean getResult = loginPage.isLoginScreenDisplayed();
            if (!getResult)
                throw new AssertionError("login page is not displayed");

        } catch (NoSuchElementException e) {
            // Element not found => treat as home screen not displayed
            throw new AssertionError("seems app launch was not successful", e);
        }

    }

    @When("user enters valid username and password from {string} sheet")
    public void iEnterValidCredentials(String sheetName) throws IOException {
        List<Map<String, String>> data = ExcelReaderManager.getInstance().getSheetData(sheetName);

        for (Map<String, String> row : data) {
            String username = row.get("username");
            String password = row.get("password");

            System.out.println("#######Username: " + username + ", Password: " + password);

            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
            loginPage.tapLogin();
        }
    }
    @When("user enters invalid username and password from {string} sheet")
    public void iEnterInValidCredentials(String sheetName) {
        List<Map<String, String>> data = ExcelReaderManager.getInstance().getSheetData(sheetName);

        for (Map<String, String> row : data) {
            String username1 = row.get("invalid_username");
            String password1 = row.get("invalid_password");

            System.out.println("#######Username: " + username1 + ", Password: " + password1);

            loginPage.enterUsername(username1);
            loginPage.enterPassword(password1);
            loginPage.tapLogin();
        }
    }
    @And("user clicks login button")
    public void tapLoginButton()
    {
        loginPage.tapLogin();
    }

    @Then("user should be logged in successfully")
    public void iShouldBeLoggedIn() {
        try {
            boolean getResult = loginPage.isHomeScreenDisplayed();
            if (!getResult)
                throw new AssertionError("home page is not displayed");

        } catch (NoSuchElementException e) {
            // Element not found => treat as home screen not displayed
            throw new AssertionError("Home screen element PRODUCTS was not found on the page", e);
        }
    }
    @Then("user should see an expected error message")
    public void verifyInvalidCredsErrorMessage() {
        try {
            boolean getResult = loginPage.isErrorMessageDisplayed();
            if (!getResult) {
                throw new AssertionError("Correct Error message is not displayed");
            }
        } catch (NoSuchElementException e) {
            // Element not found => treat as error message not displayed
            throw new AssertionError("Invalid credentials Error message element was not found on the page", e);
        }
    }
}
