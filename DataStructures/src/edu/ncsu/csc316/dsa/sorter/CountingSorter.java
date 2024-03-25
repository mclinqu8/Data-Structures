package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * CountingSorter uses the counting sort algorithm to sort data
 * 
 * @author Dr. King
 * @author Maggie Lin
 *
 * @param <E> the generic type of data to sort
 */
public class CountingSorter<E extends Identifiable> implements Sorter<E> {
	/**
	 * CountingSorter constructor.
	 */
	public CountingSorter() {
		// Empty Constructor
	}

	/**
	 * Sort data with counting sort algorithm.
	 * 
	 * @param data Data to be sorted.
	 */
	public void sort(E[] data) {
		// Since we constrained E to be Identifiable,
		// we can now access the .getId() method of E objects
		// from within this sort method
		int min = data[0].getId();
		int max = data[0].getId();
		for (int i = 0; i <= data.length - 1; i++) {
			min = Math.min(data[i].getId(), min);
			max = Math.max(data[i].getId(), max);
		}
		int range = max - min + 1;

		int[] frequencyArr = new int[range];
		// Count frequency of values
		for (int i = 0; i <= data.length - 1; i++) {
			frequencyArr[data[i].getId() - min] = frequencyArr[data[i].getId() - min] + 1;
		}
		// accumulate frequency
		for (int i = 1; i <= range - 1; i++) {
			frequencyArr[i] = frequencyArr[i - 1] + frequencyArr[i];
		}

		@SuppressWarnings("unchecked")
		E[] finalArr = (E[]) (new Identifiable[data.length]);
		for (int i = data.length - 1; i >= 0; i--) {
			finalArr[frequencyArr[data[i].getId() - min] - 1] = data[i];
			frequencyArr[data[i].getId() - min] = frequencyArr[data[i].getId() - min] - 1;
		}

		for (int i = 0; i <= data.length - 1; i++) {
			data[i] = finalArr[i];
		}
	}
}
