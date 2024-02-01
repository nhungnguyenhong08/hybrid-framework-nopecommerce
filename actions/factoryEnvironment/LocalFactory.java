package factoryEnvironment;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LocalFactory {
	private String browserName;
	private WebDriver driver;

	public LocalFactory(String browserName) {
		this.browserName = browserName;
	}

	public WebDriver createDriver() {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		switch (browserList) {
		case CHROME:
			WebDriverManager.chromedriver().setup();
			File file = new File(GlobalConstants.PROJECT_PATH + "\\browserExtensions\\Google-Dịch.crx");
			ChromeOptions options = new ChromeOptions();
			options.addExtensions(file);
			options.setAcceptInsecureCerts(true);
			driver = new ChromeDriver(options);
			break;
		case H_CHROME:
			WebDriverManager.chromedriver().setup();
			ChromeOptions options1 = new ChromeOptions();
			options1.addArguments("--headless");
			options1.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(options1);
			break;
		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();

			// Add extention to Firefox
			FirefoxProfile profile = new FirefoxProfile();
			File translate = new File(GlobalConstants.PROJECT_PATH + "\\browserExtensions\\to_google_translate-4.2.0.xpi");
			profile.addExtension(translate);
			profile.setAcceptUntrustedCertificates(true);
			profile.setAssumeUntrustedCertificateIssuer(false);
			FirefoxOptions options4 = new FirefoxOptions();
			options4.setProfile(profile);

			driver = new FirefoxDriver(options4);
			break;
		case H_FIREFOX:
			WebDriverManager.chromedriver().setup();
			FirefoxOptions options2 = new FirefoxOptions();
			options2.addArguments("--headless");
			options2.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(options2);
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
			ChromeOptions options3 = new ChromeOptions();

			if (GlobalConstants.OS_NAME.startsWith("Windows")) {
				options3.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");

			} else {
				options3.setBinary("...");
			}
			driver = new ChromeDriver(options3);
			break;
		default:
			throw new RuntimeException("Please enter the correct Browser name");
		}
		return driver;
	}
}
