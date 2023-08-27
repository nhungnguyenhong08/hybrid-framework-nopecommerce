package javaBasic;

import java.util.Scanner;

import org.testng.annotations.Test;

public class Topic_09_While_Do_While {
	Scanner scanner = new Scanner(System.in);

	public void TC_01_For() {
		int number = scanner.nextInt();

		for (int i = number; i < 100; i++) {
			// Chia hết cho 2
			if (i % 2 == 0) {
				System.out.println(i);
			}

		}
	}

	public void TC_02_While() {
		int number = scanner.nextInt();

		while (number < 100) {
			if (number % 2 == 0) {
				System.out.println(number);
				number++;
			}

		}
	}

	public void TC_03_Do_While() {
		int number = scanner.nextInt();

		do {
			if (number % 2 == 0) {
				System.out.println(number);
				number++;
			}

		} while (number < 100);

	}

	public void TC_04_While() {
		int numberA = scanner.nextInt();

		// Các số từ a - 100
		while (numberA < 100) {
			// Chia hết cho cả 3 và 5
			if (numberA % 3 == 0 && numberA % 5 == 0) {
				System.out.println(numberA);
			}
			numberA++;
		}
	}

	public void TC_05_While_Tổng_Các_Số_Lẻ() {
		int numberA = scanner.nextInt();
		int i = 0;

		while (numberA > 0) {

			if (numberA % 2 == 1) {
				System.out.println(numberA);
				i += numberA;
			}
			numberA--;
		}
		System.out.println(i);
	}

	@Test
	public void TC_06_While_Giai_Thừa() {
		int numberA = scanner.nextInt();
		int i = 1;

		while (numberA > 0) {
			i *= numberA;
			numberA--;
		}
		System.out.println(i);
	}
}
