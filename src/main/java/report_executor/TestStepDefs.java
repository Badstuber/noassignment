package report_executor;

import dictionaries.LiveBetDictionary;
import helpers.SeleniumHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import page_objects.LiveProgramePage;

public class TestStepDefs {
    private static final Logger log = LogManager.getLogger(TestStepDefs.class);
    LiveBetDictionary liveBetDictionary = LiveBetDictionary.getInstance();
    private SeleniumHelper seleniumHelper = LiveBetDictionary.seleniumHelper;
    private WebDriver browser;
    public TestStepDefs() {
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
