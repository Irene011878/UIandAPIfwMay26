package maps;

import org.openqa.selenium.By;

public class CartMap {


    public final By continueButton =
            By.xpath("//button[normalize-space()='Continue Shopping']");


    public By addToCartOverlay(int productIndex) {

        return By.xpath("(//div[contains(@class,'product-overlay')]//a[@data-product-id])["
                + productIndex + "]");

    }

    public final By viewCartBtn =
            By.cssSelector("#cartModal a[href='/view_cart']");

    public final By cartItems =
            By.xpath("//section[@id='cart_items']");

    public final By cartProducts =
            By.xpath("//tr[contains(@id,'product-')]");

    public final By productsPrices =
            By.xpath("//td[@class='cart_price']/p");

    public final By productsQuantity =
            By.xpath("//td[@class='cart_quantity']//button");

    public final By totalPrices =
            By.xpath("//td[@class='cart_total']/p");

    public final By productInformation =
            By.xpath("//div[@class='product-information']");

    public final By productQuantityBtn =
            By.id("quantity");

    public final By addToCartBtn =
            By.xpath("//button[normalize-space()='Add to cart']");

    public By quantityByProduct(String productName) {

        return By.xpath(
                "//tr[.//a[text()='" + productName + "']]" +
                        "//td[@class='cart_quantity']//button");
    }

    public final By proceedToCheckoutBtn =
            By.xpath("//a[contains(@class,'check_out') and normalize-space()='Proceed To Checkout']");


    public By removeItem(
            String productName) {

        return By.xpath(
                "//tr[.//a[text()='" + productName + "']]"
                        +
                        "//a[@class='cart_quantity_delete']");

    }


    public By productInCart(String productName) {

        return By.xpath(
                "//tr[.//a[contains(normalize-space(.),'" + productName + "')]]");

    }

    public By priceByProduct(String productName) {

        return By.xpath(
                "//tr[.//a[contains(normalize-space(.),'" + productName + "')]]" +
                        "//td[@class='cart_price']/p");
    }

    public By totalByProduct(String productName) {

        return By.xpath(
                "//tr[.//a[contains(normalize-space(.),'" + productName + "')]]" +
                        "//td[@class='cart_total']/p");
    }

}
