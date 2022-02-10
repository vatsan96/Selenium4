package decorators;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.support.events.WebDriverListener;

public class HighlightingListener implements WebDriverListener {

    @Override
    public void beforeClick(WebElement element) {
        highlight(element);
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        highlight(element);
    }

    private void highlight(WebElement element) {
        if (!(element instanceof WrapsDriver)) {
            return;
        }
        if (!element.isDisplayed()) {
            return;
        }
        String originalStyle = element.getDomAttribute("style");
        WebDriver driver = ((WrapsDriver) element).getWrappedDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String[] phases = new String[]{
                "rgba(255,0,0,1)",
                "rgba(128,128,0,1)",
                "rgba(0,255,0,1)",
                "rgba(0,128,128,1)",
                "rgba(0,0,255,1)",
        };

        for (int i = 0; i < 5; i++) {
            for (String phase : phases) {
                String property = "0px 0px 6px 6px " + phase;
                js.executeScript("arguments[0].style.boxShadow = arguments[1]", element, property);
                sleepTight(32);
            }
        }

        js.executeScript(
                "return arguments[0].style = arguments[1]",
                element,
                originalStyle == null ? "" : originalStyle);
    }

    private void sleepTight(long i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
