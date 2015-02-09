package com.coby.project1;

public class Courses {

	private boolean[][] courseOffering;
	private int[][] preReq;
	private int numCourses;
	private int semesterTypes;
	
	public Courses(boolean[][] courseOffering, int [][] preReq, int numCourses, int semesterTypes) {
		super();
		this.courseOffering = courseOffering;
		this.numCourses = numCourses;
		this.semesterTypes = semesterTypes;
		this.preReq = preReq;
	}
	
	public int[][] getPreReq() {
		return preReq;
	}
	
	public boolean[][] getCourseOffering() {
		return courseOffering;
	}

	
	public int getNumCourses() {
		return numCourses;
	}
	
	public void setNumCourses(int numCourses) {
		this.numCourses = numCourses;
	}
	
	public int getSemesterTypes() {
		return semesterTypes;
	}
	
	public void setSemesterTypes(int semesterTypes) {
		this.semesterTypes = semesterTypes;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
