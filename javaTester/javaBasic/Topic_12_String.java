package javaBasic;

import org.testng.annotations.Test;

public class Topic_12_String {
	String courseName = "Automation 16 Testing Advanced 1 4";

	public void TC_01_() {
		char courseNameArr[] = courseName.toCharArray();
		int countUpper = 0;
		int countLower = 0;
		int countNumber = 0;

		for (char character : courseNameArr) {

			// uppercase
			if (character >= 'A' && character <= 'Z') {
				countUpper++;
			}

			// lowercase
			if (character >= 'a' && character <= 'z') {
				countLower++;
			}

			// number
			if (character >= '0' && character <= '9') {
				countNumber++;
			}
		}
		System.out.println("Sum of uppercase = " + countUpper);
		System.out.println("Sum of lowercase = " + countLower);
		System.out.println("Sum of number = " + countNumber);
	}

	public void TC_02_() {
		char courseNameArr[] = courseName.toCharArray();
		int count = 0;
		for (char c : courseNameArr) {
			if (c == 'a') {
				count++;
			}
		}
		System.out.println(count);
	}

	@Test
	public void TC_03_Reverse() {
		char courseNameArr[] = courseName.toCharArray();

		for (int i = courseNameArr.length - 1; i >= 0; i--) {
			System.out.println(courseNameArr[i]);

		}
	}
}
