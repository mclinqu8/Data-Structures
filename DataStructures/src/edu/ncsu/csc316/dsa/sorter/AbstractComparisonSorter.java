package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * AbstractComparisonSorter is an abstract class that holds the common
 * functionality of sorter classes.
 * 
 * @author Dr. King
 * @author Maggie Lin
 *
 * @param <E> Generic element to be sorted
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {

	/** The comparator type of the sorter */
	private Comparator<E> comparator;

	/**
	 * AbstractComparisonSorter Constructor that allows for sorting with a custom
	 * comparator.
	 * 
	 * @param comparator The comparator to be used when sorting.
	 */
	public AbstractComparisonSorter(Comparator<E> comparator) {
		setComparator(comparator);
	}

	/**
	 * Sets the comparator to either default or specified comparator.
	 * 
	 * @param comparator The comparator to be used when sorting.
	 */
	private void setComparator(Comparator<E> comparator) {
		if (comparator == null) {
			this.comparator = new NaturalOrder();
		} else {
			this.comparator = comparator;
		}
	}

	/**
	 * The default comparator when no comparator is specified.
	 * 
	 * @author Dr. King
	 */
	private class NaturalOrder implements Comparator<E> {
		public int compare(E first, E second) {
			return ((Comparable<E>) first).compareTo(second);
		}
	}

	/**
	 * Compare two objects based on a specified comparator.
	 * 
	 * @param data1 The first object being compared.
	 * @param data2 The second object being compared.
	 * @return A number based on the order of the data being compared.
	 */
	public int compare(E data1, E data2) {
		return comparator.compare(data1, data2);
	}
}
