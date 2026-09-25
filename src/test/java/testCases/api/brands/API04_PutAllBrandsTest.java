package testCases.api.brands;

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

public class API04_PutAllBrandsTest extends BaseApiTest {

    @Epic("E-Commerce API")
    @Feature("Brands")
    @Story("Put all brands.")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that the brands endpoint does not support PUT requests.")

    @Test(groups = {"regression"})

    public void putToAllBrandsList() {

        ApiClient apiClient = new ApiClient();

        // 1. Send PUT request
        Response response = Allure.step(
                "Send PUT request to all brands",
                () -> apiClient.put(ApiEndpoints.BRANDS_LIST));

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
                "Verify response message indicates PUT is not supported");

        Assert.assertEquals(
                message,
                "This request method is not supported.",
                "Response message should indicate that the PUT method is not supported.");
    }
}