package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.MyProductReviewsPageUI;

public class UserMyProductReviewPageObject extends BasePage {
	private WebDriver driver;

	public UserMyProductReviewPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isProductReviewDisplayed(WebDriver driver, String reviewProductText) {
		waitForElementVisible(driver, MyProductReviewsPageUI.REVIEW_PRODUCT_NAME, reviewProductText);
		return isElementDisplayed(driver, MyProductReviewsPageUI.REVIEW_PRODUCT_NAME, reviewProductText);
	}

}
