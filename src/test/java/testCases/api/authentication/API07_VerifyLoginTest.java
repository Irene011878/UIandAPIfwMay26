package testCases.api.authentication;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import requests_api.ApiClient;
import requests_api.ApiEndpoints;
import setUp.BaseApiTest;
import utilities.dataProvider.TestDataProvider;

import java.util.Map;

public class API07_VerifyLoginTest extends BaseApiTest {

    @Epic("E-Commerce API")
    @Feature("Authentication")
    @Story("Verify Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a valid user can authenticate successfully through the API.")

    @Test(
            groups = {"smoke", "regression"},
            dataProvider = "apiVerifyLoginData",
            dataProviderClass = TestDataProvider.class
    )
    public void verifyLogin(Map<String, String> data) {

        ApiClient apiClient = new ApiClient();

        // STEP 1 - READ LOGIN CREDENTIALS

        String email = Allure.step(
                "Read login email from test data",
                () -> data.get("email")
        );

        String password = Allure.step(
                "Read login password from test data",
                () -> data.get("password")
        );

        // STEP 2 - SEND LOGIN REQUEST

        Response response = Allure.step(
                "Send POST request to verify login",
                () -> apiClient.post(
                        ApiEndpoints.VERIFY_LOGIN,
                        "email", email,
                        "password", password)
        );

        // STEP 3 - VALIDATE HTTP STATUS CODE

        Allure.step(
                "Validate HTTP response status code is 200",
                () -> Assert.assertEquals(
                        response.getStatusCode(),
                        200,
                        "Expected HTTP status code 200.")
        );

        // STEP 4 - VALIDATE API RESPONSE CODE

        Allure.step(
                "Validate API response code is 200",
                () -> {

                    int responseCode =
                            response.jsonPath().getInt("responseCode");

                    Assert.assertEquals(
                            responseCode,
                            200,
                            "Expected API response code 200.");
                }
        );

        // STEP 5 - VALIDATE RESPONSE MESSAGE

        Allure.step(
                "Validate API response message is 'User exists!'",
                () -> {

                    String responseMessage =
                            response.jsonPath().getString("message");

                    Assert.assertEquals(
                            responseMessage,
                            "User exists!",
                            "Unexpected API response message.");
                }
        );
    }
}
