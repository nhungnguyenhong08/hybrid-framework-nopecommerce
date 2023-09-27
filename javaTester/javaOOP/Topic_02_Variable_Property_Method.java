package javaOOP;

public class Topic_02_Variable_Property_Method {
	static int studentNumber;
	static float studentPrice;
	static String studentCountry;
	static boolean studentStatus;

	// Global variable
	private String studentName = "Automation FC";

	// Static variable: dùng và gán lại được
	public static String studentAddress = "Ho Chi Minh";

	// Dùng trong phạm vi class này (cho tất cả các instance/object dùng)
	private static String studentPhone = "0956552145";

	// Final variable: không cho phép lại lại/ k override lại
	// Cố định dữ liệu không được phép thay đổi trong quá trình chạy
	final String country = "Viet Nam";

	// Static final variable: hằng số (constant)
	// Nó không được ghi đè
	// Có thể truy cập rộng rãi cho tất cả các instance/thread
	static final float PI_NUMBER = 3.1423544f;

	// Access modifier: default (package - chỉ dùng trong package javaOOP)
	int studentID = 100056;

	// Hàm (method) - static
	public static void main(String[] args) {
		// Local variable - biến cục bộ: hàm
		String studentName = "Autonmation FC";

		if (studentName.startsWith("Automation")) {
			// Local variable - biến cục bộ: block code
			int number = 100;
		}
		studentAddress = "Da Nang";
		studentPhone = "0315242541";

		// Local variable: bắt buộc phải khởi tạo mới được sử dụng
		System.out.println(studentNumber);
		System.out.println(studentPrice);
		System.out.println(studentCountry);
		System.out.println(studentStatus);
	}

	// Constructor
	public Topic_02_Variable_Property_Method() {
		// Local variable - biến cục bộ: constructor
		String studentName = "Automation FC";

		if (this.studentName.startsWith("Automation")) {

		}
	}

	// Hàm (method) - non static
	public void display() {
		// Local variable - biến cục bộ: hàm
		String studentName = "Automation FC";
	}
}
