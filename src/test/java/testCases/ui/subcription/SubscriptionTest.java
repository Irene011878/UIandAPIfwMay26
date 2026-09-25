package testCases.ui.subcription;

import flows.SubscriptionFlow;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import setUp.BaseTest;
import utilities.dataProvider.TestDataProvider;

import java.util.Map;

@Epic("UI Tests")
@Feature("Subscription")
public class SubscriptionTest extends BaseTest {

    private HomePage homePage;
    private SubscriptionFlow subscriptionFlow;


    @BeforeMethod(alwaysRun = true)
    public void setUpPages() {

        homePage = new HomePage(driver);
        subscriptionFlow = new SubscriptionFlow(driver);

    }


    // ============================================================
    // TC010 - VERIFY SUBSCRIPTION IN HOME PAGE
    // ============================================================

    @Story("TC010 - Verify Subscription in Home Page")
    @Severity(SeverityLevel.NORMAL)
    @Description(
            "Verify that a user can successfully subscribe " +
                    "from the Home Page.")
    @Test(
            description = "TC010 - Verify Subscription in Home Page",
            groups = {"regression"},
            dataProvider = "subscriptionHomeData",
            dataProviderClass = TestDataProvider.class)


    public void TC010_verifySubscriptionInHomePage(
            Map<String, String> data) {

        // PRECONDITION: Verify that Home page is visible successfully.
        Assert.assertTrue(
                homePage.isHomeDisplayed(),
                "Home page should be displayed successfully."
        );

        // STEP 1 - Scroll down to footer
        homePage.scrollToBottom();

        // STEP 2 - Verify text 'SUBSCRIPTION'
        Assert.assertTrue(
                homePage.isSubscriptionDisplayed(),
                "'SUBSCRIPTION' section should be displayed.");


        // STEP 3 - Enter email address and click arrow button
        subscriptionFlow.subscribe(data.get("email"));

        // STEP 4 - Verify success message
        Assert.assertTrue(
                subscriptionFlow.isSubscriptionSuccessDisplayed(),
                "'You have been successfully subscribed!' " +
                        "message should be displayed.");
    }


    // ============================================================
    // TC011 - VERIFY SUBSCRIPTION IN CART PAGE
    // ============================================================

    @Story("TC011 - Verify Subscription in Cart Page")
    @Severity(SeverityLevel.NORMAL)
    @Description(
            "Verify that a user can successfully subscribe " +
                    "from the Cart Page.")
    @Test(
            description = "TC011 - Verify Subscription in Cart Page",
            groups = {"regression"},
            dataProvider = "subscriptionCartData",
            dataProviderClass = TestDataProvider.class)


    public void TC011_verifySubscriptionInCartPage(
            Map<String, String> data) {

        // PRECONDITION: Verify that Home page is visible successfully.
        Assert.assertTrue(
                homePage.isHomeDisplayed(),
                "Home page should be displayed successfully.");


        // STEP 1 - Click 'Cart' button
        homePage.clickCart();

        // STEP 2 - Scroll down to footer
        homePage.scrollToBottom();

        // STEP 3 - Verify text 'SUBSCRIPTION'
        Assert.assertTrue(
                homePage.isSubscriptionDisplayed(),
                "'SUBSCRIPTION' section should be displayed.");

        // STEP 4 - Enter email address and click arrow button
        subscriptionFlow.subscribe(data.get("email"));


        // STEP 5 - Verify success message
        Assert.assertTrue(
                subscriptionFlow.isSubscriptionSuccessDisplayed(),
                "'You have been successfully subscribed!' " +
                        "message should be displayed.");
    }
}
