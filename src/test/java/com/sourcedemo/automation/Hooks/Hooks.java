package com.sourcedemo.automation.Hooks;

import com.sourcedemo.automation.StepDefinitions.BaseSteps;
import com.sourcedemo.automation.Utils.ExtentManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class Hooks {
    @Before
    public void setUp() {
        if (BaseSteps.getDriver() == null) {
            WebDriver driver = new ChromeDriver();
            BaseSteps.setDriver(driver);
        }
    }

    @After
    public void tearDown() {
        WebDriver driver = BaseSteps.getDriver();
        if (driver != null) {
            driver.quit();
            BaseSteps.setDriver(null);
        }
    }

    @AfterAll
    public static void openLatestExtentReport() {
        // Flush the Extent report
        ExtentManager.getInstance().flush();

        File reportDir = new File("test-output");
        if (!reportDir.exists() || !reportDir.isDirectory()) {
            System.err.println("Report directory not found: " + reportDir.getAbsolutePath());
            return;
        }

        File[] htmlFiles = reportDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".html"));
        if (htmlFiles == null || htmlFiles.length == 0) {
            System.err.println("No HTML report files found in: " + reportDir.getAbsolutePath());
            return;
        }

        File latestReport = Arrays.stream(htmlFiles)
                .max(Comparator.comparingLong(File::lastModified))
                .orElse(null);

        if (latestReport != null) {
            try {
                String os = System.getProperty("os.name").toLowerCase();
                Process process;
                if (os.contains("win")) {
                    process = new ProcessBuilder("cmd", "/c", "start", latestReport.getAbsolutePath()).start();
                } else {
                    process = new ProcessBuilder("/usr/bin/open", latestReport.getAbsolutePath()).start();
                }
                process.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Could not determine the latest report file.");
        }

    }
}
