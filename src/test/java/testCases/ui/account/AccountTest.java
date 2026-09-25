package testCases.ui.account;

import flows.AccountFlow;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DeletePage;
import pages.HomePage;
import pages.LoginPage;
import setUp.BaseTest;
import utilities.dataProvider.TestDataProvider;
import utilities.uniqueMails.TestDataUtils;

import java.util.Map;

@Epic("Account")
@Feature("Registration")
public class AccountTest extends BaseTest {

    private HomePage homePage;
    private LoginPage loginPage;
    private AccountFlow accountFlow;
    private DeletePage deletePage;

    @BeforeMethod(alwaysRun = true)
    public void initializePages() {

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        accountFlow = new AccountFlow(driver);
        deletePage = new DeletePage(driver);

    }

    //=========================================================
    // TC001 - REGISTER USER
    //=========================================================

    @Story("Register User")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a new user can register successfully")

    @Test(
            description = "TC001 - Register User",
            groups = {"smoke", "regression"},
            dataProvider = "registerUserData",
            dataProviderClass = TestDataProvider.class)

    public void registerUser(Map<String, String> data) {

        // Test Data
        Map<String, String> user =
                TestDataUtils.generateUniqueUser(data);

        // Step 1: Click Signup / Login
        accountFlow.openSignup();

        //Step 2:Verify 'New User Signup!' is visible

        Assert.assertTrue(
                loginPage.isRegisterTitleDisplayed(),
                "'New User Signup!' section is not displayed");

        //Step 3 & 4: Enter name, email and click Signup
        accountFlow.startRegistration(user);

        //Step 5: Verify 'ENTER ACCOUNT INFORMATION' is visible
        Assert.assertTrue(
                loginPage.isAccountInformationDisplayed(),
                "'ENTER ACCOUNT INFORMATION' is not displayed");

        //Step 6 - 9: Fill Account Information
        accountFlow.fillAccountInformation(user);

        //Step 10: Click Create Account
        accountFlow.submitRegistration();

        //Step 11: Verify ACCOUNT CREATED!
        Assert.assertTrue(
                loginPage.isAccountCreatedMessageDisplayed(),
                "'ACCOUNT CREATED!' message is not displayed");

        // Step 12: Click Continue
        accountFlow.continueAfterRegistration();

        //System.out.println("URL = " + driver.getCurrentUrl());
        //System.out.println("TITLE = " + driver.getTitle());


        // Step 13: Verify Logged in as username
        Assert.assertTrue(
                homePage.isLoggedUserDisplayed(),
                "'Logged in as' label is not displayed");

        Assert.assertEquals(
                homePage.getLoggedUser(),
                user.get("name"),
                "Incorrect logged user");

    }

    //=========================================================
    // TC002 - LOGIN USER WITH VALID CREDENTIALS
    //=========================================================

    @Story("Login User")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a registered user can login successfully")

    @Test(
            description = "TC002 - Login User with valid credentials",
            groups = {"smoke", "regression"},
            dataProvider = "loginValidData",
            dataProviderClass = TestDataProvider.class
    )

    public void loginUserWithValidCredentials(Map<String, String> data) {

        //Test Data
        Map<String, String> user =
                TestDataUtils.generateUniqueUser(data);


        //Preconditions:
        // Register a new user to keep the test independent
        accountFlow.registerUser(user);

        // Logout to return to Login page
        accountFlow.logout();

        // Step 1: Click Login button
        accountFlow.openLogin();

        //Step 2: Verify 'Login to your account' is visible
        Assert.assertTrue(
                loginPage.isLoginTitleDisplayed(),
                "'Login to your account' title is not displayed");

        //Step 3 & 4: Enter email, password and click Login
        accountFlow.login(user);

        //Step 5: Verify Logged in as username
        Assert.assertTrue(
                homePage.isLoggedUserDisplayed(),
                "'Logged in as' label is not displayed");

        Assert.assertEquals(
                homePage.getLoggedUser(),
                user.get("name"),
                "Incorrect logged user");

    }

    //=========================================================
    // TC003 - LOGIN USER WITH INVALID CREDENTIALS
    //=========================================================

    @Story("Login User")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that login fails with incorrect email and password")

    @Test(
            description = "TC003 - Login User with incorrect email and password",
            groups = {"regression", "negative"},
            dataProvider = "loginInvalidData",
            dataProviderClass = TestDataProvider.class
    )

