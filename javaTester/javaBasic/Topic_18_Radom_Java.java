package javaBasic;

import java.util.Calendar;
import java.util.Random;

public class Topic_18_Radom_Java {

	public static void main(String[] args) throws InterruptedException {
		System.out.println(getRandomNumber());
		Thread.sleep(10000);

		System.out.println(getRandomNumber(10, 99));
		Thread.sleep(10000);

		System.out.println(getGenerateRandomNumber());
		Thread.sleep(10000);

		System.out.println(gerRandomEmail());
		Thread.sleep(10000);

		System.out.println(getRandomNumberByDateTime());
		Thread.sleep(10000);

	}

	// Random 3 numbers
	public static int getRandomNumber() {
		int uLimit = 999;
		int lLimit = 100;
		Random rand = new Random();
		return lLimit + rand.nextInt(uLimit - lLimit);
	}

	// Range (minimum - maximum)
	public static int getRandomNumber(int minimum, int maximum) {
		Random rand = new Random();
		return minimum + rand.nextInt(maximum - minimum);
	}

	public static int getGenerateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	public static String gerRandomEmail() {
		return "automation" + getRandomNumberByDateTime() + "@live.com";
	}

	// no duplicate
	public static long getRandomNumberByDateTime() {
		return Calendar.getInstance().getTimeInMillis() % 100000;
	}
}
