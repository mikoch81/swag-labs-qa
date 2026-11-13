package com.example.swaglabsqa.apps.swaglabs.webdesktop.pages;

import java.time.Duration;
import java.util.Locale;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.swaglabsqa.web.page.BasePage;

/** What every signed-in Swag Labs page shares: the header with the cart. */
public abstract class SwagLabsPage extends BasePage {

    private static final By CART_LINK = By.cssSelector(".shopping_cart_link");
    private static final By CART_BADGE = By.cssSelector(".shopping_cart_badge");

    protected final Duration timeout;

    protected SwagLabsPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
        this.timeout = timeout;
    }

    /** Number shown on the cart icon; the shop hides the badge when the cart is empty. */
    public int cartBadgeCount() {
        return driver.findElements(CART_BADGE).stream()
                .findFirst()
                .map(badge -> Integer.parseInt(badge.getText().trim()))
                .orElse(0);
    }

    @Step("Open the cart")
    public CartPage openCart() {
        waitAndClick(CART_LINK);
        return new CartPage(driver, timeout).waitUntilLoaded();
    }

    /** "Sauce Labs Backpack" -> "sauce-labs-backpack", the suffix of the shop's button ids. */
    protected static String productSlug(String productName) {
        return productName.toLowerCase(Locale.ROOT).replace(' ', '-');
    }

    /** "$29.99" or "Item total: $39.98" -> the amount after the dollar sign. */
    protected static double amount(String text) {
        return Double.parseDouble(text.substring(text.indexOf('$') + 1));
    }
}
