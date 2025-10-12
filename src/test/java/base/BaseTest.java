package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class BaseTest {

    private AppiumDriver driver;
    public static String platform; // Add this line


    public void setup(String platform) throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();

        if (platform.equalsIgnoreCase("android")) {
            caps.setCapability("platformName", "Android");
            caps.setCapability("appium:deviceName", "emulator-5554");
            caps.setCapability("appium:app", "/Users/admin/IdeaProjects/TestAuto/src/test/resources/apk/Sauce.apk");
            caps.setCapability("appium:appPackage", "com.swaglabsmobileapp");
            caps.setCapability("appium:appActivity", "com.swaglabsmobileapp.MainActivity");
            caps.setCapability("appium:automationName", "UiAutomator2");

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), caps);

        } else if (platform.equalsIgnoreCase("ios")) {
            caps.setCapability("platformName", "iOS");  // ✅ W3C standard key (no appium: prefix)
            caps.setCapability("appium:deviceName", "iPhone 16");  // ✅ Must match simulator name exactly
            caps.setCapability("appium:platformVersion", "18.0");  // ✅ Must match installed simulator version
            caps.setCapability("appium:automationName", "XCUITest");
            caps.setCapability("appium:app", "/Users/admin/IdeaProjects/TestAuto/src/test/resources/ios/iOS.Simulator.SauceLabs.Mobile.Sample.app.2.7.1.app");
            //caps.setCapability("appium:fullReset", true);
            //caps.setCapability("appium:useNewWDA", false);
            caps.setCapability("appium:noReset", false);
            caps.setCapability("appium:fullReset", false);
            caps.setCapability("appium:useNewWDA", false);

            caps.setCapability("appium:wdaLaunchTimeout", 60000); // 2 minutes
            // Add UDID, xcodeOrgId, etc., if using real device

            driver = new IOSDriver(new URL("http://127.0.0.1:4723/"), caps);

        } else {
            throw new IllegalArgumentException("Unsupported platform: " + platform);
        }
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
