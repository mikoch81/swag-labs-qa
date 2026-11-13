package com.example.swaglabsqa.apps.swaglabs.webdesktop.pages;

import java.time.Duration;
import java.util.List;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** The three checkout steps: customer information, order overview, confirmation. */
public class CheckoutPage extends SwagLabsPage {

    private static final By FIRST_NAME = By.id("first-name");
    private static final By LAST_NAME = By.id("last-name");
    private static final By POSTAL_CODE = By.id("postal-code");
    private static final By CONTINUE = By.id("continue");
    private static final By ERROR = By.cssSelector("[data-test='error']");
    private static final By ITEM_PRICE = By.cssSelector(".cart_item .inventory_item_price");
    private static final By ITEM_TOTAL = By.cssSelector(".summary_subtotal_label");
    private static final By FINISH = By.id("finish");
    private static final By CONFIRMATION = By.cssSelector(".complete-header");

    public CheckoutPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }

    @Step("Enter customer information: {firstName} {lastName}, {postalCode}")
    public CheckoutPage enterCustomer(String firstName, String lastName, String postalCode) {
        waitAndType(FIRST_NAME, firstName);
        waitAndType(LAST_NAME, lastName);
        waitAndType(POSTAL_CODE, postalCode);
        waitAndClick(CONTINUE);
        return this;
    }

    public String errorMessage() {
        return getText(ERROR);
    }

    public List<Double> itemPrices() {
        waitForVisible(FINISH);
        return driver.findElements(ITEM_PRICE).stream()
                .map(price -> amount(price.getText()))
                .toList();
    }

    public double itemTotal() {
        return amount(getText(ITEM_TOTAL));
    }

    @Step("Finish the order")
    public CheckoutPage finish() {
        waitAndClick(FINISH);
        return this;
    }

    public String confirmation() {
        return getText(CONFIRMATION);
    }
}
