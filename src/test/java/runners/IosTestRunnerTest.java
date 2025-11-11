package runners;


import base.BaseTest;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.text.SimpleDateFormat;
import java.util.Date;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions", "utils", "pages", "base"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/ios/report.html",
                "json:target/cucumber-reports/ios/report.json"
        },
        tags = "@login",
        monochrome = true
)
public class IosTestRunnerTest extends AbstractTestNGCucumberTests {
    @BeforeTest
    @Parameters({"platform", "deviceName"})
    public void setPlatformParameter(String platform, String deviceName, ITestContext context) {
        System.out.println("Setting platform from TestNG XML: " + platform);

        // Set BaseTest ThreadLocal values
        BaseTest.platform.set(platform);
        BaseTest.device.set(deviceName);

        // Safe device name (remove spaces)
        String safeDeviceName = deviceName.replaceAll("\\s+", "_");

        // Timestamp to prevent overwriting
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Dynamic report path for this run
        String reportPath = "target/cucumber-reports/"
                + platform.toLowerCase() + "/"
                + safeDeviceName + "/"
                + timestamp;

        // Store in TestNG context (optional)
        context.setAttribute("reportPath", reportPath);

        // Also store in system property (Cucumber will read it)
        System.setProperty("reportPath", reportPath);

        System.out.println("Platform: " + platform + ", Device: " + deviceName + ", Report Path: " + reportPath);
    }



}
