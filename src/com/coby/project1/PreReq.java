package com.coby.project1;

import java.io.BufferedWriter;
import java.io.IOException;

public class PreReq implements Constraint {

	private Semesters semesters;
	private Courses courses;
	private Students students;
	@Override
	public void getParameters(Semesters sem, Courses c, Students s) {
		this.semesters = sem;
		this.courses = c;
		this.students = s;
	}

	@Override
	public void writeConstraints(BufferedWriter bw) throws IOException {
		int c;
		for(int i = 1; i <= this.students.getNumberStudents(); i++) {
			c = 0;
			for(int j = 1; j <= this.courses.getNumCourses(); j++) {
				for (int k = 1; k < this.semesters.getNumberSemesters(); k++) {
			    	bw.write(" y" + i + "_" + j + "_" + k + " +");
					}
				while(students.getCourse(i - 1, c) < j && c < students.getStudent(i - 1).size() - 1) {
					c++;
				}
				if (students.getCourse(i - 1, c) == j) {
					bw.write(" y" + i + "_" + j + "_" + this.semesters.getNumberSemesters() +" = 1");
					bw.newLine();
				} else {
					bw.write(" y" + i + "_" + j + "_" + this.semesters.getNumberSemesters() +" <= 1");
					bw.newLine();
				}
	    	}
	    }
	}

}
