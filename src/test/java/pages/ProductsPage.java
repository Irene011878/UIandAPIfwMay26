package pages;

import maps.HomeMap;
import maps.ProductsMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.commonMethods.CommonMethods;

import java.util.List;

import maps.CartMap;

public class ProductsPage extends CommonMethods {

    private final ProductsMap productsMap;
    private final CartMap cartMap;

    public ProductsPage(WebDriver driver) {
        super(driver);
        productsMap = new ProductsMap();
        cartMap = new CartMap();

    }

    //PRODUCTS PAGE VALIDATIONS

    public boolean isAllProductsDisplayed() {
        return isDisplayed(productsMap.allProductsTitle);
    }

    public boolean isProductsContainerDisplayed() {
        return isDisplayed(productsMap.productsContainer);}


    public int getVisibleProductsCount() {
        return getElementsCount(productsMap.visibleProducts);
    }

//PRODUCT DETAIL

    public void openProduct(String productName) {
        click(productsMap.viewProduct(productName));
    }

    public void clickViewCart() {

        waitVisible(productsMap.viewCartButton);
        waitClickable(productsMap.viewCartButton);
        click(productsMap.viewCartButton);

    }


    public boolean isProductDetailDisplayed() {
        return isDisplayed(productsMap.productDetailContainer);
    }

    public boolean areProductsDisplayed() {
        return getVisibleProductsCount() > 0;
    }

    public boolean hasProducts() {
        return getVisibleProductsCount() > 0;
    }

    public boolean isProductNameDisplayed() {
        return isDisplayed(productsMap.productNameDetail);
    }

    public boolean isProductCategoryDisplayed() {
        return isDisplayed(productsMap.productCategoryDetail);
    }

    public boolean isPriceDisplayed() {
        return isDisplayed(productsMap.productPriceDetail);
    }

    public boolean isAvailabilityDisplayed() {
        return isDisplayed(productsMap.productAvailabilityDetail);
    }

    public boolean isConditionDisplayed() {
        return isDisplayed(productsMap.productConditionDetail);
    }

    public boolean isBrandDisplayed() {
        return isDisplayed(productsMap.productBrandDetail);
    }

    public boolean isProductPageReady() {

        return isAllProductsDisplayed()
                && areProductsDisplayed();

    }

    public boolean isProductDetailPageReady() {

        return isProductDetailDisplayed()
                && isProductNameDisplayed()
                && isProductCategoryDisplayed()
                && isPriceDisplayed()
                && isAvailabilityDisplayed()
                && isConditionDisplayed()
                && isBrandDisplayed();

    }

    //SEARCH
    public void enterSearchProduct(String productName) {
        type(productsMap.searchBar, productName);
    }

    public void clickSearchButton() {
        click(productsMap.submitSearchBtn);
    }

    public void searchProduct(String productName) {
        enterSearchProduct(productName);
        clickSearchButton();
    }

    public boolean isSearchedProductDisplayed() {
        return isDisplayed(productsMap.searchedProductPage);
    }

    public boolean areSearchResultsDisplayed() {
        return getSearchResultsCount() > 0;
    }

    public int getSearchResultsCount() {
        return getElementsCount(productsMap.searchResults);
    }

    public boolean areAllSearchResultsRelatedTo(String searchText) {

        List<String> products = getElementsText(productsMap.searchResults);

        //System.out.println("Search text = " + searchText);

        for (String product : products) {

            //System.out.println("Product = " + product);

            if (!product.toLowerCase().contains(searchText.toLowerCase())) {

                //System.out.println("NO MATCH");

                return false;

            }

        }

        return !products.isEmpty();

    }


    public boolean isProductDisplayed(String productName) {

        return isDisplayed(productsMap.productName(productName));

    }

    //CATEGORY

    public void clickCategory(String category) {

        click(productsMap.category(category));

    }

    public void clickWomenCategory() {
        click(productsMap.womenCategory);
    }

