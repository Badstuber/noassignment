package dictionaries;

import helpers.ConfigHelper;
import helpers.SeleniumHelper;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.Properties;

public class LiveBetDictionary {
    private static Properties configs = ConfigHelper.configs;
    private WebDriver browser;
    public static SeleniumHelper seleniumHelper = new SeleniumHelper();
    public static ConfigHelper configHelper = null;
    /*I created a singleton class here in order to be able to keep variables and data needed during the execution. The need for a singleton
    class may not be apparent on that stage but it will become obvious if we integrate Cucumber on that project*/
    private static LiveBetDictionary liveBetDictionary = new LiveBetDictionary();
    private LiveBetDictionary(){}
    public static LiveBetDictionary getInstance() {return liveBetDictionary;}

    public static String liveProgrammePageUrl = configs.getProperty("liveprogramepage.url");
    public static String liveBetPageUrl = configs.getProperty("livebetpage.url");

    ArrayList <Event> eventList = new ArrayList<Event>();

    public WebDriver getBrowser() {
        return browser;
    }

    public void setBrowser(WebDriver browser) {
        this.browser = browser;
    }

    public ArrayList<Event> getEventList() {
        return eventList;
    }

    public void addEventToEventList (Event event){this.eventList.add(event);}

    public static String getLiveProgrammePageUrl() {
        return liveProgrammePageUrl;
    }

    public static String getLiveBetPageUrl() {
        return liveBetPageUrl;
    }
}
