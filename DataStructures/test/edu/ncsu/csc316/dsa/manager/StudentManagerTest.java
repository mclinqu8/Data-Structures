package edu.ncsu.csc316.dsa.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.sorter.CountingSorter;
import edu.ncsu.csc316.dsa.sorter.SelectionSorter;
import edu.ncsu.csc316.dsa.sorter.Sorter;

/**
 * Test the StudentManager class.
 * 
 * @author Dr. King
 * @author Maggie Lin
 */
public class StudentManagerTest {

	/** Default StudentManager */
	private StudentManager sm;
	
	/** StudentManager with BubbleSorter */
	private StudentManager sm2;
	
	/** StudentManager with SelectonSorter and StudentIDComparator */
	private StudentManager sm3;
	
	/**
	 * Sets up the StudentManager, CountingSorter, SelectionSorter, and StudentIDComparator.
	 */
	@Before
	public void setUp() {
		sm = new StudentManager("input/student_ascendingID.csv");
		Sorter<Student> countSort = new CountingSorter<Student>();
		sm2 = new StudentManager("input/student_descendingID.csv", countSort);
		StudentIDComparator idComp = new StudentIDComparator();
		Sorter<Student> selectSort = new SelectionSorter<Student>(idComp);
		sm3 = new StudentManager("input/student_descendingID.csv", selectSort);
	}
	
	/**
	 * Test if StudentManager sorted correctly with natural ordering.
	 */
	@Test
	public void testSort() {
		Student[] sorted = sm.sort();
		assertEquals("Tanner", sorted[0].getFirst());
		assertEquals("Roxann", sorted[1].getFirst());
		assertEquals("Shanti", sorted[2].getFirst());
		assertEquals("Dante", sorted[3].getFirst());
		assertEquals("Cristine", sorted[4].getFirst());
		assertEquals("Ara", sorted[5].getFirst());
		assertEquals("Lewis", sorted[6].getFirst());
		assertEquals("Charlene", sorted[7].getFirst());
		assertEquals("Amber", sorted[8].getFirst());
		assertEquals("Lacie", sorted[9].getFirst());
		assertEquals("Idalia", sorted[10].getFirst());
		assertEquals("Tyree", sorted[11].getFirst());
		assertEquals("Evelin", sorted[12].getFirst());
		assertEquals("Alicia", sorted[13].getFirst());
		assertEquals("Loise", sorted[14].getFirst());
		assertEquals("Nichole", sorted[15].getFirst());
	}
	
	/**
	 * Test if StudentManager sorted correctly with CountingSorter.
	 */
	@Test
	public void testCountSort() {
		Student[] sorted = sm2.sort();
		assertEquals("Amber", sorted[0].getFirst());
		assertEquals("Roxann", sorted[9].getFirst());
		assertEquals("Dante", sorted[15].getFirst());
	}
	
	/**
	 * Test if StudentManager sorted correctly with SelectionSorter and StudentIDComparator.
	 */
	@Test
	public void testSelectSort() {
		Student[] sorted = sm3.sort();
		assertEquals("Amber", sorted[0].getFirst());
		assertEquals("Roxann", sorted[9].getFirst());
		assertEquals("Dante", sorted[15].getFirst());
	}
}
