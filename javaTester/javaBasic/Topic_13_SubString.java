package javaBasic;

public class Topic_13_SubString {
	public static void main(String[] args) {
		String firstText = "id=Email";
		String sencondText = "css=input[id='Password']";
		String thirdText = "xpath=//button[text()='Log in']";
		System.out.println(firstText.substring(3));
		System.out.println(sencondText.substring(4));
		System.out.println(thirdText.substring(6));
	}
}
