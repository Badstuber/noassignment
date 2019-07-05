package report_executor;
import helpers.ConfigHelper;


public class RunTest {

public static void main(String[] args) throws InterruptedException {
    ConfigHelper configHelper = new ConfigHelper();
    TestStepDefs testStepDefs = new TestStepDefs();
    testStepDefs.prepareForTest();
    testStepDefs.checkProgrammeAndBetPageAreAvailable();
    testStepDefs.getLiveProgrammeStartTimes();
    testStepDefs.tearDownTest();
    }
}
