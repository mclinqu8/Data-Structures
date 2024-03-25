package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * 
 * @author Dr. King
 * @author Maggie Lin
 *
 * @param <E> Generic elements to be sorted
 */
public interface Sorter<E> {
	/**
	 * Sort the data according the the sorting method.
	 * 
	 * @param data array of data to be sorted.
	 */
	void sort(E[] data);
}
