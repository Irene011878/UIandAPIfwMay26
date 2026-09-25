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

public class API08_VerifyLoginWithoutEmailTest extends BaseApiTest {

    @Epic("E-Commerce API")
    @Feature("Authentication")
    @Story("Verify Login without Email")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that login is rejected when the email parameter is not provided.")

    @Test(
            groups = {"regression"},
            dataProvider = "apiVerifyLoginMissingEmailData",
            dataProviderClass = TestDataProvider.class
    )
    public void verifyLoginWithoutEmail(
            Map<String, String> data) {

        ApiClient apiClient = new ApiClient();

        // 1. Get password from Excel
        String password = data.get("password");

        // 2. Send POST request WITHOUT email parameter
        Response response = Allure.step(
                "Verify login without email parameter",
                () -> apiClient.post(
                        ApiEndpoints.VERIFY_LOGIN,
                        "password",
                        password));

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
        Allure.step("Verify API response code is 400");

        Assert.assertEquals(
                responseCode,
                400,
                "Expected API response code 400.");

        // 6. Get response message
        String responseMessage =
                response.jsonPath().getString("message");

        // 7. Verify response message
        Allure.step(
                "Verify response message indicates missing email or password");

        Assert.assertEquals(
                responseMessage,
                "Bad request, email or password parameter is missing in POST request.",
                "Unexpected API response message.");
    }
}
