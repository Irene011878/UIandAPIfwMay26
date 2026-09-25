package testCases.api.products;

import io.restassured.response.Response;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;
import requests_api.ApiClient;
import requests_api.ApiEndpoints;
import setUp.BaseApiTest;
import utilities.dataProvider.TestDataProvider;

import java.util.List;
import java.util.Map;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class API05_SearchProductTest extends BaseApiTest {

    @Epic("E-Commerce API")
    @Feature("Products")
    @Story("Search product")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that products can be searched successfully using a valid search parameter.")

    @Test(
            groups = {"smoke", "regression"},
            dataProvider = "apiSearchProductData",
            dataProviderClass = TestDataProvider.class
    )
    public void searchProduct(Map<String, String> data) {

        ApiClient apiClient = new ApiClient();

        // Search parameter from Excel
        String searchProduct =
                data.get("searchProduct");

        // 1. Send POST request
        Response response = Allure.step(
                "Search product: " + searchProduct,
                () -> apiClient.post(
                        ApiEndpoints.SEARCH_PRODUCT,
                        "search_product",
                        searchProduct));


        // 2. Verify HTTP response status code

        Allure.step("Verify HTTP response status code is 200");

        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Expected HTTP status code 200.");

        // 3. Get API response code

        int responseCode =
                response.jsonPath().getInt("responseCode");

        // 4. Verify API response code

        Allure.step("Verify API response code is 200");

        Assert.assertEquals(
                responseCode,
                200,
                "Expected API response code 200.");

        // 5. Get products list

        List<?> products =
                response.jsonPath().getList("products");

        // 6. Verify products list is present

        Allure.step("Verify products list is present");

        Assert.assertNotNull(
                products,
                "Products list should be present in response.");

        // 7. Verify products list is not empty

        Allure.step("Verify products list is not empty");

        Assert.assertFalse(
                products.isEmpty(),
                "Products list should not be empty.");
    }
}