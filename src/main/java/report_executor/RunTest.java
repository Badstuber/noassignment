package report_executor;

import dictionaries.LiveBetDictionary;
import page_objects.LiveProgramePage;

public class RunTest {
LiveBetDictionary liveBetDictionary = LiveBetDictionary.getInstance();


public static void main(String[] args) {
        LiveProgramePage liveProgramePage = new LiveProgramePage(LiveBetDictionary.getInstance().getBrowser());
        liveProgramePage.prepareForTest();
        liveProgramePage.checkProgrammeAndBetPageAreAvailable();

    }
}
