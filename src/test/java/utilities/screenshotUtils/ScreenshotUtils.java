package utilities.screenshotUtils;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class ScreenshotUtils {

    public static void takeScreenshot(WebDriver driver) {

        if (driver == null) {
            return;
        }

        byte[] screenshot =
                ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES);

        /*Allure.addAttachment(
                "Screenshot on failure",
                "image/png",
                new ByteArrayInputStream(screenshot),
                ".png"
        );*/
        AllureLifecycle lifecycle =
                Allure.getLifecycle();

        lifecycle.addAttachment(
                "Screenshot on failure",
                "image/png",
                "png",
                screenshot );
    }
}
