package testCases.ui.cart;


import flows.CartFlow;
import flows.ProductFlow;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import setUp.BaseTest;
import utilities.dataProvider.TestDataProvider;

import java.util.List;
import java.util.Map;

@Epic("Cart")
@Feature("Cart Management")
public class CartTest extends BaseTest {


    // ============================================================
    // TC012 - Add Products in Cart
    // ============================================================

    @Test(
            groups = {"smoke", "regression"},
            dataProvider = "cartAddProductsData",
            dataProviderClass = TestDataProvider.class
    )

    @Story("Add products to cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description(
            "Verify that two products are successfully added to the cart " +
                    "and that their prices, quantities and total prices are correct."
    )

    public void TC012_verifyProductsAreAddedSuccessfullyToCart(
            Map<String, String> data) {

        ProductFlow productFlow = new ProductFlow(driver);
        CartFlow cartFlow = new CartFlow(driver);
        HomePage homePage = new HomePage(driver);


        // Precondition: Verify that Home page is displayed
        Assert.assertTrue(
                homePage.isHomeDisplayed(),
                "Home page should be displayed successfully.");


        // STEP 1 - Click Products button
        productFlow.openProductsPage();

        // STEP 2 - Hover over first product and click Add to cart
        productFlow.addProductToCart(data.get("productName"));

        // STEP 3 - Click Continue Shopping button
        cartFlow.continueShopping();

        // STEP 4 - Hover over second product and click Add to cart
        productFlow.addProductToCart(data.get("secondProductName"));

        // STEP 5 - Click View Cart button
        cartFlow.viewCart();

        // STEP 6 - Verify both products are added to Cart:

        // a)Verify that Cart page is displayed
        Assert.assertTrue(
                cartFlow.isCartDisplayed(),
                "Cart page should be displayed.");


        // b) Verify that both products are added to Cart
        Assert.assertEquals(
                cartFlow.getProductsCount(), 2,
                "Cart should contain exactly two products.");

        // Step 7 - Verify their prices, quantity and total price

        List<String> prices = cartFlow.getProductsPrices();
        List<String> quantities = cartFlow.getProductsQuantities();
        List<String> totals = cartFlow.getTotalPrices();

        // Verify quantities of both products
        Assert.assertEquals(quantities, List.of("1", "1"),
                "Both products should have quantity 1.");

        // Verify that prices and totals exist for both products
        Assert.assertEquals(prices.size(), 2,
                "Two product prices should be displayed.");

        Assert.assertEquals(totals.size(), 2,
                "Two product total prices should be displayed.");

        // Verify total price = price × quantity for both products
        for (int i = 0; i < prices.size(); i++) {
            double price = parsePrice(prices.get(i));
            double quantity = Double.parseDouble(quantities.get(i));
            double total = parsePrice(totals.get(i));

            Assert.assertEquals(total, price * quantity, 0.01,
                    "Total price should equal price × quantity for product "
                            + (i + 1));
        }
    }


    // ============================================================
    // TC013 - Verify Product Quantity in Cart
    // ============================================================

    @Test(
            groups = {"regression"},
            dataProvider = "cartQuantityData",
            dataProviderClass = TestDataProvider.class
    )

    @Story("Verify Product Quantity in Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description(
            "Verify that the product quantity displayed in the cart " +
                    "matches the quantity selected on the product detail page."
    )
    public void TC013_verifyProductQuantityInCart(
            Map<String, String> data) {

        ProductFlow productFlow = new ProductFlow(driver);
        CartFlow cartFlow = new CartFlow(driver);
        HomePage homePage = new HomePage(driver);


        // Precondition - Verify that Home page is displayed
        Assert.assertTrue(
                homePage.isHomeDisplayed(),
                "Home page should be displayed successfully.");


        // STEP 1 - Click View Product for the first product on Home page
        homePage.clickFirstProductView();

        // STEP 2 - Verify that Product Detail page is opened
        Assert.assertTrue(
                cartFlow.isProductInformationDisplayed(),
                "Product detail information should be displayed.");


        // STEP 3 - Increase product quantity to requested quantity
        cartFlow.setProductQuantity(data.get("quantity"));

        // STEP 4 - Click Add to cart button
        cartFlow.addProductFromProductDetail();

        // STEP 5 - Click View Cart button
        cartFlow.viewCart();

        // STEP 6 - Verify that product is displayed in cart page with exact quantity

        // a) Verify that Cart page is displayed
        Assert.assertTrue(
                cartFlow.isCartDisplayed(),
                "Cart page should be displayed.");

        // b) - Verify that product is displayed in Cart
        Assert.assertTrue(
                cartFlow.isProductInCart(data.get("productName")), "Product '"
                        + data.get("productName")
                        + "' should be displayed in Cart.");

        // c) - Verify exact product quantity
        Assert.assertEquals(
                cartFlow.getProductQuantity(data.get("productName")),
                data.get("quantity"),
                "Product quantity in Cart should match the requested quantity.");
    }

    // ============================================================
    // TC017 - Remove Products From Cart
    // ============================================================

    @Test(
            groups = {"regression"},
            dataProvider = "cartRemoveProductData",
            dataProviderClass = TestDataProvider.class
    )

    @Story("Remove Products From Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description(
            "Verify that a product can be successfully removed from the cart."
    )

    public void TC017_verifyProductIsRemovedSuccessfully(
            Map<String, String> data) {

        ProductFlow productFlow = new ProductFlow(driver);
        CartFlow cartFlow = new CartFlow(driver);
        HomePage homePage = new HomePage(driver);


        // Preconditions - Verify that Home page is displayed
        Assert.assertTrue(
                homePage.isHomeDisplayed(),
                "Home page should be displayed successfully.");

        // Click Products button
        productFlow.openProductsPage();

        // STEP 1 - Add product to cart
        productFlow.addProductToCart(data.get("productName"));

        // STEP 2 - Click Cart button
        productFlow.openCartPage();

        // STEP 3 - Verify that Cart page is displayed
        Assert.assertTrue(
                cartFlow.isCartDisplayed(),
                "Cart page should be displayed.");

        // STEP 3a - Verify that product is initially in Cart
        Assert.assertTrue(
                cartFlow.waitUntilProductDisplayed(
                        data.get("productName")),
                "Product '" +
                        data.get("productName") +
                        "' should initially be displayed in Cart.");


        // STEP 4 - Click X button corresponding to product
        cartFlow.removeProduct(data.get("productName"));

        // STEP 5 - Verify that product has been removed from Cart
        Assert.assertTrue(
                cartFlow.waitUntilProductRemoved(
                        data.get("productName")),
                "Product '" +
                        data.get("productName") +
                        "' should no longer be displayed in Cart.");
    }


    // ============================================================
    // PRICE HELPER
    // ============================================================

    private double parsePrice(String price) {

        return Double.parseDouble(
                price.replaceAll("[^0-9.]", ""));
    }
}



