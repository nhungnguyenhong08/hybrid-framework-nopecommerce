package javaBasic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Topic_02_Data_Type {
	// Primitive type/ value type:nguyên thủy
	// - Kiểu số nguyên
	byte bNumber = 6;
	
	short sNumber = 11536;
	
	int iNumber = 2156546;
	
	long lNumber= 652223;
	
	// - Kiểu số thực
	float fNumber = 5.98f;
	
	double dNumber = 5.98d;
	
	// - Kiểu boolean
	boolean bStatus = false;
	
	// - Kiểu char
	char cChar = '1';
	
	
	// Reference type: Tham chiếu
	
	// String
	String address = "Ho Chi Minh";
	
	// Array
	String [] studentAddress = {address, "Ha Noi", "Da Nang"};
	Integer[] studentNumber = {15,20,50};
	
	// Class
	Topic_02_Data_Type topic;
	
	// Interface
	WebDriver driver;
	
	// Object
	Object aObject;
	
	// Collectiton
	// List/Set/Queue/Map
	List<WebElement> homepageLinks = driver.findElements(By.tagName("a")); // cho phép lưu trùng
	Set<String> allWindows = driver.getWindowHandles(); // không lưu trùng
	List<String> productName = new ArrayList<String>();
	
	

	public static void main (String[]args) {
		
	}
}
