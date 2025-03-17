package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverFactory {
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            String browser = System.getProperty("browser", "firefox");
            try {
                switch (browser.toLowerCase()) {
                    case "firefox":
                        driver.set(new FirefoxDriver());
                        break;
                    case "edge":
                        driver.set(new EdgeDriver());
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported browser: " + browser);
                }
                logger.info("Initialized WebDriver for browser: " + browser);
            } catch (Exception e) {
                logger.error("Failed to initialize WebDriver", e);
                throw new RuntimeException(e);
            }
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
            logger.info("WebDriver closed");
        }
    }
}
