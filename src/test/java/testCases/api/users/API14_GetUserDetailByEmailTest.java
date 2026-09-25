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

public class API14_GetUserDetailByEmailTest extends BaseApiTest {

    @Epic("E-Commerce API")
    @Feature("Users")
    @Story("Verify Get User Detail By Email")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that user details can be retrieved successfully using the email address.")

    @Test(
            groups = {"regression"},
            dataProvider = "apiGetUserDetailData",
            dataProviderClass = TestDataProvider.class)

    public void getUserDetailByEmail(Map<String, String> data) {

        ApiClient apiClient = new ApiClient();

        // 1. Generate unique user
        Map<String, String> user =
                TestDataUtils.generateUniqueUser(data);

        String email = user.get("email");

        // 2. Create user account
        Response createResponse = Allure.step(
                "Create user account for detail lookup",
                () -> apiClient.post(
                        ApiEndpoints.CREATE_ACCOUNT,
                        user
                ));

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

        // 4. Get user details by email
        Response response = Allure.step(
                "Get user details by email",
                () -> apiClient.get(
                        ApiEndpoints.USER_DETAIL_BY_EMAIL,
                        "email",
                        email));

        // 5. Verify HTTP response status code
        Allure.step("Verify HTTP response status code is 200");

        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Expected HTTP status code 200.");

        // 6. Get API response code
        int responseCode =
                response.jsonPath()
                        .getInt("responseCode");

        // 7. Verify API response code
        Allure.step("Verify API response code is 200");

        Assert.assertEquals(
                responseCode,
                200,
                "Expected API response code 200.");

        // 8. Get user details
        Object userDetail =
                response.jsonPath().get("user");

        // 9. Verify user details are present
        Allure.step("Verify user details are present in response");

        Assert.assertNotNull(
                userDetail,
                "User detail should be present in response.");
    }
}
