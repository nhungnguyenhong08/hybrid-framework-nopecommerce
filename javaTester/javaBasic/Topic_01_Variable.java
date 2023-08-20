package javaBasic;

import java.util.Scanner;

public class Topic_01_Variable {
	
	// Hàm khởi tạo
	public Topic_01_Variable(){
	}
	
	// Biến global
	// K cần khởi tạo object mà chỉ cần dùng thông qua gọi ten_class.ten_bien
	static int studentNumber;
	static boolean statusValue;
	
	// static + final => hằng số(constant): phải viết hoa và phân cách bởi dấu _
	static final String BROWSER_NAME = "Chrome";
	
	static int studentPrice;
	
	String studentName ="Automation FC";
	
	public static void main (String []agrs) {
		
		  System.out.println(studentNumber); System.out.println(statusValue);
		  System.out.println(Topic_01_Variable.BROWSER_NAME);
		  System.out.println(Topic_01_Variable.statusValue);
		  System.out.println(Topic_01_Variable.studentNumber);
		 
		
		int studentPrice =5;
		System.out.println(studentPrice);
		
		// Khai báo 1 object 
		// Để dùng hàm global thông qua 1 object đã được khởi tạo
		Topic_01_Variable topic = new Topic_01_Variable();
		System.out.println(topic.studentName);
		
		System.out.println(Topic_01_Variable.BROWSER_NAME);
		
		
		// input:khi nào run thì mới nhập dữ liệu vào thông qua console
		Scanner scanner = new Scanner (System.in);
		String name = scanner.nextLine();
		System.out.println(name);
		System.out.println(name);
		System.out.print(name);
		System.out.print(name);
	}
	
	// Getter: getCurrentUrl/getTittle/getAttribute/getText/getSize/getCSSValue/getLocation/getPossition/...
	public String getStudentName(){
		return this.studentName;
	}
	
	// Setter: click/sendkey/clear/select/back/forward/refresh/get/....(liên quan tới các action)
	public void setStudentName (String stdName) {
		this.studentName = stdName;
	}
}
