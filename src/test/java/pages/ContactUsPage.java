package pages;

import maps.ContactUsMap;
import org.openqa.selenium.WebDriver;
import utilities.commonMethods.CommonMethods;

public class ContactUsPage extends CommonMethods {

    private final ContactUsMap contactUsMap;

    public ContactUsPage(WebDriver driver) {
        super(driver);
        contactUsMap = new ContactUsMap();
    }

    //VALIDATIONS
    public boolean isGetInTouchDisplayed() {
        return isDisplayed(contactUsMap.lblGetInTouch);
    }

    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(contactUsMap.messageSentSuccessfully);
    }

    //CONTACT FORM
    public void enterName(String name) {
        type(contactUsMap.textContactName, name);
    }

    public void enterEmail(String email) {
        type(contactUsMap.textContactEmail, email);
    }

    public void enterSubject(String subject) {
        type(contactUsMap.contactSubject, subject);
    }

    public void enterMessage(String message) {
        type(contactUsMap.contactMessage, message);
    }

    public void uploadContactFile(String filePath) {
        uploadFile(contactUsMap.uploadFileForContact, filePath);
    }

    public void clickSubmit() {
        click(contactUsMap.contactSubmitBtn);
    }

    public void clickBackHome() {
        click(contactUsMap.btnBackHome);
    }


}
