package browserOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BrowserOptionsTest {

    WebDriver driver;
    String cloudURL = "https://" + System.getenv("SAUCE_USERNAME") + ":" + System.getenv("SAUCE_ACCESS_KEY") + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";

    @BeforeAll
    public static void setUpWebDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUpDriverInstance() throws MalformedURLException {
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("96");
//        browserOptions.setCapability("build", "BuildName_"+Math.random());
//        browserOptions.setCapability("name", "TestCaseName");
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("build", "BuildName");
        sauceOptions.put("name", "TestCaseName");
        browserOptions.setCapability("sauce:options", sauceOptions);
        driver = new RemoteWebDriver(new URL(cloudURL), browserOptions);

//        driver = new ChromeDriver();
    }

    @Test
    public void browserOptionsTest() {
        driver.get("https://www.google.com");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
