package com.example.swaglabsqa.apps.swaglabs.webdesktop.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;
import java.util.List;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import org.junit.jupiter.api.DisplayName;

import com.example.swaglabsqa.web.extension.RegressionTest;
import com.example.swaglabsqa.apps.swaglabs.webdesktop.pages.InventoryPage;

@Epic("Swag Labs")
@Feature("Product list")
class InventoryTest extends SwagLabsTest {

    @RegressionTest
    @Story("Browse")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Every product shows a name, a price and a cart button")
    void everyProductCardIsComplete() {
        InventoryPage inventory = loginAsStandardUser();

        assertEquals(6, inventory.productNames().size(), "the shop sells six products");
        assertEquals(6, inventory.completeProductCards(),
                "every product needs a name, a price and an enabled button");
    }

    @RegressionTest
    @Story("Sort")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Sorting by price lists the cheapest product first")
    void sortsByPriceAscending() {
        List<Double> prices = loginAsStandardUser().sortBy("Price (low to high)").productPrices();

        assertEquals(prices.stream().sorted().toList(), prices);
    }

    @RegressionTest
    @Story("Sort")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Sorting by name Z to A reverses the alphabet")
    void sortsByNameDescending() {
        List<String> names = loginAsStandardUser().sortBy("Name (Z to A)").productNames();

        assertEquals(names.stream().sorted(Comparator.reverseOrder()).toList(), names);
    }
}
