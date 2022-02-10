package authentication;

import common.BaseClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Authenticating {
    private WebDriver driver;

    @BeforeEach
    void loadDriver() {
        driver = BaseClass.of(new ChromeOptions());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void noAuthentication() {
        driver.get("https://the-internet.herokuapp.com");
        driver.findElement(By.linkText("Digest Authentication")).click();
        BaseClass.sleepTight(2000);
        String body = driver.findElement(By.tagName("body")).getText();
        Assertions.assertTrue(body.contains("Congratulations!"));
    }

    @Test
    void authenticate() {
        ((HasAuthentication) driver).register(() -> new UsernameAndPassword("admin", "admin"));

        driver.get("https://the-internet.herokuapp.com");
        driver.findElement(By.linkText("Digest Authentication")).click();
        BaseClass.sleepTight(2000);
        String body = driver.findElement(By.tagName("body")).getText();
        Assertions.assertTrue(body.contains("Congratulations!"));
    }
}
