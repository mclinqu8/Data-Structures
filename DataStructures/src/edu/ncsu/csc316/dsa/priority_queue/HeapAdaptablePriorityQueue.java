package edu.ncsu.csc316.dsa.priority_queue;

import java.util.Comparator;

/**
 * A HeapAdaptablePriorityQueue is an array-based min-heap implementation of the
 * {@link AdaptablePriorityQueue} abstract data type. HeapAdaptablePriorityQueue
 * ensures a O(logn) worst-case runtime for {@link PriorityQueue#insert},
 * {@link PriorityQueue#deleteMin}, {@link AdaptablePriorityQueue#remove}, and
 * {@link AdaptablePriorityQueue#replaceKey}. HeapAdaptablePriorityQueue ensures
 * a O(1) worst-case runtime for {@link PriorityQueue#min},
 * {@link PriorityQueue#size}, {@link PriorityQueue#isEmpty}, and
 * {@link AdaptablePriorityQueue#replaceValue}.
 * 
 * The HeapAdaptablePriorityQueue class is based on an implementation developed
 * for use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * Citing Help from the Textbooks. The code for several methods is based on the
 * algorithm listed under Implementing an Adaptable Priority Queue on page 388
 * in the course textbook "Data Structures and Algorithms" by Goodrich,
 * Tamassia, Goldwasser.
 * 
 * @author Dr. King
 * @author Maggie Lin
 *
 * @param <K> the type of keys (priorities) stored in the adaptable priority
 *            queue
 * @param <V> the type of values that are associated with keys in the adaptable
 *            priority queue
 */
public class HeapAdaptablePriorityQueue<K extends Comparable<K>, V> extends HeapPriorityQueue<K, V>
		implements AdaptablePriorityQueue<K, V> {

	/**
	 * An AdaptablePQEntry extends {@link PQEntry} to maintain a reference of the
	 * entry's current index within the array-based heap data structure.
	 * 
	 * Adaptable PQ Entries must be location-aware so that the worst-case runtime of
	 * replaceKey, replaceValue, and remove are O(log n)
	 * 
	 * @author Dr. King
	 *
	 * @param <K> the type of key (priority) stored in the adaptable prioriy queue
	 *            entry
	 * @param <V> the type of value stored in the adaptable priority queue entry
	 */
	public static class AdaptablePQEntry<K, V> extends PQEntry<K, V> {

		/** Index of the current entry */
		private int index;

		/**
		 * Constructs a new AdaptablePQEntry with the given key, value, and index
		 * 
		 * @param key   the key (priority) of the adaptable priority queue entry
		 * @param value the value of the adaptable priority queue entry
		 * @param index the index within the array where the entry is currently located
		 */
		public AdaptablePQEntry(K key, V value, int index) {
			super(key, value);
			setIndex(index);
		}

		/**
		 * Returns the index of the entry within the array-based heap structure
		 * 
		 * @return the index of the entry within the array-based heap structure
		 */
		public int getIndex() {
			return index;
		}

		/**
		 * Sets the index of the entry within the array-based heap structure
		 * 
		 * @param index the index of the entry within the array-based heap structure
		 */
		public void setIndex(int index) {
			this.index = index;
		}
	}

	/**
	 * Constructs a new HeapAdaptablePriorityQueue using a custom comparator
	 * 
	 * @param compare the custom Comparator to use when comparing keys (priorities)
	 */
	public HeapAdaptablePriorityQueue(Comparator<K> compare) {
		super(compare);
	}

	/**
	 * Constructs a new HeapAdaptablePriorityQueue using the natural ordering of
	 * keys
	 */
	public HeapAdaptablePriorityQueue() {
		this(null);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Specifically, creates a new AdaptablePQEntry with an initial index set to the
	 * end of the array-based heap structure
	 */
	@Override
	protected AdaptablePQEntry<K, V> createEntry(K key, V value) {
		AdaptablePQEntry<K, V> temp = new AdaptablePQEntry<K, V>(key, value, size());
		return temp;
	}

	private AdaptablePQEntry<K, V> validate(Entry<K, V> entry) {
		if (!(entry instanceof AdaptablePQEntry)) {
			throw new IllegalArgumentException("Entry is not a valid adaptable priority queue entry.");
		}
		AdaptablePQEntry<K, V> temp = (AdaptablePQEntry<K, V>) entry;
		if (temp.getIndex() >= list.size() || list.get(temp.getIndex()) != temp) {
			throw new IllegalArgumentException("Invalid Adaptable PQ Entry.");
		}
		return temp;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void swap(int index1, int index2) {
		// Delegate to the super class swap method
		super.swap(index1, index2);
		// But then update the index of each entry so that they remain location-aware
		((AdaptablePQEntry<K, V>) list.get(index1)).setIndex(index1);
		((AdaptablePQEntry<K, V>) list.get(index2)).setIndex(index2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove(Entry<K, V> entry) throws IllegalArgumentException {
		AdaptablePQEntry<K, V> locator = validate(entry);
		int current = locator.getIndex();
		if (current == list.size() - 1) {
			list.remove(list.size() - 1);
		} else {
			swap(current, list.size() - 1);
			list.remove(list.size() - 1);
			bubble(current);
		}
	}

	/**
	 * Check and move the entry at the given index up or down the queue depending on
	 * the key of the entry
	 * 
	 * @param index index of the entry which might need to be moved
	 */
	private void bubble(int index) {
		if (index > 0 && compare(list.get(index).getKey(), list.get(parent(index)).getKey()) < 0) {
			upHeap(index);
		} else {
			downHeap(index);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void replaceKey(Entry<K, V> entry, K key) throws IllegalArgumentException {
		AdaptablePQEntry<K, V> locator = validate(entry);
		locator.setKey(key);
		bubble(locator.getIndex());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void replaceValue(Entry<K, V> entry, V value) throws IllegalArgumentException {
		AdaptablePQEntry<K, V> locator = validate(entry);
		locator.setValue(value);
	}
}