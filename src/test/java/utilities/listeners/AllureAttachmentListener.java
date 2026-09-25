package utilities.listeners;

import io.qameta.allure.Allure;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Attachment;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import setUp.DriverFactory;

import java.io.ByteArrayInputStream;
import java.util.UUID;

public class AllureAttachmentListener
        implements TestLifecycleListener {

    @Override
    public void beforeTestStop(TestResult result) {

        if (result.getStatus() != Status.FAILED
                && result.getStatus() != Status.BROKEN){
            return;
        }

        DriverFactory driverFactory =
                new DriverFactory();

        WebDriver driver =
                driverFactory.getDriver();

        if (driver == null) {
            return;
        }

        byte[] screenshot =
                ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES);

        String attachmentSource =
                UUID.randomUUID() + "-attachment.png";

        Allure.getLifecycle().writeAttachment(
                attachmentSource,
                new ByteArrayInputStream(screenshot)
        );

        Attachment attachment =
                new Attachment()
                        .setName("Screenshot on failure")
                        .setType("image/png")
                        .setSource(attachmentSource);

        result.getAttachments().add(attachment);
    }
}