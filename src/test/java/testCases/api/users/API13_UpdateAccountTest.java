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

public class API13_UpdateAccountTest extends BaseApiTest {

    @Epic("E-Commerce API")
    @Feature("Users")
    @Story("Verify Update Account")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that user account information can be updated successfully.")

    @Test(
            groups = {"regression"},
            dataProvider = "apiUpdateAccountData",
            dataProviderClass = TestDataProvider.class)

    public void updateAccount(Map<String, String> data) {

        ApiClient apiClient = new ApiClient();

        // 1. Generate unique user
        Map<String, String> user =
                TestDataUtils.generateUniqueUser(data);

        String email = user.get("email");
        String password = user.get("password");

        // CREATE ACCOUNT

        // 2. Create user account
        Response createResponse = Allure.step(
                "Create user account for update",
                () -> apiClient.post(
                        ApiEndpoints.CREATE_ACCOUNT,
                        user));

        // 3. Verify user was created successfully
        Allure.step("Verify user account was created successfully");

        Assert.assertEquals(
                createResponse.getStatusCode(),
                200,
                "Expected HTTP status code 200 when creating user.");

        Assert.assertEquals(
                createResponse.jsonPath().getInt("responseCode"),
                201,
                "Expected API response code 201 when creating user.");

        // UPDATE ACCOUNT

        // 4. Update user account
        Response updateResponse = Allure.step(
                "Update user account",
                () -> apiClient.put(
                        ApiEndpoints.UPDATE_ACCOUNT,
                        user));

        // 5. Verify HTTP response status code
        Allure.step("Verify update HTTP response status code is 200");

        Assert.assertEquals(
                updateResponse.getStatusCode(),
                200,
                "Expected HTTP status code 200.");

        // 6. Get API response code
        int responseCode =
                updateResponse.jsonPath().getInt("responseCode");

        // 7. Verify API response code
        Allure.step("Verify update API response code is 200");

        Assert.assertEquals(
                responseCode,
                200,
                "Expected API response code 200.");

        // 8. Get response message
        String responseMessage =
                updateResponse.jsonPath().getString("message");

        // 9. Verify response message
        Allure.step(
                "Verify response message indicates user was updated");

        Assert.assertEquals(
                responseMessage,
                "User updated!",
                "Unexpected API response message.");
    }
}
