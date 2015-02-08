package com.coby.project1;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.gnu.glpk.GLPK;
import org.gnu.glpk.SWIGTYPE_p_double;
import org.gnu.glpk.SWIGTYPE_p_int;
import org.gnu.glpk.glp_iocp;
import org.gnu.glpk.glp_prob;

public class Test {

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

	//  Create problem    
	    lp = GLPK.glp_create_prob();
	    System.out.println("Problem created");
	    GLPK.glp_set_prob_name(lp, "myProblem");
	}

}
