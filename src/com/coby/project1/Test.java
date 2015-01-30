package com.coby.project1;

import org.gnu.glpk.GLPK;
import org.gnu.glpk.GLPKConstants;
import org.gnu.glpk.SWIGTYPE_p_double;
import org.gnu.glpk.SWIGTYPE_p_int;
import org.gnu.glpk.glp_iocp;
import org.gnu.glpk.glp_prob;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    glp_prob lp;
	    glp_iocp iocp;
	    SWIGTYPE_p_int ind;
	    SWIGTYPE_p_double val;
	    int ret;
	    int students = 10;
	    int classes = 3;
	    int semesters = 3;

	//  Create problem    
	    lp = GLPK.glp_create_prob();
	    System.out.println("Problem created");
	    GLPK.glp_set_prob_name(lp, "myProblem");
	    
//	    Define columns
	    GLPK.glp_add_cols(lp, students * classes * semesters);
	    for(int i = 0; i < students; i++) {
	    	for (int j = 0; j < classes; j++) {
	    		for(int k = 0; k < semesters; k++) {
	    		    GLPK.glp_set_col_name(lp, k + j * semesters + i * classes + semesters, "x" + i + "_" + j +"_" + k);
	    		    GLPK.glp_set_col_kind(lp, k + j * semesters + i * classes + semesters, GLPKConstants.GLP_IV);
	    		    GLPK.glp_set_col_bnds(lp, k + j * semesters + i * classes + semesters, GLPKConstants.GLP_LO, 0, 1);
	    		}
	    	}
	    }
	}

}
