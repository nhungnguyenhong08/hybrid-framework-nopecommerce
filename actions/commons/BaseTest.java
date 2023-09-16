package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;

	protected WebDriver getBrowserDriver(String browserName) {
		switch (browserName) {
		case "chrome":
			driver = WebDriverManager.chromedriver().create();
			break;
		case "firefox":
			driver = WebDriverManager.firefoxdriver().create();
			break;
		case "edge":
			driver = WebDriverManager.edgedriver().create();
			break;
		case "opera":
			driver = WebDriverManager.operadriver().create();
			break;
		case "coccoc":
			// Cốc cốc browser trừ đi 5-6 version ra chromdriver (lấy version của trình duyệt cốc cốc - 5/6)
			WebDriverManager.chromedriver().driverVersion("114.0.5735.90").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(options);
			break;
		default:
			throw new RuntimeException("Please enter the correct Browser name");
		}
		driver.manage().window().setPosition(new Point(0, 0));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(GlobalConstants.PORTAL_PAGE_URL);
		return driver;
	}

	protected int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);

	}
}
