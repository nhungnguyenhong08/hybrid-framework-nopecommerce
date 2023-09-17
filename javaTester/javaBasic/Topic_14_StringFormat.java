package javaBasic;

public class Topic_14_StringFormat {

	// 3 page = 3 biến locator
	public static final String ADDRESS_LINK = "//div[contains(@class,'account-navigation')]//a[text()='Addresses']";
	public static final String MY_PRODUCT_REVIEW_LINK = "//div[contains(@class,'account-navigation')]//a[text()='My product reviews']";
	public static final String REWARD_POINTS_LINK = "//div[contains(@class,'account-navigation')]//a[text()='Reward points']";

	// 1 biến cho n page (format giống nhau - chỉ khác nhau bởi tên page)
	public static String DYNAMIC_SIDERBAR_LINK_BY_PAGE_NAME = "//div[contains(@class,'account-navigation')]//a[text()='%s']";

	// 1 locator để đại diện cho các page area (header/siderbar/footer)
	public static String DYNAMIC_LINK_BY_PAGE_NAME = "//div[contains(@class,'%s')]//a[text()='%s']";

	public static void main(String[] args) {
		clickToLink(ADDRESS_LINK);
		clickToLink(MY_PRODUCT_REVIEW_LINK);
		clickToLink(REWARD_POINTS_LINK);

		clickToLink(DYNAMIC_SIDERBAR_LINK_BY_PAGE_NAME, "Addresses");
		clickToLink(DYNAMIC_SIDERBAR_LINK_BY_PAGE_NAME, "My product reviews");
		clickToLink(DYNAMIC_SIDERBAR_LINK_BY_PAGE_NAME, "Reward points");

		clickToLink(DYNAMIC_LINK_BY_PAGE_NAME, "account-navigation", "Addresses");
		clickToLink(DYNAMIC_LINK_BY_PAGE_NAME, "footer-upper", "Search");
		clickToLink(DYNAMIC_LINK_BY_PAGE_NAME, "header-upper", "My account");

	}

	public static void clickToLink(String locator) {
		System.out.println("Click to: " + locator);
	}
	/*
	 * // 1 tham số động public static void clickToLink(String dynamicLocator, String pageName) { String locator = String.format(dynamicLocator, pageName);
	 * System.out.println("Click to: " + locator); }
	 * 
	 * // 2 tham số động public static void clickToLink(String dynamicLocator, String areaName, String pageName) { String locator = String.format(dynamicLocator,
	 * areaName, pageName); System.out.println("Click to: " + locator); }
	 */

	// n1- tham số động
	public static void clickToLink(String dynamicLocator, String... params) {
		String locator = String.format(dynamicLocator, (Object[]) params);
		System.out.println("Click to: " + locator);
	}
}
