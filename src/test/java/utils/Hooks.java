package utils;

import base.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    public AppiumDriver driver;

    public Hooks(AppiumDriver driver) {
        this.driver = driver;
    }

    @Before
    public void beforeScenario() throws InterruptedException {

        Thread.sleep(4000);
    }

         @After
         public void afterScenario(Scenario scenario) {
             if (scenario.isFailed()) {
                 TakesScreenshot ts = (TakesScreenshot) driver;
                 byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                 scenario.attach(screenshot, "image/png", "Failed Step Screenshot");
             }
         }
}