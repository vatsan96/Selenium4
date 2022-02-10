package seleniumGrid;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class GridTest {

    WebDriver driver;

    @Parameters({"browser"})
    @BeforeTest
    public void setUpDriverInstance(String browser) throws MalformedURLException {
        switch (browser) {
            case "firefox":
                driver = RemoteWebDriver.builder().oneOf(new FirefoxOptions()).address("http://localhost:4444/").build();
                break;
            case "edge":
                driver = RemoteWebDriver.builder().oneOf(new EdgeOptions()).address("http://localhost:4444/").build();
                break;
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                driver = new RemoteWebDriver(new URL("http://localhost:4444/"), options);
                break;
            default:
                break;
        }
    }

    @Test
    public void firstTest() {
        driver.get("https://www.google.com");
    }

    @Test
    public void secondTest() {
        driver.get("https://www.amazon.com");
    }
}
