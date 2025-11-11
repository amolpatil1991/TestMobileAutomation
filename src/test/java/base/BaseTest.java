package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class BaseTest {

    //private AppiumDriver driver;
    public static ThreadLocal<String> platform = new ThreadLocal<>();
    public static ThreadLocal<String> device = new ThreadLocal<>();



    public void setup(String platformName, String deviceName) throws Exception {

        platform.set(platformName);
        device.set(deviceName);


        DesiredCapabilities caps = new DesiredCapabilities();

        if (platformName.equalsIgnoreCase("android")) {
            caps.setCapability("platformName", "Android");
            caps.setCapability("appium:deviceName", deviceName);
            caps.setCapability("appium:app", "/Users/admin/IdeaProjects/TestAuto/src/test/resources/apk/Sauce.apk");
            caps.setCapability("appium:appPackage", "com.swaglabsmobileapp");
            caps.setCapability("appium:appActivity", "com.swaglabsmobileapp.MainActivity");
            caps.setCapability("appium:automationName", "UiAutomator2");

            AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), caps);
            DriverManager.setDriver(driver);

        } else if (platformName.equalsIgnoreCase("ios")) {
            caps.setCapability("platformName", "iOS");  // ✅ W3C standard key (no appium: prefix)
            caps.setCapability("appium:deviceName", deviceName);  // ✅ Must match simulator name exactly
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

            AppiumDriver driver = new IOSDriver(new URL("http://127.0.0.1:4724/"), caps);
            DriverManager.setDriver(driver);


        } else {
            throw new IllegalArgumentException("Unsupported platform: " + platform);
        }
    }

    public void tearDown() {
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
        }
    }

    public AppiumDriver getDriver() {
        return DriverManager.getDriver();
    }

}
