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
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * BubbleSorter constructor.
	 */
	public BubbleSorter() {
		this(null);
	}

	/**
	 * BubbleSorter constructor with specified comparator.
	 * 
	 * @param comparator The comparator to be used when sorting.
	 */
	public BubbleSorter(Comparator<E> comparator) {
		super(comparator);
	}

	/**
	 * Sort data with bubble sort algorithm.
	 * 
	 * @param data Data to be sorted.
	 */
	public void sort(E[] data) {
		boolean sort = true;
		while (sort) {
			sort = false;
			for (int i = 1; i <= data.length - 1; i++) {
				if (super.compare(data[i], data[i - 1]) < 0) {
					E temp = data[i - 1];
					data[i - 1] = data[i];
					data[i] = temp;
					sort = true;
				}
			}
		}
	}
}
