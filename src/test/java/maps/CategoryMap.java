package maps;

import org.openqa.selenium.By;

public class CategoryMap {

    //CATEGORY

    public final By sideBarCategoryTitle =
            By.xpath("//h2[contains(text(),'Category')]");

    //Main category
    public By category(String categoryName) {
        return By.xpath("//a[normalize-space()='" + categoryName + "']");

    }

    //Subcategory
    public By categoryOption(String categoryName) {

        return By.xpath(
                "//div[@class='panel-body']//a[normalize-space()='"
                        + categoryName + "']");

    }


    public final By categoryPageTitle =
            By.xpath("//h2[@class='title text-center']");


    //BRANDS
    public final By sideBarBrandTitle =
            By.xpath("//h2[contains(text(),'Brands')]");

    public By brandOption(String brandName) {

        return By.xpath("//div[@class='brands-name']" +
                "//a[normalize-space()='" + brandName + "']");

    }

    public final By brandPageTitle =
            By.xpath("//h2[@class='title text-center']");


}


