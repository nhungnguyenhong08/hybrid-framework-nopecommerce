package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.nopCommerce.admin.AdminCustomerAddressPageObject;
import pageObjects.nopCommerce.admin.AdminCustomerDetailPageObject;
import pageObjects.nopCommerce.admin.AdminCustomerPageObject;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.admin.AdminProductDetailPageObject;
import pageObjects.nopCommerce.admin.AdminProductPageObject;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserChangePasswordPageObject;
import pageObjects.nopCommerce.user.UserCheckOutPageObject;
import pageObjects.nopCommerce.user.UserCompareProductPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserOrderPageObject;
import pageObjects.nopCommerce.user.UserProductPageObject;
import pageObjects.nopCommerce.user.UserRecentlyViewedProductPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointsPageObject;
import pageObjects.nopCommerce.user.UserSearchPageObject;
import pageObjects.nopCommerce.user.UserShoppingCartPageObject;
import pageObjects.nopCommerce.user.UserWishlistPageObject;

public class PageGeneratorManagerNopCommerce {

	public static UserHomePageObject getUserHomePage(WebDriver driver) {
		return new UserHomePageObject(driver);
	}

	public static UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
		return new UserRegisterPageObject(driver);
	}

	public static UserLoginPageObject getUserLoginPage(WebDriver driver) {
		return new UserLoginPageObject(driver);
	}

	public static UserCustomerInforPageObject getUserCustomerInforPage(WebDriver driver) {
		return new UserCustomerInforPageObject(driver);
	}

	public static UserAddressPageObject getUserAddressPage(WebDriver driver) {
		return new UserAddressPageObject(driver);
	}

	public static UserMyProductReviewPageObject getUserMyProductReviewPage(WebDriver driver) {
		return new UserMyProductReviewPageObject(driver);
	}

	public static UserRewardPointsPageObject getUserRewardPointsPage(WebDriver driver) {
		return new UserRewardPointsPageObject(driver);
	}

	public static UserChangePasswordPageObject getUserChangPasswordPage(WebDriver driver) {
		return new UserChangePasswordPageObject(driver);
	}

	public static UserProductPageObject getUserProductPage(WebDriver driver) {
		return new UserProductPageObject(driver);
	}

	public static UserSearchPageObject getUserSearchPage(WebDriver driver) {
		return new UserSearchPageObject(driver);
	}

	public static UserWishlistPageObject getUserWishlistPage(WebDriver driver) {
		return new UserWishlistPageObject(driver);
	}

	public static UserShoppingCartPageObject getUserShoppingCartPage(WebDriver driver) {
		return new UserShoppingCartPageObject(driver);
	}

	public static UserCompareProductPageObject getUserCompareProductPage(WebDriver driver) {
		return new UserCompareProductPageObject(driver);
	}

	public static UserRecentlyViewedProductPageObject getUserRecentlyViewedProductPage(WebDriver driver) {
		return new UserRecentlyViewedProductPageObject(driver);
	}

	public static UserCheckOutPageObject getUserCheckOutProductPage(WebDriver driver) {
		return new UserCheckOutPageObject(driver);
	}

	public static UserOrderPageObject getUserOrderPage(WebDriver driver) {
		return new UserOrderPageObject(driver);
	}

	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}

	public static AdminDashboardPageObject getAdminDashboardPage(WebDriver driver) {
		return new AdminDashboardPageObject(driver);
	}

	public static AdminProductPageObject getAdminProductPage(WebDriver driver) {
		return new AdminProductPageObject(driver);
	}

	public static AdminProductDetailPageObject getAdminDetailProductPage(WebDriver driver) {
		return new AdminProductDetailPageObject(driver);
	}

	public static AdminCustomerPageObject getAdminCustomerPage(WebDriver driver) {
		return new AdminCustomerPageObject(driver);
	}

	public static AdminCustomerDetailPageObject getAdminCustomerDetailPage(WebDriver driver) {
		return new AdminCustomerDetailPageObject(driver);
	}

	public static AdminCustomerAddressPageObject getAdminCustomerAddressPage(WebDriver driver) {
		return new AdminCustomerAddressPageObject(driver);
	}

}
