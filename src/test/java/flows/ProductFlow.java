package flows;

import io.qameta.allure.Step;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.HomePage;
import pages.ProductsPage;

/*public class ProductFlow {

    private final HomePage homePage;
    private final ProductsPage productsPage;
    private final CartPage cartPage;

    public ProductFlow(WebDriver driver) {

        homePage = new HomePage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }

    // PRODUCTS

    public void openProductsPage() {
        homePage.clickProducts();
        homePage.handleGoogleVignette();

        if (!productsPage.isAllProductsDisplayed()) {

            homePage.clickProducts();

        }



    }


    public void openProductDetail(String productName) {
        productsPage.openProduct(productName);
    }

    //SEARCH

    public void searchProduct(String productName) {
        productsPage.searchProduct(productName);
    }

    //CATEGORY

    public void openWomenCategory(String subCategory) {

        productsPage.clickWomenCategory();
        productsPage.clickCategory(subCategory);

    }

    public void openMenCategory(String subCategory) {

        productsPage.clickMenCategory();
        productsPage.clickCategory(subCategory);

    }

    public void openKidsCategory(String subCategory) {

        productsPage.clickKidsCategory();
        productsPage.clickCategory(subCategory);

    }

    // BRANDS

    public void openBrand(String brand) {
        productsPage.clickBrand(brand);
    }

    //CART

    public void addProductToCart(String productName) {
        productsPage.addProductToCart(productName);
    }

    public void addAllSearchResultsToCart() {

        productsPage.addAllSearchResultsToCart();

    }

    public void viewCart() {
        productsPage.clickViewCart();
    }

    public void openCartPage() {
        homePage.clickCart();
    }

    //REVIEW

    public void submitReview(String name, String email, String review) {

        productsPage.submitReview(name, email, review);

    }

    //RECOMMENDED ITEMS

    public void addRecommendedProduct(String productName) {

        homePage.scrollToBottom();
        productsPage.addRecommendedProduct(productName);

    }

    public void addFirstRecommendedProduct() {

        homePage.scrollToBottom();
        productsPage.clickFirstRecommendedItemToCart();

    }

}*/

public class ProductFlow {

    private final HomePage homePage;
    private final ProductsPage productsPage;
    private final CartPage cartPage;

    public ProductFlow(WebDriver driver) {

        homePage = new HomePage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }

    // PRODUCTS

    public void openProductsPage() {

        Allure.step(
                "Navigate to Products page", () -> {

                    homePage.clickProducts();
                    homePage.handleGoogleVignette();

                    if (!productsPage.isAllProductsDisplayed()) {

                        homePage.clickProducts();

                    }
                }
        );
    }


    public void openProductDetail(String productName) {

        Allure.step(
                "Open product detaill: " + productName,
                () ->productsPage.openProduct(productName));
    }

    // SEARCH

    public void searchProduct(String productName) {

        Allure.step(
                "Search for product: " + productName,
                () -> productsPage.searchProduct(productName));
    }

    // CATEGORY

    public void openWomenCategory(String subCategory) {

        Allure.step(
                "Open Women category: " + subCategory, () -> {
                    productsPage.clickWomenCategory();
                    productsPage.clickCategory(subCategory);

                });
    }


    public void openMenCategory(String subCategory) {

        Allure.step(
                "Open Men category: " + subCategory, () -> {

                    productsPage.clickMenCategory();
                    productsPage.clickCategory(subCategory);

                });
    }

    public void openKidsCategory(String subCategory) {

        Allure.step(
                "Open Kids category " + subCategory, () -> {

                    productsPage.clickKidsCategory();
                    productsPage.clickCategory(subCategory);

                });
    }

    // BRANDS

    public void openBrand(String brand) {

        Allure.step(
                "Open brand " + brand, ()-> productsPage.clickBrand(brand));
    }

    // CART

    public void addProductToCart(String productName) {

        Allure.step(
                "Add product to Cart " + productName, ()->
        productsPage.addProductToCart(productName));
    }

    public void addAllSearchResultsToCart() {

        Allure.step(
                "Add all search results to cart", ()->
                        productsPage.addAllSearchResultsToCart());

    }

    public void viewCart() {

        Allure.step(
                "View Cart", ()-> productsPage.clickViewCart());
    }

    public void openCartPage() {

        Allure.step(
                "Open Cart Page", ()-> homePage.clickCart());
    }

    // REVIEW

    public void submitReview(String name, String email, String review) {

        Allure.step(
                "Submit product review", ()->
                        productsPage.submitReview(name, email, review));

    }

    // RECOMMENDED ITEMS

    public void addRecommendedProduct(String productName) {

        Allure.step(
                "Add recommended product to cart: " + productName, ()->{

        homePage.scrollToBottom();
        productsPage.addRecommendedProduct(productName);

    });
}


    public void addFirstRecommendedProduct() {

        Allure.step(
                "Add first recommended product to cart", () -> {

                    homePage.scrollToBottom();
                    productsPage.clickFirstRecommendedItemToCart();

                });
    }

}