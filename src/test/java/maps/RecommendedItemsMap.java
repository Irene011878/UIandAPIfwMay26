package maps;

import org.openqa.selenium.By;

public class RecommendedItemsMap {

    public final By recommendedItemsTitle =
            By.xpath("//h2[contains(text(),'recommended items')]");

    public By recommendedProduct(String productName) {

        return By.xpath("//div[contains(@class,'recommended_items')]"
                + "//p[normalize-space()='"
                + productName + "']");

    }

    public By addRecommendedProductToCart(String productName) {

        return By.xpath(
                "//div[contains(@class,'recommended_items')]" +
                        "//div[@class='productinfo text-center']"
                        + "[.//p[normalize-space()='"
                        + productName + "']]"
                        + "//a[contains(@class,'add-to-cart')]");

    }
}
