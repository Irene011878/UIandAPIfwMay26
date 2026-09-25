package setUp;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.configReader.ConfigReader;
import org.testng.annotations.Listeners;
//import utilities.listeners.TestListener;

/*public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)

    public void setUp() {

        driver = new DriverFactory().initializeDriver();
        driver.manage().deleteAllCookies();
        openBaseUrl();

    }

    private void openBaseUrl() {

        driver.get(ConfigReader.getProperty("base.url"));

    }

    protected void refreshPage() {

        driver.navigate().refresh();

    }

    protected String getCurrentUrl() {

        return driver.getCurrentUrl();

    }

    protected String getPageTitle() {

        return driver.getTitle();

    }

    @AfterMethod(alwaysRun = true)

    public void tearDown() {

        if (driver != null) {

            driver.quit();
        }
    }
}*/
//@Listeners(TestListener.class)
public class BaseTest {

    protected WebDriver driver;

    private final DriverFactory driverFactory =
            new DriverFactory();

    @BeforeMethod(alwaysRun = true)

    public void setUp() {

        driver = driverFactory.initializeDriver();

        driver.manage().deleteAllCookies();

        openBaseUrl();

    }

    private void openBaseUrl() {

        driver.get(
                ConfigReader.getProperty("base.url"));

    }

    protected void refreshPage() {

        driver.navigate().refresh();

    }

    protected String getCurrentUrl() {

        return driver.getCurrentUrl();

    }

    protected String getPageTitle() {

        return driver.getTitle();

    }

    @AfterMethod(alwaysRun = true)

    public void tearDown() {

        if (driver != null) {

            driver.quit();
        }

        driverFactory.removeDriver();

    }
}
