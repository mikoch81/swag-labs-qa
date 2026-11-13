package com.example.swaglabsqa.web.test;

import java.time.Duration;
import java.util.Properties;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import com.example.swaglabsqa.core.config.ConfigLoader;
import com.example.swaglabsqa.core.util.ResourcePath;
import com.example.swaglabsqa.web.config.SmokeExpectations;
import com.example.swaglabsqa.web.config.WebConfig;
import com.example.swaglabsqa.web.driver.WebDriverFactory;
import com.example.swaglabsqa.web.extension.ScreenshotOnFailureExtension;

/**
 * Boots a {@link WebDriver} for each test using values from
 * {@code config.properties} on the test classpath.
 *
 * <p>Browser selection order (highest wins):
 * <ol>
 *   <li>System property {@code -Dbrowser=firefox}</li>
 *   <li>System property {@code -Dbrowser.name=firefox} (what the generated CI pipelines pass)</li>
 *   <li>Environment variable {@code QAFF_BROWSER=firefox}</li>
 *   <li>{@code browser=chrome} in {@code config.properties}</li>
 *   <li>Default: {@code chrome}</li>
 * </ol>
 * Supported values: {@code chrome}, {@code chromium} (same as chrome),
 * {@code firefox}, {@code edge}.
 *
 * <p>Headless: {@code -Dbrowser.headless=true|false} overrides
 * {@code headless} in {@code config.properties} (default {@code true}).
 *
 * <p>Failed tests automatically capture a screenshot via
 * {@link ScreenshotOnFailureExtension} — saved to
 * {@code target/screenshots/<ClassName>/<method>-<nano>.png}.
 */
@ExtendWith(ScreenshotOnFailureExtension.class)
public abstract class BaseWebTest {

    protected WebDriver driver;
    protected Properties config;
    protected Duration timeout;
    protected WebConfig webConfig;
    protected SmokeExpectations smoke;

    @BeforeEach
    void startBrowser() {
        config = ConfigLoader.fromClasspath("config.properties");
        // -Dbrowser.headless (passed by the generated CI pipelines) overrides config.properties.
        boolean headless = Boolean.parseBoolean(
                System.getProperty("browser.headless", config.getProperty("headless", "true")));
        long timeoutSeconds = Long.parseLong(config.getProperty("timeoutSeconds", "10"));
        timeout = Duration.ofSeconds(timeoutSeconds);
        webConfig = WebConfig.from(config);
        smoke = SmokeExpectations.from(config);
        String browser = resolveBrowser(config);
        driver = WebDriverFactory.newDriver(browser, headless);
    }

    private static String resolveBrowser(Properties config) {
        String sysprop = System.getProperty("browser");
        if (sysprop != null && !sysprop.isBlank()) return sysprop.trim();
        String ciProp = System.getProperty("browser.name");
        if (ciProp != null && !ciProp.isBlank()) return ciProp.trim();
        String env = System.getenv("QAFF_BROWSER");
        if (env != null && !env.isBlank()) return env.trim();
        return config.getProperty("browser", "chrome");
    }

      protected String resolveSmokeUrl(String fixtureClasspathName) {
        if (WebConfig.TARGET_FIXTURE.equals(webConfig.target())) {
          return ResourcePath.classpathFileUrl(fixtureClasspathName);
        }

        String baseUrl = webConfig.baseUrl();
        if (baseUrl == null
            || baseUrl.isBlank()
            || WebConfig.LOCAL_FIXTURE_SENTINEL.equalsIgnoreCase(baseUrl.trim())) {
          throw new IllegalStateException(
              "External web target selected but baseUrl is not configured. "
                  + "Set -Dweb.baseUrl, QAFF_WEB_BASE_URL, or baseUrl in config.properties.");
        }

        return baseUrl.trim();
      }

    @AfterEach
    void stopBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
