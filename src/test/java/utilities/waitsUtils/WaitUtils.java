package utilities.waitsUtils;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.configReader.ConfigReader;

import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.Function;


public class WaitUtils {

    private static final int EXPLICIT_WAIT =
            Integer.parseInt(ConfigReader.getProperty("explicit.wait"));

    protected final WebDriver driver;
    protected final WebDriverWait wait;


    public WaitUtils(WebDriver driver) {

        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));

    }

    //PRIVATE
    private TimeoutException buildTimeoutException(String message, TimeoutException e) {

        return new TimeoutException(message, e);

    }

    // VISIBLE
    public WebElement waitVisible(By locator) {

        try {
            return wait.until(
                    ExpectedConditions
                            .visibilityOfElementLocated(locator));
        }

        catch (TimeoutException e) {

            throw buildTimeoutException("Element not visible: " + locator, e);

        }

    }

    public WebElement waitVisible(WebElement element) {

        try {
            return wait.until(
                    ExpectedConditions
                            .visibilityOf(element));

        }

        catch (TimeoutException e) {

            throw buildTimeoutException("WebElement not visible.", e);

        }

    }

    // CLICKABLE
    public WebElement waitClickable(By locator) {

        try {
            return wait.until(
                    ExpectedConditions
                            .elementToBeClickable(locator));

        }

        catch (TimeoutException e) {

            throw buildTimeoutException("Element not clickable: " + locator, e);

        }

    }

    public WebElement waitClickable(WebElement element) {

        try {
            return wait.until(
                    ExpectedConditions
                            .elementToBeClickable(element));

        }

        catch (TimeoutException e) {

            throw buildTimeoutException("WebElement not clickable.", e);

        }

    }

    // PRESENT
    public WebElement waitPresent(By locator) {

        try {
            return wait.until(
                    ExpectedConditions
                            .presenceOfElementLocated(locator));

        }

        catch (TimeoutException e) {

            throw buildTimeoutException("Element not present: " + locator, e);

        }

    }

    //INVISIBLE
    public boolean waitInvisible(By locator) {

        try {

            return wait.until(
                    ExpectedConditions
                            .invisibilityOfElementLocated(locator));

        }

        catch (TimeoutException e) {

            throw buildTimeoutException("Element still visible: " + locator, e);

        }

    }

    //NUMBER OF ELEMENTS
    public List<WebElement> waitNumberOfElements(By locator, int number) {

        try {
            return wait.until(
                    ExpectedConditions
                            .numberOfElementsToBe(locator, number));

        }

        catch (TimeoutException e) {

            throw buildTimeoutException("Expected " + number +
                            " elements but condition was not met: " + locator, e);

        }

    }

    // TEXT / ATTRIBUTE
    public boolean waitText(By locator, String text) {

        try {
            return wait.until(
                    ExpectedConditions
                            .textToBePresentInElementLocated(locator, text));

        }

        catch (TimeoutException e) {

            throw buildTimeoutException("Expected text not found: \"" + text + "\"", e);

        }

    }

    //ATTRIBUTE
    public boolean waitAttributeContains(By locator, String attribute, String value) {

        try {
            return wait.until(
                    ExpectedConditions
                            .attributeContains(locator, attribute, value));

        }

        catch (TimeoutException e) {

            throw buildTimeoutException(
                    "Attribute '" + attribute + "' does not contain '" + value + "'.", e);

        }

    }

    public boolean waitAttributeToBe(By locator, String attribute, String value) {

        try {
            return wait.until(
                    ExpectedConditions
                            .attributeToBe(locator, attribute, value));

        }

        catch (TimeoutException e) {

            throw buildTimeoutException("Attribute '" + attribute + "' was not equal to '" + value + "'.", e);

        }

    }

    //SELECTED
    public boolean waitElementSelected(By locator) {

        try {
            return wait.until(
                    ExpectedConditions
                            .elementSelectionStateToBe(locator, true));

        }

        catch (TimeoutException e) {

            throw buildTimeoutException(
                    "Element was not selected: " + locator, e);

        }

    }

    // PAGE
    public boolean waitUrlContains(String url) {

        try {
            return wait.until(
                    ExpectedConditions
                            .urlContains(url));

        }

        catch (TimeoutException e) {

            throw buildTimeoutException("URL does not contain: " + url, e);

        }

    }

    public boolean waitTitleContains(String title) {

        try {
            return wait.until(
                    ExpectedConditions
                            .titleContains(title));

        }

        catch (TimeoutException e) {

            throw buildTimeoutException("Title does not contain: " + title, e);

        }

    }

    public boolean waitPageLoaded() {

        try {
            return Boolean.TRUE.equals(

                    wait.until(driver ->
                            ((JavascriptExecutor) driver)
                                    .executeScript("return document.readyState")
                                    .equals("complete")));

        }

        catch (TimeoutException e) {

            throw buildTimeoutException("Page did not finish loading.", e);

        }

    }

    public boolean waitElementVisible(By locator, int timeoutSeconds) {

        try {

            return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator))
                    .isDisplayed();

        } catch (TimeoutException e) {

            return false;

        }

    }

    // ALERT
    public Alert waitAlert() {

        try {
            return wait.until(
                    ExpectedConditions
                            .alertIsPresent());

        }

        catch (TimeoutException e) {

            throw buildTimeoutException("Alert not displayed.", e);

        }

    }


    public <T> T waitUntil(
            Function<WebDriver, T> condition,
            int timeoutSeconds) {

        return new WebDriverWait(
                driver,
                Duration.ofSeconds(timeoutSeconds))
                .until(condition);

    }

    //SHORT WAIT -GOOGLE VIGNETTE AND OTHERS

    public WebElement waitVisible(By locator, int timeoutSeconds) {

        try {

            return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));

        }

        catch (TimeoutException e) {

            return null;

        }

    }


}

