package com.coby.project1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CreateSchedule {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		FileReader fr = new FileReader("student_schedule.sol");
		BufferedReader br = new BufferedReader(fr);

		String path = System.getProperty("java.library.path");
		System.out.println("Java path: " + path);
		
		String line = br.readLine();
		for (int i = 0; (line = br.readLine()) != null; i++) {
			if (i == 0) {
				String[] tokens = line.split("[ ]+");
				System.out.println("The capacity of students per class needs to be a minimum of: " + tokens[1]);
			} else {
			
			}
		}
	}

}
