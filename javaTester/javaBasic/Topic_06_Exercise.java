package javaBasic;

import java.util.Locale;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Topic_06_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Scanner scanner = new Scanner (System.in);

	
	
	public void TC_01() {
		
		int number = scanner.nextInt();
		
		if (number % 2 ==0) {
			System.out.println("So:" + number + " " + "là số chẵn");
		}else {
			System.out.println("So:" + number + " " + "là số lẻ");
		}
	}
	
	public void TC_02() {
		int numberA = scanner.nextInt();
		int numberB = scanner.nextInt();
		
		if (numberA > numberB) {
			System.out.println(numberA + " lớn hơn " + numberB);
		}else if(numberA < numberB) {
			System.out.println(numberA + " nhỏ hơn " + numberB);
		}else {
			System.out.println(numberA + " bằng " + numberB);
			
		}
	}
	

	public void TC_03() {
		String firstStudent = scanner.nextLine();
		String secondStudent = scanner.nextLine();
		
		// Reference: String
		// Kiểm tra cái value của biến
		// Kiểm tra vị trí của biến trong vùng nhớ
		if (firstStudent.equals(secondStudent)) {
			System.out.println("Hai sinh viên giống tên nhau");
		}else {
			System.out.println("Hai sinh viên khác tên nhau");
			
		}
		
		// Primiriver type
		// Chỉ kiểm tra giá trị nên có thể vùng nhớ 2 biến khác nhau 
		// => cùng tên nhưng chạy testcase lại rơi vào else
		if (firstStudent == secondStudent) {
			System.out.println("Hai sinh viên giống tên nhau");
		}else {
			System.out.println("Hai sinh viên khác tên nhau");
			
		}
	}
	

	public void TC_04() {
		
		int numberA = scanner.nextInt();
		int numberB = scanner.nextInt();
		int numberC = scanner.nextInt();
		
		if (numberA > numberB && numberA > numberC) {
			System.out.println(numberA + " là số lớn nhất.");
		} else if (numberB > numberC) {
			System.out.println(numberB + " là số lớn nhất.");
		} else {
			System.out.println(numberC + " là số lớn nhất.");
		} {

		}

	}
	
	
	public void TC_05() {
		int numberA = scanner.nextInt();
		
		if (10 <=  numberA && numberA <= 100) {
			System.out.println(numberA + " nằm trong khoảng [10..100]");
		} else {
			System.out.println(numberA + " nằm ngoài khoảng [10..100]");
		}
	}

	
	public void TC_06() {
		scanner.useLocale(Locale.ENGLISH);
		float studentPoint = scanner.nextFloat();
		
		
		if (studentPoint >= 8.5 && studentPoint <= 10) {
			System.out.println("Hệ số A");
		} else if (studentPoint < 8.5 && studentPoint >= 7.5) {
			System.out.println("Hệ số B");
		} else if (studentPoint < 7.5 && studentPoint >= 5) {
			System.out.println("Hệ số C");
		} else if (studentPoint < 5 && studentPoint >= 0) {
			System.out.println("Hệ số D");
		} else {
			System.out.println("Bạn vui lòng nhập điểm lại");
		}
	}

	@Test
	public void TC_07() {
		int month = scanner.nextInt();
		 
		// Tháng 1 3 5 7 8 10 12
		if ( month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			System.out.println("Tháng này có 31 ngày");
		} else if (month == 2) {
			System.out.println("Tháng này có 28 hoặc 29 ngày");
		} else {
			System.out.println("Tháng này có 30 ngày");
		} 
	}
}


