package javaOOP;

public class Testing_Instance_Variable {

	public static void main(String[] args) {
		Topic_01_Class_Object_Student firstStudent = new Topic_01_Class_Object_Student();

		// Instance variable
		firstStudent.studentID = 1532254;

		System.out.println(Topic_02_Variable_Property_Method.studentAddress);

		Topic_02_Variable_Property_Method.studentAddress = "Ha Noi";
	}

}
