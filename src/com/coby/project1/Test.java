package com.coby.project1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.gnu.glpk.SWIGTYPE_p_double;
import org.gnu.glpk.SWIGTYPE_p_int;
import org.gnu.glpk.glp_iocp;
import org.gnu.glpk.glp_prob;

public class Test {

//	public static void offset(Semesters s, int i, int j, int k) {
//		
//	}
	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
//		Set up Semesters
		Semesters semester = new Semesters(12);
		
//		Set up Courses
		boolean [][] courseOfferings = {{true,false,false},
	    		{true,true,true},
	    		{true,true,true},
	    		{true,true,true},
	    		{false,false,true},
	    		{true,true,true},
	    		{true,false,false},
	    		{true,true,true},
	    		{true,true,true},
	    		{false,false,true},
	    		{true,false,false},
	    		{true,true,true},
	    		{true,true,true},
	    		{false,false,true},
	    		{true,false,false},
	    		{false,false,true},
	    		{true,false,false},
	    		{false,false,true}};
		
		int[][] preReq = {{4 ,16},
				{12,1 },
				{9 ,13},
				{3 ,7 }};
		
		Classes classes = new Classes(courseOfferings, preReq, 18, 3);
		
//		Setup students
		Students students = new Students("student_schedule.txt");
	    
//	    Setup GLP Problem
	    glp_prob lp;
	    glp_iocp iocp;
	    SWIGTYPE_p_int ind;
	    SWIGTYPE_p_double val;
	    int ret;

//  	Create LP file    
	    File file = new File("student_schedule.lp");
	    
//	    Create the file if it doesn't exist
	    if(!file.exists()) {
	    	file.createNewFile();
	    }
	    
	    FileWriter fw = new FileWriter(file.getAbsoluteFile());
	    BufferedWriter bw = new BufferedWriter(fw);
	    
//	    Objective Statement
	    bw.write("Min X");
	    bw.newLine();
	    
//	    Constraints
	    bw.write("Subject To");
	    bw.newLine();
	    
//	    Variables
	    bw.write("Binary");
	    bw.newLine();
	    for(int i = 0; i < students.getNumberStudents(); i++) {
	    	for(int j = 0; j < classes.getNumCourses(); j++) {
	    		for (int k = 0; k < semester.getNumberSemesters(); k++) {
	    			bw.write("y" + i + "_" + j + "_" + k);
	    			bw.newLine();
	    		}
	    	}
	    }

	    
	    
//	    Close the files
	    bw.close();
	    fw.close();
	}
}
