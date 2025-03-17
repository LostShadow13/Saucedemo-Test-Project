package config;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class TestBase {
    protected WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        LoggerClass.logInfo("Given the user is on the Saucedemo login page");
    }

    @AfterEach
    public void teardown() {
        DriverFactory.quitDriver();
        LoggerClass.logInfo("Browser session ended");
    }
}
