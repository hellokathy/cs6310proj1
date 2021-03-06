package com.coby.project1;

import java.io.BufferedWriter;
import java.io.IOException;

public class Enrollment implements Constraint{

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
		for(int i = 1; i <= this.students.getNumberStudents(); i++) {
			for (int k = 1; k <= this.semesters.getNumberSemesters(); k++) {
				for(int j = 1; j < this.courses.getNumCourses(); j++) {
		    		bw.write(" y" + i + "_" + j + "_" + k + " +");
				}
				bw.write(" y" + i + "_" + this.courses.getNumCourses() + "_" + k +" <= 2");
				bw.newLine();
	    	}
	    }
	}

}
