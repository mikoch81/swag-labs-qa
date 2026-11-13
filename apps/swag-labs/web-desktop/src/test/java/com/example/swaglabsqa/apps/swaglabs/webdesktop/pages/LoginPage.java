package com.example.swaglabsqa.apps.swaglabs.webdesktop.pages;

import java.time.Duration;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.swaglabsqa.web.page.BasePage;

public class LoginPage extends BasePage {

    private static final By USERNAME = By.id("user-name");
    private static final By PASSWORD = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By ERROR = By.cssSelector("[data-test='error']");

    private final Duration timeout;

    public LoginPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
        this.timeout = timeout;
    }

    @Step("Open the login page")
    public LoginPage open(String url) {
        driver.get(url);
        waitForVisible(LOGIN_BUTTON);
        return this;
    }

    @Step("Log in as '{username}'")
    public InventoryPage loginAs(String username, String password) {
        submit(username, password);
        return new InventoryPage(driver, timeout).waitUntilLoaded();
    }

    @Step("Submit the login form as '{username}'")
    public LoginPage submit(String username, String password) {
        waitAndType(USERNAME, username);
        waitAndType(PASSWORD, password);
        waitAndClick(LOGIN_BUTTON);
        return this;
    }

    public String errorMessage() {
        return getText(ERROR);
    }
}
