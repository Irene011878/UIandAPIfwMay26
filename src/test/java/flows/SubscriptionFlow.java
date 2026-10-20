package flows;

import io.qameta.allure.Step;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import pages.SubscriptionPage;


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