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

public class API12_DeleteAccountTest extends BaseApiTest {

    @Epic("E-Commerce API")
    @Feature("Users")
    @Story("Verify Delete Account")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that an existing user account can be deleted successfully.")

    @Test(
            groups = {"regression"},
            dataProvider = "apiDeleteAccountData",
            dataProviderClass = TestDataProvider.class)

    public void deleteAccount(Map<String, String> data) {

        ApiClient apiClient = new ApiClient();

        // 1. Generate unique user
        Map<String, String> user =
                TestDataUtils.generateUniqueUser(data);

        String email = user.get("email");
        String password = user.get("password");

        // CREATE ACCOUNT
        // 2. Create user account
        Response createResponse = Allure.step(
                "Create user account for deletion",
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

        // DELETE ACCOUNT

        Map<String, String> deleteData =
                new java.util.HashMap<>();

        deleteData.put("email", email);
        deleteData.put("password", password);

        // 4. Delete user account
        Response deleteResponse = Allure.step(
                "Delete user account",
                () -> apiClient.delete(
                        ApiEndpoints.DELETE_ACCOUNT,
                        deleteData));

        // 5. Verify HTTP response status code
        Allure.step("Verify delete HTTP response status code is 200");

        Assert.assertEquals(
                deleteResponse.getStatusCode(),
                200,
                "Expected HTTP status code 200.");

        // 6. Get API response code
        int responseCode =
                deleteResponse.jsonPath().getInt("responseCode");

        // 7. Verify API response code
        Allure.step("Verify delete API response code is 200");

        Assert.assertEquals(
                responseCode,
                200,
                "Expected API response code 200.");

        // 8. Get response message
        String responseMessage =
                deleteResponse.jsonPath()
                        .getString("message");

        // 9. Verify response message
        Allure.step(
                "Verify response message indicates account was deleted");

        Assert.assertEquals(
                responseMessage,
                "Account deleted!",
                "Unexpected API response message.");
    }
}
