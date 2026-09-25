package flows;

import io.qameta.allure.Step;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import pages.CheckoutPage;

import java.util.Map;

/**
 * Business Flow for Checkout module.
 *
 * This class contains reusable business processes related to:
 * - Checkout
 * - Payment
 * - Invoice Download
 * - Order completion
 *
 * NOTE:
 * This class MUST NOT contain assertions.
 * Assertions belong in the test classes.
 */
/*public class CheckoutFlow {

    private final CheckoutPage checkoutPage;

    public CheckoutFlow(WebDriver driver) {
        checkoutPage = new CheckoutPage(driver);
    }

    // ============================================================
    // CHECKOUT
    // ============================================================

    @Step("Enter order comment")
    public void enterComment(String comment) {
        checkoutPage.enterComment(comment);
    }

    @Step("Click Place Order")
    public void clickPlaceOrder() {
        checkoutPage.clickPlaceOrder();
    }

    // ============================================================
    // ORDER VALIDATIONS
    // ============================================================

    @Step("Verify Review Your Order section is displayed")
    public boolean isOrderReviewDisplayed() {
        return checkoutPage.isOrderReviewDisplayed();
    }

    // ============================================================
    // ADDRESS VALIDATIONS
    // ============================================================

    @Step("Verify Address Details section is displayed")
    public boolean isAddressDetailsDisplayed() {
        return checkoutPage.isAddressDetailsDisplayed();
    }

    // ============================================================
    // DELIVERY ADDRESS
    // ============================================================

    @Step("Verify Delivery Address section is displayed")
    public boolean isDeliveryAddressDisplayed() {
        return checkoutPage.isDeliveryAddressDisplayed();
    }

    @Step("Get Delivery Name")
    public String getDeliveryName() {
        return checkoutPage.getDeliveryName();
    }

    @Step("Get Delivery Company")
    public String getDeliveryCompany() {
        return checkoutPage.getDeliveryCompany();
    }

    @Step("Get Delivery Address")
    public String getDeliveryAddress() {
        return checkoutPage.getDeliveryAddress();
    }

    @Step("Get Delivery Address 2")
    public String getDeliveryAddress2() {
        return checkoutPage.getDeliveryAddress2();
    }

    @Step("Get Delivery City, State and Zip Code")
    public String getDeliveryCityStateZip() {
        return checkoutPage.getDeliveryCityStateZip();
    }

    @Step("Get Delivery Country")
    public String getDeliveryCountry() {
        return checkoutPage.getDeliveryCountry();
    }

    @Step("Get Delivery Phone")
    public String getDeliveryPhone() {
        return checkoutPage.getDeliveryPhone();
    }

    // ============================================================
    // BILLING ADDRESS
    // ============================================================

    @Step("Verify Billing Address section is displayed")
    public boolean isBillingAddressDisplayed() {
        return checkoutPage.isBillingAddressDisplayed();
    }

    @Step("Get Billing Name")
    public String getBillingName() {
        return checkoutPage.getBillingName();
    }

    @Step("Get Billing Company")
    public String getBillingCompany() {
        return checkoutPage.getBillingCompany();
    }

    @Step("Get Billing Address")
    public String getBillingAddress() {
        return checkoutPage.getBillingAddress();
    }

    @Step("Get Billing Address 2")
    public String getBillingAddress2() {
        return checkoutPage.getBillingAddress2();
    }

    @Step("Get Billing City, State and Zip Code")
    public String getBillingCityStateZip() {
        return checkoutPage.getBillingCityStateZip();
    }

    @Step("Get Billing Country")
    public String getBillingCountry() {
        return checkoutPage.getBillingCountry();
    }

    @Step("Get Billing Phone")
    public String getBillingPhone() {
        return checkoutPage.getBillingPhone();
    }

    // ============================================================
    // PAYMENT
    // ============================================================

    @Step("Complete payment")
    public void completePayment(Map<String, String> data) {
        checkoutPage.completePayment(
                data.get("cardName"),
                data.get("cardNumber"),
                data.get("cvc"),
                data.get("month"),
                data.get("year")
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

    @Step("Download invoice")
    public boolean downloadInvoice(String fileName, int timeoutSeconds) {
        return checkoutPage.downloadInvoice(
                fileName,
                timeoutSeconds
        );
    }

    // ============================================================
    // NAVIGATION
    // ============================================================

    @Step("Continue after order completion")
    public void continueAfterOrder() {
        checkoutPage.clickContinue();
    }
}*/
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