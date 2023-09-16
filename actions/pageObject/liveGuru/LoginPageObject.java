package pageObject.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveGuru.LoginPageUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public RegisterPageObject clickToCreateAnAccountButton() {
		waitForElementClickable(driver, LoginPageUI.CREATE_AN_ACCOUNT_BUTTON);
		clickToElement(driver, LoginPageUI.CREATE_AN_ACCOUNT_BUTTON);
		return PageGeneratorManager.getRegisterPage(driver);
	}

	public void inputToPasswordTextboxLogin(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX_LOGIN);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX_LOGIN, password);

	}

	public void inputToEmailTextboxLogin(String email) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX_LOGIN);
		sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX_LOGIN, email);

	}

	public MyDashboardPageObject clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getMyDashboardPage(driver);
	}

}
