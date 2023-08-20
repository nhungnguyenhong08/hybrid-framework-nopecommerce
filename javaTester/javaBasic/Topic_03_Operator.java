package javaBasic;

import org.testng.Assert;

public class Topic_03_Operator {
	
	public static void main (String[] args) {
		int number = 10;
		number += 5;
		System.out.println(number);
		
		// 15/5 = 3 (lấy phần nguyên)
		System.out.println(number /5);
		
		// 15%7 = 2 dư 1 (lấy phần dư)
		System.out.println(number %7);
		
		System.out.println(number++);
		// In number ra trước: 15
		// ++ = tăng number lên 1 = 16
		
		System.out.println(++number);
		// ++ trước: tăng number lên 1 = 17
		// In number: 17
		
		for (int i =0 ;i<3; i++) {
			System.out.println(i);
		}
		
		Assert.assertTrue(5<6);
		String address ="Ho Chi Minh";
		
		if (!(address == "Ha Noi")) {
			System.out.println("Address is not the same");	
		}
		
		// Toán tử điều kiện
		boolean status = (address == "Ha Noi")? true : false;
		System.out.println(status);
	}

}
