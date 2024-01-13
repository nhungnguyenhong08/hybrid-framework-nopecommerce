package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.RecentlyViewedProductPageUI;

public class UserRecentlyViewedProductPageObject extends BasePage {
	private WebDriver driver;

	public UserRecentlyViewedProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isRequestedPageDisplayed(WebDriver driver, String pageTitle) {
		waitForElementVisible(driver, RecentlyViewedProductPageUI.PAGE_TITLE, pageTitle);
		return isElementDisplayed(driver, RecentlyViewedProductPageUI.PAGE_TITLE, pageTitle);
	}

	public int countRecentlyViewedProductDisplayed() {
		waitForAllElementVisible(driver, RecentlyViewedProductPageUI.NUMBER_PRODUCT_DISPLAYED);
		return getElementSize(driver, RecentlyViewedProductPageUI.NUMBER_PRODUCT_DISPLAYED);
	}

	public boolean isProductNameAtRecentlyViewedBytextDisplayed(String productName) {
		waitForElementVisible(driver, RecentlyViewedProductPageUI.DYNAMIC_PRODUCT_NAME_BY_TEXT, productName);
		return isElementDisplayed(driver, RecentlyViewedProductPageUI.DYNAMIC_PRODUCT_NAME_BY_TEXT, productName);
	}

}
