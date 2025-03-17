package tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
public class LoginTest extends TestBase {

    @ParameterizedTest
    @MethodSource("utils.DataProviderClass#loginData")
    public void testLogin(String username, String password, String expectedMessage) {
        LoggerClass.logInfo("When the user enters username: " + username + " and password: " + password);
        driver.findElement(By.xpath("//*[@data-test='username']")).sendKeys(username);
        driver.findElement(By.xpath("//*[@data-test='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='login-button']")).click();

        if (expectedMessage.equals("Swag Labs")) {
            String title = driver.findElement(By.xpath("//span[@class='title']")).getText();
            assertThat(title).isEqualTo(expectedMessage);
            LoggerClass.logInfo("Then the user is redirected to the dashboard");
        } else {
            String error = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
            assertThat(error).contains(expectedMessage);
            LoggerClass.logInfo("Then the login fails with error message: " + error);
        }
    }
}
