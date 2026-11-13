package com.example.swaglabsqa.apps.swaglabs.webdesktop.tests;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;

import com.example.swaglabsqa.web.config.WebConfig;
import com.example.swaglabsqa.web.test.BaseWebTest;
import com.example.swaglabsqa.apps.swaglabs.webdesktop.data.TestUsers;
import com.example.swaglabsqa.apps.swaglabs.webdesktop.pages.InventoryPage;
import com.example.swaglabsqa.apps.swaglabs.webdesktop.pages.LoginPage;

/** Shared start of every Swag Labs test: the live shop, optionally signed in. */
abstract class SwagLabsTest extends BaseWebTest {

    static final String BACKPACK = "Sauce Labs Backpack";
    static final String BIKE_LIGHT = "Sauce Labs Bike Light";

    // These tests exercise the real shop; the offline fixture has no shop to test.
    @BeforeEach
    void liveShopOnly() {
        Assumptions.assumeTrue(WebConfig.TARGET_EXTERNAL.equals(webConfig.target()),
                "needs the live shop (target=external)");
    }

    LoginPage openLoginPage() {
        return new LoginPage(driver, timeout).open(resolveSmokeUrl("fixtures/swag-labs.html"));
    }

    InventoryPage loginAsStandardUser() {
        return openLoginPage().loginAs(TestUsers.STANDARD, TestUsers.PASSWORD);
    }
}
