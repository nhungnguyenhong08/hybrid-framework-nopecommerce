package javaOOP;

public class Topic_03_Method {

	// Non-static
	void showCarName() {
		System.out.println("Huyndai Tucson");
	}

	static void showCarColor() {
		System.out.println("White");
	}

	public static void main(String[] args) {
		// Gọi vào trong 1 hàm static khác được
		showCarColor();

		// Gọi qua instance/ object (khi gọi hàm non-static)
		Topic_03_Method object = new Topic_03_Method();
		object.showCarName();

	}

}
