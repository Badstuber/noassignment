package page_objects;

import dictionaries.LiveBetDictionary;
import helpers.SeleniumHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LiveProgramePage {
    private static final Logger log = LogManager.getLogger(LiveProgramePage.class);
    private WebDriver webDriver;
    private SeleniumHelper seleniumHelper = LiveBetDictionary.seleniumHelper;
    LiveBetDictionary liveBetDictionary = LiveBetDictionary.getInstance();
    @FindBy(className = "button")
    private WebElement acceptCookiesButton;


    public LiveProgramePage (WebDriver webDriver){
        log.info("Initializing LiveProgramePage object");
        this.webDriver=webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public LiveProgramePage navigateToUrl(String url){
        log.info("Navigating to: " + url);
        webDriver.navigate().to(url);
        return this;
    }

    public LiveProgramePage acceptCookies(){
        log.info("Checking if cookie message is present");
        seleniumHelper.waitUntilVisible(acceptCookiesButton).click();
        return this;
    }

    public void prepareForTest(){
        liveBetDictionary.setBrowser(seleniumHelper.openBrowser());
        seleniumHelper.prepareBrowser(liveBetDictionary.getBrowser());
    }

    public void checkProgrammeAndBetPageAreAvailable(){
        LiveProgramePage liveProgramePage = new LiveProgramePage(liveBetDictionary.getBrowser());
        liveProgramePage.navigateToUrl("https://www.novibet.gr/live-schedule").acceptCookies();
        liveProgramePage.navigateToUrl("https://www.novibet.gr/live-betting").acceptCookies();

    }
}
