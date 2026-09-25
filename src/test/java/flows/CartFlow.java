package flows;

import org.openqa.selenium.WebDriver;
import pages.CartPage;
import io.qameta.allure.Allure;

import java.util.List;

/*public class CartFlow {

    private final CartPage cartPage;

    public CartFlow(WebDriver driver){

        cartPage = new CartPage(driver);
    }

    // CART VALIDATIONS

    public boolean isCartDisplayed(){
        return cartPage.isCartDisplayed();
    }

    public int getProductsCount(){
        return cartPage.getProductsCount();
    }

    public boolean isProductInCart(String productName){
        return cartPage.isProductInCart(productName);
    }

    public boolean waitUntilProductDisplayed(String productName){
        return cartPage.waitUntilProductDisplayed(productName);
    }

    // PRODUCT DATA

    public List<String> getProductsPrices(){
        return cartPage.getProductsPrices(); }

    public List<String> getProductsQuantities(){
        return cartPage.getProductsQuantities(); }

    public List<String> getTotalPrices(){
        return cartPage.getTotalPrices(); }

    // PRODUCT DATA BY PRODUCT
    public String getProductPrice(String productName){
        return cartPage.getProductPrice(productName);
    }

    public String getProductQuantity(String productName){
        return cartPage.getProductQuantity(productName);
    }

    public String getProductTotal(String productName){
        return cartPage.getProductTotal(productName); }

    // PRODUCT DETAIL

    public boolean isProductInformationDisplayed(){
        return cartPage.isProductInformationDisplayed();
    }

    public void setProductQuantity(String quantity){
        cartPage.setProductQuantity(quantity);
    }

    public void addProductFromProductDetail(){
        cartPage.clickAddToCartButton();
    }

    //CART NAVIGATION
    public void continueShopping(){
        cartPage.clickContinueShopping();
    }

    public void viewCart(){
        cartPage.clickViewCart();
    }


    // REMOVE PRODUCT

    public void removeProduct(String productName){
        cartPage.removeProduct(productName);
    }

    public boolean waitUntilProductRemoved(String productName){
        return cartPage.waitUntilProductRemoved(productName);
    }

    // CHECKOUT

    public void proceedToCheckout(){
        cartPage.clickProceedToCheckout();
    }
}*/
public class CartFlow {

    private final CartPage cartPage;

    public CartFlow(WebDriver driver) {

        cartPage = new CartPage(driver);
    }

    // CART VALIDATIONS

    public boolean isCartDisplayed() {
        return cartPage.isCartDisplayed();
    }

    public int getProductsCount() {
        return cartPage.getProductsCount();
    }

    public boolean isProductInCart(String productName) {
        return cartPage.isProductInCart(productName);
    }

    public boolean waitUntilProductDisplayed(String productName) {
        return cartPage.waitUntilProductDisplayed(productName);
    }

    // PRODUCT DATA

    public List<String> getProductsPrices() {
        return cartPage.getProductsPrices();
    }

    public List<String> getProductsQuantities() {
        return cartPage.getProductsQuantities();
    }

    public List<String> getTotalPrices() {
        return cartPage.getTotalPrices();
    }

    // PRODUCT DATA BY PRODUCT

    public String getProductPrice(String productName) {
        return cartPage.getProductPrice(productName);
    }

    public String getProductQuantity(String productName) {
        return cartPage.getProductQuantity(productName);
    }

    public String getProductTotal(String productName) {
        return cartPage.getProductTotal(productName);
    }

    // PRODUCT DETAIL

    public boolean isProductInformationDisplayed() {
        return cartPage.isProductInformationDisplayed();
    }

    public void setProductQuantity(String quantity) {

        Allure.step(
                "Set product quantity to: " + quantity,
                () -> cartPage.setProductQuantity(quantity)
        );
    }

    public void addProductFromProductDetail() {

        Allure.step(
                "Add product to cart from product detail",
                () -> cartPage.clickAddToCartButton()
        );
    }

    // CART NAVIGATION

    public void continueShopping() {

        Allure.step(
                "Continue shopping",
                () -> cartPage.clickContinueShopping()
        );
    }

    public void viewCart() {

        Allure.step(
                "View cart",
                () -> cartPage.clickViewCart()
        );
    }

    // REMOVE PRODUCT

    public void removeProduct(String productName) {

        Allure.step(
                "Remove product from cart: " + productName,
                () -> cartPage.removeProduct(productName)
        );
    }

    public boolean waitUntilProductRemoved(String productName) {
        return cartPage.waitUntilProductRemoved(productName);
    }

    // CHECKOUT

    public void proceedToCheckout() {

        Allure.step(
                "Proceed to checkout",
                () -> cartPage.clickProceedToCheckout()
        );
    }
}