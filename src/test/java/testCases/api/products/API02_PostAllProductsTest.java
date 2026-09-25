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

public class API02_PostAllProductsTest extends BaseApiTest {

    @Epic("E-Commerce API")
    @Feature("Products")
    @Story("Post all products")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the products list endpoint does not support POST requests.")

    @Test(groups = {"regression"})

    public void postToAllProductsList() {

        ApiClient apiClient = new ApiClient();

        // 1. Send POST request
        Response response = Allure.step(
                "Send POST request to all products",
                () -> apiClient.post(ApiEndpoints.PRODUCTS_LIST));

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

        Allure.step("Verify API response code is 405");

        Assert.assertEquals(
                responseCode,
                405,
                "Expected API response code 405.");

        // 5. Get response message

        String message =
                response.jsonPath().getString("message");

        // 6. Verify response message

        Allure.step(
                "Verify response message indicates POST is not supported");

        Assert.assertEquals(
                message,
                "This request method is not supported.",
                "Response message should indicate that the POST method is not supported.");
    }
}
