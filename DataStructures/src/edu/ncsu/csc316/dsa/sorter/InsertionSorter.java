package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * 
 * @author Dr. King
 * @author Maggie Lin
 * 
 * @param <E> the generic type of data to sort
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/** InsertionSorter Constructor */
	public InsertionSorter() {
		this(null);
	}

	/**
	 * InsertionSorter constructor with specified comparator.
	 * 
	 * @param comparator The comparator to be used when sorting.
	 */
	public InsertionSorter(Comparator<E> comparator) {
		super(comparator);
	}

	/**
	 * Sort data with Insertion sort algorithm.
	 * 
	 * @param data data to be sorted.
	 */
	public void sort(E[] data) {
		for (int i = 1; i <= data.length - 1; i++) {
			E item = data[i];
			int j = i - 1;
			while (j >= 0 && super.compare(item, data[j]) < 0) {
				data[j + 1] = data[j];
				j = j - 1;
			}
			data[j + 1] = item;
		}
	}
}