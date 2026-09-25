package maps;

import org.openqa.selenium.By;

public class CheckoutMap {

    public final By addressDetailsContainer =
            By.xpath("//div[@data-qa='checkout-info']");

    // DELIVERY ADDRESS
    public final By deliveryAddressSection =
            By.id("address_delivery");

    public final By deliveryName =
            By.xpath("//ul[@id='address_delivery']//li[@class='address_firstname address_lastname']");

    public final By deliveryCompany =
            By.xpath("(//ul[@id='address_delivery']//li[contains(@class,'address_address1 address_address2')])[1]");

    public final By deliveryAddress =
            By.xpath("(//ul[@id='address_delivery']//li[contains(@class,'address_address1 address_address2')])[2]");

    public final By deliveryAddress2 =
            By.xpath("(//ul[@id='address_delivery']//li[contains(@class,'address_address1 address_address2')])[3]");

    public final By deliveryCityStateZip =
            By.xpath("//ul[@id='address_delivery']//li[contains(@class,'address_city')]");

    public final By deliveryCountry =
            By.xpath("//ul[@id='address_delivery']//li[@class='address_country_name']");

    public final By deliveryPhone =
            By.xpath("//ul[@id='address_delivery']//li[@class='address_phone']");

    //BILLING ADDRESS
    public final By billingAddressSection =
            By.id("address_invoice");

    public final By billingName =
            By.xpath("//ul[@id='address_invoice']//li[@class='address_firstname address_lastname']");

    public final By billingCompany =
            By.xpath("(//ul[@id='address_invoice']//li[contains(@class,'address_address1 address_address2')])[1]");

    public final By billingAddress =
            By.xpath("(//ul[@id='address_invoice']//li[contains(@class,'address_address1 address_address2')])[2]");

    public final By billingAddress2 =
            By.xpath("(//ul[@id='address_invoice']//li[contains(@class,'address_address1 address_address2')])[3]");

    public final By billingCityStateZip =
            By.xpath("//ul[@id='address_invoice']//li[contains(@class,'address_city')]");

    public final By billingCountry =
            By.xpath("//ul[@id='address_invoice']//li[@class='address_country_name']");

    public final By billingPhone =
            By.xpath("//ul[@id='address_invoice']//li[@class='address_phone']");

    public final By cartInfo =
            By.xpath("//div[@class='product-information']");

    public final By orderReviewSection =
            By.xpath("//h2[normalize-space()='Review Your Order']");

    public final By commentArea =
            By.xpath("//textarea[@class='form-control']");

    // PLACE ORDER
    public final By btnPlaceOrder =
            By.xpath("//a[contains(@class,'check_out') and normalize-space()='Place Order']");

    //PAYMENT DETAILS
    public final By nameOnCard =
            By.xpath("//input[@name='name_on_card']");

    public final By cardNumber =
            By.xpath("//input[@name='card_number']");

    public final By cardCvc =
            By.xpath("//input[@name='cvc']");

    public final By cardMonthExpiration =
            By.xpath("//input[@class='form-control card-expiry-month']");

    public final By cardYearExpiration =
            By.xpath("//input[@name='expiry_year']");

    public final By btnPayAndConfirmOrder =
            By.xpath("//button[@id='submit']");

    public final By orderPlacedSuccessMessage =
            By.xpath("//p[contains(text(), 'Congratulations! Your order has been confirmed!')]");


    public final By btnDownloadInvoice =
            By.xpath("//a[contains(@class,'check_out') and contains(text(),'Download Invoice')]");

    public final By btnContinue =
            By.xpath("//a[contains(@class, 'btn-primary') and contains(text(), 'Continue')]");

}
