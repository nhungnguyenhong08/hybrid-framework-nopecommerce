package javaOOP;

public class Topic_01_Class_Object_Student {
	private int studentID;
	private String studentName;
	private Float knowledgePoint;
	private Float practicePoint;

	private int getStudentID() {
		return studentID;
	}

	private void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	private String getStudentName() {
		return studentName;
	}

	private void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	private Float getKnowledgePoint() {
		return knowledgePoint;
	}

	private void setKnowledgePoint(Float knowledgePoint) {
		this.knowledgePoint = knowledgePoint;
	}

	private Float getPracticePoint() {
		return practicePoint;
	}

	private void setPracticePoint(Float practicePoint) {
		this.practicePoint = practicePoint;
	}

	private Float getAveragePoint() {
		return (this.knowledgePoint + this.practicePoint * 2) / 3;
	}

	private void showStudentInfor() {
		System.out.println("*********************************");
		System.out.println("Studen ID = " + getStudentID());
		System.out.println("Studen name = " + getStudentName());
		System.out.println("Studen knowledgePoint = " + getKnowledgePoint());
		System.out.println("Studen praceticePoint = " + getPracticePoint());
		System.out.println("Studen averagePoint = " + getAveragePoint());
		System.out.println("*********************************");

	}

	public static void main(String[] args) {
		Topic_01_Class_Object_Student firstStudent = new Topic_01_Class_Object_Student();

		firstStudent.setStudentID(26544);
		firstStudent.setStudentName("John Terry");
		firstStudent.setKnowledgePoint(8.0f);
		firstStudent.setPracticePoint(8.5f);
		firstStudent.showStudentInfor();

		Topic_01_Class_Object_Student secondStudent = new Topic_01_Class_Object_Student();
		secondStudent.setStudentID(26545);
		secondStudent.setStudentName("Tom Jay");
		secondStudent.setKnowledgePoint(7.0f);
		secondStudent.setPracticePoint(7.8f);
		secondStudent.showStudentInfor();

	}
}
