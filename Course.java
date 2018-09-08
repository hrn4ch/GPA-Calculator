//Hana Nur (hrn4ch)

/**
 * <h1>Course</h1>
 * Course class holds method for dealing with courses that is used in GPA Calculator.
 * 
 * @author Hana Nur (hrn4ch)
 */

import java.util.ArrayList;

public class Course {
	
	private String courseName;
	private double creditHours;
	private String grade;

	/**
	 * Constructs a course when passed nothing, default.
	 */
	public Course() {
		courseName = "";
		creditHours = 3;
		grade = "";
	}
	/**
	 * Constructs a Course when passed a course name, credit hours, and grade
	 * @param courseName
	 * @param creditHours
	 * @param grade
	 */
	public Course(String courseName, double creditHours, String grade) {
		this.courseName = courseName;
		this.creditHours = creditHours;
		this.grade = grade;
	}
	
	/**
	 * Constructs a course when only passed a course grade. 
	 * Sets course name to empty string and credit hours to 3.
	 * @param grade
	 */
	public Course(String grade) {
		courseName = "";
		creditHours = 3;
		this.grade = grade;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public double getCreditHours() {
		return creditHours;
	}

	public void setCreditHours(double creditHours) {
		this.creditHours = creditHours;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	/**
	 * Converts the letter grade to the corresponding GPA value from 1.0-4.0.
	 * If the grade does not have a corresponding GPA value, the method returns
	 * -1.0 as the GPA.
	 * @param grade
	 * @return double GPA value
	 */
	public double convertLetterGrade(String grade) {
		if(grade.equals("A+") || grade.equals("A")) {
			return 4.0;
		}else if(grade.equals("A-")) {
			return 3.7;
		}else if(grade.equals("B+")) {
			return 3.3;
		}else if(grade.equals("B")) {
			return 3.0;
		}else if(grade.equals("B-")) {
			return 2.7;
		}else if(grade.equals("C+")) {
			return 2.3;
		}else if(grade.equals("C")) {
			return 2.0;
		}else if(grade.equals("C-")) {
			return 1.7;
		}else if(grade.equals("D+")) {
			return 1.3;
		}else if(grade.equals("D")) {
			return 1.0;
		}else if(grade.equals("F")){
			return 0.0;
		}else {
			return -1.0;
		}
	}

	/**
	 * Course toString() method. Prints out the course name, credit hours, and grade, respectively. 
	 */
	@Override
	public String toString() {
		return "Course [courseName=" + courseName + ", creditHours=" + creditHours + ", grade=" + grade + "]";
	}
	
	

}
