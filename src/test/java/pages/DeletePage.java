package pages;

import maps.DeleteMap;
import org.openqa.selenium.WebDriver;
import utilities.commonMethods.CommonMethods;

public class DeletePage extends CommonMethods {

    private final DeleteMap deleteMap;

    public DeletePage(WebDriver driver) {
        super(driver);
        deleteMap = new DeleteMap();
    }

    //VALIDATIONS
    public boolean isDeletedUserMessageDisplayed() {
        return isDisplayed(deleteMap.lblDeletedUser);
    }

    public String getDeletedUserMessage() {
        return getText(deleteMap.lblDeletedUser);
    }
}
