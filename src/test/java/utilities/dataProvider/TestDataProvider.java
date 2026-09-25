package utilities.dataProvider;

import org.testng.annotations.DataProvider;
import utilities.excelReader.ExcelReader;

import java.util.Map;


public final class TestDataProvider {

    // SHEETS
    private static final String USERS_SHEET = "Users";
    private static final String CHECKOUT_SHEET = "Checkout";
    private static final String SUBSCRIPTION_SHEET = "Subscription";
    private static final String CONTACT_US_SHEET = "ContactUs";
    private static final String PRODUCTS_SHEET = "Products";
    private static final String REVIEW_SHEET = "Review";
    private static final String API_SHEET = "API";


    // TEST CASES
    private static final String TC_REGISTER_USER = "TC001";
    private static final String TC_LOGIN_VALID = "TC002";
    private static final String TC_LOGIN_INVALID = "TC003";
    private static final String TC_DELETE_ACCOUNT = "TC027";
    private static final String TC_VERIFY_PRODUCTS = "TC008";
    private static final String TC_SEARCH_PRODUCT = "TC009";
    private static final String TC_VIEW_CATEGORY = "TC018";
    private static final String TC_VIEW_BRAND = "TC019";
    private static final String TC_SEARCH_CART_LOGIN = "TC020";
    private static final String TC_ADD_REVIEW = "TC021";
    private static final String TC_RECOMMENDED_ITEMS = "TC022";
    private static final String TC_CART_ADD_PRODUCTS = "TC012";
    private static final String TC_CART_QUANTITY = "TC013";
    private static final String TC_CART_REMOVE_PRODUCT = "TC017";
    private static final String TC_CHECKOUT_REGISTER_DURING = "TC014";
    private static final String TC_CHECKOUT_REGISTER_BEFORE = "TC015";
    private static final String TC_CHECKOUT_LOGIN_BEFORE = "TC016";
    private static final String TC_CHECKOUT_ADDRESS = "TC023";
    private static final String TC_CHECKOUT_INVOICE = "TC024";
    private static final String TC_SUBSCRIPTION_HOME = "TC010";
    private static final String TC_SUBSCRIPTION_CART = "TC011";
    private static final String API_SEARCH_PRODUCT = "API05";
    private static final String API_SEARCH_PRODUCT_MISSING = "API06";
    private static final String API_VERIFY_LOGIN = "API07";
    private static final String API_VERIFY_LOGIN_MISSING_EMAIL = "API08";
    private static final String API_VERIFY_LOGIN_INVALID = "API10";
    private static final String API_CREATE_ACCOUNT = "API11";
    private static final String API_DELETE_ACCOUNT = "API12";
    private static final String API_UPDATE_ACCOUNT = "API13";
    private static final String API_GET_USER_DETAIL = "API14";


    @DataProvider(name = "registerUserData")
    public Object[][] registerUserData() {
        return ExcelReader.getTestDataAsMap(
                USERS_SHEET,
                TC_REGISTER_USER
        );
    }

    @DataProvider(name = "loginValidData")
    public Object[][] loginValidData() {
        return ExcelReader.getTestDataAsMap(
                USERS_SHEET,
                TC_LOGIN_VALID
        );
    }

    @DataProvider(name = "loginInvalidData")
    public Object[][] loginInvalidData() {
        return ExcelReader.getTestDataAsMap(
                USERS_SHEET,
                TC_LOGIN_INVALID
        );
    }

    @DataProvider(name = "deleteAccountData")
    public Object[][] deleteAccountData() {
        return ExcelReader.getTestDataAsMap(
                USERS_SHEET,
                TC_DELETE_ACCOUNT
        );
    }

    @DataProvider(name = "checkoutUsers")
    public Object[][] checkoutUsers() {
        return ExcelReader.getSheetDataAsMap(
                CHECKOUT_SHEET
        );
    }

