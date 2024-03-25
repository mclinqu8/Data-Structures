package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * QuickSorter sorts arrays of comparable elements using the quicksort
 * algorithm. This implementation allows the client to specify a specific pivot
 * selection strategy: (a) use the first element as the pivot, (b) use the last
 * element as the pivot, (c) use the middle element as the pivot, or (d) use an
 * element at a random index as the pivot.
 * 
 * Using the randomized pivot selection strategy ensures O(nlogn)
 * expected/average case runtime when sorting n elements that are comparable
 * 
 * @author Dr. King
 * @author Maggie Lin
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class QuickSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	/**
	 * Pivot selection strategy that uses the element at the first index each time a
	 * pivot must be selected
	 */
	public static final PivotSelector FIRST_ELEMENT_SELECTOR = new FirstElementSelector();

	/**
	 * Pivot selection strategy that uses the element at the last index each time a
	 * pivot must be selected
	 */
	public static final PivotSelector LAST_ELEMENT_SELECTOR = new LastElementSelector();

	/**
	 * Pivot selection strategy that uses the element at the middle index each time
	 * a pivot must be selected
	 */
	public static final PivotSelector MIDDLE_ELEMENT_SELECTOR = new MiddleElementSelector();

	/**
	 * Pivot selection strategy that uses the element at a randomly-chosen index
	 * each time a pivot must be selected
	 */
	public static final PivotSelector RANDOM_ELEMENT_SELECTOR = new RandomElementSelector();

	/**
	 * Client's chosen selector
	 */
	private PivotSelector selector;

	/**
	 * Constructs a new QuickSorter with a provided custom Comparator and a
	 * specified PivotSelector strategy
	 * 
	 * @param comparator a custom comparator to use when sorting
	 * @param selector   the pivot selection strategy to use when selecting pivots
	 */
	public QuickSorter(Comparator<E> comparator, PivotSelector selector) {
		super(comparator);
		setSelector(selector);
	}

	/**
	 * Constructs a new QuickSorter using the natural ordering of elements. Pivots
	 * are selected using the provided PivotSelector strategy
	 * 
	 * @param selector the pivot selection strategy to use when selecting pivots
	 */
	public QuickSorter(PivotSelector selector) {
		this(null, selector);
	}

	/**
	 * Constructs a new QuickSorter with a provided custom Comparator and the
	 * default random pivot selection strategy
	 * 
	 * @param comparator a custom comparator to use when sorting
	 */
	public QuickSorter(Comparator<E> comparator) {
		this(comparator, null);
	}

	/**
	 * Constructs a new QuickSorter that uses an element's natural ordering and uses
	 * the random pivot selection strategy
	 */
	public QuickSorter() {
		this(null, null);
	}

	/**
	 * Set the pivot selector to the given selector or if there is no given selector
	 * set the selector to the default random selector
	 * 
	 * @param selector the specified pivot selector
	 */
	private void setSelector(PivotSelector selector) {
		if (selector == null) {
			this.selector = new RandomElementSelector();
		} else {
			this.selector = selector;
		}
	}

	/**
	 * {@inheritDoc} Sort the array of data using the quick sort method
	 */
	@Override
	public void sort(E[] data) {
		int low = 0;
		int high = data.length - 1;
		quickSort(data, low, high);
	}

	/**
	 * Sort an array of elements using quick sort
	 * 
	 * @param data the given array of elements
	 * @param low  the lowest index to sort
	 * @param high the highest index to sort
	 */
	private void quickSort(E[] data, int low, int high) {
		if (low < high) {
			int pivotLocation = partition(data, low, high);
			quickSort(data, low, pivotLocation - 1);
			quickSort(data, pivotLocation + 1, high);
		}
	}

	/**
	 * Selects a pivot element and move elements less than the pivot element to be
	 * before the pivot index and the elements more than the pivot element to be
	 * after the pivot element
	 * 
	 * @param data the given array of elements
	 * @param low  the lowest index to sort
	 * @param high the highest index to sort
	 * @return the index of the pivot element after sorting all the elements to be
	 *         before or after the pivot element
	 */
	private int partition(E[] data, int low, int high) {
		// Select a Pivot element
		int pivotIndex = selector.selectPivot(low, high);
		// Swap the pivot to be the last element in the array
		swap(data, pivotIndex, high);
		return partitionHelper(data, low, high);
	}

	/**
	 * Using the highest index as the pivot element, move elements less than the
	 * pivot element to be before the pivot index and the elements more than the
	 * pivot element to be after the pivot element
	 * 
	 * @param data the given array of elements
	 * @param low  the lowest index to sort
	 * @param high the highest index to sort
	 * @return the index of the pivot
	 */
	private int partitionHelper(E[] data, int low, int high) {
		E pivot = data[high];
		// Index of smaller elements
		int index = low;
		for (int j = low; j <= high - 1; j++) {
			if (super.compare(data[j], pivot) <= 0) {
				swap(data, index, j);
				index = index + 1;
			}
		}

		// swap the index with the pivot (data[high] is the pivot)
		swap(data, index, high);

		// Return the index of the pivot element
		return index;

	}

	/**
	 * Swap the first index with the second index
	 * 
	 * @param data        the given array of elements
	 * @param firstIndex  the element in the index to be swapped with the other
	 *                    index
	 * @param secondIndex the index to be swapped with
	 */
	private void swap(E[] data, int firstIndex, int secondIndex) {
		E temp;
		temp = data[firstIndex];
		data[firstIndex] = data[secondIndex];
		data[secondIndex] = temp;
	}

	/**
	 * Defines the behaviors of a PivotSelector
	 * 
	 * @author Dr. King
	 *
	 */
	private interface PivotSelector {
		/**
		 * Returns the index of the selected pivot element
		 * 
		 * @param low  - the lowest index to consider
		 * @param high - the highest index to consider
		 * @return the index of the selected pivot element
		 */
		int selectPivot(int low, int high);
	}

	/**
	 * FirstElementSelector chooses the first index of the array as the index of the
	 * pivot element that should be used when sorting
	 * 
	 * @author Dr. King
	 *
	 */
	public static class FirstElementSelector implements PivotSelector {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int selectPivot(int low, int high) {
			return low;
		}
	}

	/**
	 * LastElementSelector chooses the last index of the array as the index of the
	 * pivot element that should be used when sorting
	 * 
	 * @author Maggie Lin
	 *
	 */
	public static class LastElementSelector implements PivotSelector {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int selectPivot(int low, int high) {
			return high;
		}
	}

	/**
	 * MiddleElementSelector chooses the middle index of the array as the index of
	 * the pivot element that should be used when sorting
	 * 
	 * @author Maggie Lin
	 *
	 */
	public static class MiddleElementSelector implements PivotSelector {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int selectPivot(int low, int high) {
			int middle = (low + high) / 2;
			return middle;
		}
	}

	/**
	 * RandomElementSelector chooses a random index of the array as the index of the
	 * pivot element that should be used when sorting
	 * 
	 * @author Maggie Lin
	 *
	 */
	public static class RandomElementSelector implements PivotSelector {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int selectPivot(int low, int high) {
			int random = low + (int) (Math.random() * ((high - low) + 1));
			return random;
		}
	}
}