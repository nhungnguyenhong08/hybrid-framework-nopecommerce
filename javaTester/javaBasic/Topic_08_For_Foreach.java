package javaBasic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Topic_08_For_Foreach {

	WebDriver driver;
	
	
	public void TC_01_For_Iterate() {
		// Array
		String[] cityName = {"Ha Noi", "Ho Chi Minh", "Da Nang", "Can Tho"};
		
		// Array/ List/ Set/ Queue (index)
		// Index bắt đầu từ 0
		
		// For kết hợp if: Thỏa mãn điều kiện mới action
		// Biến đếm - dùng làm điều kiện để filter
		
		for (int i = 0; i < cityName.length; i++) {
			if (cityName[i].equals("Da Nang")) {
			// Action
				System.out.println("Do action!");
				break;
			}
		
		}
		
		// For dùng để chạy qua hết tất cả các giá trị
		for (String city : cityName) {
			}
		
		}
	
	@Test
	public void TC_02_For_Each() {
		String[] cityName = {"Ha Noi", "Ho Chi Minh", "Da Nang", "Can Tho"};
		// Java collection
		// Class: ArrayList/ LinkedList/ Vector/..
		// Interface: List/ Set/ Queue
		List<String> cityAddress = new ArrayList<String>();
		System.out.println(cityAddress.size());
		
		// Complie (Coding)
		cityAddress.add("Ha Giang");
		cityAddress.add("Thai Binh");
		cityAddress.add("Hai Duong");
		
		System.out.println(cityAddress.size());
		
		// Runtime (Running)
		for (String city : cityName) {
			cityAddress.add(city);
		}
		System.out.println(cityAddress.size());
		
		for (String cityAdd : cityAddress) {
			System.out.println(cityAdd);
		}
		
		// Java Generic
		List<WebElement> links = driver.findElements(By.xpath("//a"));
		
		// Xử lý dữ liệu: get text/value/css/attribute
		// k sử dụng cho các action như click chuyển trang vì có thể sẽ refresh lại DOM/HTML
		// => các elememt lưu trong links không còn tồn tại => fail testcase (StaleElementException)
		for (WebElement link : links) {
			
		}
	}
}


