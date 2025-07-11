package com.saucedemo.POJOS;

import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.PageFactory.initElements;

@Data
public class LoginPage {
    WebDriverWait wait;
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, ofSeconds(10));
        initElements(this.driver, this);
    }

    @FindBy(css = "#user-name")
    private WebElement usernameInput;

    @FindBy(css = "#password")
    private WebElement passwordInput;

    @FindBy(css = "#login-button")
    private WebElement loginButton;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    public void login(String userName, String password) {
        usernameInput.clear();
        usernameInput.sendKeys(userName);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    // Error message retrieval with wait
    public String getErrorMessageText() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.getText();
        } catch (Exception e) {
            return null; // Or return "No error message shown"
        }
    }
}
