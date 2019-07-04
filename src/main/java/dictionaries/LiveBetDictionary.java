package dictionaries;

import helpers.SeleniumHelper;
import org.openqa.selenium.WebDriver;

public class LiveBetDictionary {
    private WebDriver browser;
    public static SeleniumHelper seleniumHelper = new SeleniumHelper();

    private static LiveBetDictionary liveBetDictionary = new LiveBetDictionary();
    private LiveBetDictionary(){}

    public static LiveBetDictionary getInstance() {return liveBetDictionary;}

    public WebDriver getBrowser() {
        return browser;
    }

    public void setBrowser(WebDriver browser) {
        this.browser = browser;
    }
}
