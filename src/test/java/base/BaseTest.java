package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class BaseTest {

    private AppiumDriver driver;

    public void setup() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:deviceName", "emulator-5554");
        caps.setCapability("appium:app", "/Users/admin/IdeaProjects/TestAuto/src/test/resources/apk/Sauce.apk");
        caps.setCapability("appium:appPackage", "com.swaglabsmobileapp");
        caps.setCapability("appium:appActivity", "com.swaglabsmobileapp.MainActivity");
        caps.setCapability("appium:automationName", "UiAutomator2");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), caps);
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public AppiumDriver getDriver() {
        return driver;
    }

}
