package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.admin.AdminProductDetailPageUI;

public class AdminProductDetailPageObject extends BasePage {
	private WebDriver driver;

	public AdminProductDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isHeaderTitleByTextDisplayed(String titleText) {
		waitForElementVisible(driver, AdminProductDetailPageUI.HEADER_TITLE_BY_TEXT, titleText);
		return isElementDisplayed(driver, AdminProductDetailPageUI.HEADER_TITLE_BY_TEXT, titleText);
	}

}
