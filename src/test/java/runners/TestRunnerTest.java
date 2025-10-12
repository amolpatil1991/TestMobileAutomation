package runners;


import base.BaseTest;
import org.junit.runner.RunWith;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions", "utils", "pages", "base"},
        plugin = {
                "pretty",
                "html:target/cucumber-html-report.html",
                "json:target/cucumber.json"
        },
        tags = "@login",
        monochrome = true
)
public class TestRunnerTest extends AbstractTestNGCucumberTests {
    @BeforeSuite
    @Parameters({"platform"})
    public void setPlatformParameter(String platform) {
        System.out.println("Setting platform from TestNG XML: " + platform);
        BaseTest.platform = platform;  // Set static variable accessible in Hooks
    }

}
