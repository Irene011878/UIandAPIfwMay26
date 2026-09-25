package testCases.api.products;

import io.restassured.response.Response;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;
import requests_api.ApiClient;
import requests_api.ApiEndpoints;
import setUp.BaseApiTest;
import utilities.dataProvider.TestDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import java.util.Map;

public class API06_SearchProductWithoutParameterTest extends BaseApiTest {

    @Epic("E-Commerce API")
    @Feature("Products")
    @Story("Search product without parameters.")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the API returns an appropriate error message when the search parameter is missing.")

    @Test(
            groups = {"regression"},
            dataProvider = "apiSearchProductMissingData",
            dataProviderClass = TestDataProvider.class
    )
    public void searchProductWithoutParameter(
            Map<String, String> data) {

        ApiClient apiClient = new ApiClient();

        // 1. Send POST request WITHOUT search_product parameter
        Response response = Allure.step(
                "Search product without search parameter",
                () -> apiClient.post(ApiEndpoints.SEARCH_PRODUCT)
        );

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

        Allure.step("Verify API response code is 400");

        Assert.assertEquals(
                responseCode,
                400,
                "Expected API response code 400.");

        // 5. Get response message

        String responseMessage =
                response.jsonPath().getString("message");

        // 6. Verify response message

        Allure.step(
                "Verify response message indicates missing search parameter");

        Assert.assertEquals(
                responseMessage,
                "Bad request, search_product parameter is missing in POST request.",
                "Unexpected API response message.");
    }
}