package pages;

import maps.CheckoutMap;
import org.openqa.selenium.WebDriver;
import utilities.commonMethods.CommonMethods;

public class CheckoutPage extends CommonMethods {

    private final CheckoutMap checkoutMap;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        checkoutMap = new CheckoutMap();
    }

    //ADDRESS VALIDATIONS
    public boolean isAddressDetailsDisplayed() {
        return isDisplayed(checkoutMap.addressDetailsContainer);
    }


    // DELIVERY ADDRESS
    public boolean isDeliveryAddressDisplayed() {
        return isDisplayed(checkoutMap.deliveryAddressSection);
    }

    public String getDeliveryName() {
        return getText(checkoutMap.deliveryName);
    }

    public String getDeliveryCompany() {
        return getText(checkoutMap.deliveryCompany);
    }

    public String getDeliveryAddress() {
        return getText(checkoutMap.deliveryAddress);
    }

    public String getDeliveryAddress2() {
        return getText(checkoutMap.deliveryAddress2);
    }

    public String getDeliveryCityStateZip() {
        return getText(checkoutMap.deliveryCityStateZip);
    }

    public String getDeliveryCountry() {
        return getText(checkoutMap.deliveryCountry);
    }

    public String getDeliveryPhone() {
        return getText(checkoutMap.deliveryPhone);
    }

    //BILLING ADDRESS
    public boolean isBillingAddressDisplayed() {
        return isDisplayed(checkoutMap.billingAddressSection);
    }

    public String getBillingName() {
        return getText(checkoutMap.billingName);
    }

    public String getBillingCompany() {
        return getText(checkoutMap.billingCompany);
    }

    public String getBillingAddress() {
        return getText(checkoutMap.billingAddress);
    }

    public String getBillingAddress2() {
        return getText(checkoutMap.billingAddress2);
    }

    public String getBillingCityStateZip() {
        return getText(checkoutMap.billingCityStateZip);
    }

    public String getBillingCountry() {
        return getText(checkoutMap.billingCountry);
    }

    public String getBillingPhone() {
        return getText(checkoutMap.billingPhone);
    }

    //COMMENTS
    public void enterComment(String comment) {
        type(checkoutMap.commentArea, comment);
    }

    // PLACE ORDER
    public void clickPlaceOrder() {
        scrollToElement(checkoutMap.btnPlaceOrder);
        click(checkoutMap.btnPlaceOrder);
        closeGoogleVignetteAndStayOnPage();
    }

    public boolean isOrderReviewDisplayed() {
        return isDisplayed(checkoutMap.orderReviewSection);
    }


    public void enterNameOnCard(String name) {
        type(checkoutMap.nameOnCard, name);
    }


    //PAYMENT INFORMATION
    public void enterCardNumber(String cardNumber) {
        type(checkoutMap.cardNumber, cardNumber);
    }

    public void enterCvc(String cvc) {
        type(checkoutMap.cardCvc, cvc);
    }

    public void enterExpirationMonth(String month) {
        type(checkoutMap.cardMonthExpiration, month);
    }

    public void enterExpirationYear(String year) {
        type(checkoutMap.cardYearExpiration, year);
    }

    public void clickPayAndConfirmOrder() {
        click(checkoutMap.btnPayAndConfirmOrder);
    }

    //COMPLETE PAYMENT
    public void completePayment(
            String name,
            String cardNumber,
            String cvc,
            String month,
            String year) {

        enterNameOnCard(name);
        enterCardNumber(cardNumber);
        enterCvc(cvc);
        enterExpirationMonth(month);
        enterExpirationYear(year);
        clickPayAndConfirmOrder();
    }

    //ORDER VALIDATIONS
    public boolean isOrderPlacedSuccessfully() {
        return isDisplayed(checkoutMap.orderPlacedSuccessMessage);
    }

    //DOWNLOAD INVOICE
    public void clickDownloadInvoice() {

        closeGoogleVignetteAndStayOnPage();
        scrollToElement(checkoutMap.btnDownloadInvoice);
        jsClick(checkoutMap.btnDownloadInvoice);
        closeGoogleVignetteAndStayOnPage();
    }

    //CONTINUE
    public void clickContinue() {
        click(checkoutMap.btnContinue);
    }

    //DOWNLOAD INVOICE
    public boolean downloadInvoice(String fileName, int timeoutSeconds) {

        deleteFile(fileName);
        clickDownloadInvoice();
        return waitForFileDownload(fileName, timeoutSeconds);
    }

}
