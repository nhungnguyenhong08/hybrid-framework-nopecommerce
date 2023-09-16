package com.liveguru.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.liveGuru.HomePageObject;
import pageObject.liveGuru.LoginPageObject;
import pageObject.liveGuru.MyDashboardPageObject;
import pageObject.liveGuru.PageGeneratorManager;
import pageObject.liveGuru.RegisterPageObject;

public class Level_01_Page_Generator_Manager extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private MyDashboardPageObject myDashboardPage;
	private String email, password, firstName, lastName;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.get("http://live.techpanda.org/");
		homePage = PageGeneratorManager.getHomePage(driver);

		email = "afc" + generateFakeNumber() + "@mail.vn";
		password = "123456";
		firstName = "Automation";
		lastName = "FC";
	}

	@Test
	public void Create_Account_01__Sucess() {
		loginPage = homePage.clickToMyAccountLink();

		registerPage = loginPage.clickToCreateAnAccountButton();

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextboxCreate(email);
		registerPage.inputToPasswordTextboxCreate(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		myDashboardPage = registerPage.clickToRegisterButton();

		Assert.assertEquals(myDashboardPage.getRegisterSucessMessage(), "Thank you for registering with Main Website Store.");

	}

	@Test
	public void Login_02_Sucess() {
		myDashboardPage.clickToAccountWrapper();

		homePage = myDashboardPage.clickToLogoutLink();

		loginPage = homePage.clickToMyAccountLink();

		loginPage.inputToEmailTextboxLogin(email);
		loginPage.inputToPasswordTextboxLogin(password);

		loginPage.clickToLoginButton();
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
