package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;

	protected final Logger log;

	public BaseTest() {
		log = LogManager.getLogger(getClass());
	}

	protected WebDriver getBrowserDriver(String browserName) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		switch (browserList) {
		case CHROME:
			driver = WebDriverManager.chromedriver().create();
			break;
		case FIREFOX:
			driver = WebDriverManager.firefoxdriver().create();
			break;
		case EDGE:
			driver = WebDriverManager.edgedriver().create();
			break;
		case OPERA:
			driver = WebDriverManager.operadriver().create();
			break;
		case COCCOC:
			// Cốc cốc browser trừ đi 5-6 version ra chromdriver (lấy version của trình duyệt cốc cốc - 5/6)
			WebDriverManager.chromedriver().driverVersion("114.0.5735.90").setup();
			ChromeOptions options = new ChromeOptions();

			if (GlobalConstants.OS_NAME.startsWith("Windows")) {
				options.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");

			} else {
				options.setBinary("...");
			}
			driver = new ChromeDriver(options);
			break;
		default:
			throw new RuntimeException("Please enter the correct Browser name");
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIME_OUT, TimeUnit.SECONDS);
		driver.get(GlobalConstants.PORTAL_PAGE_URL);
		return driver;
	}

	protected WebDriver getBrowserDriver(String browserName, String appUrl) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		switch (browserList) {
		case CHROME:
			driver = WebDriverManager.chromedriver().create();
			break;
		case H_CHROME:
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(options);
			break;
		case FIREFOX:
			driver = WebDriverManager.firefoxdriver().create();
			break;
		case H_FIREFOX:
			WebDriverManager.chromedriver().setup();
			FirefoxOptions options1 = new FirefoxOptions();
			options1.addArguments("--headless");
			options1.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(options1);
			break;
		case EDGE:
			driver = WebDriverManager.edgedriver().create();
			break;
		case OPERA:
			driver = WebDriverManager.operadriver().create();
			break;
		case COCCOC:
			// Cốc cốc browser trừ đi 5-6 version ra chromdriver (lấy version của trình duyệt cốc cốc - 5/6)
			WebDriverManager.chromedriver().driverVersion("114.0.5735.90").setup();
			ChromeOptions options2 = new ChromeOptions();

			if (GlobalConstants.OS_NAME.startsWith("Windows")) {
				options2.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");

			} else {
				options2.setBinary("...");
			}
			driver = new ChromeDriver(options2);
			break;
		default:
			throw new RuntimeException("Please enter the correct Browser name");
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIME_OUT, TimeUnit.SECONDS);
		driver.get(appUrl);
		return driver;
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	protected String getEnvironmentUrl(String environmentName) {
		String envUrl = null;
		EnvironmentList environment = EnvironmentList.valueOf(environmentName.toUpperCase());
		switch (environment) {
		case DEV:
			envUrl = "https://demo.nopcommerce.com/";
			break;
		case TESTTING:
			envUrl = "https://demo.nopcommerce.com/";
			break;
		case STAGING:
			envUrl = "https://demo.nopcommerce.com/";
			break;
		case PRODUCTION:
			envUrl = "https://demo.nopcommerce.com/";
			break;
		default:
			throw new RuntimeException("Please enter the correct Environment name");
		}
		return envUrl;
	}

	protected int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);

	}

	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			// Add lỗi vào ReportNGListener
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}
}
