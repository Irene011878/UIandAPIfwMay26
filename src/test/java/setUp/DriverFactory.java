package setUp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utilities.configReader.ConfigReader;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER =
            new ThreadLocal<>();

    public WebDriver initializeDriver() {

        String browser = ConfigReader.getProperty("browser");

        switch (browser.toLowerCase()) {

            case "chrome":
                return createChromeDriver();

            default:
                throw new RuntimeException(
                        "Unsupported browser: " + browser);
        }

    }

    public WebDriver getDriver() {

        return DRIVER.get();
    }

    public void removeDriver() {

        DRIVER.remove();
    }

    private WebDriver createChromeDriver() {

        ChromeDriver driver =
                new ChromeDriver(buildChromeOptions());

        DRIVER.set(driver);

        String downloadPath =
                ConfigReader.getProperty("download.path");

        File downloadDirectory =
                new File(downloadPath);

        if (!downloadDirectory.exists()) {
            downloadDirectory.mkdirs();
        }

        Map<String, Object> downloadBehavior =
                new HashMap<>();

        downloadBehavior.put(
                "behavior",
                "allow");

        downloadBehavior.put(
                "downloadPath",
                downloadDirectory.getAbsolutePath());

        driver.executeCdpCommand(
                "Browser.setDownloadBehavior",
                downloadBehavior);

        configureDriver(driver);

        return driver;
    }

    private ChromeOptions buildChromeOptions() {

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();

        String downloadPath =
                ConfigReader.getProperty("download.path");

        File downloadDirectory =
                new File(downloadPath);

        if (!downloadDirectory.exists()) {
            downloadDirectory.mkdirs();
        }

        // HEADLESS

        boolean headless =
                Boolean.parseBoolean(
                        System.getProperty(
                                "headless",
                                ConfigReader.getProperty("headless")));

        if (headless) {

            options.addArguments("--headless=new");

        }

        // DOWNLOADS

        prefs.put(
                "download.default_directory",
                downloadPath);

        prefs.put(
                "download.prompt_for_download",
                false);

        prefs.put(
                "download.directory_upgrade",
                true);

        prefs.put(
                "safebrowsing.enabled",
                true);

        // PASSWORD MANAGER / AUTOFILL

        prefs.put(
                "profile.password_manager_enabled",
                false);

        prefs.put(
                "credentials_enable_service",
                false);

        prefs.put(
                "autofill.profile_enabled",
                false);

        prefs.put(
                "autofill.credit_card_enabled",
                false);

        // CONTENT SETTINGS

        prefs.put(
                "profile.default_content_setting_values.notifications",
                2);

        prefs.put(
                "profile.managed_default_content_settings.images",
                1);

        prefs.put(
                "profile.default_content_setting_values.ads",
                2);

        options.setExperimentalOption(
                "prefs",
                prefs);

        // NOTIFICATIONS

        options.addArguments(
                "--disable-notifications");

        // POPUPS

        options.addArguments(
                "--disable-popup-blocking");

        options.addArguments(
                "--disable-save-password-bubble");

        // AUTOFILL

        options.addArguments(
                "--disable-features=AutofillServerCommunication");

        // BROWSER UI + SITE FEATURES

        options.addArguments(
                "--disable-infobars");

        options.addArguments(
                "--disable-features=IsolateOrigins,site-per-process");

        options.addArguments(
                "--disable-features=InterestFeedContentSuggestions");

        options.addArguments(
                "--disable-features=OptimizationHints");

        // SELENIUM STABILITY

        options.addArguments(
                "--disable-blink-features=AutomationControlled");

        options.addArguments(
                "--remote-allow-origins=*");

        options.setAcceptInsecureCerts(true);

        // PERFORMANCE

        options.addArguments(
                "--disable-background-networking");

        options.addArguments(
                "--disable-background-timer-throttling");

        options.addArguments(
                "--disable-renderer-backgrounding");

        // WINDOW

        options.addArguments(
                "--start-maximized");

        return options;

    }

    private void configureDriver(WebDriver driver) {

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(
                Duration.ZERO);

        driver.manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(60));

        driver.manage().timeouts().scriptTimeout(
                Duration.ofSeconds(30));

    }

}