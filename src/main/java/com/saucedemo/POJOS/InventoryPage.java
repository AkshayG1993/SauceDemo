package com.saucedemo.POJOS;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.PageFactory.initElements;

@Log4j2
@Data
public class InventoryPage {
    WebDriverWait wait;
    WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, ofSeconds(10));
        initElements(this.driver, this);
    }

    @FindBy(css = ".inventory_list")
    private WebElement inventoryList;

    @FindBy(css = "div.app_logo")
    private WebElement appLogo;

    @FindBy(css = ".inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartButton;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_linkn")
    private WebElement logoutLink;

    @FindBy(css = ".product_sort_container")
    private WebElement sortDropdown;

    public boolean isAppLogoDisplayed() {
        log.info("Checking if app logo is displayed");
        try {
            wait.until(ExpectedConditions.visibilityOf(appLogo));
            return true;
        } catch (Exception e) {
            log.error("App logo is not displayed: {}" + e.getMessage());
            return false;
        }
    }

    public boolean isProductListDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(inventoryList));
            return inventoryList.isDisplayed();
        } catch (Exception e) {
            log.error("Product list is not displayed: {}", e.getMessage());
            return false;
        }
    }

    public boolean isCartIconDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(cartButton));
            return cartButton.isDisplayed();
        } catch (Exception e) {
            log.error("Cart icon is not displayed: {}", e.getMessage());
            return false;
        }
    }

    public boolean isMenuButtonDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(menuButton));
            return menuButton.isDisplayed();
        } catch (Exception e) {
            log.error("Menu button is not displayed: {}", e.getMessage());
            return false;
        }
    }

    public boolean isSortedByNameAsc() {
        List<WebElement> productElements = getProductNameElements();
        List<String> names = productElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        List<String> sortedNames = names.stream()
                .sorted()
                .collect(Collectors.toList());
        log.info("Names: {}", names);
        log.info("Sorted names for Asc: {}", sortedNames);
        return names.equals(sortedNames);
    }

    public boolean isSortedByNameDesc() {
        List<WebElement> productElements = getProductNameElements();
        List<String> names = productElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        List<String> sortedNames = names.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        log.info("Names: {}", names);
        log.info("Sorted names for Desc: {}", sortedNames);
        return names.equals(sortedNames);
    }

    public boolean isSortedByPriceDesc() {
        List<WebElement> productElements = getProductPriceElements();
        List<Double> prices = productElements.stream()
                .map(element -> Double.parseDouble(element.getText().replace("$", "")))
                .collect(Collectors.toList());
        List<Double> sortedPrices = prices.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        log.info("Prices: {}", prices);
        log.info("Sorted prices for Desc: {}", sortedPrices);
        return prices.equals(sortedPrices);
    }

    public boolean isSortedByPriceAsc() {
        List<WebElement> productElements = getProductPriceElements();
        List<Double> prices = productElements.stream()
                .map(element -> Double.parseDouble(element.getText().replace("$", "")))
                .collect(Collectors.toList());
        List<Double> sortedPrices = prices.stream()
                .sorted()
                .collect(Collectors.toList());
        log.info("Prices: {}", prices);
        log.info("Sorted prices for Asc: {}", sortedPrices);
        return prices.equals(sortedPrices);
    }

    private List<WebElement> getProductPriceElements() {
        return getDriver().findElements(By.cssSelector(".inventory_item_price"));
    }

    // Helper to get product name elements
    private List<WebElement> getProductNameElements() {
        // Replace with your actual locator
        return getDriver().findElements(By.cssSelector(".inventory_item_name"));
    }

    public boolean allItemsHaveNamePriceImage() {
        log.info("Checking if all items have name, price, and image");
        for (WebElement item : inventoryItems) {
            WebElement nameElement = item.findElement(By.cssSelector(".inventory_item_name"));
            WebElement priceElement = item.findElement(By.cssSelector(".inventory_item_price"));
            WebElement imageElement = item.findElement(By.cssSelector(".inventory_item_img"));

            if (nameElement.getText().isEmpty() || priceElement.getText().isEmpty() || !imageElement.isDisplayed()) {
                log.error("Item is missing name, price, or image: {}", item.getText());
                return false;
            }
        }
        return true;
    }

    public void addOrRemoveProductToCart(String productName) {
        List<WebElement> items = getDriver().findElements(By.cssSelector(".inventory_item"));
        for (WebElement item : items) {
            String name = item.findElement(By.cssSelector(".inventory_item_name")).getText();
            if (name.equalsIgnoreCase(productName)) {
                item.findElement(By.cssSelector("button.btn_inventory")).click();
                return;
            }
        }
        throw new NoSuchElementException("Product not found: " + productName);
    }

}