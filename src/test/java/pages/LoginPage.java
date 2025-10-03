package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class LoginPage  {

    public AppiumDriver driver;

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
    }

    By usernameField = By.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]");
    By passwordField = By.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]");
    By loginButton = By.xpath("//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]");
    By productsLabel = By.xpath("//android.widget.TextView[@text=\"PRODUCTS\"]");

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
}
