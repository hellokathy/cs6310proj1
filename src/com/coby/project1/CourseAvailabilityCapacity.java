package com.coby.project1;

import java.io.BufferedWriter;
import java.io.IOException;

public class CourseAvailabilityCapacity implements Constraint {

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
		for(int j = 1; j <= this.courses.getNumCourses(); j++) {
			for (int k = 1; k <= this.semesters.getNumberSemesters(); k++) {
				for(int i = 1; i < this.students.getNumberStudents(); i++) {
		    		bw.write(" y" + i + "_" + j + "_" + k + " +");
		    	}
				if (this.courses.getCourseOffering()[j -1][(k - 1) % 3]) {
				    bw.write(" y" + this.students.getNumberStudents() + "_" + j + "_" + k +" - X <= 0");
					bw.newLine();
				} else {
				    bw.write(" y" + this.students.getNumberStudents() + "_" + j + "_" + k +" = 0");
					bw.newLine();
				}
	    	}
	    }
	}

	
}
