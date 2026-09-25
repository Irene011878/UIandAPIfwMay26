package utilities.commonMethods;

import maps.HomeMap;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utilities.configReader.ConfigReader;
import utilities.waitsUtils.WaitUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class
CommonMethods extends WaitUtils {

    private final HomeMap homeMap;

    public CommonMethods(WebDriver driver) {
        super(driver);
        homeMap = new HomeMap();
    }

    // CLICK
    public void click(By locator) {

        try {
            waitClickable(locator).click();
        } catch (ElementClickInterceptedException e) {

            System.out.println("Normal click intercepted. Trying JavaScript click...");
            jsClick(locator);
            handleGoogleVignette();

        } catch (StaleElementReferenceException e) {

            System.out.println("Stale element. Retrying click...");
            waitClickable(locator).click();


        } catch (TimeoutException e) {

            throw new TimeoutException("Element was not clickable: " + locator, e);

        } catch (WebDriverException e) {

            throw new WebDriverException("Unable to click element: " + locator, e);

        }

    }


    public void jsClick(By locator) {

        try {

            WebElement element = waitVisible(locator);

            ((JavascriptExecutor) driver)
                    .executeScript(
                            "arguments[0].click();",
                            element);

        } catch (JavascriptException e) {

            throw new JavascriptException(
                    "Unable to execute JavaScript click on: " + locator,
                    e);

        }

    }

    // TYPE
    public void type(By locator, String value) {

        WebElement element = waitVisible(locator);
        element.clear();
        element.sendKeys(value);

    }

    public void clear(By locator) {
        waitVisible(locator).clear();
    }

    public String getText(By locator) {
        return waitVisible(locator).getText();
    }

    // STATE
    public boolean isDisplayed(By locator) {

        try {
            return waitVisible(locator).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }

    }

    public boolean isEnabled(By locator) {

        try {
            return waitVisible(locator).isEnabled();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }

    }

    public boolean isSelected(By locator) {
        return waitVisible(locator).isSelected();
    }


    // SELECT
    public void selectByText(By locator, String value) {
        Select select = new Select(waitVisible(locator));
        select.selectByVisibleText(value);
    }

    // CHECKBOX
    public void check(By locator) {

        WebElement element = waitClickable(locator);

        if (!element.isSelected()) {

            click(locator);

        }

    }

    public void uncheck(By locator) {

        if (isSelected(locator)) {

            click(locator);

        }

    }

    // SCROLL
    public void scrollToElement(By locator) {

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        waitVisible(locator));


    }

    public void scrollBottom() {

        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0,document.body.scrollHeight)");

    }

    public void scrollTop() {

        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0,0)");

    }

    // ACTIONS
    private Actions actions() {
        return new Actions(driver);
    }

    public void hover(By locator) {
        actions().moveToElement(waitVisible(locator)).perform();
    }

    public void doubleClick(By locator) {
        actions().doubleClick(waitVisible(locator)).perform();
    }

    public void rightClick(By locator) {
        actions().contextClick(waitVisible(locator)).perform();
    }

    public void dragAndDrop(By sourceLocator, By targetLocator) {
        actions().dragAndDrop(waitVisible(sourceLocator), waitVisible(targetLocator)).perform();
    }

    // FILE
    public void uploadFile(By locator, String file) {
        waitVisible(locator).sendKeys(file);
    }

    // ALERT
    public void acceptAlert() {
        waitAlert().accept();
    }

    public void dismissAlert() {
        waitAlert().dismiss();
    }

    public String getAlertText() {
        return waitAlert().getText();
    }

    public void sendAlertText(String text) {
        waitAlert().sendKeys(text);
    }

    // URL
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // COUNT
    public int getElementsCount(By locator) {

        List<WebElement> elements = driver.findElements(locator);
        return elements.size();

    }

    public List<String> getElementsText(By locator) {

        List<String> texts = new ArrayList<>();

        for (WebElement element : driver.findElements(locator)) {

            texts.add(element.getText().trim());

        }

        return texts;
    }

    //DELETE INVOICE BEFORE THE TEST
    public void deleteFile(String fileName) {

        File file =
                new File(
                        ConfigReader.getProperty("download.path")
                                + File.separator + fileName
                );

        if (file.exists()) {

            file.delete();

        }

    }


    public boolean waitForFileDownload(String fileName, int timeoutSeconds) {

        File file = new File(
                ConfigReader.getProperty("download.path")
                        + File.separator + fileName);

        return waitUntil(
                driver -> file.exists() && file.length() > 0,
                timeoutSeconds);

    }


    public void handleGoogleVignette() {

        closeGoogleVignetteIfPresent();

        if (driver.getCurrentUrl().contains("google_vignette")) {

            driver.navigate().to("https://automationexercise.com/");

            waitPageLoaded();

        }

    }

    public void closeGoogleVignetteAndStayOnPage() {
        closeGoogleVignetteIfPresent();
    }


    public void closeGoogleVignetteIfPresent() {

        try {

            WebElement closeButton =
                    waitVisible(homeMap.btnDismissGoogleVignette, 2);

            if (closeButton != null && closeButton.isDisplayed()) {

                jsClick(homeMap.btnDismissGoogleVignette);

                waitInvisible(homeMap.btnDismissGoogleVignette);
                waitPageLoaded();

            }

        } catch (Exception ignored) {

        }

    }

}
