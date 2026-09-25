package testCases.ui.products;


import flows.AccountFlow;
import flows.ProductFlow;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductsPage;
import setUp.BaseTest;
import utilities.dataProvider.TestDataProvider;

import java.util.Map;

@Epic("UI Tests")
@Feature("Products")
public class ProductsTest extends BaseTest {

    private HomePage homePage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private ProductFlow productFlow;
    private AccountFlow accountFlow;
    private LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void setUpPages() {

        homePage = new HomePage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        loginPage = new LoginPage(driver);

        productFlow = new ProductFlow(driver);
        accountFlow = new AccountFlow(driver);

    }

    @Story("TC008 - Verify All Products and Product Detail Page")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that all products are displayed and the selected product detail page shows complete information.")

    @Test(
            description = "TC008 - All products are displayed and the selected product detail page shows complete information",
            groups = {"smoke", "regression"},
            dataProvider = "verifyProductsData",
            dataProviderClass = TestDataProvider.class
    )


    public void TC008_verifyAllProductsAndProductDetailPage(Map<String, String> data) {

        // 1. Click on 'Products' button
        productFlow.openProductsPage();

        // 2. Verify user is navigated to ALL PRODUCTS page successfully
        Assert.assertTrue(
                productsPage.isAllProductsDisplayed(),
                "All Products page is not displayed.");

        // 3. Verify products list is visible
        Assert.assertTrue(
                productsPage.areProductsDisplayed(),
                "Products list is not displayed.");

        // 4. Click on 'View Product' of first product
        productFlow.openProductDetail(data.get("productName"));

        // 5. Verify user is landed to product detail page
        Assert.assertTrue(
                productsPage.isProductDetailDisplayed(),
                "Product detail page is not displayed.");

        // 6. Verify product information
        Assert.assertTrue(
                productsPage.isProductDetailPageReady(),
                "Product information is incomplete.");
    }

    @Story("TC009 - Search Product")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that searching for a product displays only matching products.")

    @Test(
            description = "TC009 - Searching for a product displays only matching products.",
            groups = {"smoke", "regression"},
            dataProvider = "searchProductsData",
            dataProviderClass = TestDataProvider.class
    )


    public void TC009_searchProduct(Map<String, String> data) {

        // 1. Click on 'Products' button
        productFlow.openProductsPage();

        // 2. Verify user is navigated to ALL PRODUCTS page successfully
        Assert.assertTrue(
                productsPage.isProductPageReady(),
                "Products page is not ready.");

        // 3. Enter product name in search input and click search button
        productFlow.searchProduct(data.get("searchText"));

        // 4. Verify 'SEARCHED PRODUCTS' is visible
        Assert.assertTrue(
                productsPage.isSearchedProductDisplayed(),
                "Searched Products title is not displayed.");

        // 5. Verify all the products related to search are visible
        Assert.assertTrue(
                productsPage.areAllSearchResultsRelatedTo(data.get("searchText")),
                "Not all search results match the search criteria.");
    }

    @Story("TC018 - View Category Products")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that products are displayed correctly when browsing categories.")

    @Test(
            description = "TC018 - View Category Products",
            groups = {"regression"},
            dataProvider = "categoryProductsData",
            dataProviderClass = TestDataProvider.class
    )


    public void TC018_viewCategoryProducts(Map<String, String> data) {

        // 1. Verify that categories are visible on left side bar
        productFlow.openProductsPage();

        Assert.assertTrue(
                homePage.isCategoryPanelDisplayed(),
                "Category panel is not displayed.");

        // 2. Click on 'Women' category
        // 3. Click on any category link under 'Women' category
        productFlow.openWomenCategory(data.get("subCategory"));

        // 4. Verify that category page is displayed
        String womenCategoryTitle =
                data.get("category") + " - " + data.get("subCategory") + " PRODUCTS";

        Assert.assertTrue(
                productsPage.isCategoryTitleDisplayed(womenCategoryTitle),
                "Women category page is not displayed.");


        // 5. Click on any sub-category link of 'Men' category
        productFlow.openMenCategory(data.get("secondSubCategory"));

        // 6. Verify that user is navigated to that category page
        String menCategoryTitle =
                data.get("secondCategory") + " - " + data.get("secondSubCategory") + " PRODUCTS";

        Assert.assertTrue(
                productsPage.isCategoryTitleDisplayed(menCategoryTitle),
                "Men category page is not displayed.");

    }

    @Story("TC019 - View Brand Products")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that products are displayed correctly when browsing different brands.")

    @Test(
            description = "TC019 - View Brand Products",
            groups = {"regression"},
            dataProvider = "brandProductsData",
            dataProviderClass = TestDataProvider.class
    )

