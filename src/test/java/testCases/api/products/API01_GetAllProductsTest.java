package testCases.api.products;

import io.restassured.response.Response;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;
import requests_api.ApiClient;
import requests_api.ApiEndpoints;
import setUp.BaseApiTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import java.util.List;

public class API01_GetAllProductsTest extends BaseApiTest {

    @Epic("E-Commerce API")
    @Feature("Products")
    @Story("Get all products")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the API returns the complete list of products successfully")

    @Test(groups = {"smoke", "regression"})

    public void getAllProductsList() {

        ApiClient apiClient = new ApiClient();

        // 1. Send GET request
        Response response = Allure.step(
                "Send GET request to all products",
                    () -> apiClient.get(ApiEndpoints.PRODUCTS_LIST)
        );

        // 2. Verify response status code
        //Allure.step("Verify response status code is 200");
        Allure.step("Response status code: " + response.getStatusCode());

        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Expected HTTP status code 200.");

        // 3. Get products list from JSON response
        List<?> products =
                response.jsonPath()
                        .getList("products");

        // 4. Verify products list exists
        Allure.step("Verify products list is present");

        Assert.assertNotNull(
                products,
                "Products list should be present in response.");

        // 5. Verify products list is not empty
        Allure.step("Verify products list is not empty");

        Assert.assertFalse(
                products.isEmpty(),
                "Products list should not be empty.");
    }
}