    public void clickMenCategory() {
        click(productsMap.menCategory);
    }

    public void clickKidsCategory() {
        click(productsMap.kidsCategory);
    }

    public boolean isCategoryTitleDisplayed(String expectedTitle) {

        String actualTitle =
                getCategoryTitle()
                        .replaceAll("\\s+", " ")
                        .trim()
                        .toUpperCase();

        expectedTitle =
                expectedTitle
                        .replaceAll("\\s+", " ")
                        .trim()
                        .toUpperCase();

        return actualTitle.equals(expectedTitle);

    }

    public String getCategoryTitle() {

        return getText(productsMap.categoryTitle);

    }

    //BRANDS
    public boolean isBrandsPanelDisplayed() {
        return isDisplayed(productsMap.brandsPanel);
    }

    public void clickBrand(String brand) {
        click(productsMap.brand(brand));
    }

    public boolean isBrandTitleDisplayed(String brand) {

        String actualTitle = getBrandTitle()
                .replaceAll("\\s+", " ")
                .trim()
                .toUpperCase();

        String expected =
                ("BRAND - " + brand + " PRODUCTS")
                        .toUpperCase();

        //System.out.println("Expected = " + expected);
        //System.out.println("Actual = " + actualTitle);

        return actualTitle.contains(expected);

    }

    public String getBrandTitle() {

        return getText(productsMap.brandTitle);

    }

    //ADD PRODUCT TO CART

    public void addProductToCart(String productName) {

        scrollToElement(productsMap.productCard(productName));

        hover(productsMap.productCard(productName));

        jsClick(productsMap.addProductToCart(productName));

        waitVisible(cartMap.viewCartBtn);
    }

    public void addAllSearchResultsToCart() {

        int totalProducts = getSearchResultsCount();

        //System.out.println("Total products found = " + totalProducts);

        for (int i = 1; i <= totalProducts; i++) {

            System.out.println("--------------------------------");
            System.out.println("Adding product " + i);

            scrollToElement(productsMap.searchedProductCard(i));
            //System.out.println("Scroll OK");

            hover(productsMap.searchedProductCard(i));
            //System.out.println("Hover OK");

            jsClick(productsMap.searchedProductAddToCart(i));
            //System.out.println("Add To Cart clicked");

            if (i < totalProducts) {

                //System.out.println("Click Continue Shopping");

                click(productsMap.continueShoppingButton);

                //System.out.println("Continue Shopping clicked");

            } else {

                //System.out.println("Last product");

                waitVisible(productsMap.viewCartButton);

                jsClick(productsMap.viewCartButton);

                //System.out.println("View Cart clicked");

            }
        }

        //System.out.println("End of addAllSearchResultsToCart()");
    }


    //REVIEW
    public boolean isReviewSectionDisplayed() {
        return isDisplayed(productsMap.writeYourReviewText);
    }

    public void enterReviewName(String name) {
        type(productsMap.reviewName, name);
    }

    public void enterReviewEmail(String email) {
        type(productsMap.reviewEmail, email);
    }

    public void enterReviewText(String review) {
        type(productsMap.reviewTextArea, review);
    }

    public void clickSubmitReview() {
        click(productsMap.btnReview);
    }


    public void submitReview(String name, String email, String review) {

        enterReviewName(name);
        enterReviewEmail(email);
        enterReviewText(review);
        clickSubmitReview();

    }

    public boolean isReviewSuccessMessageDisplayed() {
        return isDisplayed(productsMap.reviewSuccessMessage);
    }

    //RECOMMENDED ITEMS
    public void addRecommendedProduct(String productName) {

        click(productsMap.recommendedNextButton);
        hover(productsMap.recommendedProductCard(productName));
        click(productsMap.recommendedProductAddToCart(productName));

    }

    public void clickFirstRecommendedItemToCart() {

        click(productsMap.firstRecommendedAddToCart);

    }

}