    public void TC019_viewBrandProducts(Map<String, String> data) {

        // 1. Click on 'Products' button
        productFlow.openProductsPage();

        // 2. Verify that Brands are visible on left side bar
        Assert.assertTrue(
                productsPage.isBrandsPanelDisplayed(),
                "Brands panel is not displayed.");

        // 3. Click on any brand name
        productFlow.openBrand(data.get("brand"));

        //System.out.println("Expected = " + data.get("brand"));

        /*String actualTitle = driver.findElement(
                By.xpath("//h2[@class='title text-center']")
        ).getText();*/

        //System.out.println("Actual = " + actualTitle);

        // 4. Verify that user is navigated to brand page and brand products are displayed
        Assert.assertTrue(
                productsPage.isBrandTitleDisplayed(data.get("brand")),
                "Brand page is not displayed for: " + data.get("brand")
        );

        Assert.assertTrue(
                productsPage.areProductsDisplayed(),
                "Products are not displayed for brand: " + data.get("brand")
        );

        // 5. Click on any other brand link
        productFlow.openBrand(data.get("secondBrand"));

        // 6. Verify that user is navigated to that brand page and can see products
        Assert.assertTrue(
                productsPage.isBrandTitleDisplayed(data.get("secondBrand")),
                "Brand page is not displayed for: " + data.get("secondBrand")
        );

        Assert.assertTrue(
                productsPage.areProductsDisplayed(),
                "Products are not displayed for brand: " + data.get("secondBrand")
        );

    }

    @Story("TC020 - Search Products and Verify Cart After Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that searched products remain in the shopping cart after user login.")

    @Test(
            description = "TC020 - Search Products and Verify Cart After Login",
            groups = {"regression"},
            dataProvider = "searchCartLoginData",
            dataProviderClass = TestDataProvider.class
    )


    public void TC020_searchProductsAndVerifyCartAfterLogin(Map<String, String> data) {

        // 1. Click on 'Products' button
        productFlow.openProductsPage();

        // 2. Verify user is navigated to ALL PRODUCTS page successfully
        Assert.assertTrue(
                productsPage.isProductPageReady(),
                "Products page is not displayed."
        );

        // 3. Enter product name in search input and click search button
        productFlow.searchProduct(data.get("searchText"));

        // 4. Verify 'SEARCHED PRODUCTS' is visible
        Assert.assertTrue(
                productsPage.isSearchedProductDisplayed(),
                "Searched Products title is not displayed."
        );

        // 5. Verify all the products related to search are visible
        Assert.assertTrue(
                productsPage.areAllSearchResultsRelatedTo(data.get("searchText")),
                "Not all search results match the search criteria."
        );

        // 6. Add searched products to cart
        productFlow.addAllSearchResultsToCart();
        productFlow.viewCart();

        // 7. Verify that product is visible in cart
        Assert.assertTrue(
                cartPage.isProductInCart(data.get("productName")),
                "Product was not added to the cart."
        );

        // 8. Click 'Signup / Login' button and submit login details
        homePage.clickSignLogin();
        accountFlow.login(data);

        // 9. Again, go to Cart page
        productFlow.openCartPage();

        // 10. Verify that product is visible in cart after login
        Assert.assertTrue(
                cartPage.isProductInCart(data.get("productName")),
                "Product was not retained in the cart after login."
        );

    }

    //=========================================================
    // TC021 - ADD REVIEW ON PRODUCT
    //=========================================================

    @Story("TC021 - Add Review on Product")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that a user can submit a review successfully.")

    @Test(
            description = "TC021 - Add Review on Product",
            groups = {"regression"},
            dataProvider = "reviewProductsData",
            dataProviderClass = TestDataProvider.class
    )

    public void TC021_addReviewOnProduct(Map<String, String> data) {

        //Step 1: Click on 'Products' button
        productFlow.openProductsPage();

        //Step 2: Verify user is navigated to ALL PRODUCTS page successfully
        Assert.assertTrue(
                productsPage.isProductPageReady(),
                "Products page is not displayed."
        );

        //Step 3: Click on 'View Product' of first product
        productFlow.openProductDetail(
                data.get("productName"));

        //Step 4: Verify 'Write Your Review' is visible
        Assert.assertTrue(
                productsPage.isReviewSectionDisplayed(),
                "'Write Your Review' section is not displayed."
        );

        //Step 5: Enter name, email and review
        //Step 6: Click 'Submit' button
        productFlow.submitReview(
                data.get("name"),
                data.get("email"),
                data.get("review")
        );

        //Step 7: Verify success message
        Assert.assertTrue(
                productsPage.isReviewSuccessMessageDisplayed(),
                "Review success message is not displayed."
        );

    }

    //=========================================================
    // TC022 - ADD TO CART FROM RECOMMENDED ITEMS
    //=========================================================

    @Story("TC022 - Add to Cart from Recommended Items")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that a recommended product can be added to the cart successfully.")

    @Test(
            description = "TC022 - Add to Cart from Recommended Items",
            groups = {"regression"},
            dataProvider = "recommendedProductsData",
            dataProviderClass = TestDataProvider.class
    )

    public void TC022_addRecommendedProductToCart(Map<String, String> data) {

        //Step 1: Scroll to bottom of page
        homePage.scrollToBottom();

        //Step 2: Verify 'RECOMMENDED ITEMS' are visible
        Assert.assertTrue(
                homePage.isRecommendedItemsDisplayed(),
                "'RECOMMENDED ITEMS' section is not displayed."
        );

        //Step 3: Add recommended product to cart
        productFlow.addRecommendedProduct(
                data.get("productName")
        );

        //Step 4: Click 'View Cart'
        productFlow.viewCart();

        //Step 5: Verify product is displayed in cart
        Assert.assertTrue(
                cartPage.isProductInCart(
                        data.get("productName")
                ),
                "Recommended product was not added to the cart."
        );

    }

}