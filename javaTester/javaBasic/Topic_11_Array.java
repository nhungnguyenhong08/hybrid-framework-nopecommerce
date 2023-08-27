package javaBasic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

public class Topic_11_Array {
	int number[] = { 3, -7, 2, 5, 9, -6, 10, 12 };

	public static void main(String[] args) {

		int number[] = { 12, 7, 5, 61, 15 };

		int[] student = { 12, 7, 5, 61, 15 };

		// Lấy tra phần tử đầu tiên
		System.out.println(student[0]);

		// Vượt quá bound
		System.out.println(student[5]);

		// Cố định
		// Được define cố định bao nhiều phần tử khi mình viết code (compile)
		String studentName[] = { "Nam", "Long", "An" };

		studentName[0] = "Hoa";

		int b[] = new int[5];
		b[0] = 10;

		// for_iterate
		for (int i = 0; i < studentName.length; i++) {
			if (studentName[i].equals("Long")) {
				System.out.println("Click vào Long");
			}
		}

		// for_each
		for (String std : studentName) {
			if (std.equals("Long")) {
				System.out.println("Click vào Long");
			}
		}

		// Động
		ArrayList<String> stdName = new ArrayList<String>();

		// Khi chạy code mới add (Runtime)
		for (String std : stdName) {
			stdName.add(std);

		}

		// Convert từ array sang ArrayList
		List<String> names = Arrays.asList("Tom", "Jerry", "Donald");
		for (String name : names) {
			System.out.println("Name: " + name);
		}

		// Convert từ array sang toString
		String std_Name = Arrays.toString(studentName);
		System.out.println(std_Name);

	}

	public void TC_01_Find_Max_Number() {
		int x = 0;
		for (int i = 0; i < number.length; i++) {
			if (x < number[i]) {
				x = number[i];
			}
		}
		System.out.println("Max number = " + x);
	}

	public void TC_02_Sum_First_And_Last_Number() {
		System.out.println("Sum =" + (number[0] + number[number.length - 1]));
	}

	public void TC_03_Get_Even_Number() {
		for (int i = 0; i < number.length; i++) {
			if (number[i] % 2 == 0) {
				System.out.println("Even number = " + number[i]);
			}
		}
	}

	@Test
	public void TC_04_Average_All_Number() {
		int sum = 0;
		for (int i = 0; i < number.length; i++) {
			sum += number[i];
		}
		System.out.println("Sum all number = " + sum);

		float average = sum / number.length;
		System.out.println("Average all number = " + average);
	}

}
