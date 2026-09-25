package testCases.ui.checkout;

import flows.AccountFlow;
import flows.CartFlow;
import flows.CheckoutFlow;
import flows.ProductFlow;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import setUp.BaseTest;
import utilities.dataProvider.TestDataProvider;
import utilities.uniqueMails.TestDataUtils;

import java.util.Map;

@Epic("Checkout")
@Feature("Checkout Management")

public class CheckoutTest extends BaseTest {

    private HomePage homePage;
    private LoginPage loginPage;

    private AccountFlow accountFlow;
    private ProductFlow productFlow;
    private CartFlow cartFlow;
    private CheckoutFlow checkoutFlow;


    @BeforeMethod(alwaysRun = true)
    public void initializePages() {

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);

        accountFlow = new AccountFlow(driver);
        productFlow = new ProductFlow(driver);
        cartFlow = new CartFlow(driver);
        checkoutFlow = new CheckoutFlow(driver);

    }


    // ============================================================
    // TC014 - REGISTER DURING CHECKOUT
    // ============================================================

    @Story("Register During Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Description(
            "Verify that a new user can register during checkout " +
                    "and successfully place an order."
    )

    @Test(
            description = "TC014 - Register During Checkout",
            groups = {"regression"},
            dataProvider = "checkoutRegisterDuringData",
            dataProviderClass = TestDataProvider.class
    )

    public void registerDuringCheckout(
            Map<String, String> checkoutData,
            Map<String, String> userData) {

        // TEST DATA
        // Generate a unique user so the test remains independent.
        Map<String, String> user =
                TestDataUtils.generateUniqueUser(userData);

        //PRECONDITION
        // Verify that Home page is displayed successfully.
        Assert.assertTrue(
                homePage.isHomeDisplayed(),
                "Home page should be displayed successfully.");

        // STEP 1 - Add products to cart

        // Click Products button and open Products page.
        productFlow.openProductsPage();

        // Add first product - Blue Top
        productFlow.addProductToCart(checkoutData.get("productName"));

        // Click Continue Shopping
        cartFlow.continueShopping();

        // Add second product - Men Tshirt
        productFlow.addProductToCart(checkoutData.get("secondProductName"));

        // STEP 2 - Click Cart button
        cartFlow.viewCart();

        // STEP 3 - Verify that Cart page is displayed
        Assert.assertTrue(
                cartFlow.isCartDisplayed(),
                "Cart page should be displayed.");

        // Verify first product is in Cart
        Assert.assertTrue(
                cartFlow.isProductInCart(
                        checkoutData.get("productName")),
                "Product '" +
                        checkoutData.get("productName") +
                        "' should be displayed in Cart.");

        // Verify second product is in Cart
        Assert.assertTrue(
                cartFlow.isProductInCart(
                        checkoutData.get("secondProductName")),
                "Product '" +
                        checkoutData.get("secondProductName") +
                        "' should be displayed in Cart.");

        // STEP 4 - Click Proceed To Checkout
        cartFlow.proceedToCheckout();

        // STEP 5 - Click Register / Login button=
        accountFlow.openSignup();

        // STEP 6 - Fill all details in Signup and create account

        // Verify "New User Signup!" is displayed.
        Assert.assertTrue(
                loginPage.isRegisterTitleDisplayed(),
                "'New User Signup!' section is not displayed.");

        // Enter name and email and click Signup.
        accountFlow.startRegistration(user);

        // Verify "ENTER ACCOUNT INFORMATION" is displayed.
        Assert.assertTrue(
                loginPage.isAccountInformationDisplayed(),
                "'ENTER ACCOUNT INFORMATION' is not displayed.");


        // Fill account information.
        accountFlow.fillAccountInformation(user);

        // Click Create Account.
        accountFlow.submitRegistration();


        // STEP 7 - Verify ACCOUNT CREATED! and click Continue
        Assert.assertTrue(
                loginPage.isAccountCreatedMessageDisplayed(),
                "'ACCOUNT CREATED!' message is not displayed.");

        accountFlow.continueAfterRegistration();

        // STEP 8 - Verify Logged in as username
        Assert.assertTrue(
                homePage.isLoggedUserDisplayed(),
                "'Logged in as' label is not displayed.");


        Assert.assertEquals(
                homePage.getLoggedUser(),
                user.get("name"),
                "Incorrect logged user.");


        // STEP 9 - Click Cart button
        productFlow.openCartPage();

        // STEP 10 - Click Proceed To Checkout button
        cartFlow.proceedToCheckout();

        // STEP 11 - Verify Address Details and Review Your Order

        // Verify Address Details
        Assert.assertTrue(
                checkoutFlow.isAddressDetailsDisplayed(),
                "Address Details should be displayed.");

        // Verify Billing Address section

        Assert.assertTrue(
                checkoutFlow.isBillingAddressDisplayed(),
                "Billing Address should be displayed.");


        // Verify Review Your Order / cart information
        Assert.assertTrue(
                checkoutFlow.isOrderReviewDisplayed(),
                "Review Your Order section should be displayed."
        );


        // STEP 12 - Enter description and click Place Order
        checkoutFlow.enterComment(checkoutData.get("comment"));
        checkoutFlow.clickPlaceOrder();

        // STEP 13 and 14 - Enter payment details and Pay and Confirm Order
        checkoutFlow.completePayment(checkoutData);

        // STEP 15 - Verify success message
        Assert.assertTrue(
                checkoutFlow.isOrderPlacedSuccessfully(),
                "Order should be placed successfully.");

    }

    // ============================================================
    // TC015 - Register Before Checkout
    // ============================================================

    @Story("Register Before Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a registered user can successfully place an order.")

    @Test(
            description = "TC015 - Register Before Checkout",
            groups = {"regression"},
            dataProvider = "checkoutRegisterBeforeData",
            dataProviderClass = TestDataProvider.class
    )

    public void registerBeforeCheckout(
            Map<String, String> data,
            Map<String, String> userData) {

        // TEST DATA
        Map<String, String> user =
                TestDataUtils.generateUniqueUser(userData);

        // PRECONDITION: Verify Home page is displayed
        Assert.assertTrue(
                homePage.isHomeDisplayed(),
                "Home page should be displayed successfully.");

        // STEP 1 - Click Signup / Login
        accountFlow.openSignup();

        // STEP 2 - Fill all details in Signup and create account
        Assert.assertTrue(
                loginPage.isRegisterTitleDisplayed(),
                "'New User Signup!' section should be displayed.");

        accountFlow.startRegistration(user);

        Assert.assertTrue(
                loginPage.isAccountInformationDisplayed(),
                "'ENTER ACCOUNT INFORMATION' section should be displayed.");

        accountFlow.fillAccountInformation(user);

        accountFlow.submitRegistration();

        // STEP 3 - Verify ACCOUNT CREATED! and click Continue
        Assert.assertTrue(
                loginPage.isAccountCreatedMessageDisplayed(),
                "'ACCOUNT CREATED!' message should be displayed.");

        accountFlow.continueAfterRegistration();

        // STEP 4 - Verify Logged in as username
        Assert.assertTrue(
                homePage.isLoggedUserDisplayed(),
                "'Logged in as' label should be displayed.");

        Assert.assertEquals(
                homePage.getLoggedUser(),
                user.get("name"),
                "Incorrect logged user.");


        // STEP 5 - Add products to cart
        productFlow.openProductsPage();
        productFlow.addProductToCart(data.get("productName"));
        cartFlow.continueShopping();
        productFlow.addProductToCart(data.get("secondProductName"));

        // STEP 6 - Click Cart button
        productFlow.openCartPage();

        // STEP 7 - Verify that Cart page is displayed
        Assert.assertTrue(
                cartFlow.isCartDisplayed(),
                "Cart page should be displayed.");

        // STEP 8 - Click Proceed To Checkout
        cartFlow.proceedToCheckout();

        // STEP 9 - Verify Address Details and Review Your Order
        Assert.assertTrue(
                checkoutFlow.isAddressDetailsDisplayed(),
                "Address Details section should be displayed.");

        Assert.assertTrue(
                checkoutFlow.isOrderReviewDisplayed(),
                "Review Your Order section should be displayed.");

        // STEP 10 - Enter description and click Place Order
        checkoutFlow.enterComment(data.get("comment"));
        checkoutFlow.clickPlaceOrder();

        // STEP 11 and 12 - Enter payment details and Click Pay and Confirm Order
        checkoutFlow.completePayment(data);

        // STEP 13 - Verify success message
        Assert.assertTrue(
                checkoutFlow.isOrderPlacedSuccessfully(),
                "Order should be placed successfully.");
    }


    // ============================================================
    // TC016 - Login Before Checkout
    // ============================================================

    @Story("Login Before Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Description(
            "Verify that an existing user can login successfully " +
                    "and place an order."
    )
    @Test(
            description = "TC016 - Login Before Checkout",
            groups = {"smoke", "regression"},
            dataProvider = "checkoutLoginBeforeData",
            dataProviderClass = TestDataProvider.class
    )
    public void loginBeforeCheckout(
            Map<String, String> checkoutData,
            Map<String, String> userData) {

        // TEST DATA: Create a unique user to keep the test independent
        Map<String, String> user =
                TestDataUtils.generateUniqueUser(userData);

        // PRECONDITION:
        // Verify Home page is displayed
        Assert.assertTrue(
                homePage.isHomeDisplayed(),
                "Home page should be displayed successfully.");

        // Register a new user
        accountFlow.registerUser(user);

        // Logout to return to Login page
        accountFlow.logout();

        // STEP 1 - Click Signup / Login
        accountFlow.openLogin();

        // STEP 2 - Fill email, password and click Login
        Assert.assertTrue(
                loginPage.isLoginTitleDisplayed(),
                "'Login to your account' title should be displayed.");

        accountFlow.login(user);

        // STEP 3 - Verify Logged in as username
        Assert.assertTrue(
                homePage.isLoggedUserDisplayed(),
                "'Logged in as' label should be displayed.");

        Assert.assertEquals(
                homePage.getLoggedUser(),
                user.get("name"),
                "Incorrect logged user.");

        // STEP 4 - Add products to cart
        productFlow.openProductsPage();
        productFlow.addProductToCart(checkoutData.get("productName"));
        cartFlow.continueShopping();
        productFlow.addProductToCart(checkoutData.get("secondProductName"));

        // STEP 5 - Click Cart button
        productFlow.openCartPage();

        // STEP 6 - Verify that Cart page is displayed
        Assert.assertTrue(
                cartFlow.isCartDisplayed(),
                "Cart page should be displayed.");

        // STEP 7 - Click Proceed To Checkout
        cartFlow.proceedToCheckout();

        // STEP 8 - Verify Address Details and Review Your Order
        Assert.assertTrue(
                checkoutFlow.isAddressDetailsDisplayed(),
                "Address Details section should be displayed.");

        Assert.assertTrue(
                checkoutFlow.isOrderReviewDisplayed(),
                "Review Your Order section should be displayed.");

        // STEP 9 - Enter description and click Place Order
        checkoutFlow.enterComment(checkoutData.get("comment"));
        checkoutFlow.clickPlaceOrder();

        // STEP 10 and 11- Enter payment details & Click Pay and Confirm Order
        checkoutFlow.completePayment(checkoutData);

        // STEP 12 - Verify success message
        Assert.assertTrue(
                checkoutFlow.isOrderPlacedSuccessfully(),
                "Order should be placed successfully.");
    }

    // ============================================================
    // TC023 - Verify address details in checkout page
    // ============================================================

    @Story("Verify address details in checkout page")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that user can verify address details were  registered successfully")

    @Test(
            description = "TC023 - Verify Address Details in Checkout",
            groups = {"regression"},
            dataProvider = "checkoutAddressData",
            dataProviderClass = TestDataProvider.class
    )
    public void verifyAddressDetailsInCheckout(
            Map<String, String> data,
            Map<String, String> userData) {

        // TEST DATA
        Map<String, String> user =
                TestDataUtils.generateUniqueUser(userData);

        // STEP 1 - Click Signup / Login
        accountFlow.openSignup();

        // STEP 2 - Fill all details in Signup and create account
        Assert.assertTrue(
                loginPage.isRegisterTitleDisplayed(),
                "'New User Signup!' section should be displayed.");

        accountFlow.startRegistration(user);

        Assert.assertTrue(
                loginPage.isAccountInformationDisplayed(),
                "'ENTER ACCOUNT INFORMATION' section should be displayed.");

        accountFlow.fillAccountInformation(user);

        accountFlow.submitRegistration();

        // STEP 3 - Verify ACCOUNT CREATED! and click Continue

        Assert.assertTrue(
                loginPage.isAccountCreatedMessageDisplayed(),
                "'ACCOUNT CREATED!' message should be displayed.");

        accountFlow.continueAfterRegistration();

        // STEP 4 - Verify Logged in as username
        Assert.assertTrue(
                homePage.isLoggedUserDisplayed(),
                "'Logged in as' label should be displayed.");

        Assert.assertEquals(
                homePage.getLoggedUser(),
                user.get("name"),
                "Incorrect logged user.");

        // STEP 5 - Add products to cart

        productFlow.openProductsPage();
        productFlow.addProductToCart(data.get("productName"));
        cartFlow.continueShopping();
        productFlow.addProductToCart(data.get("secondProductName"));

        // STEP 6 - Click Cart button
        productFlow.openCartPage();

        // STEP 7 - Verify that Cart page is displayed
        Assert.assertTrue(
                cartFlow.isCartDisplayed(),
                "Cart page should be displayed.");

        // STEP 8 - Click Proceed To Checkout
        cartFlow.proceedToCheckout();

        // STEP 9 - Verify Delivery Address
        Assert.assertTrue(
                checkoutFlow.isAddressDetailsDisplayed(),
                "Address Details section should be displayed.");

        Assert.assertTrue(
                checkoutFlow.isDeliveryAddressDisplayed(),
                "Delivery Address section should be displayed.");

        // Delivery Name

        Assert.assertEquals(
                checkoutFlow.getDeliveryName(),
                "Mrs. " + user.get("firstName") + " " + user.get("lastName"),
                "Delivery name does not match registration data.");

        // Delivery Company
        Assert.assertEquals(
                checkoutFlow.getDeliveryCompany(),
                user.get("company"),
                "Delivery company does not match registration data.");

        // Delivery Address
        Assert.assertEquals(
                checkoutFlow.getDeliveryAddress(),
                user.get("address"),
                "Delivery address does not match registration data.");

        // Delivery Address 2
        if (user.get("address2") != null
                && !user.get("address2").isBlank()) {

            Assert.assertEquals(
                    checkoutFlow.getDeliveryAddress2(),
                    user.get("address2"),
                    "Delivery address 2 does not match registration data.");
        }

        // Delivery City + State + Zip Code
        String expectedCityStateZip =
                user.get("city") + " "
                        + user.get("state") + " "
                        + user.get("zipCode");

        Assert.assertEquals(
                checkoutFlow.getDeliveryCityStateZip(),
                expectedCityStateZip,
                "Delivery city, state and zip code do not match registration data.");

        // Delivery Country
        Assert.assertEquals(
                checkoutFlow.getDeliveryCountry(),
                user.get("country"),
                "Delivery country does not match registration data.");

        // Delivery Phone
        Assert.assertEquals(
                checkoutFlow.getDeliveryPhone(),
                user.get("mobile"),
                "Delivery phone does not match registration data.");


        // STEP 10 - Verify Billing Address
        Assert.assertTrue(
                checkoutFlow.isBillingAddressDisplayed(),
                "Billing Address section should be displayed.");

        // Billing Name

        Assert.assertEquals(
                checkoutFlow.getBillingName(),
                "Mrs. " + user.get("firstName") + " " + user.get("lastName"),
                "Billing name does not match registration data.");

        // Billing Company
        Assert.assertEquals(
                checkoutFlow.getBillingCompany(),
                user.get("company"),
                "Billing company does not match registration data.");

        // Billing Address
        Assert.assertEquals(
                checkoutFlow.getBillingAddress(),
                user.get("address"),
                "Billing address does not match registration data.");

        // Billing Address 2
        if (user.get("address2") != null
                && !user.get("address2").isBlank()) {

            Assert.assertEquals(
                    checkoutFlow.getBillingAddress2(),
                    user.get("address2"),
                    "Billing address 2 does not match registration data.");
        }

        // Billing City + State + Zip Code
        Assert.assertEquals(
                checkoutFlow.getBillingCityStateZip(),
                expectedCityStateZip,
                "Billing city, state and zip code do not match registration data.");

        // Billing Country
        Assert.assertEquals(
                checkoutFlow.getBillingCountry(),
                user.get("country"),
                "Billing country does not match registration data.");

        // Billing Phone
        Assert.assertEquals(
                checkoutFlow.getBillingPhone(),
                user.get("mobile"),
                "Billing phone does not match registration data.");
    }


    // ============================================================
    // TC024 - Download Invoice after purchase order
    // ============================================================

    @Story("Register During Checkout and Download Invoice")
    @Severity(SeverityLevel.CRITICAL)
    @Description(
            "Verify that a new user can register during checkout, " +
                    "successfully place an order, download the invoice " +
                    "and continue after order completion."
    )
    @Test(
            description = "TC024 - Register During Checkout and Download Invoice",
            groups = {"regression"},
            dataProvider = "checkoutInvoiceData",
            dataProviderClass = TestDataProvider.class
    )
    public void registerDuringCheckoutAndDownloadInvoice(
            Map<String, String> checkoutData,
            Map<String, String> userData) {

        // TEST DATA: Generate a unique user so the test remains independent.
        Map<String, String> user =
                TestDataUtils.generateUniqueUser(userData);

        // PRECONDITION
        // Verify that Home page is displayed successfully.
        Assert.assertTrue(
                homePage.isHomeDisplayed(),
                "Home page should be displayed successfully.");

        // STEP 1 - Add products to cart
        productFlow.openProductsPage();
        productFlow.addProductToCart(checkoutData.get("productName"));
        cartFlow.continueShopping();
        productFlow.addProductToCart(checkoutData.get("secondProductName"));

        // STEP 2 - Click Cart button
        cartFlow.viewCart();

        // STEP 3 - Verify that Cart page is displayed
        Assert.assertTrue(
                cartFlow.isCartDisplayed(),
                "Cart page should be displayed.");

        // Verify first product is in Cart.
        Assert.assertTrue(
                cartFlow.isProductInCart(
                        checkoutData.get("productName")),
                "Product '" +
                        checkoutData.get("productName") +
                        "' should be displayed in Cart.");

        // Verify second product is in Cart.
        Assert.assertTrue(
                cartFlow.isProductInCart(
                        checkoutData.get("secondProductName")),
                "Product '" +
                        checkoutData.get("secondProductName") +
                        "' should be displayed in Cart.");

        // STEP 4 - Click Proceed To Checkout
        cartFlow.proceedToCheckout();

        // STEP 5 - Click Register / Login button
        accountFlow.openSignup();

        // STEP 6 - Fill all details in Signup and create account

        // Verify "New User Signup!" is displayed.
        Assert.assertTrue(
                loginPage.isRegisterTitleDisplayed(),
                "'New User Signup!' section is not displayed.");

        // Enter name and email and click Signup.
        accountFlow.startRegistration(user);

        // Verify "ENTER ACCOUNT INFORMATION" is displayed.
        Assert.assertTrue(
                loginPage.isAccountInformationDisplayed(),
                "'ENTER ACCOUNT INFORMATION' is not displayed.");

        // Fill account information.
        accountFlow.fillAccountInformation(user);

        // Click Create Account.
        accountFlow.submitRegistration();

        // STEP 7 - Verify ACCOUNT CREATED! and click Continue
        Assert.assertTrue(
                loginPage.isAccountCreatedMessageDisplayed(),
                "'ACCOUNT CREATED!' message is not displayed.");

        accountFlow.continueAfterRegistration();

        // STEP 8 - Verify Logged in as username
        Assert.assertTrue(
                homePage.isLoggedUserDisplayed(),
                "'Logged in as' label is not displayed.");

        Assert.assertEquals(
                homePage.getLoggedUser(),
                user.get("name"),
                "Incorrect logged user.");

        // STEP 9 - Click Cart button
        productFlow.openCartPage();

        // STEP 10 - Click Proceed To Checkout button
        cartFlow.proceedToCheckout();

        // STEP 11 - Verify Address Details and Review Your Order

        // Verify Address Details.
        Assert.assertTrue(
                checkoutFlow.isAddressDetailsDisplayed(),
                "Address Details should be displayed.");

        // Verify Billing Address section.
        Assert.assertTrue(
                checkoutFlow.isBillingAddressDisplayed(),
                "Billing Address should be displayed.");

        // Verify Review Your Order section.
        Assert.assertTrue(
                checkoutFlow.isOrderReviewDisplayed(),
                "Review Your Order section should be displayed.");

        // STEP 12 - Enter description and click Place Order

        checkoutFlow.enterComment(checkoutData.get("comment"));
        checkoutFlow.clickPlaceOrder();

        // STEP 13 and 14 - Enter payment details and Pay and Confirm Order
        checkoutFlow.completePayment(checkoutData);

        // STEP 15 - Verify success message
        Assert.assertTrue(
                checkoutFlow.isOrderPlacedSuccessfully(),
                "Order should be placed successfully.");

        // STEP 16 - Download Invoice and verify it was downloaded
        Assert.assertTrue(
                checkoutFlow.downloadInvoice(
                        "invoice.txt",
                        10),
                "Invoice should be downloaded successfully.");

        // STEP 17 - Click Continue
        checkoutFlow.continueAfterOrder();
    }
}

