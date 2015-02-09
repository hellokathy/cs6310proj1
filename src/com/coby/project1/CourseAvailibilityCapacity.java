package com.coby.project1;

public class CourseAvailibilityCapacity implements Constraint {

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
	public void createCOnstraints() {
		// TODO Auto-generated method stub
		
	}

	
}
