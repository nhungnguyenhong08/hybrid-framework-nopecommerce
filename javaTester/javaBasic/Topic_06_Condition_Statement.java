package javaBasic;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_06_Condition_Statement {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	public void TC_01_IF() {
		boolean status = 5 < 3;
		System.out.println(status);
		
		// Hàm If sẽ nhận vào 1 điều kiện ĐÚNG
		// Kiểm tra duy nhất 1 điều kiện
		// Nếu điều kiện này đúng thì sẽ action (xxx) trong phần thân
		if (status) {
			// Đúng thì sẽ vào phần thân của ìf
			// Sai thì bỏ qua
			System.out.println("Go to if");
		}
		
		// Gán (assign)
		int studentNumber = 10;
		
		// == So sách
		status = (studentNumber == 10);
		System.out.println(status);
		
		WebDriver driver = new FirefoxDriver();
		
		WebElement salePopup = driver.findElement(By.id(""));
		// Element luôn luôn có trong DOM dù pop up có hiển thị hay k
		if (salePopup.isDisplayed()) {
		}
		
		// Element k có trong DOM khi popup không hiển thị
		List <WebElement> salePopups = driver.findElements(By.id(""));
		
		// Check element k hiển thị 
		if (salePopups.size()>0 && salePopups.get(0).isDisplayed()) {
			
		}

		// Uncheck to checkbox
		WebElement languageCheckbox = driver.findElement(By.id(""));
		if (languageCheckbox.isSelected()) {
			languageCheckbox.click();
			
		}
		
		// Check to checkbox
		if (!languageCheckbox.isSelected()) {
			languageCheckbox.click();
			
		}
	}
	

	public void TC_02_IF_ELSE() {
		// Có tới 2 điều kiện: Nếu như đúng thì vào if - sai thì vào else
		
		// Nếu driver start với browser như Chrome/ Firefox/ Edge/ Safari thì dùng hàm Click
		// thông thường (builtin) của Selenium WebElement
		
		// Nếu driver là IE thì dùng làm click của JavascriptExecutor
		
		/*
		 * System.setProperty("webdriver.ie.driver", projectPath +
		 * "\\browserDrivers\\IEDriverServer.exe"); driver = new
		 * InternetExplorerDriver();
		 */
		
		  System.setProperty("webdriver.chrome.driver", projectPath +
		  "\\browserDrivers\\chromedriver.exe"); 
		  driver = new ChromeDriver();
		 
		  System.out.println(driver.toString());
		  
		if (driver.toString().contains("internet explorer")) {
			System.out.println("Click by Javascript Executor");
		} else {
			System.out.println("Click by Selenium WebDriver");

		}
	}
	
	
	@Parameters("browser")
	@Test
	public void TC_03_IF_ELSE_IF_ELSE(String browserName) {
		// Có nhiều điều kiện
		// Best practise: k nên dùng if-else quá nhiều 
		
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath +
			"\\browserDrivers\\chromedriver.exe"); 
			driver = new ChromeDriver();	
		} else if (browserName.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", projectPath +
			  "\\browserDrivers\\geckodriver.exe"); 
			  driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")){
			System.setProperty("webdriver.edge.driver", projectPath +
			  "\\browserDrivers\\msedgedriver.exe"); 
			  driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("ie")){
			System.setProperty("webdriver.ie.driver", projectPath +
			  "\\browserDrivers\\IEDriverServer.exe"); 
			  driver = new InternetExplorerDriver();
		}else {//Safari/ Opera/ Coccoc/...
			throw new RuntimeException("Please input correct browser name!");
		}
		System.out.println(browserName);
		System.out.println(driver.toString());
		
		driver.quit();
		
		// if-else
		int age = 20;
		String access = (age<18)? "You can not access":"Welcome to our system!";
		
		if (age <18) {
			access = "You can not access";
			
		} else {
			access = "Welcome to our system!";
		}
	}


}
