package com.sourcedemo.automation.StepDefinitions;

import com.saucedemo.POJOS.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Objects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginSteps extends BaseSteps {
    private LoginPage loginPage;

    @Given("the user try to open the inventory page without login")
    public void theUserTryToOpenTheInventoryPageWithoutLoggingIn() {
        getDriver().get(inventoryPageUrl);
    }

    @Given("the login page should be loaded")
    public void theLoginPageShouldBeLoaded() {
        assertEquals(getDriver().getCurrentUrl(), baseUrl, "User is not on the login page");
        loginPage = new LoginPage(getDriver());
    }

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        getDriver().get(baseUrl);
        loginPage = new LoginPage(getDriver());
    }

    @When("the user logs in with username {string} and password {string}")
    public void theUserLogsInWithUsernameAndPassword(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("the user should be redirected to the inventory page")
    public void theUserShouldBeRedirectedToTheInventoryPage() {
        assertTrue(Objects.requireNonNull(getDriver().getCurrentUrl()).contains("inventory"));
    }

    @Then("the user should still be in login page")
    public void theUserShouldStillBeInLoginPage() {
        assertEquals(getDriver().getCurrentUrl(), "https://www.saucedemo.com/", "User is not on the login page");
    }

    @Then("the error message should be displayed {string}")
    public void theErrorMessageShouldBeDisplayed(String errorMsg) {
        String actualErrorMsg = loginPage.getErrorMessageText();
        assertEquals(actualErrorMsg, errorMsg, "Error message does not match");
    }

    @And("the user closes the browser")
    public void the_user_closes_the_browser() {
        getDriver().close();
    }
}
