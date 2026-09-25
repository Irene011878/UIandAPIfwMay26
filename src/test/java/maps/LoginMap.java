package maps;

import org.openqa.selenium.By;

public class LoginMap {

    //Log in or New User Signup!

    public final By registerTitle =
            By.xpath("//h2[normalize-space()='New User Signup!']");

    public final By signUpName =
            By.cssSelector("input[data-qa='signup-name']");

    public final By signupEmail =
            By.cssSelector("input[data-qa='signup-email']");

    public final By signUpBtn =
            By.cssSelector("button[data-qa='signup-button']");

    //Enter Account Information

    public final By accountInformationTitle =
            By.xpath("//h2[normalize-space()='Enter Account Information']");

    public final By mrGender =
            By.id("id_gender1");

    public final By mrsGender =
            By.id("id_gender2");

    public final By password =
            By.id("password");

    public final By day =

            By.id("days");

    public final By month =
            By.id("months");

    public final By year =
            By.id("years");

    public final By newsLetter =
            By.xpath("//input[@id='newsletter']");

    public final By specialOffers =
            By.xpath("//input[@id='optin']");

    public final By firstName =
            By.id("first_name");

    public final By lastName =
            By.id("last_name");

    public final By company =
            By.id("company");


    public final By address1 =
            By.id("address1");

    public final By address2 =
            By.xpath("//input[@id='address2']");

    public final By country =
            By.id("country");

    public final By state =
            By.id("state");

    public final By city =
            By.id("city");

    public final By zipCode =
            By.id("zipcode");

    public final By mobileNum =
            By.id("mobile_number");

    public final By btnCreateAccount =
            By.cssSelector("button[data-qa='create-account']");

    public final By acctCreatedMessage =
            By.cssSelector("[data-qa='account-created']");

    public final By continueBtn =
            By.xpath("//a[text()='Continue']");

    // LOGIN

    public final By loginTitle =
            By.xpath("//h2[contains(text(),'Login to your account')]");

    public final By loginEmail =
            By.cssSelector("input[data-qa='login-email']");

    public final By loginPassword =
            By.cssSelector("input[data-qa='login-password']");

    public final By loginButton =
            By.cssSelector("button[data-qa='login-button']");

// ERRORS

    public final By invalidLoginMessage =
            By.xpath("//p[contains(text(),'Your email or password is incorrect!')]");

    public final By existingEmailMessage =
            By.xpath("//p[contains(text(),'Email Address already exist!')]");

}
