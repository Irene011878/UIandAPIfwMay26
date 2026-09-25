package pages;

import maps.CartMap;
import org.openqa.selenium.WebDriver;
import utilities.commonMethods.CommonMethods;

import java.util.List;

public class CartPage extends CommonMethods {

    private final CartMap cartMap;

    public CartPage(WebDriver driver) {
        super(driver);
        cartMap = new CartMap();
    }

    //CART VALIDATIONS
    public boolean isCartDisplayed() {
        return isDisplayed(cartMap.cartItems);
    }

    public int getProductsCount() {
        return getElementsCount(cartMap.cartProducts);
    }

    public boolean isProductInCart(String productName) {
        return isDisplayed(cartMap.productInCart(productName));

    }

    public boolean waitUntilProductDisplayed(String productName) {

        return waitVisible(
                cartMap.productInCart(productName)
        ).isDisplayed();
    }

    // PRODUCT DATA

    public List<String> getProductsPrices() {
        return getElementsText(cartMap.productsPrices);
    }

    public List<String> getProductsQuantities() {
        return getElementsText(cartMap.productsQuantity);
    }

    public List<String> getTotalPrices() {
        return getElementsText(cartMap.totalPrices);
    }

    public String getProductPrice(String productName) {
        return getText(cartMap.priceByProduct(productName));
    }

    public String getProductTotal(String productName) {
        return getText(cartMap.totalByProduct(productName));
    }


    //ADD TO CART
    public void addProductToCart(int productIndex) {
        click(cartMap.addToCartOverlay(productIndex));
    }

    public void clickContinueShopping() {
        click(cartMap.continueButton);
    }

    public void clickViewCart() {
        click(cartMap.viewCartBtn);
    }


    //PRODUCT DETAIL PAGE
    public boolean isProductInformationDisplayed() {
        closeGoogleVignetteAndStayOnPage();
        return isDisplayed(cartMap.productInformation);
    }

    public void setProductQuantity(String quantity) {
        clear(cartMap.productQuantityBtn);
        type(cartMap.productQuantityBtn, quantity);
    }

    public void clickAddToCartButton() {
        click(cartMap.addToCartBtn);
    }

    //QUANTITY VALIDATION
    public String getProductQuantity(String productName) {
        return getText(cartMap.quantityByProduct(productName));
    }

    //CHECKOUT
    public void clickProceedToCheckout() {
        click(cartMap.proceedToCheckoutBtn);
    }

    //REMOVE PRODUCT
    public void removeProduct(String productName) {
        click(cartMap.removeItem(productName));
    }

    public boolean waitUntilProductRemoved(String productName) {
        return waitInvisible(cartMap.productInCart(productName));
    }


}
