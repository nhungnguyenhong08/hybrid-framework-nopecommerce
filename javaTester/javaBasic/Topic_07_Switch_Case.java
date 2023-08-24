package javaBasic;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

public class Topic_07_Switch_Case {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Scanner scanner = new Scanner (System.in);
	
	@Parameters("browser")
	
	public void TC_01_Switch_Case(String browserName) {
		driver = getBrowserDriver(browserName);
		
		System.out.println(browserName);
		
		driver.quit();
	}
		// Có nhiều điều kiện
		// Best practise: k nên dùng if-else quá nhiều 
		// Dùng switch case
	
	
	

	public void TC_02_(){
		int month = scanner.nextInt();
		
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			System.out.println("Tháng này có 31 ngày");
			break;
		case 2:
			System.out.println("Tháng này có 28 hoặc 29 ngày");
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			System.out.println("Tháng này có 30 ngày");
			break;
		default:
			System.out.println("Vui lòng nhập tháng trong khoảng 1-12!");
			break;
		}
	}
	

	public void TC_03_(){
	int number = scanner.nextInt();
		
		switch (number) {
		case 1:
			System.out.println("One");
			break;
		case 2:
			System.out.println("Two");
			break;
		case 3:
			System.out.println("Three");
			break;
		case 4:
			System.out.println("Four");
			break;
		case 5:
			System.out.println("Five");
			break;
		case 6:
			System.out.println("Six");
			break;
		case 7:
			System.out.println("Seven");
			break;
		case 8:
			System.out.println("Eight");
			break;
		case 9:
			System.out.println("Nine");
			break;
		case 10:
			System.out.println("Ten");
			break;
	
		}
	}
	
	
	
	@Test
	public void TC_04_(){
		String operator= scanner.nextLine();
		int firstNumber = scanner.nextInt();
		int secondNumber = scanner.nextInt();
		
		System.out.println(firstNumber);
		System.out.println(secondNumber);
		System.out.println("--" + operator + "--");
		
		switch (operator) {
		case "+":
			System.out.println("A + B  = " + (firstNumber + secondNumber));
			break;
		case "-":
			System.out.println("A - B  = " + (firstNumber - secondNumber));
			break;
		case "*":
			System.out.println("A * B  = " + (firstNumber * secondNumber));
			break;
		case "/":
			System.out.println("A / B  = " + (firstNumber / secondNumber));
			break;
		case "%":
			System.out.println("A % B  = " + (firstNumber % secondNumber));
			break;
		}
	}
	public WebDriver getBrowserDriver (String browserName) {
		switch (browserName) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", projectPath +
					"\\browserDrivers\\chromedriver.exe"); 
			driver = new ChromeDriver();	
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", projectPath +
					  "\\browserDrivers\\geckodriver.exe"); 
			driver = new FirefoxDriver();
			break;
			
		case "edge":
			System.setProperty("webdriver.edge.driver", projectPath +
					  "\\browserDrivers\\msedgedriver.exe"); 
			driver = new EdgeDriver();
			break;
			
		default:
			new RuntimeException("Please input correct browser name!");
			break;
		}
		return driver;
	}
		
}
