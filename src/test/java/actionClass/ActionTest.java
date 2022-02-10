package actionClass;

import common.BaseClass;
import decorators.HighlightingListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringDecorator;

public class ActionTest {
    WebDriver driver;

    @BeforeAll
    public static void setUpWebDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUpDriverInstance() {
        WebDriver baseDriver = new ChromeDriver();
        driver = new EventFiringDecorator(new HighlightingListener()).decorate(baseDriver);
    }

    @Test
    public void actionClickTest() {
        driver.get("http://www.facebook.com/");
        WebElement txtUsername = driver.findElement(By.id("email"));
        txtUsername.sendKeys("Hello");
        Actions action = new Actions(driver);
        action.click(txtUsername); //has replaced moveToElement(onElement).click() {}
        action.keyDown(txtUsername, Keys.SHIFT);
        action.sendKeys(txtUsername, "hello");
        action.keyUp(txtUsername, Keys.SHIFT);
        action.doubleClick(txtUsername); //has replaced moveToElement(element).doubleClick()
        action.contextClick(txtUsername); //has replaced moveToElement(onElement).contextClick()
        action.build().perform();
        BaseClass.sleepTight(2000);
    }

    @AfterEach
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
