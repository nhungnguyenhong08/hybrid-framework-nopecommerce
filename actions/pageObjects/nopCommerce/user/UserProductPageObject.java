package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.ProductPageUI;

public class UserProductPageObject extends BasePage {
	private WebDriver driver;

	public UserProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickProductToSeeDetailByText(WebDriver driver, String productName) {
		waitForElementClickable(driver, ProductPageUI.PRODUCT_TITLE_BY_TEXT, productName);
		clickToElement(driver, ProductPageUI.PRODUCT_TITLE_BY_TEXT, productName);
	}

	public void clickToAddYourReviewLink() {
		waitForElementClickable(driver, ProductPageUI.ADD_YOUR_REVIEW_LINK);
		clickToElement(driver, ProductPageUI.ADD_YOUR_REVIEW_LINK);
	}

	public void inputToReviewTextBox(WebDriver driver, String reviewText) {
		waitForElementVisible(driver, ProductPageUI.REVIEW_TEXTAREA);
		sendkeyToElement(driver, ProductPageUI.REVIEW_TEXTAREA, reviewText);
	}

	public void AddProductRatingByValue(WebDriver driver, String reviewRating) {
		waitForElementClickable(driver, ProductPageUI.PRODUCT_RATING, reviewRating);
		clickToElement(driver, ProductPageUI.PRODUCT_RATING, reviewRating);
	}

	public boolean isSuccessMessageDisplayed() {
		waitForElementVisible(driver, ProductPageUI.REVIEW_RESULT);
		return isElementDisplayed(driver, ProductPageUI.REVIEW_RESULT);
	}

	public String getProductReviewPage() {
		waitForElementVisible(driver, ProductPageUI.REVIEW_PAGE_HEADER);
		return getElementText(driver, ProductPageUI.REVIEW_PAGE_HEADER);
	}

}