    @DataProvider(name = "subscriptionHomeData")
    public Object[][] subscriptionHomeData() {

        return ExcelReader.getTestDataAsMap(
                SUBSCRIPTION_SHEET,
                TC_SUBSCRIPTION_HOME
        );
    }

    @DataProvider(name = "subscriptionCartData")
    public Object[][] subscriptionCartData() {

        return ExcelReader.getTestDataAsMap(
                SUBSCRIPTION_SHEET,
                TC_SUBSCRIPTION_CART
        );
    }

    @DataProvider(name = "contactUsers")
    public Object[][] contactUsers() {
        return ExcelReader.getSheetDataAsMap(
                CONTACT_US_SHEET
        );
    }

    @DataProvider(name = "verifyProductsData")
    public Object[][] verifyProductsData() {
        return ExcelReader.getTestDataAsMap(
                PRODUCTS_SHEET,
                TC_VERIFY_PRODUCTS
        );
    }

    @DataProvider(name = "searchProductsData")
    public Object[][] searchProductsData() {
        return ExcelReader.getTestDataAsMap(
                PRODUCTS_SHEET,
                TC_SEARCH_PRODUCT
        );
    }

    @DataProvider(name = "apiSearchProductMissingData")
    public Object[][] apiSearchProductMissingData() {

        return ExcelReader.getTestDataAsMapMultipleRows(
                API_SHEET,
                API_SEARCH_PRODUCT_MISSING
        );
    }

    @DataProvider(name = "apiVerifyLoginData")
    public Object[][] apiVerifyLoginData() {

        return ExcelReader.getTestDataAsMapMultipleRows(
                API_SHEET,
                API_VERIFY_LOGIN
        );
    }

    @DataProvider(name = "apiVerifyLoginMissingEmailData")
    public Object[][] apiVerifyLoginMissingEmailData() {

        return ExcelReader.getTestDataAsMapMultipleRows(
                API_SHEET,
                API_VERIFY_LOGIN_MISSING_EMAIL
        );
    }

    @DataProvider(name = "apiVerifyLoginInvalidData")
    public Object[][] apiVerifyLoginInvalidData() {

        return ExcelReader.getTestDataAsMapMultipleRows(
                API_SHEET,
                API_VERIFY_LOGIN_INVALID
        );
    }

    @DataProvider(name = "apiCreateAccountData")
    public Object[][] apiCreateAccountData() {

        return ExcelReader.getTestDataAsMapMultipleRows(
                API_SHEET,
                API_CREATE_ACCOUNT
        );
    }

    @DataProvider(name = "apiDeleteAccountData")
    public Object[][] apiDeleteAccountData() {

        return ExcelReader.getTestDataAsMapMultipleRows(
                API_SHEET,
                API_DELETE_ACCOUNT
        );
    }

    @DataProvider(name = "apiUpdateAccountData")
    public Object[][] apiUpdateAccountData() {

        return ExcelReader.getTestDataAsMapMultipleRows(
                API_SHEET,
                API_UPDATE_ACCOUNT
        );
    }

    @DataProvider(name = "apiGetUserDetailData")
    public Object[][] apiGetUserDetailData() {

        return ExcelReader.getTestDataAsMapMultipleRows(
                API_SHEET,
                API_GET_USER_DETAIL
        );
    }

    @DataProvider(name = "categoryProductsData")
    public Object[][] categoryProductsData() {
        return ExcelReader.getTestDataAsMap(
                PRODUCTS_SHEET,
                TC_VIEW_CATEGORY
        );
    }

    @DataProvider(name = "brandProductsData")
    public Object[][] brandProductsData() {
        return ExcelReader.getTestDataAsMap(
                PRODUCTS_SHEET,
                TC_VIEW_BRAND
        );
    }

    @DataProvider(name = "searchCartLoginData")
    public Object[][] searchCartLoginData() {
        return ExcelReader.getTestDataAsMap(
                PRODUCTS_SHEET,
                TC_SEARCH_CART_LOGIN
        );
    }

