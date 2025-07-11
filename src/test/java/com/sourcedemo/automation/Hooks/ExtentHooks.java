package com.sourcedemo.automation.Hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.sourcedemo.automation.StepDefinitions.BaseSteps;
import com.sourcedemo.automation.Utils.ExtentManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ExtentHooks {
    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Before
    public void beforeScenario(Scenario scenario) {
        test.set(extent.createTest(scenario.getName()));
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        String stepDescription = scenario.getName();
        byte[] screenshot = ((TakesScreenshot) BaseSteps.getDriver()).getScreenshotAs(OutputType.BYTES);
        if (scenario.isFailed()) {
            test.get().fail("Scenario failed: " + stepDescription,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(
                            java.util.Base64.getEncoder().encodeToString(screenshot), "Failure Screenshot"
                    ).build()
            );
        }
    }

    @After
    public void afterScenario() {
        extent.flush();
    }

    public static ExtentTest getTest() {
        return test.get();
    }

}
