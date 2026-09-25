package maps;

import org.openqa.selenium.By;

public class SubscriptionMap {

    public final By txtSubscriptionEmail =
            By.id("susbscribe_email");


    public final By btnSubscription =
            By.id("subscribe");

    public final By btnScrollUp =
            By.id("scrollUp");


    public final By lblSubscriptionSuccess =
            By.xpath("//*[contains(text(),'You have been successfully subscribed!')]");
}
