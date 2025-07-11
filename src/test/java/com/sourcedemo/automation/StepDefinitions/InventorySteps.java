package com.sourcedemo.automation.StepDefinitions;

import com.saucedemo.POJOS.InventoryPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static org.testng.Assert.*;

@Log4j2
public class InventorySteps extends BaseSteps {
    private InventoryPage inventoryPage;

    @And("the inventory page components should be loaded")
    public void theInventoryPageComponentShouldBeLoaded() {
        inventoryPage = new InventoryPage(getDriver());
        assertTrue(inventoryPage.isAppLogoDisplayed(), "App logo is not displayed");
        assertTrue(inventoryPage.isProductListDisplayed(), "Product list is not displayed");
        assertTrue(inventoryPage.isCartIconDisplayed(), "Cart icon is not displayed");
        assertTrue(inventoryPage.isMenuButtonDisplayed(), "Menu button is not displayed");
    }

    @Then("all inventory items should have a name, price, and image")
    public void allInventoryItemsShouldHaveNamePriceImage() {
        assertTrue(inventoryPage.allItemsHaveNamePriceImage(), "Not all items have name, price, and image");
    }

    @And("select sorting option {string} from the inventory page")
    public void selectSortingOption(String str) {
        Select select = new Select(inventoryPage.getSortDropdown());
        select.selectByVisibleText(str);
    }

    @And("changes as per sorting type {string} been observed")
    public void changesAsPerSortingTypeBeenObserved(String str) {
        log.info("Selected sorting option: " + str);
        switch (str) {
            case "Name (A to Z)":
                assertTrue(inventoryPage.isSortedByNameAsc(), "Products are not sorted by Name (A to Z)");
                break;
            case "Name (Z to A)":
                assertTrue(inventoryPage.isSortedByNameDesc(), "Products are not sorted by Name (Z to A)");
                break;
            case "Price (low to high)":
                assertTrue(inventoryPage.isSortedByPriceAsc(), "Products are not sorted by Price (low to high)");
                break;
            case "Price (high to low)":
                assertTrue(inventoryPage.isSortedByPriceDesc(), "Products are not sorted by Price (high to low)");
                break;
            default:
                throw new IllegalArgumentException("Invalid sorting option: " + str);
        }
    }

    @When("the user adds {string} to the cart")
    public void theUserAddsProductToTheCart(String product) {
        inventoryPage.addOrRemoveProductToCart(product);
    }

    @When("the user remove {string} to the cart")
    public void theUserRemovesProductToTheCart(String product) {
        inventoryPage.addOrRemoveProductToCart(product);
    }

    @Then("the cart icon should show {int} count")
    public void theCartIconShouldShowExpectedCount(int expectedCount) {

        if (expectedCount == 0) {
            assertFalse(wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("span.shopping_cart_badge"), expectedCount)).iterator().hasNext(),
                    "The count icon should not be displayed when the cart is empty");
        } else {
            WebElement cartCountElement = inventoryPage.getCartButton().findElement(By.cssSelector("span.shopping_cart_badge"));
            int cartCount = Integer.parseInt(cartCountElement.getText());
            assertEquals(cartCount, expectedCount, "Count mismatch in cart icon, expected: 1, actual: " + cartCount);
        }
    }

}
