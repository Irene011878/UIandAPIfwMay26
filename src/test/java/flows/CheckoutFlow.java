package flows;

import io.qameta.allure.Step;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import pages.CheckoutPage;

import java.util.Map;

public class CheckoutFlow {

    private final CheckoutPage checkoutPage;

    public CheckoutFlow(WebDriver driver) {
        checkoutPage = new CheckoutPage(driver);
    }

    // ============================================================
    // CHECKOUT
    // ============================================================

    public void enterComment(String comment) {

        Allure.step(
                "Enter order comment",
                () -> checkoutPage.enterComment(comment)
        );
    }

    public void clickPlaceOrder() {

        Allure.step(
                "Click Place Order",
                () -> checkoutPage.clickPlaceOrder()
        );
    }

    // ============================================================
    // ORDER VALIDATIONS
    // ============================================================

    public boolean isOrderReviewDisplayed() {
        return checkoutPage.isOrderReviewDisplayed();
    }

    // ============================================================
    // ADDRESS VALIDATIONS
    // ============================================================

    public boolean isAddressDetailsDisplayed() {
        return checkoutPage.isAddressDetailsDisplayed();
    }

    // ============================================================
    // DELIVERY ADDRESS
    // ============================================================

    public boolean isDeliveryAddressDisplayed() {
        return checkoutPage.isDeliveryAddressDisplayed();
    }

    public String getDeliveryName() {
        return checkoutPage.getDeliveryName();
    }

    public String getDeliveryCompany() {
        return checkoutPage.getDeliveryCompany();
    }

    public String getDeliveryAddress() {
        return checkoutPage.getDeliveryAddress();
    }

    public String getDeliveryAddress2() {
        return checkoutPage.getDeliveryAddress2();
    }

    public String getDeliveryCityStateZip() {
        return checkoutPage.getDeliveryCityStateZip();
    }

    public String getDeliveryCountry() {
        return checkoutPage.getDeliveryCountry();
    }

    public String getDeliveryPhone() {
        return checkoutPage.getDeliveryPhone();
    }

    // ============================================================
    // BILLING ADDRESS
    // ============================================================

    public boolean isBillingAddressDisplayed() {
        return checkoutPage.isBillingAddressDisplayed();
    }

    public String getBillingName() {
        return checkoutPage.getBillingName();
    }

    public String getBillingCompany() {
        return checkoutPage.getBillingCompany();
    }

    public String getBillingAddress() {
        return checkoutPage.getBillingAddress();
    }

    public String getBillingAddress2() {
        return checkoutPage.getBillingAddress2();
    }

    public String getBillingCityStateZip() {
        return checkoutPage.getBillingCityStateZip();
    }

    public String getBillingCountry() {
        return checkoutPage.getBillingCountry();
    }

    public String getBillingPhone() {
        return checkoutPage.getBillingPhone();
    }

    // ============================================================
    // PAYMENT
    // ============================================================

    public void completePayment(Map<String, String> data) {

        Allure.step(
                "Complete payment",
                () -> checkoutPage.completePayment(
                        data.get("cardName"),
                        data.get("cardNumber"),
                        data.get("cvc"),
                        data.get("month"),
                        data.get("year")
                )
        );
    }

    // ============================================================
    // ORDER VALIDATION
    // ============================================================

    public boolean isOrderPlacedSuccessfully() {
        return checkoutPage.isOrderPlacedSuccessfully();
    }

    // ============================================================
    // INVOICE
    // ============================================================

    public boolean downloadInvoice(String fileName, int timeoutSeconds) {

        final boolean[] downloaded = {false};

        Allure.step(
                "Download invoice: " + fileName,
                () -> downloaded[0] = checkoutPage.downloadInvoice(
                        fileName,
                        timeoutSeconds
                )
        );

        return downloaded[0];
    }

    // ============================================================
    // NAVIGATION
    // ============================================================

    public void continueAfterOrder() {

        Allure.step(
                "Continue after order completion",
                () -> checkoutPage.clickContinue()
        );
    }
}