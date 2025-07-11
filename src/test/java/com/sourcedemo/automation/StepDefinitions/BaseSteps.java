package com.sourcedemo.automation.StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseSteps {

    public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    public static Actions actions;
    public WebDriverWait wait;
    public WebDriver driver;
    public static final String baseUrl = "https://www.saucedemo.com/";
    public static final String inventoryPageUrl = "https://www.saucedemo.com/inventory.html";

    public BaseSteps() {
        driver = getDriver();
        if (driver == null) {
            driver = new ChromeDriver();
            // Initialize WebDriver here, e.g., with a DriverFactory
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            threadLocalDriver.set(driver);
        }
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    public static void setDriver(WebDriver driver) {
        threadLocalDriver.set(driver);
    }

}
