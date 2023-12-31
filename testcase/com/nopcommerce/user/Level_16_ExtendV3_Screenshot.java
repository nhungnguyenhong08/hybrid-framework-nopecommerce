package com.nopcommerce.user;

import java.lang.reflect.Method;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManagerNopCommerce;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_16_ExtendV3_Screenshot extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, emailAddress, validPassword;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManagerNopCommerce.getUserHomePage(driver);

		firstName = "Automation";
		lastName = "FC";
		emailAddress = "afc" + generateFakeNumber() + "@gmail.com";
		validPassword = "123456";
	}

	@Test
	public void User_01_Register(Method method) {
		registerPage = homePage.clickToRegisterLink();

		registerPage.inputToFirstnameTextbox(firstName);

		registerPage.inputToLastnameTextbox(lastName);

		registerPage.inputToEmailTextbox(emailAddress);

		registerPage.inputToPasswordTextbox(validPassword);

		registerPage.inputToConfirmPasswordTextbox(validPassword);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getSucessRegisterMessage(), "Your registration completed");

		homePage = registerPage.clickToHomeLink();

	}

	@Test
	public void User_02_Login(Method method) {
		homePage = registerPage.clickToHomeLink();
		loginPage = homePage.clickToLoginLink();

		loginPage.inputToEmailTextbox(emailAddress);

		loginPage.inputToPasswordTextbox(validPassword);

		homePage = loginPage.clickToLoginButton();

		Assert.assertFalse(homePage.isMyAccountLinkDisplayed());

		customerInforPage = homePage.clickToMyAccountLink();

		Assert.assertFalse(customerInforPage.isMyAccountPageDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);

	}

}
