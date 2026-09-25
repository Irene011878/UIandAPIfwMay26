package maps;

import org.openqa.selenium.By;

public class ProductsMap {

    //PRODUCTS PAGE

    public final By allProductsTitle =
            By.xpath("//h2[contains(text(), 'All Products')]");

    public final By productsContainer =
            By.xpath("//div[@class='features_items']");

    public final By visibleProducts =
            By.xpath("//div[@class='product-image-wrapper']");

    public final By viewCartButton =
            By.cssSelector("a[href='/view_cart']");

    //SEARCH
    public final By searchBar =
            By.cssSelector("input#search_product");

    public final By submitSearchBtn =
            By.xpath("//button[@id='submit_search']");

    public final By searchedProductPage =
            By.xpath("//h2[text()='Searched Products']");

    public final By searchResults =
            By.xpath("//div[@class='features_items']//div[@class='productinfo text-center']/p");

    // SEARCH RESULTS - ADD TO CART

    public final By searchedProductCards =
            By.xpath("//div[@class='features_items']//div[@class='product-image-wrapper']");

    public final By continueShoppingButton =
            By.xpath("//button[text()='Continue Shopping']");

    public By searchedProductCard(int index) {

        return By.xpath(
                "(//div[@class='product-image-wrapper'])[" + index + "]");
    }

    public By searchedProductAddToCart(int index) {

        return By.xpath(
                "(//div[@class='product-image-wrapper'])[" + index + "]" +
                        "//a[contains(@class,'add-to-cart')]");
    }


    //PRODUCT DETAIL

    public final By productDetailContainer =
            By.xpath("//div[@class='product-information']");

    public By viewProduct(String productName) {

        return By.xpath(
                "//div[@class='product-image-wrapper']" +
                        "[.//p[text()='" + productName + "']]" +
                        "//a[contains(@href,'product_details')]");

    }

    public final By productNameDetail =
            By.xpath("//div[@class='product-information']/h2");

    public final By productCategoryDetail =
            By.xpath("//div[@class='product-information']/p");

    public final By productPriceDetail =
            By.xpath("//div[@class='product-information']//span/span");

    public final By productAvailabilityDetail =
            By.xpath("//b[text()='Availability:']");

    public final By productConditionDetail =
            By.xpath("//b[text()='Condition:']");

    public final By productBrandDetail =
            By.xpath("//b[text()='Brand:']");

    //ADD TO CART

    public By productCard(String productName) {

        return By.xpath(
                "//div[@class='product-image-wrapper']" +
                        "[.//p[text()='" + productName + "']]");
    }

    public By addProductToCart(String productName) {

        return By.xpath(
                "//div[@class='product-image-wrapper']" +
                        "[.//p[text()='" + productName + "']]" +
                        "//div[contains(@class,'product-overlay')]" +
                        "//a[contains(@class,'add-to-cart')]");
    }

    public By productName(String productName) {

        return By.xpath(
                "//p[text()='"
                        + productName + "']");
    }

    //CATEGORY
    public final By womenCategory =
            By.xpath("//a[@href='#Women']");

    public final By menCategory =
            By.xpath("//a[@href='#Men']");

    public final By kidsCategory =
            By.xpath("//a[@href='#Kids']");

    // WOMEN locator dinamico en lugar de dress, tops, saree
    public By category(String category) {

        return By.xpath("//a[contains(text(),'" + category + "')]");

    }


    //5 ago metodo definitivo no dinamico
    public final By categoryTitle =
            By.xpath("//h2[@class='title text-center']");

    // BRANDS

    public final By brandsPanel =
            By.xpath("//div[@class='brands_products']");

    public By brand(String brandName) {

        return By.xpath(
                "//div[@class='brands-name']//a[contains(.,'"
                        + brandName + "')]");
    }

    public final By brandTitle =
            By.xpath("//h2[@class='title text-center']");


    //REVIEW

    public final By writeYourReviewText =
            By.xpath("//a[normalize-space()='Write Your Review']");

    public final By reviewName =
            By.xpath("//input[@id='name']");

    public final By reviewEmail =
            By.xpath("//input[@id='email']");

    public final By reviewTextArea =
            By.xpath("//textarea[@id='review']");

    public final By btnReview =
            By.cssSelector("button#button-review");

    public final By reviewSuccessMessage =
            By.xpath("//span[normalize-space()='Thank you for your review.']");


//RECOMMENDED ITEMS

    public By recommendedProductAddToCart(String productName) {

        return By.xpath(
                "//div[@id='recommended-item-carousel']" +
                        "[.//p[text()='" + productName + "']]" +
                        "//a[contains(@class,'add-to-cart')]");
    }

    public By recommendedNextButton =
            By.xpath("//div[@id='recommended-item-carousel']//a[@class='right recommended-item-control']");

    public final By firstRecommendedAddToCart =
            By.xpath("(//div[@id='recommended-item-carousel']//a[contains(@class,'add-to-cart')])[1]");

    public By recommendedProductCard(String productName) {

        return By.xpath(
                "//div[@id='recommended-item-carousel']" +
                        "[.//p[text()='" + productName + "']]");
    }

}