    public void loginUserWithInvalidCredentials(
            Map<String, String> data) {

        //Step 1: Click Login button
        accountFlow.openLogin();

        //Step 2: Verify 'Login to your account' is visible
        Assert.assertTrue(
                loginPage.isLoginTitleDisplayed(),
                "'Login to your account' title is not displayed");

        //Step 3 & 4: Enter invalid credentials and click Login
        accountFlow.login(data);

        //Step 5: Verify error message
        Assert.assertTrue(
                loginPage.isInvalidLoginMessageDisplayed(),
                "'Your email or password is incorrect!' message is not displayed");

    }

    //=========================================================
    // TC004 - LOGOUT USER
    //=========================================================

    @Story("Logout User")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that a logged user can logout successfully")


    @Test(
            description = "TC004 - Logout User",
            groups = {"regression"},
            dataProvider = "loginValidData",
            dataProviderClass = TestDataProvider.class
    )
    public void logoutUser(Map<String, String> data) {

        // Test Data
        Map<String, String> user =
                TestDataUtils.generateUniqueUser(data);

        //Precondition
        accountFlow.registerUser(user);

        //Step 1: Click Login button
        accountFlow.logout();
        accountFlow.openLogin();

        //Step 2: Verify Login page is displayed
        Assert.assertTrue(
                loginPage.isLoginTitleDisplayed(),
                "'Login to your account' title is not displayed.");

        //Step 3 & 4: Enter valid credentials and click Login
        accountFlow.login(user);

        //Step 5: Verify Logged in as username
        Assert.assertTrue(
                homePage.isLoggedUserDisplayed(),
                "'Logged in as' label is not displayed.");

        Assert.assertEquals(
                homePage.getLoggedUser(),
                user.get("name"),
                "Incorrect logged user.");

        //Step 6: Click Logout
        accountFlow.logout();

        //Step 7: Verify Login page is displayed
        Assert.assertTrue(
                loginPage.isLoginTitleDisplayed(),
                "User was not redirected to Login page after logout.");

    }

    //=========================================================
    // TC005 - REGISTER USER WITH EXISTING EMAIL
    //=========================================================

    @Story("Register User")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that a user cannot register with an existing email")


    @Test(
            description = "TC005 - Register User with Existing Email",
            groups = {"regression", "negative"},
            dataProvider = "registerUserData",
            dataProviderClass = TestDataProvider.class
    )

    public void registerUserWithExistingEmail(
            Map<String, String> data) {

        //Precondition
        //Test Data

        Map<String, String> user =
                TestDataUtils.generateUniqueUser(data);

        accountFlow.registerUser(user);

        accountFlow.logout();

        //Step 1: Click Signup/Login button
        accountFlow.openSignup();

        //Step 2: Verify 'New User Signup!' is visible
        Assert.assertTrue(
                loginPage.isRegisterTitleDisplayed(),
                "'New User Signup!' title is not displayed.");

        //Step 3 & 4: Enter existing name and email
        accountFlow.startRegistration(user);

        //Step 5: Verify existing email message
        Assert.assertTrue(
                loginPage.isExistingEmailMessageDisplayed(),
                "'Email Address already exist!' message is not displayed.");

    }

    //=========================================================
    // TC027 - DELETE ACCOUNT
    //=========================================================

    @Story("Delete Account")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a logged user can delete the account successfully")

    @Test(
            description = "TC027 - Delete Account",
            groups = {"smoke", "regression"},
            dataProvider = "deleteAccountData",
            dataProviderClass = TestDataProvider.class
    )

    public void deleteAccount(
            Map<String, String> data) {

        //Precondition
        //Test Data: Create a unique user
        Map<String, String> user =
                TestDataUtils.generateUniqueUser(data);

        accountFlow.registerUser(user);

        accountFlow.logout();

        //Step 1: Click on 'Signup / Login'
        accountFlow.openLogin();

        //Step 2: Verify 'Login to your account' is visible
        Assert.assertTrue(
                loginPage.isLoginTitleDisplayed(),
                "'Login to your account' title is not displayed.");

        //Step 3 & 4:Enter credentials and click Login
        accountFlow.login(user);

        //Step 5: Verify 'Logged in as username'
        Assert.assertTrue(
                homePage.isLoggedUserDisplayed(),
                "Logged user label is not displayed."
        );

        Assert.assertEquals(
                homePage.getLoggedUser(),
                user.get("name"),
                "Incorrect logged user.");

        //Step 6: Click Delete Account
        accountFlow.deleteAccount();

        //Step 7: Verify ACCOUNT DELETED!
        Assert.assertTrue(
                deletePage.isDeletedUserMessageDisplayed(),
                "'ACCOUNT DELETED!' message is not displayed.");

        Assert.assertEquals(
                deletePage.getDeletedUserMessage(),
                "ACCOUNT DELETED!",
                "Incorrect delete account message.");

    }

}
