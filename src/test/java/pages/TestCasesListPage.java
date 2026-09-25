package pages;

import maps.TestCasesListMap;
import org.openqa.selenium.WebDriver;
import utilities.commonMethods.CommonMethods;

public class TestCasesListPage extends CommonMethods {

    private final TestCasesListMap testCasesListMap;

    public TestCasesListPage(WebDriver driver) {
        super(driver);
        testCasesListMap = new TestCasesListMap();
    }

    //VALIDATIONS
    public boolean isTestCasesTitleDisplayed() {
        return isDisplayed(testCasesListMap.lblTestCasestitle);
    }

    public String getTestCasesTitle() {
        return getText(testCasesListMap.lblTestCasestitle);
    }
}
