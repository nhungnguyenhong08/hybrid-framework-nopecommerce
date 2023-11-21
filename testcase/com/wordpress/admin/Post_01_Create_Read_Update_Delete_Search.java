package com.wordpress.admin;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.wordpress.AdminDashboardPO;
import pageObject.wordpress.AdminLoginPO;
import pageObject.wordpress.AdminPostAddNewPO;
import pageObject.wordpress.AdminPostSearchPO;
import pageObject.wordpress.PageGeneratorManager;
import pageObject.wordpress.UserHomePO;
import pageObject.wordpress.UserPostDetailPO;

public class Post_01_Create_Read_Update_Delete_Search extends BaseTest {
	private WebDriver driver;
	AdminLoginPO adminLoginPage;
	AdminDashboardPO adminDashboardPage;
	AdminPostSearchPO adminPostSearchPage;
	AdminPostAddNewPO adminPostAddNewPage;
	UserHomePO userHomePage;
	UserPostDetailPO userPostDeatilPage;

	String adminUsername = "automationfc";
	String adminPassword = "automationfc";
	int randomNumber = generateFakeNumber();
	String postTitle = "Live Coding Title " + randomNumber;
	String postBody = "Live Coding Body " + randomNumber;
	String authorName = "Automation Admin";
	String searchPostUrl;
	String adminUrl, userUrl;
	String currentDay = getCurrentDay();

	@Parameters({ "browser", "urlAdmin", "urlUser" })
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl, String userUrl) {
		log.info("Pre-Condition - Step 01: Open browser and admin url");
		this.adminUrl = adminUrl;
		this.userUrl = userUrl;
		driver = getBrowserDriver(browserName, this.adminUrl);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

		log.info("Pre-Condition - Step 02: Enter to Username textbox with value: " + adminUsername);
		adminLoginPage.enterToUsernameTextbox(adminUsername);

		log.info("Pre-Condition - Step 03: Enter to Password textbox with value: " + adminPassword);
		adminLoginPage.enterToPasswordTextbox(adminPassword);

		log.info("Pre-Condition - Step 04: Click to Login button");
		adminDashboardPage = adminLoginPage.clickToLoginButton();
	}

	@Test
	public void Post_01_Create_New_Post() {
		log.info("Create_Post - Step 01: Click to 'Posts' menu link");

		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();

		log.info("Create_Post - Step 02: Get 'Search Posts' page Url");
		searchPostUrl = adminPostSearchPage.getPageUrl(driver);

		log.info("Create_Post - Step 03: Click to 'Add New' button");
		adminPostAddNewPage = adminPostSearchPage.clickToAddNewButton();

		log.info("Create_Post - Step 04: Enter to post title");
		adminPostAddNewPage.enterToAddNewPostTitle(postTitle);

		log.info("Create_Post - Step 05: Enter to post body");
		adminPostAddNewPage.enterToAddNewPostBody(postBody);

		log.info("Create_Post - Step 06: Click to 'Publish' button");
		adminPostAddNewPage.clickToPublishButton();

		log.info("Create_Post - Step 07: Click to 'Pre Publish' button");
		adminPostAddNewPage.clickToPrePublishButton();

		log.info("Create_Post - Step 08: Verify 'Post published.' message is displayed");
		verifyTrue(adminPostAddNewPage.isPostPublishMessageDisplayed("Post published."));

	}

	@Test
	public void Post_02_Search_And_View_Post() {
		log.info("Search_Post - Step 01: Open 'Search Post' page");
		adminPostSearchPage = adminPostAddNewPage.openSearchPostPageUrl(searchPostUrl);

		log.info("Search_Post - Step 02: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(postTitle);

		log.info("Search_Post - Step 03: Click to 'Search Posts' button");
		adminPostSearchPage.clickToSearchPostsButton();

		log.info("Search_Post - Step 04: Verify Search table contains '" + postTitle + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("title", postTitle));

		log.info("Search_Post - Step 05: Verify Search table contains '" + authorName + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("author", authorName));

		log.info("Search_Post - Step 06: Open End User site");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.userUrl);

		log.info("Search_Post - Step 07: Verify Post infor displayed at Home page");
		verifyTrue(userHomePage.isPostInforDisplayedWithPostTitle(postTitle));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostBody(postTitle, postBody));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostAuthor(postTitle, authorName));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostCurrentDay(postTitle, currentDay));

		log.info("Search_Post - Step 08: Click to Post title");
		userPostDeatilPage = userHomePage.clikToPostTitle(postTitle);

		log.info("Search_Post - Step 09: Verify Post infor displayed at Post detail page");
		verifyTrue(userPostDeatilPage.isPostInforDisplayedWithPostTitle(postTitle));
		verifyTrue(userPostDeatilPage.isPostInforDisplayedWithPostBody(postTitle, postBody));
		verifyTrue(userPostDeatilPage.isPostInforDisplayedWithPostAuthor(postTitle, authorName));
		verifyTrue(userPostDeatilPage.isPostInforDisplayedWithPostCurrentDay(postTitle, currentDay));
	}

	@Test
	public void Post_04_Edit_Post() {

	}

	@Test
	public void Post_05_Delete_Post() {

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);

	}

}
