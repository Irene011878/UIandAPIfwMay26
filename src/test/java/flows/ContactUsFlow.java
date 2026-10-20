package flows;

import io.qameta.allure.Step;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import pages.ContactUsPage;
import pages.HomePage;
import utilities.configReader.ConfigReader;

import java.nio.file.Paths;
import java.util.Map;


public class ContactUsFlow {

    private final HomePage homePage;
    private final ContactUsPage contactUsPage;

    public ContactUsFlow(WebDriver driver) {

        homePage = new HomePage(driver);
        contactUsPage = new ContactUsPage(driver);

    }

    // ============================================================
    // CONTACT US
    // ============================================================

    public void openContactUs() {

        Allure.step(
                "Open Contact Us page",
                () -> homePage.clickContactUs()
        );
    }

    public void fillContactForm(Map<String, String> data) {

        Allure.step(
                "Fill Contact Us form",
                () -> {

                    contactUsPage.enterName(data.get("name"));
                    contactUsPage.enterEmail(data.get("email"));
                    contactUsPage.enterSubject(data.get("subject"));
                    contactUsPage.enterMessage(data.get("message"));

                }
        );
    }

    public void uploadFile(Map<String, String> data) {

        Allure.step(
                "Upload contact file",
                () -> {

                    String filePath = Paths.get(
                            ConfigReader.getProperty("resources.files.path"),
                            data.get("file")
                    ).toAbsolutePath().toString();

                    contactUsPage.uploadContactFile(filePath);

                }
        );
    }

    public void submitContactForm() {

        Allure.step(
                "Submit Contact Us form",
                () -> {

                    contactUsPage.clickSubmit();
                    contactUsPage.acceptAlert();

                }
        );
    }

    public void backToHome() {

        Allure.step(
                "Return to Home page",
                () -> contactUsPage.clickBackHome()
        );
    }
}
