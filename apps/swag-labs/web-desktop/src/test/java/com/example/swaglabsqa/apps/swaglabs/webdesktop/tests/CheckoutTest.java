package com.example.swaglabsqa.apps.swaglabs.webdesktop.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import org.junit.jupiter.api.DisplayName;

import com.example.swaglabsqa.web.extension.RegressionTest;
import com.example.swaglabsqa.web.extension.SmokeTest;
import com.example.swaglabsqa.apps.swaglabs.webdesktop.pages.CheckoutPage;

@Epic("Swag Labs")
@Feature("Checkout")
class CheckoutTest extends SwagLabsTest {

    @SmokeTest
    @Story("Place an order")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("A customer buys two products and gets an order confirmation")
    void customerPlacesAnOrder() {
        CheckoutPage checkout = checkoutWithTwoProducts()
                .enterCustomer("Ada", "Example", "00-001")
                .finish();

        assertEquals("Thank you for your order!", checkout.confirmation());
        assertEquals(0, checkout.cartBadgeCount(), "the cart is emptied by a placed order");
    }

    @RegressionTest
    @Story("Order overview")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("The item total equals the sum of the product prices")
    void itemTotalIsTheSumOfPrices() {
        CheckoutPage overview = checkoutWithTwoProducts().enterCustomer("Ada", "Example", "00-001");

        double sum = overview.itemPrices().stream().mapToDouble(Double::doubleValue).sum();

        assertEquals(sum, overview.itemTotal(), 0.001);
    }

    @RegressionTest
    @Story("Form validation")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Checkout cannot continue without a first name")
    void firstNameIsRequired() {
        String error = checkoutWithTwoProducts().enterCustomer("", "Example", "00-001").errorMessage();

        assertEquals("Error: First Name is required", error);
    }

    private CheckoutPage checkoutWithTwoProducts() {
        return loginAsStandardUser()
                .addToCart(BACKPACK)
                .addToCart(BIKE_LIGHT)
                .openCart()
                .checkout();
    }
}
