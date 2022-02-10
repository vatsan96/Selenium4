package decoratingWebdriver;

import common.BaseClass;
import decorators.HighlightingListener;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DecoratingWebdriver {
    WebDriverWait wait;
    private WebDriver driver;

    @BeforeEach
    void loadDriver() {
        WebDriver baseDriver = BaseClass.of(new ChromeOptions());
        driver = new EventFiringDecorator(new HighlightingListener()).decorate(baseDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void loadWikipedia() {
        driver.get("http://duckduckgo.com");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("cheese");
        driver.findElement(By.id("search_button_homepage")).click();
        BaseClass.sleepTight(2000);
        wait.until(d -> d.findElement(By.linkText("Cheese - Wikipedia"))).click();
        BaseClass.sleepTight(2000);
    }
}
