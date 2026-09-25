package flows;

import io.qameta.allure.Step;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import pages.SubscriptionPage;

/**
 * Business Flow for Subscription module.
 *
 * This class contains reusable business processes related to:
 *
 * - Subscription
 * - Subscription validation
 * - Footer subscription
 *
 * NOTE:
 * This class MUST NOT contain assertions.
 * Assertions belong in the test classes.
 */

/*public class SubscriptionFlow {

    private final SubscriptionPage subscriptionPage;


    public SubscriptionFlow(WebDriver driver) {
        subscriptionPage = new SubscriptionPage(driver);
    }

    // ============================================================
    // SUBSCRIPTION
    // ============================================================

    @Step("Enter subscription email and subscribe")
    public void subscribe(String email) {
        subscriptionPage.subscribe(email);
    }

    // ============================================================
    // SUBSCRIPTION VALIDATIONS
    // ============================================================

    @Step("Verify subscription success message is displayed")
    public boolean isSubscriptionSuccessDisplayed() {
        return subscriptionPage.isSubscriptionSuccessDisplayed();
    }

    // ============================================================
    // SUBSCRIPTION este estaba borrado desde antes de Allure
    // ============================================================

    /*@Step("Scroll down to Subscription section")
    public void scrollToSubscription() {
        subscriptionPage.scrollToSubscription();
    }

    @Step("Verify Subscription section is displayed")
    public boolean isSubscriptionDisplayed() {
        return subscriptionPage.isSubscriptionDisplayed();
    }

    @Step("Subscribe with email")
    public void subscribe(String email) {
        subscriptionPage.subscribe(email);
    }

    @Step("Verify subscription success message is displayed")
    public boolean isSubscriptionSuccessDisplayed() {
        return subscriptionPage.isSubscriptionSuccessDisplayed();
    }*/

public class SubscriptionFlow {

    private final SubscriptionPage subscriptionPage;

    public SubscriptionFlow(WebDriver driver) {
        subscriptionPage = new SubscriptionPage(driver);
    }

    // ============================================================
    // SUBSCRIPTION
    // ============================================================

    public void subscribe(String email) {

        Allure.step(
                "Enter subscription email and subscribe",
                () -> subscriptionPage.subscribe(email)
        );
    }

    // ============================================================
    // SUBSCRIPTION VALIDATIONS
    // ============================================================

    public boolean isSubscriptionSuccessDisplayed() {
        return subscriptionPage.isSubscriptionSuccessDisplayed();
    }
}