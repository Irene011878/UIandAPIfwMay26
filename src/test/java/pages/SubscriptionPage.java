package pages;

import maps.SubscriptionMap;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import utilities.commonMethods.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class SubscriptionPage extends CommonMethods {

    private final SubscriptionMap subscriptionMap;

    public SubscriptionPage(WebDriver driver) {
        super(driver);
        subscriptionMap = new SubscriptionMap();
    }

    //VALIDATIONS

    public boolean isSubscriptionSuccessDisplayed() {
        return isDisplayed(subscriptionMap.lblSubscriptionSuccess);
    }

    //SUBSCRIPTION

    public void scrollToSubscription() {

        WebDriver currentDriver = driver;

        ((JavascriptExecutor) currentDriver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        currentDriver.findElement(
                                subscriptionMap.txtSubscriptionEmail
                        )
                );
    }

    public void enterSubscriptionEmail(String email) {
        //scrollToElement(subscriptionMap.txtSubscriptionEmail);
        type(subscriptionMap.txtSubscriptionEmail, email);
    }

    public void clickSubscribe() {
        click(subscriptionMap.btnSubscription);
    }

    public void subscribe(String email) {
        enterSubscriptionEmail(email);
        clickSubscribe();
    }

}
