package com.coby.project1;

import gurobi.GRB;
import gurobi.GRBEnv;
import gurobi.GRBException;
import gurobi.GRBModel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ClassScheduler {

	/**
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException,
			IOException {

		// Set up Semesters
		Semesters semesters = new Semesters(12);

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

		// Students students = new Students("student_schedule.txt");
		if (args.length >= 1) {
			Students students = new Students(args[0]);

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
			enrollment.getParameters(semesters, courses, students);
			enrollment.writeConstraints(bw);

			// Course Availability and Course Capacity Constraint
			CourseAvailabilityCapacity courseAvailabilityCapacity = new CourseAvailabilityCapacity();
			courseAvailabilityCapacity.getParameters(semesters, courses,
					students);
			courseAvailabilityCapacity.writeConstraints(bw);

			// PreReq Constraint
			PreReq preReq = new PreReq();
			preReq.getParameters(semesters, courses, students);
			preReq.writeConstraints(bw);

			// Student Schedule Constraint
			StudentSchedule studentSchedule = new StudentSchedule();
			studentSchedule.getParameters(semesters, courses, students);
			studentSchedule.writeConstraints(bw);

			// Variables
			bw.write("Binary");
			bw.newLine();
			for (int i = 1; i <= students.getNumberStudents(); i++) {
				for (int j = 1; j <= courses.getNumCourses(); j++) {
					for (int k = 1; k <= semesters.getNumberSemesters(); k++) {
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
			try {
				GRBEnv env = new GRBEnv();
				GRBModel model = new GRBModel(env, "student_schedule.lp");

				model.optimize();

				int optimstatus = model.get(GRB.IntAttr.Status);

				if (optimstatus == GRB.Status.INF_OR_UNBD) {
					model.getEnv().set(GRB.IntParam.Presolve, 0);
					model.optimize();
					optimstatus = model.get(GRB.IntAttr.Status);
				}

				if (optimstatus == GRB.Status.OPTIMAL) {
					double objval = model.get(GRB.DoubleAttr.ObjVal);
					System.out.println("Optimal objective: " + objval);
					model.write("student_schedule.sol");
				} else if (optimstatus == GRB.Status.INFEASIBLE) {
					System.out.println("Model is infeasible");

					// Compute and write out IIS
					model.computeIIS();
					model.write("model.ilp");
				} else if (optimstatus == GRB.Status.UNBOUNDED) {
					System.out.println("Model is unbounded");
				} else {
					System.out
							.println("Optimization was stopped with status = "
									+ optimstatus);
				}

				// Dispose of model and environment
				model.dispose();
				env.dispose();

			} catch (GRBException e) {
				System.out.println("Error code: " + e.getErrorCode() + ". "
						+ e.getMessage());
			}
		} else {

			System.err.println("Students file not specified. Use ");
			return;
		}
		// FileReader fr = new FileReader("student_schedule.sol");
		// BufferedReader br = new BufferedReader(fr);
		//
		// // Create schedule file
		// File file_sch = new File("student_schedule_final.txt");
		//
		// // Create the file if it doesn't exist
		// if (!file.exists()) {
		// file.createNewFile();
		// }
		//
		// FileWriter fw_sch = new FileWriter(file_sch.getAbsoluteFile());
		// BufferedWriter bw_sch = new BufferedWriter(fw_sch);
		//
		// int[][][] schedule = new int[students.getNumberStudents()][courses
		// .getNumCourses()][semesters.getNumberSemesters()];
		//
		// String line = br.readLine();
		// for (int i = 0; (line = br.readLine()) != null; i++) {
		// if (i == 0) {
		// String[] tokens = line.split("[ ]+");
		// bw_sch.write("The capacity of students per class needs to be a minimum of: "
		// + tokens[1]);
		// bw_sch.newLine();
		// } else {
		// String[] tokens = line.split("[y_ ]+");
		// int s = Integer.parseInt(tokens[1]);
		// int c = Integer.parseInt(tokens[2]);
		// int sem = Integer.parseInt(tokens[3]);
		// int t = Integer.parseInt(tokens[4]);
		// schedule[s - 1][c - 1][sem - 1] = t;
		// }
		// }
		//
		// for (int i = 0; i < students.getNumberStudents(); i++) {
		// bw_sch.write("Student " + (i + 1) + ":");
		// bw_sch.newLine();
		// for (int j = 0; j < courses.getNumCourses(); j++) {
		// for (int k = 1; k < semesters.getNumberSemesters(); k++) {
		// bw_sch.write(schedule[i][j][k]);
		// bw_sch.newLine();
		// }
		// }
		// }
		//
		// // Close the files
		// bw_sch.close();
		// fw_sch.close();
		// br.close();
		// fr.close();
	}
}
