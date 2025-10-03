package stepDefinitions;

import base.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginSteps extends BaseTest {


    LoginPage loginPage;

    @Given("the app is launched")
    public void launchApp() throws Exception {
        setup();
        loginPage = new LoginPage(getDriver());
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
    public void iShouldBeLoggedIn()
    {
        loginPage.isHomeScreenDisplayed();
        System.out.println("Logged in verification logic goes here");
    }


}
