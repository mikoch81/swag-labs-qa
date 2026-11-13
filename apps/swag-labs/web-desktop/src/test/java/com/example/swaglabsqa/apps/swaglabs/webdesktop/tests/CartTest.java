package com.example.swaglabsqa.apps.swaglabs.webdesktop.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import org.junit.jupiter.api.DisplayName;

import com.example.swaglabsqa.web.extension.RegressionTest;
import com.example.swaglabsqa.apps.swaglabs.webdesktop.pages.CartPage;
import com.example.swaglabsqa.apps.swaglabs.webdesktop.pages.InventoryPage;

@Epic("Swag Labs")
@Feature("Cart")
class CartTest extends SwagLabsTest {

    @RegressionTest
    @Story("Add to cart")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Added products are counted on the cart icon and listed in the cart")
    void addedProductsAppearInTheCart() {
        InventoryPage inventory = loginAsStandardUser().addToCart(BACKPACK).addToCart(BIKE_LIGHT);

        assertEquals(2, inventory.cartBadgeCount());
        assertEquals(List.of(BACKPACK, BIKE_LIGHT), inventory.openCart().productNames());
    }

    @RegressionTest
    @Story("Remove from cart")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Removing a product leaves the rest of the cart untouched")
    void removedProductLeavesTheCart() {
        CartPage cart = loginAsStandardUser()
                .addToCart(BACKPACK)
                .addToCart(BIKE_LIGHT)
                .openCart()
                .remove(BACKPACK);

        assertEquals(List.of(BIKE_LIGHT), cart.productNames());
        assertEquals(1, cart.cartBadgeCount());
    }
}
