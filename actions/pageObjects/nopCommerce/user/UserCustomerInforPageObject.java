package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.MyAccoutnPageUI;

public class UserCustomerInforPageObject extends BasePage {
	private WebDriver driver;

	public UserCustomerInforPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isMyAccountPageDisplayed() {
		waitForElementVisible(driver, MyAccoutnPageUI.CUSTOMER_INFOR_HEADER);
		return isElementDisplayed(driver, MyAccoutnPageUI.CUSTOMER_INFOR_HEADER);
	}

	public String getSucessUpdateMessage() {
		waitForElementVisible(driver, MyAccoutnPageUI.UPDATE_SUCCESS_MESSAGE);
		return getElementText(driver, MyAccoutnPageUI.UPDATE_SUCCESS_MESSAGE);
	}

}
