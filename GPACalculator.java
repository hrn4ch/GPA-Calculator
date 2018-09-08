//Hana Nur (hrn4ch)
/**
 * <h1>GPA Calculator</h1>
 * GPA Calculator Program will display GPA for inputed courses and determine grades
 * to get target GPA.
 * 
 * @author Hana Nur (hrn4ch)
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GPACalculator  {

	private JFrame frame;
	private JPanel p1, p2;
	private JLabel courseLabel, creditHourLabel, gradeLabel, gpaLabel, targetGPALabel;
	private JTextField inputCourse, inputCreditHours, gpa, targetGPA, warning;
	private JComboBox<String> inputGrade;
	private JButton submitBtn, addEmptyCreditHours, removeOne, removeAll, requiredGrades;
	private JTable table;
	private ArrayList<Course> coursesTaken = new ArrayList<Course>();
	private String[] gradeLetters = { "Select", "A+", "A", "A-", "B+", "B", 
			"B-", "C+", "C", "C-", "D+", "D", "F" };
	/**Source: http://web.mit.edu/6.005/www/sp14/psets/ps4/java-6-tutorial/components.html*/

	/**
	 * This is the main method. The main method only creates a new instance of the GPACalculator, which uses the
	 * initialize method to make the GUI.
	 * @param args
	 */
	public static void main(String[] args) {
		new GPACalculator();
	}

	/**
	 * This is the GPACalculator constructor. The GPA Calculator GUI instances are made using this constructor.
	 */
	public GPACalculator() {
		initialize();
	}

	/**
	 * This is the initialize method that initializes all components of the GPA Calculator GUI. Also, all action handlers
	 * are handled in the initialize method, and the Course and CourseList classes are used in this method.
	 */

	public void initialize() {

		/**Create frame*/
		frame = new JFrame(); 
		frame.setLayout(new BorderLayout()); /**Use border layout for panels*/
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("GPA Calculator");	
		frame.setDefaultLookAndFeelDecorated(true);

		/**Fit frame to screen*/
		int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);

		/**Initialize gpa component used in both panels*/
		gpa = new JTextField();



		/**
		 *PANEL 1: Courses
		 *This panel is for the user input (course name, credit hours, and grade) and table of courses.
		 *Inputting course name and grade is optional, credit hours is necessary to add course to table of 
		 *courses taken/to take.
		 *
		 *Buttons to add course using inputed data and button to add five blank 3 credit courses to table
		 *of courses taken are also included in first panel.
		 *
		 *GridBagLayout used in panel 1 for easy design and simple layout.
		 */

		p1 = new JPanel();
		p1.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;


		/** Course name input label
		 * Initialize, set GridBag constraints, and add to panel 1*/
		courseLabel = new JLabel("Course Name");
		courseLabel.setFont(new Font("DialogInput", Font.BOLD, 30));
		c.gridx = 0;	
		c.gridy = 0;
		c.insets = new Insets(10,10,10,10);
		p1.add(courseLabel, c);

		/**Label is above text field, assuming user inputs course name
		 * in the text field below the label.
		 * */

		/** Credit hour input label
		 * Initialize, set GridBag constraints, and add to panel 1*/
		creditHourLabel = new JLabel("Credit Hours");
		creditHourLabel.setFont(new Font("DialogInput", Font.BOLD, 30));
		c.gridx = 1;
		c.gridy = 0;  
		p1.add(creditHourLabel, c);

		/**Label is above text field, assuming user inputs credit hours
		 * in the text field below the label.
		 * */

		/**Letter grade label
		 * Initialize, set GridBag constraints, and add to panel 1*/
		gradeLabel = new JLabel("Grade Letter");
		gradeLabel.setFont(new Font("DialogInput", Font.BOLD, 30));
		c.gridx = 2;
		c.gridy = 0;
		p1.add(gradeLabel, c);

		/**Label is above combobox for letter grade*/

		/**Text field for inputting course name
		 * Initialize, set GridBag constraints, and add to panel 1*/
		inputCourse = new JTextField("");
		c.gridx = 0;
		c.gridy = 1;
		inputCourse.setFont(new Font("DialogInput", Font.PLAIN, 18));
		p1.add(inputCourse, c);

		/**Text field for inputting credit hours
		 * Initialize, set GridBag constraints, and add to panel 1*/
		inputCreditHours = new JTextField("");
		c.gridx = 1;
		c.gridy = 1;
		inputCreditHours.setFont(new Font("DialogInput", Font.PLAIN, 18));
		p1.add(inputCreditHours, c);

		/**Assuming user inputs a number in the credit hour field to add course*/

		/**Combobox for selecting letter grade
		 * Initialize, set GridBag constraints, and add to panel 1*/
		inputGrade = new JComboBox<String>(gradeLetters);
		c.gridx = 2;
		c.gridy = 1;
		p1.add(inputGrade, c);

		/**Assuming user selects the letter grade for that class or user leaves 
		 * combobox on "Select" to add a class with no grade.*/

		/**Submit button for adding a course
		 * Initialize, set GridBag constraints, and add to panel 1*/
		submitBtn = new JButton("Add Course");
		c.gridx = 3;
		c.gridy = 1;
		p1.add(submitBtn, c);

		/**Add Empty Credit Hours button adds five 3 credit courses with no course names or grades
		 * Initialize, set GridBag constraints, and add to panel 1*/
		addEmptyCreditHours = new JButton("Add 15 Blank Credit Hours");
		c.gridx = 3;
		c.gridy = 2;
		p1.add(addEmptyCreditHours, c);

		/**Creates a table model that cannot be edited to display the courses
		 * Source: https://www.thoughtco.com/defaulttablemodel-overview-2033890*/
		String[] tableHeader = new String[]{"Course Name", "Credit Hours", "Grade"};
		DefaultTableModel model = new DefaultTableModel(tableHeader, 0) {
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};

		/**JTable of courses added
		 * Initialize and set edits*/
		table = new JTable(model);
		model.addRow(tableHeader);
		table.setRowHeight(20);
		table.setBackground(Color.pink);
		table.getColumnModel().getColumn(0).setWidth(40);
		table.getColumnModel().getColumn(1).setWidth(40);
		table.getColumnModel().getColumn(2).setWidth(40);
		table.getShowHorizontalLines();
		table.getShowVerticalLines();
		table.setRowHeight(40);
		table.setGridColor(Color.BLACK);
		table.setIntercellSpacing(new Dimension(20,20));
		table.setFont(new Font("Dialog", Font.PLAIN, 20));

		/**This Action Listener will make a new course using the arguments inputed/selected by
		 * inputCourse, inputCreditHours, and inputGrade. This new course is then added to the JTable 
		 * of courses by pressing the submitBtn that says "Add Course"*/
		submitBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event) {
				if(!inputCreditHours.getText().trim().equals("")) {
					String courseName = inputCourse.getText().trim();
					double creditHours = Double.parseDouble(inputCreditHours.getText().trim());
					String grade = (String)inputGrade.getSelectedItem();
					Course newCourse = new Course(courseName, creditHours, grade); /**Values inputed/selected are used as arguments*/
					coursesTaken.add(newCourse);	
					Object[] data = new Object[3];
					for(Course c: coursesTaken) {
						data[0] = c.getCourseName();		/**Puts course name in first column*/
						data[1] = c.getCreditHours();	/**Puts credit hours in second column*/
						if(c.convertLetterGrade(c.getGrade())>0) {
							data[2] = c.convertLetterGrade(c.getGrade());	/**Puts grade in third column*/
						}else {
							data[2] = "";	/**Puts empty string in third column if grade not specified*/
						}
					}
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					if(model.getRowCount() < 12) {
						model.addRow(data);
					}
					gpa.setText(Double.toString(CourseList.currentGPA(coursesTaken)));	/**Calculate GPA of courses with grade specified*/
				}
			}
		});

		/**Set JTable GridBag constraints and add to panel 1*/
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 3;
		p1.add(table, c);

		/**Add five 3 credit courses with no name or grade to table of courses when addEmptyCreditHours
		 * button is pushed. Also adds those courses to coursesTaken Array List for later gpa/target gpa
		 * calculations.*/
		addEmptyCreditHours.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event) {
				Object[] blankCredit = {"", 3.0, ""};
				for(int i=0; i < 5; i++) {
					if(model.getRowCount() < 12) {
						model.addRow(blankCredit);
						coursesTaken.add(new Course());
					}
				}
			}
		});



		/**
		 * 	PANEL 2 - SUMMARY
		 * 	Includes buttons and actionhandlers for removing one or more course. I placed this here because
		 *  it was more aesthetically pleasing when placed in the second panel.
		 *  
		 *  Target course GPA's and current GPA both calculated in second panel.
		 */


		p2 = new JPanel();
		p2.setBackground(Color.pink);
		p2.setLayout(new GridBagLayout());
		GridBagConstraints c2 = new GridBagConstraints();
		c2.fill = GridBagConstraints.BOTH;

		/**Label for target GPA text field
		 * Initialize, set GridBag constraints, and add to panel 2*/
		targetGPALabel = new JLabel("Input Target GPA");
		c2.gridx = 0;
		c2.gridy = 0;
		targetGPALabel.setFont(new Font("DialogInput", Font.PLAIN, 24));
		p2.add(targetGPALabel, c2);

		/**Label for target GPA text field
		 * Initialize, set GridBag constraints, and add to panel 2*/
		targetGPA = new JTextField("        ");
		c2.gridx = 1;
		c2.gridy = 0;
		targetGPA.setFont(new Font("DialogInput", Font.PLAIN, 24));
		p2.add(targetGPA, c2);

		/**Initialize warning for targetGPA text fields*/
		warning = new JTextField();

		/**Button for setting grades needed to achieve target GPA. Changes previously
		 * empty grades cells to show grade needed to get target GPA. 
		 * Initialize, set GridBag constraints, and add to panel 2*/
		requiredGrades = new JButton("Calculate Grades Required");
		c2.gridx = 1;
		c2.gridy = 1;
		requiredGrades.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event) {
				double target = 0.0;
				if(!targetGPA.getText().trim().equals("")) {
					target = Double.parseDouble(targetGPA.getText().trim()); 	/**Get inputed targetGPA value*/
				}
				double[] index = CourseList.targetGPA(coursesTaken, target); 	/**Call targetGPA() method, see CourseList.java for details*/
				if(index[0] <= 4.00000 && index[0] >= 2.00000) {	/**Set blank grades to required grade for targetGPA if below 4.0 and above 2.0*/
					for(int i=1; i < index.length; i++) {
						model.setValueAt(index[0], (int)(index[i]+1), 2);
					}
					warning.setText("");
				}else if(index[0] > 4.0) { 	/**Display warning if required grades are not below 4.0 and above 2.0*/
					warning.setText("WARNING: GPA required is greater than 4.0, try again with more credit hours");
				}else{
					warning.setText("WARNING: GPA required is less than 2.0, try again with less credit hours");
				}
			}	
		});
		p2.add(requiredGrades, c2);

		/**Assuming that the required scores to achieve target GPA should be placed in the list of courses*/

		/**Button for removing the last course added to table of courses. Pressing button will remove
		 * last course from table and coursesTaken ArrayList
		 * Initialize, set GridBag constraints, and add to panel 2*/
		removeOne = new JButton("Remove Last Course");
		c2.gridx = 2;
		c2.gridy = 1;
		removeOne.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event) {
				if(model.getRowCount()>1) {	
					model.removeRow(model.getRowCount()-1);	/**Removes last row/course*/
					coursesTaken.remove(coursesTaken.size()-1);
					gpa.setText(Double.toString(CourseList.currentGPA(coursesTaken)));
				}
			}
		});
		p2.add(removeOne, c2);

		/**Assuming ability of removing one course includes removing just the last course
		 * Assuming removed courses do not affect GPA*/

		/**Button for removing every course added to table of courses. Pressing button will clear 
		 * courses from table and empty coursesTaken ArrayList
		 * Initialize, set GridBag constraints, and add to panel 2*/
		removeAll = new JButton("Remove All Courses");
		c2.gridx = 3;
		c2.gridy = 1;
		removeAll.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event) {
				while(model.getRowCount() > 1) {		/**Remove each course, leaving only header in table*/
					model.removeRow(model.getRowCount()-1);
					coursesTaken.remove(coursesTaken.size()-1);
				}
				gpa.setText(Double.toString(CourseList.currentGPA(coursesTaken)));
			}
		});
		p2.add(removeAll, c2);

		/**Label for target GPA text field
		 * Initialize, set GridBag constraints, and add to panel 2*/
		gpaLabel = new JLabel("Current GPA");
		c2.gridx = 2;
		c2.gridy = 0;
		gpaLabel.setFont(new Font("DialogInput", Font.PLAIN, 24));
		p2.add(gpaLabel, c2);

		/**Set GridBag constraints for gpa and add to panel 2*/
		c2.gridx = 3;
		c2.gridy = 0;
		gpa.setFont(new Font("DialogInput", Font.PLAIN, 24));
		p2.add(gpa, c2);

		/**Set GridBag constraints for warning and add to panel 2*/
		c2.gridx = 0;
		c2.gridwidth = 4;
		c2.gridy = 2;
		p2.add(warning, c2);

		/**Add panel 1 to the North of the frame, and panel 2 to the South
		 * Set frame to be visible/displayed*/
		frame.add(p1, BorderLayout.NORTH);
		frame.add(p2, BorderLayout.SOUTH);
		frame.setVisible(true);

	}

}
