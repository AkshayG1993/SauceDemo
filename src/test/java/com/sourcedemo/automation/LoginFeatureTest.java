package com.sourcedemo.automation;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        tags = "not @ignore",
        features = "src/test/resources/features",
        glue = {"com.sourcedemo.automation.StepDefinitions", "com.sourcedemo.automation.Hooks"},
        plugin = {"pretty", "html:target/cucumber-reports.html","com.sourcedemo.automation.Hooks.StepDescriptionLogger"}
)
public class LoginFeatureTest extends AbstractTestNGCucumberTests {
    // This class runs the Cucumber tests for the login feature
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}