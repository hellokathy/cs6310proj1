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
		int m = this.semesters.getNumberSemesters();
		for(int i = 1; i <= this.students.getNumberStudents(); i++) {
			for(int j = 0; j < this.courses.getPreReq().length; j++) {
				for (int k = 1; k < this.semesters.getNumberSemesters(); k++) {
					int pre = m - k;
					int post = pre + 1;
			    	bw.write(" " + post + " y" + i + "_" + this.courses.getPreReq()[j][1] + "_" + k + " - " + 
			    				pre + " y" + i + "_" + this.courses.getPreReq()[j][0] + "_" + k + " +");
				}


		    	bw.write(" " + 1 + " y" + i + "_" + this.courses.getPreReq()[j][1] + "_" + m + " - " + 
	    				0 + " y" + i + "_" + this.courses.getPreReq()[j][0] + "_" + m + " <= 0");
					bw.newLine();
	    	}
	    }
	}

}
