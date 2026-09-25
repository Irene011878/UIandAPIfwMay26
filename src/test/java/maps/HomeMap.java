package maps;

import org.openqa.selenium.By;

public class HomeMap {

    // HEADER MENU OK

    public final By btnHome =
            By.xpath("//a[contains(text(),'Home')]");

    public final By btnProducts =
            By.xpath("//a[@href='/products']");

    public final By btnCart =
            By.xpath("//a[contains(text(),'Cart')]");

    public final By btnSignupLogin =
            By.xpath("//a[contains(text(),'Signup / Login')]");

    public final By btnContactUs =
            By.xpath("//a[contains(text(),'Contact us')]");

    public final By btnTestCases =
            By.xpath("//a[contains(text(),'Test Cases')]");


    // ACCOUNT

    public final By lblLoggedUser =
            By.xpath("//a[contains(.,'Logged in as')]/b");

    public final By btnDeleteAccount =
            By.xpath("//a[contains(text(),'Delete Account')]");

    public final By btnLogout =
            By.xpath("//a[contains(text(),'Logout')]");


    // SCROLL

    public final By footer =
            By.id("footer");


    public final By btnScrollUp =
            By.id("scrollUp");


    // SUBSCRIPTION

    public final By lblSubscription =
            By.xpath("//*[contains(text(),'Subscription')]");


    // CATEGORY

    public final By categoryPanel =
            By.xpath("//div[@class='left-sidebar']");

    // RECOMMENDED ITEMS
    public final By recommendedItemsTitle =
            By.xpath("//h2[contains(text(),'recommended items')]");

    // PRODUCTS

    public final By firstProductViewButton =
            By.xpath("(//a[contains(@href,'/product_details/')])[1]");


    // HOME VALIDATION

    public final By lblAutomationEngineers =
            By.xpath("//h2[normalize-space()='Full-Fledged practice website for Automation Engineers']");

    public final By imgWebSitelogo =
            By.xpath("//img[@alt='Website for automation practice']");

    //GOOGLE VIGNETTE
    public final By btnDismissGoogleVignette =
            By.id("dismiss-button");
}
