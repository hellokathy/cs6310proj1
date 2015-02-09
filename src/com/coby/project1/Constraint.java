package com.coby.project1;

import java.io.BufferedWriter;
import java.io.IOException;

public interface Constraint {
	public void getParameters(Semesters sem, Courses c, Students s);
	void writeConstraints(BufferedWriter bw) throws IOException;
}
