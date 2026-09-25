package flows;

import io.qameta.allure.Step;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;

import java.util.Map;

/**
 * Business Flow for Account module.
 *
 * This class contains reusable business processes related to:
 * - User Registration
 * - Login
 * - Logout
 * - Delete Account
 *
 * NOTE:
 * This class MUST NOT contain assertions.
 * Assertions belong to the test classes.
 */

/*public class AccountFlow {

    private final HomePage homePage;
    private final LoginPage loginPage;

    public AccountFlow(WebDriver driver){

        this.homePage = new HomePage(driver);
        this.loginPage = new LoginPage(driver);
    }

    //NAVIGATION

    @Step("Open Signup page")
    public void openSignup(){
        homePage.clickSignLogin();
    }

    // Opens Login page: Same action as openSignup(), but improves test readability.
    @Step("Open Login page")
    public void openLogin(){
        homePage.clickSignLogin();
    }

    //REGISTRATION

    @Step("Starts user registration by entering: name and email and clicking Signup")
    public void startRegistration(Map<String, String> data){

        loginPage.enterSignupName(data.get("name"));
        loginPage.enterSignupEmail(data.get("email"));
        loginPage.clickSignupButton();

    }

    @Step("Completes Account Information form")
    public void fillAccountInformation(Map<String, String> data){

        loginPage.selectGender(data.get("gender"));
        loginPage.enterPassword(data.get("password"));
        loginPage.selectBirthDay(data.get("day"));
        loginPage.selectBirthMonth(data.get("month"));
        loginPage.selectBirthYear(data.get("year"));

        if (Boolean.parseBoolean(data.get("newsletter"))) {
            loginPage.selectNewsletter();
        }

        if (Boolean.parseBoolean(data.get("offers"))) {
            loginPage.selectSpecialOffers();
        }

        loginPage.enterFirstName(data.get("firstName"));
        loginPage.enterLastName(data.get("lastName"));
        loginPage.enterCompany(data.get("company"));
        loginPage.enterAddress(data.get("address"));
        loginPage.enterAddress2(data.get("address2"));
        loginPage.selectCountry(data.get("country"));
        loginPage.enterState(data.get("state"));
        loginPage.enterCity(data.get("city"));
        loginPage.enterZipCode(data.get("zipCode"));
        loginPage.enterMobileNumber(data.get("mobile"));

    }

    @Step("Clicks Create Account button")
    public void submitRegistration(){
        loginPage.clickCreateAccount();
    }

    @Step("Clicks Continue button after account creation")
    public void continueAfterRegistration(){

        loginPage.clickContinue();

        homePage.handleGoogleVignette();

    }*/

    /**
     * Registers a new user from start to finish.
     * This method encapsulates the complete registration business flow.
     */
    /*@Step("Register a new user")
    public void registerUser(Map<String, String> data) {

        openSignup();
        startRegistration(data);
        fillAccountInformation(data);
        submitRegistration();
        continueAfterRegistration();

    }

    //LOGIN
    @Step("Logs in using valid or invalid credentials")
    public void login(Map<String, String>data){

        loginPage.enterLoginEmail(data.get("email"));
        loginPage.enterLoginPassword(data.get("password"));
        loginPage.clickLoginButton();

    }

    //LOGOUT
    @Step("Logs out current user")
    public void logout(){
        homePage.clickLogOut();
    }

    //DELETE ACCOUNT
    @Step("Deletes current logged account")
    public void deleteAccount(){

        homePage.clickDeleteAccount();
    }

}*/

public class AccountFlow {

    private final HomePage homePage;
    private final LoginPage loginPage;

    public AccountFlow(WebDriver driver){

        this.homePage = new HomePage(driver);
        this.loginPage = new LoginPage(driver);
    }

    //NAVIGATION

    public void openSignup(){

        Allure.step("Open Signup page", () -> {
            homePage.clickSignLogin();
        });
    }

    // Opens Login page: Same action as openSignup(), but improves test readability.
    public void openLogin(){

        Allure.step("Open Login page", () -> {
            homePage.clickSignLogin();
        });
    }

    //REGISTRATION

    public void startRegistration(Map<String, String> data){

        Allure.step("Starts user registration by entering: name and email and clicking Signup", () -> {

            loginPage.enterSignupName(data.get("name"));
            loginPage.enterSignupEmail(data.get("email"));
            loginPage.clickSignupButton();

        });
    }

    public void fillAccountInformation(Map<String, String> data){

        Allure.step("Completes Account Information form", () -> {

            loginPage.selectGender(data.get("gender"));
            loginPage.enterPassword(data.get("password"));
            loginPage.selectBirthDay(data.get("day"));
            loginPage.selectBirthMonth(data.get("month"));
            loginPage.selectBirthYear(data.get("year"));

            if (Boolean.parseBoolean(data.get("newsletter"))) {
                loginPage.selectNewsletter();
            }

            if (Boolean.parseBoolean(data.get("offers"))) {
                loginPage.selectSpecialOffers();
            }

            loginPage.enterFirstName(data.get("firstName"));
            loginPage.enterLastName(data.get("lastName"));
            loginPage.enterCompany(data.get("company"));
            loginPage.enterAddress(data.get("address"));
            loginPage.enterAddress2(data.get("address2"));
            loginPage.selectCountry(data.get("country"));
            loginPage.enterState(data.get("state"));
            loginPage.enterCity(data.get("city"));
            loginPage.enterZipCode(data.get("zipCode"));
            loginPage.enterMobileNumber(data.get("mobile"));

        });
    }

    public void submitRegistration(){

        Allure.step("Clicks Create Account button", () -> {
            loginPage.clickCreateAccount();
        });
    }

    public void continueAfterRegistration(){

        Allure.step("Clicks Continue button after account creation", () -> {

            loginPage.clickContinue();
            homePage.handleGoogleVignette();

        });
    }

    /**
     * Registers a new user from start to finish.
     * This method encapsulates the complete registration business flow.
     */
    public void registerUser(Map<String, String> data){

        Allure.step("Register a new user", () -> {

            openSignup();
            startRegistration(data);
            fillAccountInformation(data);
            submitRegistration();
            continueAfterRegistration();

        });
    }

    //LOGIN

    public void login(Map<String, String> data){

        Allure.step("Logs in using valid or invalid credentials", () -> {

            loginPage.enterLoginEmail(data.get("email"));
            loginPage.enterLoginPassword(data.get("password"));
            loginPage.clickLoginButton();

        });
    }

    //LOGOUT

    public void logout(){

        Allure.step("Logs out current user", () -> {
            homePage.clickLogOut();
        });
    }

    //DELETE ACCOUNT

    public void deleteAccount(){

        Allure.step("Deletes current logged account", () -> {
            homePage.clickDeleteAccount();
        });
    }
}

