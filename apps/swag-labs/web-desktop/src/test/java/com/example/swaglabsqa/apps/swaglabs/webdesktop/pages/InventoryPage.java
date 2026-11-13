package com.example.swaglabsqa.apps.swaglabs.webdesktop.pages;

import java.time.Duration;
import java.util.List;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InventoryPage extends SwagLabsPage {

    private static final By TITLE = By.cssSelector(".title");
    private static final By SORT = By.cssSelector(".product_sort_container");
    private static final By ITEM = By.cssSelector(".inventory_item");
    private static final By ITEM_NAME = By.cssSelector(".inventory_item_name");
    private static final By ITEM_PRICE = By.cssSelector(".inventory_item_price");
    private static final By ITEM_BUTTON = By.cssSelector("button");

    public InventoryPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }

    public InventoryPage waitUntilLoaded() {
        waitForVisible(ITEM);
        return this;
    }

    public String title() {
        return getText(TITLE);
    }

    @Step("Sort products by '{option}'")
    public InventoryPage sortBy(String option) {
        selectByVisibleText(SORT, option);
        return this;
    }

    public List<String> productNames() {
        return driver.findElements(ITEM_NAME).stream().map(WebElement::getText).toList();
    }

    public List<Double> productPrices() {
        return driver.findElements(ITEM_PRICE).stream()
                .map(price -> amount(price.getText()))
                .toList();
    }

    /** Products that show a name, a price and an enabled button. */
    public long completeProductCards() {
        return driver.findElements(ITEM).stream()
                .filter(card -> !card.findElement(ITEM_NAME).getText().isBlank()
                        && card.findElement(ITEM_PRICE).getText().startsWith("$")
                        && card.findElement(ITEM_BUTTON).isEnabled())
                .count();
    }

    @Step("Add '{productName}' to the cart")
    public InventoryPage addToCart(String productName) {
        waitAndClick(By.id("add-to-cart-" + productSlug(productName)));
        return this;
    }
}
