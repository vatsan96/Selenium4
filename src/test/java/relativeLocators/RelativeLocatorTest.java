package relativeLocators;

import common.BaseClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class RelativeLocatorTest {

    WebDriver driver;


    @BeforeAll
    public static void setUpWebDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUpDriverInstance() {
        driver = new ChromeDriver();
    }

    @Test
    public void RelativeLocTest() {
        driver.get("https://automationbookstore.dev/");
        WebElement firstBook = driver.findElement(By.cssSelector("#pid1_title"));
        WebElement theBookRightOf = driver.findElement(with(By.tagName("h2")).toRightOf(firstBook));
        System.out.println("The text is: " + theBookRightOf.getText());
        WebElement advSelBook = driver.findElement(By.cssSelector("#pid6_title"));
        WebElement theJavaTesterBook = driver.findElement(with(By.tagName("h2")).toLeftOf(advSelBook).below(firstBook));
        System.out.println("The text is: " + theJavaTesterBook.getText());
        BaseClass.sleepTight(2000);
    }

    static void highlightElement(final WebElement element, final WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("var viewPortHeight = Math.max(document.documentElement.clientHeight," + " window.innerHeight || 0);" + "var elementTop = arguments[0].getBoundingClientRect().top;" + "window.scrollBy(0, elementTop-(viewPortHeight/2));",
                element);
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: Green; border: 2px solid yellow;");
    }

    @AfterEach
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    public void wikiTest() {
        driver.get("https://en.wikipedia.org/wiki/Cheese");

        List<WebElement> thumbnails = driver.findElements(By.className("tright"));
        WebElement middle = thumbnails.get(1);
        highlightElement(middle, driver);
        BaseClass.sleepTight(2000);
        Assertions.assertTrue(middle.getText().contains("platter"));

        WebElement above = driver.findElement(RelativeLocator.with(By.className("tright")).above(middle));
        highlightElement(above, driver);
        BaseClass.sleepTight(3000);
    }
}
