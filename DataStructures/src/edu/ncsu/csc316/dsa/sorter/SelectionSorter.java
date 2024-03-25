package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * SelectionSorter uses the selection sort algorithm to sort data.
 * 
 * @author Dr. King
 * @author Maggie Lin
 *
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/** SelectionSorter constructor */
	public SelectionSorter() {
		this(null);
	}

	/**
	 * SelectionSorter constructor with specified comparator.
	 * 
	 * @param comparator The comparator to be used when sorting.
	 */
	public SelectionSorter(Comparator<E> comparator) {
		super(comparator);
	}

	/**
	 * Sort data with selection sort algorithm.
	 * 
	 * @param data Data to be sorted.
	 */
	public void sort(E[] data) {
		for (int i = 0; i <= data.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j <= data.length - 1; j++) {
				if (super.compare(data[j], data[min]) < 0) {
					min = j;
				}
			}
			if (i != min) {
				E temp = data[i];
				data[i] = data[min];
				data[min] = temp;
			}
		}
	}
}
