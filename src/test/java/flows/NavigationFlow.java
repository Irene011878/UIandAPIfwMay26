package flows;


import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.TestCasesListPage;
import io.qameta.allure.Allure;

/*public class NavigationFlow {

    private final HomePage homePage;
    private final TestCasesListPage testCasesListPage;


    public NavigationFlow(WebDriver driver){
        homePage = new HomePage(driver);
        testCasesListPage = new TestCasesListPage(driver);

        System.out.println("URL = " + driver.getCurrentUrl());
        System.out.println("TITLE = " + driver.getTitle());
        System.out.println("Home displayed: " + homePage.isHomeDisplayed());

    }

    // VERIFY TEST CASES PAGE

    public void openTestCasesPage() {
        homePage.clickTestCases();
        homePage.handleGoogleVignette();

        if (!testCasesListPage.isTestCasesTitleDisplayed()) {

            homePage.clickTestCases();

        }
    }


    // VERIFY SCROLL UP USING ARROW BUTTON
    // VERIFY SCROLL UP WITHOUT ARROW BUTTON

    public void scrollDown(){
        homePage.scrollToBottom();
    }


    public void scrollUpUsingArrow(){
        homePage.clickScrollUpArrow();
    }


    public void scrollUpManually(){
        homePage.scrollToTop();
    }

}*/
public class NavigationFlow {

    private final HomePage homePage;
    private final TestCasesListPage testCasesListPage;

    public NavigationFlow(WebDriver driver) {

        homePage = new HomePage(driver);
        testCasesListPage = new TestCasesListPage(driver);

        System.out.println("URL = " + driver.getCurrentUrl());
        System.out.println("TITLE = " + driver.getTitle());
        System.out.println("Home displayed: " + homePage.isHomeDisplayed());
    }

    // ============================================================
    // VERIFY TEST CASES PAGE
    // ============================================================

    public void openTestCasesPage() {

        Allure.step(
                "Open Test Cases page",
                () -> {

                    homePage.clickTestCases();
                    homePage.handleGoogleVignette();

                    if (!testCasesListPage.isTestCasesTitleDisplayed()) {

                        homePage.clickTestCases();

                    }
                }
        );
    }

    // ============================================================
    // VERIFY SCROLL UP USING ARROW BUTTON
    // VERIFY SCROLL UP WITHOUT ARROW BUTTON
    // ============================================================

    public void scrollDown() {

        Allure.step(
                "Scroll down to bottom of page",
                () -> homePage.scrollToBottom()
        );
    }

    public void scrollUpUsingArrow() {

        Allure.step(
                "Scroll up using arrow button",
                () -> homePage.clickScrollUpArrow()
        );
    }

    public void scrollUpManually() {

        Allure.step(
                "Scroll up manually",
                () -> homePage.scrollToTop()
        );
    }
}