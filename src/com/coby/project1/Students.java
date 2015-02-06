package com.coby.project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Students {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Scanner students = new Scanner(new File("student_schedule.txt"));
		String s;
		
		while (students.hasNextLine()) {
			s = students.nextLine();
			if (s.matches("\\s+\\d\\..*")) {
				System.out.println(s);
				String[] tokens = s.split("[ .]+");
				for(String t:tokens) {
					if (t.matches(".*\\d.*"))
					System.out.println(Integer.parseInt(t));
					
				}
			}
			
		}
		students.close();
	}

}
