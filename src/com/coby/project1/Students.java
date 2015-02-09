package com.coby.project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Students {

	private int numberStudents;
	private ArrayList<ArrayList<Integer>> students;
	
	public Students(String inputFile) throws FileNotFoundException, IOException {
		super();
		Scanner s = new Scanner(new File(inputFile));
		String l;
		this.numberStudents = 0;
		
		this.students = new ArrayList<ArrayList<Integer>>(0);
		
		while (s.hasNextLine()) {
			l = s.nextLine();
			if (l.matches("\\s+\\d+\\..*")) {
				this.numberStudents++;
				
				ArrayList<Integer> schedule = new ArrayList<Integer>(0);
				
				String[] tokens = l.split("[ .]+");
				for(String t:tokens) {
					if (t.matches(".*\\d+.*")) {
						int course = Integer.parseInt(t);
						schedule.add(course);
					}
				}
				Collections.sort(schedule);
				this.students.add(schedule);
			}
		}	
	}
	
	public void setStudents(String inputFile)  throws FileNotFoundException, IOException {
		
	}
	
	
	public int getNumberStudents() {
		return this.numberStudents;
	}
	
	public ArrayList<ArrayList<Integer>> getStudents() {
		return this.students;
	}
	
	public ArrayList<Integer> getStudent(int i) {
		return this.students.get(i);
	}
	
	public Integer getCourse(int i, int j) {
		return this.students.get(i).get(j);
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Scanner s = new Scanner(new File("student_schedule.txt"));
		String l;
		int numStudents = 0;
		
		ArrayList<ArrayList<Integer>> students = new ArrayList<ArrayList<Integer>>(0);
		
		while (s.hasNextLine()) {
			l = s.nextLine();
			if (l.matches("\\s+\\d+\\..*")) {
//				System.out.println(l);
				numStudents++;
				
				ArrayList<Integer> schedule = new ArrayList<Integer>(0);
				
				String[] tokens = l.split("[ .]+");
				for(String t:tokens) {
					if (t.matches(".*\\d+.*")) {
						int course = Integer.parseInt(t);
//						System.out.println(course);
						schedule.add(course);
					}
				}
				Collections.sort(schedule);
				students.add(schedule);
			}
		}
		
		for(ArrayList<Integer> i:students) {
			for(Integer j:i) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
		
		System.out.println("number of students:" + numStudents);
		s.close();
	}
}
