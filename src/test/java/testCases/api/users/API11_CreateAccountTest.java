package testCases.api.users;

import io.restassured.response.Response;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;
import requests_api.ApiClient;
import requests_api.ApiEndpoints;
import setUp.BaseApiTest;
import utilities.dataProvider.TestDataProvider;
import utilities.uniqueMails.TestDataUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import java.util.Map;

public class API11_CreateAccountTest extends BaseApiTest {

    @Epic("E-Commerce API")
    @Feature("Users")
    @Story("Verify Create Account")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a new user account can be created successfully.")

    @Test(
            groups = {"smoke", "regression"},
            dataProvider = "apiCreateAccountData",
            dataProviderClass = TestDataProvider.class)

    public void createAccount(Map<String, String> data) {

        ApiClient apiClient = new ApiClient();

        // 1. Generate unique user data
        Map<String, String> user =
                TestDataUtils.generateUniqueUser(data);

        String email =
                user.get("email");

        // 2. Send POST request to create account
        Response response = Allure.step(
                "Create new user account",
                () -> apiClient.post(
                        ApiEndpoints.CREATE_ACCOUNT, user));

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
        Allure.step("Verify API response code is 201");

        Assert.assertEquals(
                responseCode,
                201,
                "Expected API response code 201.");

        // 6. Get response message
        String responseMessage =
                response.jsonPath().getString("message");

        // 7. Verify response message
        Allure.step(
                "Verify response message indicates user was created");

        Assert.assertEquals(
                responseMessage,
                "User created!",
                "Unexpected API response message.");
    }
}


