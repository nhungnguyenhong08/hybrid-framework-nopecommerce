package commons;

import java.io.File;

public class GlobalConstants {
	public static final String PORTAL_PAGE_URL = "https://demo.nopcommerce.com/";
	public static final String ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com/";
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String JAVA_VERSION = System.getProperty("java.version");

	public static final String OS_NAME = System.getProperty("os.name");
	public static final String UPLOAD_FILE = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	public static final String DOWNLOAD_FILE = PROJECT_PATH + File.separator + "downloadFiles";
	public static final String BROWSER_LOG = PROJECT_PATH + File.separator + "browserLogs" + File.separator;
	public static final String DRAG_DROP_HTML5 = PROJECT_PATH + File.separator + "dragDropHTML5";
	public static final String AUTO_IT_SCRIPT = PROJECT_PATH + File.separator + "autoIT";
	public static final String REPORTNG_SCREENSHOT = PROJECT_PATH + File.separator + "ReportNGScreenShots" + File.separator;
	public static final String EXTENT_PATH = PROJECT_PATH + File.separator + "ExtentReportV2" + File.separator;

	public static final String DB_DEV_URL = "32.18.252.185:9860";
	public static final String DB_DEV_USER = "automationfc";
	public static final String DB_DEV_PASS = "P@sswOrld1@";

	public static final String DB_TEST_URL = "32.18.195.23:9860";
	public static final String DB_TEST_USER = "automationfc";
	public static final String DB_TEST_PASS = "P@sswOrld1@";

	public static final long SHORT_TIME_OUT = 5;
	public static final long LONG_TIME_OUT = 15;
	public static final long RETRY_TEST_FAIL = 3;

	public static final String BROWSER_UERNAME = "nhungnguyen_9rLsbA";
	public static final String BROWSER_AUTOMATE_KEY = "UevjakxKzz38VqX9KH8h";
	public static final String BROWSER_STACK_URL = "https://" + BROWSER_UERNAME + ":" + BROWSER_AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	public static final String SAUCE_UERNAME = "oauth-nhungnth8.neu-16f5f";
	public static final String SAUCE_AUTOMATE_KEY = "d8137b9f-f381-475c-8fa8-279e8a5aed2e";
	public static final String SAUCE_URL = "https://" + SAUCE_UERNAME + ":" + SAUCE_AUTOMATE_KEY + "@ondemand.eu-central-1.saucelabs.com:443/wd/hub";

}
