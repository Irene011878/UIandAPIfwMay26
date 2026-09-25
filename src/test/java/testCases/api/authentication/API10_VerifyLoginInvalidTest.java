package testCases.api.authentication;

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

public class API10_VerifyLoginInvalidTest extends BaseApiTest {

    @Epic("E-Commerce API")
    @Feature("Authentication")
    @Story("Verify Login Invalid")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that login is rejected when invalid credentials are provided.")

    @Test(
            groups = {"regression"},
            dataProvider = "apiVerifyLoginInvalidData",
            dataProviderClass = TestDataProvider.class
    )
    public void verifyLoginWithInvalidDetails(
            Map<String, String> data) {

        ApiClient apiClient = new ApiClient();

        // 1. Get invalid login credentials from Excel
        String email = data.get("email");
        String password = data.get("password");

        // 2. Send POST request with invalid credentials
        Response response = Allure.step(
                "Verify login with invalid credentials",
                () -> apiClient.post(
                        ApiEndpoints.VERIFY_LOGIN,
                        "email", email,
                        "password", password));

        // 3. Verify HTTP response status code
        Allure.step("Verify HTTP response status code is 200");

        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Expected HTTP status code 200.");

        // 4. Get API response code
        int responseCode =
                response.jsonPath().getInt("responseCode");

        // 5. Verify API response code
        Allure.step("Verify API response code is 404");

        Assert.assertEquals(
                responseCode,
                404,
                "Expected API response code 404.");

        // 6. Get response message
        String responseMessage =
                response.jsonPath().getString("message");

        // 7. Verify response message
        Allure.step(
                "Verify response message indicates user was not found");

        Assert.assertEquals(
                responseMessage,
                "User not found!",
                "Unexpected API response message.");
    }
}
