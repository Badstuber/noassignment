package helpers;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;

public class SeleniumHelper {
    private static final Logger log = LogManager.getLogger(SeleniumHelper.class);
    static String driverVersion = System.getProperty("chromedriver-version", "2.37");
    public void prepareBrowser (WebDriver webDriver){
        log.info("Deleting cookies");
        webDriver.manage().deleteAllCookies();
    }

    public WebDriver openBrowser (){
        WebDriver browser;
        ChromeDriverManager.getInstance(CHROME).version(driverVersion).setup();
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        browser = openRegularBrowser(capabilities);
        browser.manage().window().setSize(new Dimension(1600,1200));
        return browser;
    }

    private WebDriver openRegularBrowser(DesiredCapabilities capabilities) {
        WebDriver browser = null;
        log.info("Initializing Chrome WebDriver");
        try {
            browser = new ChromeDriver(capabilities);
        } catch (Exception e){
            log.info("Error loading WebDriver");
            e.printStackTrace();
        }
        log.info("Chrome has started");
        return browser;

    }

    public WebElement waitUntilVisible(WebElement webElement){
        Wait<WebElement> wait = (new FluentWait<WebElement>(webElement).withTimeout(30L, TimeUnit.SECONDS)).
                pollingEvery(1L, TimeUnit.SECONDS).
                ignoring(NoSuchElementException.class).
                ignoring(StaleElementReferenceException.class).
                ignoring(ElementNotVisibleException.class);
        return (WebElement)wait.until(new Function<WebElement, WebElement>() {
            public WebElement apply(WebElement element){
                log.info("Waiting for element to be present: " + element);
                return element.isDisplayed() ? element : null;
            }
        });
    }

}
