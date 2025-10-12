package pages;

import base.BaseTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class LoginPage  {

    private final AppiumDriver driver;

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;


        switch (BaseTest.platform.toLowerCase()) {
            case "android":
                usernameField = By.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]");
                passwordField = By.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]");
                loginButton = By.xpath("//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]");
                productsLabel = By.xpath("//android.widget.TextView[@text=\"PRODUCTS\"]");
                errorMessage = By.xpath("//android.widget.TextView[@text=\"Username and password do not match any user in this service.\"]");
                break;

            case "ios":
                usernameField = By.xpath("//XCUIElementTypeTextField[@name=\"test-Username\"]");
                passwordField = By.xpath("//XCUIElementTypeSecureTextField[@name=\"test-Password\"]");
                loginButton = By.xpath("//XCUIElementTypeOther[@name=\"test-LOGIN\"]");
                productsLabel = By.xpath("//XCUIElementTypeStaticText[@name=\"PRODUCTS\"]");
                errorMessage = By.xpath("//XCUIElementTypeStaticText[@name=\"Username and password do not match any user in this service.\"]");
                break;

            default:
                throw new RuntimeException("Unsupported platform type: " + BaseTest.platform);
        }
    }

    By usernameField = By.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]");
    ////XCUIElementTypeTextField[@name="test-Username"]
    By passwordField = By.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]");
    ////XCUIElementTypeSecureTextField[@name="test-Password"]
    By loginButton = By.xpath("//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]");
    ////XCUIElementTypeOther[@name="test-LOGIN"]
    By productsLabel = By.xpath("//android.widget.TextView[@text=\"PRODUCTS\"]");
    ////XCUIElementTypeStaticText[@name="PRODUCTS"]
    By errorMessage = By.xpath("//android.widget.TextView[@text=\"Username and password do not match any user in this service.\"]");
    ////XCUIElementTypeOther[@name="test-Error message"]  label = Username and password do not match any user in this service.
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void tapLogin() {
        driver.findElement(loginButton).click();
    }

    public boolean isHomeScreenDisplayed() {
        return driver.findElement(productsLabel).isDisplayed();
    }

    public boolean isLoginScreenDisplayed() {
        return driver.findElement(usernameField).isDisplayed();
    }

    public boolean isErrorMessageDisplayed() {
        return driver.findElement(errorMessage).isDisplayed();
    }
}
