package pages;

import maps.HomeMap;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import utilities.commonMethods.CommonMethods;

public class HomePage extends CommonMethods {

    private final HomeMap homeMap;

    public HomePage(WebDriver driver) {

        super(driver);

        homeMap = new HomeMap();
    }

    //VALIDATIONS
    public boolean isHomeDisplayed() {
        return isDisplayed(homeMap.imgWebSitelogo);
    }

    public boolean isAutomationEngineersMessageDisplayed() {
        scrollToTop();
        return isDisplayed(homeMap.lblAutomationEngineers);
    }

    public String getLoggedUser() {
        return getText(homeMap.lblLoggedUser);
    }

    public boolean isLoggedUserDisplayed() {
        return isDisplayed(homeMap.lblLoggedUser);
    }

    public boolean isSubscriptionDisplayed() {
        return isDisplayed(homeMap.lblSubscription);
    }

    public boolean isCategoryPanelDisplayed() {
        return isDisplayed(homeMap.categoryPanel);
    }

    public boolean isRecommendedItemsDisplayed() {
        scrollBottom();
        return isDisplayed(homeMap.recommendedItemsTitle);
    }

    // SCROLL

    public void scrollToBottom() {
        scrollBottom();
    }

    public void scrollToTop() {
        scrollTop();
    }

    public void clickScrollUpArrow() {

        jsClick(homeMap.btnScrollUp);

        waitVisible(homeMap.lblAutomationEngineers);
    }

    //NAVIGATION
    public void clickHome() {
        click(homeMap.btnHome);
    }

    public void clickProducts() {
        click(homeMap.btnProducts);
    }

    public void clickCart() {
        click(homeMap.btnCart);
    }

    public void clickSignLogin() {
        click(homeMap.btnSignupLogin);
    }

    public void clickContactUs() {
        click(homeMap.btnContactUs);
    }

    public void clickTestCases() {
        click(homeMap.btnTestCases);
    }

    public void clickLogOut() {
        click(homeMap.btnLogout);
    }

    public void clickDeleteAccount() {
        click(homeMap.btnDeleteAccount);
    }

    // PRODUCTS

    public void clickFirstProductView() {
        click(homeMap.firstProductViewButton);
    }


}
