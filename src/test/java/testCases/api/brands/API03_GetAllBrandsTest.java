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

import java.util.List;

public class API03_GetAllBrandsTest extends BaseApiTest {

    @Epic("E-Commerce API")
    @Feature("Brands")
    @Story("Get all brands.")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that the API returns the complete list of brands successfully.")

    @Test(groups = {"regression"})

    public void getAllBrandsList() {

        ApiClient apiClient = new ApiClient();

        // 1. Send GET request
        Response response = Allure.step(
                "Send GET request to all brands",
                () -> apiClient.get(ApiEndpoints.BRANDS_LIST)
        );

        // 2. Verify HTTP response status code

        Allure.step("Verify HTTP response status code is 200");

        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Expected HTTP status code 200.");

        // 3. Get brands list

        List<?> brands =
                response.jsonPath().getList("brands");

        // 4. Verify brands list exists

        Allure.step("Verify brands list is present");

        Assert.assertNotNull(
                brands,
                "Brands list should be present in response.");

        // 5. Verify brands list is not empty

        Allure.step("Verify brands list is not empty");

        Assert.assertFalse(
                brands.isEmpty(),
                "Brands list should not be empty.");
    }
}