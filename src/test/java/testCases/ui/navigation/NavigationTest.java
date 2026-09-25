package testCases.ui.navigation;

import flows.NavigationFlow;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.TestCasesListPage;
import setUp.BaseTest;


@Epic("Navigation")
@Feature("Navigation")
public class NavigationTest extends BaseTest {

    private HomePage homePage;
    private TestCasesListPage testCasesListPage;
    private NavigationFlow navigationFlow;


    @BeforeMethod(alwaysRun = true)
    public void initializePages(){

        homePage = new HomePage(driver);
        testCasesListPage = new TestCasesListPage(driver);
        navigationFlow = new NavigationFlow(driver);

    }

    //=========================================================
    // TC007 - VERIFY TEST CASES PAGE
    //=========================================================

    @Story("Verify Test Cases Page")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that user can navigate to Test Cases page successfully")


    @Test(
            description = "TC007 - Verify Test Cases Page",
            groups = {"regression"}
    )

    public void verifyTestCasesPage(){

        //Step 1: Click on Test Cases button
        navigationFlow.openTestCasesPage();

        //Step 2: Verify user is navigated to Test Cases page successfully
        Assert.assertTrue(
                testCasesListPage.isTestCasesTitleDisplayed(),
                "'Test Cases' page title is not displayed");

    }

    //=========================================================
    // TC025 - VERIFY SCROLL UP USING ARROW BUTTON
    //=========================================================

    @Story("Verify Scroll Up Using Arrow Button")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that user can scroll down and return to top using the arrow button")


    @Test(
            description = "TC025 - Verify Scroll Up using Arrow button and Scroll Down functionality",
            groups = {"regression"}
    )

    public void verifyScrollUpUsingArrowButton(){

        //Step 1: Scroll down page to bottom
        navigationFlow.scrollDown();

        //Step 2: Verify Subscription is visible
        Assert.assertTrue(
                homePage.isSubscriptionDisplayed(),
                "'SUBSCRIPTION' section is not displayed");

        //Step 3: Click arrow button to move upward
        navigationFlow.scrollUpUsingArrow();

        //Step 4: Verify page is scrolled up
        Assert.assertTrue(
                homePage.isAutomationEngineersMessageDisplayed(),
                "'Full-Fledged practice website for Automation Engineers' text is not displayed"
        );

    }

    //=========================================================
    // TC026 - VERIFY SCROLL UP WITHOUT ARROW BUTTON
    //=========================================================

    @Story("Verify Scroll Up Without Arrow Button")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that user can manually scroll up and return to top")


    @Test(
            description = "TC026 - Verify Scroll Up without Arrow button and Scroll Down functionality",
            groups = {"regression"}
    )

    public void verifyScrollUpWithoutArrowButton(){

        //Step 1: Scroll down page to bottom
        navigationFlow.scrollDown();

        //Step 2: Verify Subscription is visible
        Assert.assertTrue(
                homePage.isSubscriptionDisplayed(),
                "'SUBSCRIPTION' section is not displayed");

        //Step 3: Scroll up manually
        navigationFlow.scrollUpManually();

        //Step 4: Verify page is scrolled up
        Assert.assertTrue(
                homePage.isAutomationEngineersMessageDisplayed(),
                "'Full-Fledged practice website for Automation Engineers' text is not displayed"
        );

    }

}