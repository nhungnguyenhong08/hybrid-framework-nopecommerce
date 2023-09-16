package pageObject.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveGuru.MyDashboardPageUI;

public class MyDashboardPageObject extends BasePage {
	private WebDriver driver;

	public MyDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public HomePageObject clickToLogoutLink() {
		waitForElementClickable(driver, MyDashboardPageUI.LOGOUT_LINK);
		clickToElement(driver, MyDashboardPageUI.LOGOUT_LINK);
		return PageGeneratorManager.getHomePage(driver);
	}

	public String getRegisterSucessMessage() {
		waitForElementVisible(driver, MyDashboardPageUI.REGISTER_SUCESS_MESSAGE);
		return getElementText(driver, MyDashboardPageUI.REGISTER_SUCESS_MESSAGE);

	}

	public void clickToAccountWrapper() {
		waitForElementClickable(driver, MyDashboardPageUI.ACCOUNT_WRAPPER);
		clickToElement(driver, MyDashboardPageUI.ACCOUNT_WRAPPER);
	}

}
