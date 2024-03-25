package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator to compare students based on id number
 * 
 * @author Dr. King
 * @author Maggie Lin
 */
public class StudentIDComparator implements Comparator<Student> {

	/**
	 * StudentIDComparator constructor
	 */
	public StudentIDComparator() {
		// Empty Constructor
	}

	/**
	 * Compares students based on id number in ascending order
	 * 
	 * @return A number based on the order of the Students' Id.
	 */
	@Override
	public int compare(Student one, Student two) {
		return one.getId() - two.getId();
	}

}
