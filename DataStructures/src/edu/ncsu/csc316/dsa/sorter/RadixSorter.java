package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * RadixSorter uses the radix sort algorithm to sort data.
 * 
 * @author Dr. King
 * @author Maggie Lin
 *
 * @param <E> the generic type of data to sort
 */
public class RadixSorter<E extends Identifiable> implements Sorter<E> {

	/** RadixSorter constructor */
	public RadixSorter() {
		// Empty Constructor
	}

	/**
	 * Sort data with radix sort algorithm.
	 * 
	 * @param data Data to be sorted.
	 */
	public void sort(E[] data) {
		int max = 0;
		for (int i = 0; i <= data.length - 1; i++) {
			max = Math.max(max, data[i].getId());
		}
		// Determine how many digits are in the largest value
		double digitCount = Math.ceil(Math.log(max + 1) / Math.log(10));

		int places = 1;

		for (int j = 1; j <= digitCount; j++) {
			int[] frequencyArr = new int[10];
			for (int i = 0; i <= data.length - 1; i++) {
				frequencyArr[(data[i].getId() / places) % 10] = frequencyArr[(data[i].getId() / places) % 10] + 1;
			}

			for (int i = 1; i <= 9; i++) {
				frequencyArr[i] = frequencyArr[i - 1] + frequencyArr[i];
			}

			@SuppressWarnings("unchecked")
			E[] finalArr = (E[]) (new Identifiable[data.length]);
			for (int i = data.length - 1; i >= 0; i--) {
				finalArr[frequencyArr[(data[i].getId() / places) % 10] - 1] = data[i];
				frequencyArr[(data[i].getId() / places) % 10] = frequencyArr[(data[i].getId() / places) % 10] - 1;
			}

			for (int i = 0; i <= data.length - 1; i++) {
				data[i] = finalArr[i];
			}

			places = places * 10;
		}

	}
}
