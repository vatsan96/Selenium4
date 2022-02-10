package shadowDOM;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;

import java.util.List;

public class ShadowDOMTest {

    WebDriver driver;
    WebElement shadowHost = driver.findElement(By.cssSelector("#app"));

    @BeforeAll
    public static void setUpWebDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUpDriverInstance() {
        driver = new ChromeDriver();
    }

//    @Test
//    public void ShadowDomTest() throws InterruptedException {
//        driver.get("https://books-pwakit.appspot.com/");
//        WebElement shadowHost = driver.findElement(By.cssSelector("book-app"));
//        WebElement shadowObject = shadowHost.getShadowRoot().findElement(By.cssSelector("#input"));
//        shadowObject.sendKeys("Hello");
//        Thread.sleep(2000);
//    }

    //    @Test
    public void ShadowTest1() throws InterruptedException {
        Thread.sleep(15000);
        WebElement shadowHost = driver.findElement(By.cssSelector("#nuxeo-app"));
        WebElement shadowObject = shadowHost.getShadowRoot().findElement(By.cssSelector("#container"));
        System.out.println("Text is:" + shadowObject.getText());
        WebElement anotherShadowObj = shadowHost.getShadowRoot().findElement(By.cssSelector("#container > p"));
        System.out.println("Text is:" + anotherShadowObj.getText());

        List<WebElement> sidemenuList = shadowHost.getShadowRoot().findElements(By.cssSelector("nuxeo-app paper-icon-button[id='button']"));

        // click browse button
        sidemenuList.get(2).click();
        Thread.sleep(8000);

        // click on clavin klien
        shadowHost.getShadowRoot().findElement(By.cssSelector("nuxeo-menu-icon[label='Search']")).click();

        // click create btn
        shadowHost.getShadowRoot().findElement(By.cssSelector("nuxeo-app paper-fab[id='createBtn']")).click();
        Thread.sleep(8000);

        // asset type list
        shadowHost.getShadowRoot().findElement(By.cssSelector("nuxeo-app paper-button[name='Graphics']")).click();
        Thread.sleep(5000);
//        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
//        WebElement titleInput = setAssetCreationInput("Title" + new Random());
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        String randomLetters = generator.generate(4);
//        titleInput.sendKeys("Shadow_Asset_" + randomLetters);
//		jse.executeScript("arguments[0].value='Shadow_Asset_" + randomLetters + "';", titleInput);
        // select asset type
        Thread.sleep(5000);
        WebElement element = setAssetCreationSuggestion("Usage Tier");
        element.click();
        Thread.sleep(4000);
        element.getShadowRoot().findElement(By.cssSelector("div[data-item-id='0']")).click();

        System.out.println("******************");

        WebElement uploadChild = shadowHost.getShadowRoot().findElement(By.cssSelector("nuxeo-dropzone[role='widget'] input[type='file']"));
        Thread.sleep(3000);

        // switch to the file upload window
//        final ClassLoader classLoader = getClass().getClassLoader();
//        final File file = new File(classLoader.getResource("sups.jpg").getPath());
//        System.out.println("*******" + classLoader.getResource("sups.jpg").getPath() + "***********");
//        System.out.println("*******" + file.getPath() + "***********");
//        uploadChild.sendKeys(file.getPath());

        // adding asset type
        WebElement assetType = setAssetCreationSuggestion("Asset Type");
//        scrollToElement(assetType);
        assetType.click();
        Thread.sleep(3000);
//        assetType.getShadowRoot().findElement(By.cssSelector(, "div[data-item-id='icons']").click();

        // adding brand name
        WebElement brand = setAssetCreationSuggestion("Brand");
//        scrollToElement(brand);
        brand.click();
        Thread.sleep(5000);
        WebElement brandOption = brand.getShadowRoot().findElement(By.cssSelector("div[data-item-id='ck']"));
//        scrollToElement(brandOption);
        brandOption.click();
        Thread.sleep(1000);

        // click create button
//        WebElement ele = shadow.findElement("nuxeo-app paper-button[id='create']");
//        Assert.assertTrue(shadow.findElement("nuxeo-app paper-button[id='create']").isDisplayed());
//		Assert.assertTrue(shadow.findElement("nuxeo-app paper-button[id='create']").is());
//        ele.click();
//
//        clickOnLastInput(shadow);
//		clickOnLastInput(shadow);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).build().perform();
    }

    public WebElement setAssetCreationSuggestion(String field) {
        // title value
        WebElement element = null;
        List<WebElement> inputList = shadowHost.getShadowRoot().findElements(By.cssSelector("nuxeo-app nuxeo-directory-suggestion[role='widget']"));
        for (int i = 0; i < inputList.size(); i++) {
            if (inputList.get(i).getShadowRoot().findElement(By.cssSelector("nuxeo-app label")).getText().equals(field)) {
                System.out.println(inputList.get(i).getShadowRoot().findElement(By.cssSelector("nuxeo-app label")).getText());
                element = inputList.get(i).getShadowRoot().findElement(By.cssSelector("div#input"));
                break;
            }
        }
        return element;
    }

    @BeforeTest
    public void login() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\srivatsan.seshadri\\Downloads\\chromedriver.exe");
//		System.setProperty("webdriver.gecko.driver", "C:\\Users\\srivatsan.seshadri\\Downloads\\geckodriver.exe");
//		ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
//		driver = new FirefoxDriver();
//		driver.get("https://pvh.preprod.nuxeocloud.com/nuxeo/ui/#!/home");
        driver.get("https://pvh-uat.apps.prod.nuxeo.io/nuxeo/ui/#!/home");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//div[@id='existing']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("CK_Librarian");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("welcome2pvh");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@class='login_button']")).click();
        Thread.sleep(5000);
    }


    public void assetCreation() {
        driver.get("https://radogado.github.io/shadow-dom-demo/");
    }

    @AfterEach
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @org.testng.annotations.Test
    public void searchAsset() throws InterruptedException {
        shadowHost.getShadowRoot().findElement(By.cssSelector("nuxeo-menu-icon[label='Search']")).click();
        WebElement searchInput = shadowHost.getShadowRoot().findElement(By.cssSelector("nuxeo-card[heading='Search All Fields'] iron-input[class='input-element'] input"));
        searchInput.click();
        searchInput.sendKeys("Asset_creation_Shadow_Automation");

        List<WebElement> cardList = shadowHost.getShadowRoot().findElements(By.cssSelector("nuxeo-app nuxeo-data-grid[name='grid'] a[class='title']"));
        System.out.println("****************" + cardList.size() + "****************");
        for (int i = 0; i < cardList.size(); i++) {
            if (cardList.get(i).getAttribute("href").contains("Asset_creation_Shadow_Automation")) {
                System.out.println("****************" + cardList.get(i).getAttribute("href") + "****************");
                shadowHost.getShadowRoot().findElements(By.cssSelector("nuxeo-app nuxeo-data-grid[name='grid'] div[class='thumbnailContainer']")).get(i)
                        .click();
                break;
            }
        }

        // verifying the folder name and the file name
        String fileName = shadowHost.getShadowRoot().findElement(By.cssSelector("nuxeo-app a[class='current breadcrumb-item breadcrumb-item-current']"))
                .getText();
        String brandName = shadowHost.getShadowRoot().findElements(By.cssSelector("nuxeo-app span[class='breadcrumb-item-title']")).get(1).getText();
        System.out.println("************" + fileName + "************" + brandName + "**************");
        Assert.assertTrue(brandName.equals("Calvin Klein"));
        Assert.assertEquals(fileName, "Asset_creation_Shadow_Automation");
        Thread.sleep(5000);

    }
}
