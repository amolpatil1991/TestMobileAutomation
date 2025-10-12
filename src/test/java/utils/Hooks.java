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

    private final BaseTest baseTest;

    public Hooks(BaseTest baseTest) {
        this.baseTest = baseTest;
    }

    @Before
    public void beforeScenario() throws Exception {

        baseTest.setup(BaseTest.platform);
        Thread.sleep(10000);
        String excelPath = "/Users/admin/IdeaProjects/TestAuto/src/test/resources/TestData.xlsx";
        ExcelReaderManager.initialize(excelPath);
        System.out.println("Driver initialized: " + (baseTest.getDriver() != null));

    }

         @After
         public void afterScenario(Scenario scenario) {
             if (scenario.isFailed()) {
                 TakesScreenshot ts = (TakesScreenshot) baseTest.getDriver();
                 byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                 scenario.attach(screenshot, "image/png", "Failed Step Screenshot");
             }
             ExcelReaderManager.getInstance().closeWorkbook();

             baseTest.tearDown();

         }
}