package pages;

import maps.CategoryMap;
import org.openqa.selenium.WebDriver;
import utilities.commonMethods.CommonMethods;

public class CategoryPage extends CommonMethods {

    private final CategoryMap categoryMap;

    public CategoryPage(WebDriver driver) {
        super(driver);
        categoryMap = new CategoryMap();
    }

    //CATEGORY VALIDATIONS

    public boolean isCategorySectionDisplayed() {
        return isDisplayed(categoryMap.sideBarCategoryTitle);
    }

    public String getCategoryPageTitle() {
        return getText(categoryMap.categoryPageTitle);
    }

    public boolean categoryTitleContains(String expectedText) {

        return getCategoryPageTitle()
                .toUpperCase()
                .contains(
                        expectedText.toUpperCase());

    }

    //CATEGORY ACTIONS *****
    public void clickCategory(String categoryName) {
        click(categoryMap.category(categoryName));
    }

    public void clickCategoryOption(String categoryOption) {
        click(categoryMap.categoryOption(categoryOption));
    }

    public void selectCategory(String categoryName, String categoryOption) {
        clickCategory(categoryName);
        clickCategoryOption(categoryOption);
    }

    //BRAND VALIDATIONS
    public boolean isBrandSectionDisplayed() {
        return isDisplayed(categoryMap.sideBarCategoryTitle);
    }

    public String getBrandPageTitle() {
        return getText(categoryMap.brandPageTitle);
    }

    public boolean brandTitleContains(String expectedText) {

        return getBrandPageTitle()
                .toUpperCase()
                .contains(
                        expectedText.toUpperCase());

    }

    //BRAND ACTIONS
    public void clickBrand(String brandName) {
        click(categoryMap.brandOption(brandName));
    }


}
