package com.example.swaglabsqa.apps.swaglabs.webdesktop.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.example.swaglabsqa.web.extension.RegressionTest;
import com.example.swaglabsqa.web.extension.SmokeTest;
import com.example.swaglabsqa.apps.swaglabs.webdesktop.data.TestUsers;
import com.example.swaglabsqa.apps.swaglabs.webdesktop.pages.InventoryPage;

@Epic("Swag Labs")
@Feature("Login")
class LoginTest extends SwagLabsTest {

    @SmokeTest
    @Story("Sign in")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("A standard user signs in and lands on the product list")
    void standardUserSignsIn() {
        InventoryPage inventory = loginAsStandardUser();

        assertEquals("Products", inventory.title());
        assertTrue(driver.getCurrentUrl().endsWith("/inventory.html"),
                "signed-in user should land on the inventory page");
    }

    @RegressionTest
    @Story("Access control")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("A locked-out user is refused with a clear message")
    void lockedOutUserIsRefused() {
        String error = openLoginPage()
                .submit(TestUsers.LOCKED_OUT, TestUsers.PASSWORD)
                .errorMessage();

        assertEquals("Epic sadface: Sorry, this user has been locked out.", error);
    }

    @ParameterizedTest(name = "{2}")
    @CsvSource({
        "'', secret_sauce, Username is required",
        "standard_user, '', Password is required"
    })
    @Tag("regression")
    @Story("Form validation")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("The login form names the missing field")
    void missingCredentialIsNamed(String username, String password, String expected) {
        String error = openLoginPage().submit(username, password).errorMessage();

        assertEquals("Epic sadface: " + expected, error);
    }
}
