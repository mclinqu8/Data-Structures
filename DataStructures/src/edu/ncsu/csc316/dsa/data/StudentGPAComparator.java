package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator for comparing Students based on GPA.
 * 
 * @author Dr. King
 * @author Maggie Lin
 */
public class StudentGPAComparator implements Comparator<Student> {

	/**
	 * Compares students based on GPA in descending order.
	 * 
	 * @return A number based on the order of the Students' GPA.
	 */
	@Override
	public int compare(Student one, Student two) {
		if (one.getGpa() - two.getGpa() > 0) {
			return -1;
		} else if (one.getGpa() - two.getGpa() < 0) {
			return 1;
		} else {
			return 0;
		}
	}

}
