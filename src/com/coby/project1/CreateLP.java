package com.coby.project1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreateLP {

	/**
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException,
			IOException {

		// Set up Semesters
		Semesters semester = new Semesters(12);

		// Set up Courses
		boolean[][] courseOfferings = { { true, false, false },
				{ true, true, true }, { true, true, true },
				{ true, true, true }, { false, false, true },
				{ true, true, true }, { true, false, false },
				{ true, true, true }, { true, true, true },
				{ false, false, true }, { true, false, false },
				{ true, true, true }, { true, true, true },
				{ false, false, true }, { true, false, false },
				{ false, false, true }, { true, false, false },
				{ false, false, true } };

		int[][] preRequisites = { { 4, 16 }, { 12, 1 }, { 9, 13 }, { 3, 7 } };

		Courses courses = new Courses(courseOfferings, preRequisites, 18, 3);

		// Setup students
//
//		File student_file = new File(args[0]);
		Students students = new Students("student_schedule.txt");

		// Create LP file
		File file = new File("student_schedule.lp");

		// Create the file if it doesn't exist
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);

		// Objective Statement
		bw.write("Min X");
		bw.newLine();

		// Constraints
		bw.write("Subject To");
		bw.newLine();

		// Enrollment Constraint
		Enrollment enrollment = new Enrollment();
		enrollment.getParameters(semester, courses, students);
		enrollment.writeConstraints(bw);

		// Course Availability and Course Capacity Constraint
		CourseAvailabilityCapacity courseAvailabilityCapacity = new CourseAvailabilityCapacity();
		courseAvailabilityCapacity.getParameters(semester, courses, students);
		courseAvailabilityCapacity.writeConstraints(bw);

		// PreReq Constraint
		PreReq preReq = new PreReq();
		preReq.getParameters(semester, courses, students);
		preReq.writeConstraints(bw);

		// Student Schedule Constraint
		StudentSchedule studentSchedule = new StudentSchedule();
		studentSchedule.getParameters(semester, courses, students);
		studentSchedule.writeConstraints(bw);

		// Variables
		bw.write("Binary");
		bw.newLine();
		for (int i = 1; i <= students.getNumberStudents(); i++) {
			for (int j = 1; j <= courses.getNumCourses(); j++) {
				for (int k = 1; k <= semester.getNumberSemesters(); k++) {
					bw.write("y" + i + "_" + j + "_" + k);
					bw.newLine();
				}
			}
		}
		bw.write("end");
		bw.newLine();

		// Close the files
		bw.close();
		fw.close();
		
		// Run Gurobi
		StringBuffer output = new StringBuffer();
		Process p;
		try {
			p = Runtime.getRuntime().exec("gurobi_cl ResultFile=student_schedule.sol student_schedule.lp");
//			p = Runtime.getRuntime().exec("ping google.com");
			p.waitFor();
			BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));
 
                        String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
