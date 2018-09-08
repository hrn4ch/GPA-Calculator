//Hana Nur (hrn4ch)

/**
 * <h1>CourseList</h1>
 * CourseList class holds static methods for dealing with ArrayLists of Courses and @extends Course
 * 
 * @author Hana Nur (hrn4ch)
 */

import java.util.ArrayList;
import java.util.Arrays;

public class CourseList extends Course{
	
	/**
	 * The courseGPA method takes a list of courses and for each course that has a grade that
	 * returns a positive value when using Course.convertLetterGrade() method, and finds the GPA
	 * of those courses. GPA is found by taking the sum of the product of credit hours and grade value
	 * for each course, and dividing this sum by sum of the credit hours of each course.
	 * 
	 * @param courses
	 * @return double GPA
	 */
	public static double currentGPA(ArrayList<Course> courses) {
		double gpa = 0;
		double total = 0.0;
		double creditsSum = 0.0;
		for(Course course: courses) {
			double courseGPA = course.convertLetterGrade(course.getGrade());
			if(courseGPA >= 0 && course.getCreditHours()!=0) {
				total += courseGPA*course.getCreditHours();
				creditsSum += course.getCreditHours();
			}
		}
		gpa = total/creditsSum;
		return gpa;
	}
	
	/**
	 * This method finds the grades needed for courses with blank grades in a list of courses
	 * in order to reach a target GPA. This value is found by multiplying the target GPA by
	 * the total number of credit hours, subtracting this value from the sum of the products 
	 * of the inputed grades their corresponding credit hours, and dividing the difference by
	 * the sum of the blank courses' credit hours.
	 * 
	 * @param courses
	 * @param target
	 * @return double[] targetCourseGrades - The first value is the course grades needed to reach
	 * the target, and the following values are the indices of the blank courses.
	 */
	
	public static double[] targetGPA(ArrayList<Course> courses, double target) {
		double sumScore = 0;
		double sumCreditHours = 0;
		double sumUngradedCreditHours = 0;
		int numUngradedCourses = 0;
		double[] targetCourseGrades = new double[courses.size()+1];		//First value is targetCourseGrades, other values are indices 
		for(Course c: courses) {											//of courses with this target grade
			if(c.convertLetterGrade(c.getGrade()) >= 0) {
				sumScore += c.convertLetterGrade(c.getGrade())*c.getCreditHours();
			}else {
				sumUngradedCreditHours += c.getCreditHours();
				targetCourseGrades[numUngradedCourses+1] = courses.indexOf(c);
				numUngradedCourses++;
				
			}
			sumCreditHours += c.getCreditHours();
		}
		double sumUngradedScore = (target*sumCreditHours)-sumScore;
		double courseGPA = sumUngradedScore/sumUngradedCreditHours;
		targetCourseGrades[0] = courseGPA;
		return targetCourseGrades;
	}
	
}
