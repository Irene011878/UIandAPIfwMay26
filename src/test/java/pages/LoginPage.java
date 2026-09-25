package pages;

import maps.LoginMap;
import org.openqa.selenium.WebDriver;
import utilities.commonMethods.CommonMethods;

public class LoginPage extends CommonMethods {

    private final LoginMap loginMap;

    public LoginPage(WebDriver driver) {

        super(driver);

        loginMap = new LoginMap();
    }

    //VALIDATIONS
    public boolean isRegisterTitleDisplayed() {
        return isDisplayed(loginMap.registerTitle);
    }

    public boolean isAccountCreatedMessageDisplayed() {
        return isDisplayed(loginMap.acctCreatedMessage);
    }

    public String getAccountCreatedMessage() {
        return getText(loginMap.acctCreatedMessage);
    }

    public boolean isLoginTitleDisplayed() {
        return isDisplayed(loginMap.loginTitle);
    }

    public boolean isAccountInformationDisplayed() {
        return isDisplayed(loginMap.accountInformationTitle);
    }

    public boolean isInvalidLoginMessageDisplayed() {
        return isDisplayed(loginMap.invalidLoginMessage);
    }

    public boolean isExistingEmailMessageDisplayed() {
        return isDisplayed(loginMap.existingEmailMessage);
    }

    // LOGIN
    public void enterLoginEmail(String email) {
        type(loginMap.loginEmail, email);
    }

    public void enterLoginPassword(String password) {
        type(loginMap.loginPassword, password);
    }

    public void clickLoginButton() {
        click(loginMap.loginButton);
    }

    //SIGN UP
    public void enterSignupName(String name) {
        type(loginMap.signUpName, name);
    }

    public void enterSignupEmail(String email) {
        type(loginMap.signupEmail, email);
    }

    public void clickSignupButton() {
        click(loginMap.signUpBtn);
    }

    public void selectGender(String gender) {

        switch (gender.toLowerCase()) {

            case "mr":
                click(loginMap.mrGender);
                break;

            case "mrs":
                click(loginMap.mrsGender);
                break;

            default:
                throw new IllegalArgumentException(
                        "Invalid gender: " + gender);
        }

    }

    public void enterPassword(String password) {
        type(loginMap.password, password);
    }

    public void selectBirthDay(String day) {
        selectByText(loginMap.day, day);
    }

    public void selectBirthMonth(String month) {
        selectByText(loginMap.month, month);
    }

    public void selectBirthYear(String year) {
        selectByText(loginMap.year, year);
    }

    public void selectNewsletter() {
        click(loginMap.newsLetter);
    }

    public void selectSpecialOffers() {
        click(loginMap.specialOffers);
    }

    public void enterFirstName(String firstName) {
        type(loginMap.firstName, firstName);
    }

    public void enterLastName(String lastName) {
        type(loginMap.lastName, lastName);
    }

    public void enterCompany(String company) {
        type(loginMap.company, company);
    }

    public void enterAddress(String address) {
        type(loginMap.address1, address);
    }

    public void enterAddress2(String address2) {
        type(loginMap.address2, address2);
    }

    public void selectCountry(String country) {
        selectByText(loginMap.country, country);
    }

    public void enterState(String state) {
        type(loginMap.state, state);
    }

    public void enterCity(String city) {
        type(loginMap.city, city);
    }

    public void enterZipCode(String zipCode) {
        type(loginMap.zipCode, zipCode);
    }

    public void enterMobileNumber(String mobileNumber) {
        type(loginMap.mobileNum, mobileNumber);
    }

    //BUTTONS
    public void clickCreateAccount() {
        click(loginMap.btnCreateAccount);
    }

    public void clickContinue() {
        click(loginMap.continueBtn);
    }

}
