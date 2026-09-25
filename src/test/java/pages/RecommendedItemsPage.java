package pages;

import maps.RecommendedItemsMap;
import org.openqa.selenium.WebDriver;
import utilities.commonMethods.CommonMethods;

public class RecommendedItemsPage extends CommonMethods {

    private final RecommendedItemsMap recommendedItemsMap;

    public RecommendedItemsPage(WebDriver driver) {
        super(driver);
        recommendedItemsMap = new RecommendedItemsMap();
    }

    //ADD RECOMMENDED PRODUCT
    public void addRecommendedProductToCart(String productName) {
        click(recommendedItemsMap.addRecommendedProductToCart(productName));
    }

    public boolean isRecommendedItemsDisplayed() {
        return isDisplayed(recommendedItemsMap.recommendedItemsTitle);
    }

    public boolean isRecommendedProductDisplayed(String productName) {
        return isDisplayed(recommendedItemsMap.recommendedProduct(productName));
    }
}