    @DataProvider(name = "recommendedProductsData")
    public Object[][] recommendedProductsData() {
        return ExcelReader.getTestDataAsMap(
                PRODUCTS_SHEET,
                TC_RECOMMENDED_ITEMS
        );
    }

    @DataProvider(name = "reviewProductsData")
    public Object[][] reviewData() {
        return ExcelReader.getTestDataAsMap(
                REVIEW_SHEET,
                TC_ADD_REVIEW
        );
    }

    @DataProvider(name = "apiSearchProductData")
    public Object[][] apiSearchProductData() {

        return ExcelReader.getTestDataAsMapMultipleRows(
                API_SHEET,
                API_SEARCH_PRODUCT
        );
    }

    @DataProvider(name = "cartAddProductsData")
    public Object[][] cartAddProductsData() {
        return ExcelReader.getTestDataAsMap(
                PRODUCTS_SHEET,
                TC_CART_ADD_PRODUCTS
        );
    }

    @DataProvider(name = "cartQuantityData")
    public Object[][] cartQuantityData() {
        return ExcelReader.getTestDataAsMap(
                PRODUCTS_SHEET,
                TC_CART_QUANTITY
        );
    }

    @DataProvider(name = "cartRemoveProductData")
    public Object[][] cartRemoveProductData() {
        return ExcelReader.getTestDataAsMap(
                PRODUCTS_SHEET,
                TC_CART_REMOVE_PRODUCT
        );
    }


    @DataProvider(name = "checkoutRegisterDuringData")
    public Object[][] checkoutRegisterDuringData() {

        Object[][] checkoutData =
                ExcelReader.getTestDataAsMap(
                        CHECKOUT_SHEET,
                        TC_CHECKOUT_REGISTER_DURING
                );

        Object[][] userData =
                ExcelReader.getTestDataAsMap(
                        USERS_SHEET,
                        TC_REGISTER_USER
                );

        return new Object[][]{
                {
                        checkoutData[0][0],
                        userData[0][0]
                }
        };
    }

    @DataProvider(name = "checkoutRegisterBeforeData")
    public Object[][] checkoutRegisterBeforeData() {

        Map<String, String> checkoutData =
                ExcelReader.getTestData(
                        CHECKOUT_SHEET,
                        TC_CHECKOUT_REGISTER_BEFORE
                );

        Map<String, String> userData =
                ExcelReader.getTestData(
                        USERS_SHEET,
                        TC_REGISTER_USER
                );

        return new Object[][]{
                {checkoutData, userData}
        };
    }

    @DataProvider(name = "checkoutLoginBeforeData")
    public Object[][] checkoutLoginBeforeData() {

        Map<String, String> checkoutData =
                ExcelReader.getTestData(
                        CHECKOUT_SHEET,
                        TC_CHECKOUT_LOGIN_BEFORE
                );

        Map<String, String> userData =
                ExcelReader.getTestData(
                        USERS_SHEET,
                        TC_LOGIN_VALID
                );

        return new Object[][]{
                {checkoutData, userData}
        };
    }

    @DataProvider(name = "checkoutAddressData")
    public Object[][] checkoutAddressData() {

        Map<String, String> checkoutData =
                ExcelReader.getTestData(
                        CHECKOUT_SHEET,
                        TC_CHECKOUT_ADDRESS
                );

        Map<String, String> userData =
                ExcelReader.getTestData(
                        USERS_SHEET,
                        TC_REGISTER_USER
                );

        return new Object[][]{
                {checkoutData, userData}
        };
    }

    @DataProvider(name = "checkoutInvoiceData")
    public Object[][] checkoutInvoiceData() {

        Map<String, String> checkoutData =
                ExcelReader.getTestData(
                        CHECKOUT_SHEET,
                        TC_CHECKOUT_INVOICE
                );

        Map<String, String> userData =
                ExcelReader.getTestData(
                        USERS_SHEET,
                        TC_REGISTER_USER
                );

        return new Object[][]{
                {checkoutData, userData}
        };
    }

}









