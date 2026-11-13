package com.example.swaglabsqa.apps.swaglabs.webdesktop.pages;

import java.time.Duration;
import java.util.List;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends SwagLabsPage {

    private static final By CHECKOUT = By.id("checkout");
    private static final By ITEM_NAME = By.cssSelector(".cart_item .inventory_item_name");

    public CartPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }

    public CartPage waitUntilLoaded() {
        waitForVisible(CHECKOUT);
        return this;
    }

    public List<String> productNames() {
        return driver.findElements(ITEM_NAME).stream().map(WebElement::getText).toList();
    }

    @Step("Remove '{productName}' from the cart")
    public CartPage remove(String productName) {
        waitAndClick(By.id("remove-" + productSlug(productName)));
        return this;
    }

    @Step("Proceed to checkout")
    public CheckoutPage checkout() {
        waitAndClick(CHECKOUT);
        return new CheckoutPage(driver, timeout);
    }
}
