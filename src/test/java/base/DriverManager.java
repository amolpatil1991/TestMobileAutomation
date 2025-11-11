package base;

import io.appium.java_client.AppiumDriver;

public class DriverManager {

    // Each thread has its own driver instance
    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static synchronized void setDriver(AppiumDriver driverInstance) {
        driver.set(driverInstance);
    }

    public static synchronized AppiumDriver getDriver() {
        return driver.get();
    }

    public static synchronized void unload() {
        driver.remove();
    }
}
