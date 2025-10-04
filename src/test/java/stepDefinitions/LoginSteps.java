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

    @When("user enters username {string} and password {string}")
    public void iEnterValidCredentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.tapLogin();
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
