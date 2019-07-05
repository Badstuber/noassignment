package page_objects;

import dictionaries.Event;
import dictionaries.LiveBetDictionary;
import helpers.SeleniumHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

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

    public List<WebElement> getAllEvents(){
        return liveBetDictionary.getBrowser().findElements(By.xpath("//*[@id=\"left\"]/div/div[1]/div[2]/div/div/div/div[*]"));
    }

    public void populateEventsWithDetails(List<WebElement> elements){
        for (int i = 0; i < elements.size(); i++) {
            Event event = new Event();
            event.setStartTime(elements.get(i).findElement(By.xpath("div[1]/div/div[1]/span")).getText());
            event.setHome(elements.get(i).findElement(By.xpath("div[1]/div/div[3]/div[1]")).getText());
            event.setAway(elements.get(i).findElement(By.xpath("div[1]/div/div[3]/div[3]")).getText());
            liveBetDictionary.addEventToEventList(event);
        }
    }
}

