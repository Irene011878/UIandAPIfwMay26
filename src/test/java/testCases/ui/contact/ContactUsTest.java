package testCases.ui.contact;

import flows.ContactUsFlow;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;
import setUp.BaseTest;
import utilities.dataProvider.TestDataProvider;

import java.util.Map;

@Epic("Navigation")
@Feature("Contact Us")
public class ContactUsTest extends BaseTest {

    private HomePage homePage;
    private ContactUsPage contactUsPage;
    private ContactUsFlow contactFlow;

    @BeforeMethod(alwaysRun = true)
    public void initializePages() {

        homePage = new HomePage(driver);
        contactUsPage = new ContactUsPage(driver);
        contactFlow = new ContactUsFlow(driver);

    }

    //=========================================================
    // TC006 - CONTACT US FORM
    //=========================================================

    @Story("Contact Us Form")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the Contact Us form can be submitted successfully")

    @Test(
            description = "TC006 - Contact Us Form",
            groups = {"regression"},
            dataProvider = "contactUsers",
            dataProviderClass = TestDataProvider.class
    )

    public void submitContactUsForm(Map<String, String> data) {

        //=========================================================
        // Preconditions
        //=========================================================

        // Verify Home Page is displayed
        Assert.assertTrue(
                homePage.isHomeDisplayed(),
                "Home page is not displayed."
        );

        // Step 1: Click Contact Us button
        contactFlow.openContactUs();

        // Step 2: Verify GET IN TOUCH is visible

        Assert.assertTrue(
                contactUsPage.isGetInTouchDisplayed(),
                "'GET IN TOUCH' title is not displayed.");

        // Step 3: Enter name, email, subject and message
        contactFlow.fillContactForm(data);

        // Step 4: Upload file
        contactFlow.uploadFile(data);

        // Step 5 & 6: Click Submit and accept confirmation alert
        contactFlow.submitContactForm();

        // Step 7: Verify success message

        Assert.assertTrue(
                contactUsPage.isSuccessMessageDisplayed(),
                "'Success! Your details have been submitted successfully.' message is not displayed.");

        // Step 8: Click Home and verify Home page
        contactFlow.backToHome();

        Assert.assertTrue(
                homePage.isHomeDisplayed(),
                "Home page is not displayed after returning from Contact Us page.");

    }

}
