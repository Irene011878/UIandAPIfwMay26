package testCases.ui.navigation;

import org.testng.Assert;
import org.testng.annotations.Test;
import setUp.BaseTest;

public class TestScreenshot extends BaseTest {

    @Test
    public void verifyScreenshotOnFailure() {


        // Step 1: Open Automation Exercise
        Assert.assertTrue(
                false,
                "Intentional failure to verify screenshot capture."
        );
    }
}
