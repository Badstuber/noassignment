package report_executor;


import dictionaries.LiveBetDictionary;
import helpers.ConfigHelper;
import helpers.SeleniumHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebElement;
import page_objects.LiveProgramePage;

import java.util.List;


public class TestStepDefs {
    private static final Logger log = LogManager.getLogger(TestStepDefs.class);
    LiveBetDictionary liveBetDictionary = LiveBetDictionary.getInstance();
    private SeleniumHelper seleniumHelper = LiveBetDictionary.seleniumHelper;
    public static ConfigHelper configHelper = null;
    public TestStepDefs() {
        configHelper = new ConfigHelper();
    }

    public void prepareForTest(){
        liveBetDictionary.setBrowser(seleniumHelper.openBrowser());
        seleniumHelper.prepareBrowser(liveBetDictionary.getBrowser());
    }

    public void tearDownTest() {
        seleniumHelper.closeBrowser(liveBetDictionary.getBrowser());
    }

    public void checkProgrammeAndBetPageAreAvailable() throws InterruptedException {

        LiveProgramePage liveProgramePage = new LiveProgramePage(liveBetDictionary.getBrowser());
        try {
            liveProgramePage.navigateToUrl(LiveBetDictionary.getLiveProgrammePageUrl()).acceptCookies();
            log.info("Navigation to live schedule succeded");
        } catch (Exception e){
            log.error("Navigation to live schedule failed");
            e.printStackTrace();
        }
        Thread.sleep(5000);
        try {
            liveProgramePage.navigateToUrl(LiveBetDictionary.getLiveBetPageUrl());
            log.info("Navigation to live betting succeded");
        } catch (Exception e){
            log.error("Navigation to live betting failed");
            e.printStackTrace();
        }

    }

    public void getLiveProgrammeStartTimes(){
        LiveProgramePage liveProgramePage = new LiveProgramePage(liveBetDictionary.getBrowser());
        try {
            liveProgramePage.navigateToUrl(LiveBetDictionary.getLiveProgrammePageUrl());
            log.info("Navigation to live schedule succeded");
        } catch (Exception e){
            log.error("Navigation to live schedule failed");
            e.printStackTrace();
        }
        List<WebElement> elements = liveProgramePage.getAllEvents();
        liveProgramePage.populateEventsWithDetails(elements);
        log.info(elements.size() + " events populated with Home and Away teams, as well as the minutes to start");

    }
}
