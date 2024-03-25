package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Random;

/**
 * A SkipListMap is an ordered (meaning entries are stored in a sorted order
 * based on the keys of the entries) linked-memory representation of the Map
 * abstract data type. This link-based map maintains several levels of linked
 * lists to help approximate the performance of binary search using a
 * linked-memory structure. SkipListMap ensures a O(logn) expected/average
 * runtime for lookUps, insertions, and deletions.
 *
 * The SkipListMap class is based on algorithms developed for use with the
 * textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 *
 * @author Dr. King
 * @author Maggie Lin
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class SkipListMap<K extends Comparable<K>, V> extends AbstractOrderedMap<K, V> {

	/**
	 * Coin tosses are used when inserting entries into the data structure to ensure
	 * 50/50 probability that an entry will be added to the current level of the
	 * skip list structure
	 */
	private Random coinToss;

	/**
	 * Start references the topmost, leftmost corner of the skip list. In other
	 * words, start references the sentinel front node at the top level of the skip
	 * list
	 */
	private SkipListNode<K, V> start;

	/**
	 * The number of entries stored in the map
	 */
	private int size;

	/**
	 * The number of levels of the skip list data structure
	 */
	private int height;

	/**
	 * Constructs a new SkipListMap where keys of entries are compared based on
	 * their natural ordering based on {@link Comparable#compareTo}
	 */
	public SkipListMap() {
		this(null);
	}

	/**
	 * Constructs a new SkipListMap where keys of entries are compared based on a
	 * provided {@link Comparator}
	 *
	 * @param compare a Comparator that defines comparisons rules for keys in the
	 *                map
	 */
	public SkipListMap(Comparator<K> compare) {
		super(compare);
		coinToss = new Random();
		// Create a dummy head node for the left "-INFINITY" sentinel tower
		start = new SkipListNode<K, V>(null);
		// Create a dummy tail node for the right "+INFINITY" sentinel tower
		start.setNext(new SkipListNode<K, V>(null));
		// Set the +INFINITY tower's previous to be the "start" node
		start.getNext().setPrevious(start);
		size = 0;
		height = 0;
	}

	// Helper method to determine if an entry is one of the sentinel
	// -INFINITY or +INFINITY nodes (containing a null key)
	private boolean isSentinel(SkipListNode<K, V> node) {
		return node.getEntry() == null;
	}

	private SkipListNode<K, V> lookUp(K key) {
		SkipListNode<K, V> current = start;
		while (current.below != null) {
			current = current.below;
			while (!isSentinel(current.next) && compare(key, current.next.getEntry().getKey()) >= 0) {
				current = current.next;
			}
		}
		return current;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public V get(K key) {
		SkipListNode<K, V> temp = lookUp(key);
		if (!isSentinel(temp) && temp.getEntry().getKey().equals(key)) {
			return temp.getEntry().getValue();
		}
		return null;
	}

	/**
	 * Insert the SkipListNode with the given entry above the given SkipListNode 
	 * @param prev the SkipListNode before the current entry
	 * @param down the SkipListNode below the current entry
	 * @param entry the entry contained in the current SkipListNode
	 * @return the current SkipListNode inserted into the map
	 */
	private SkipListNode<K, V> insertAfterAbove(SkipListNode<K, V> prev, SkipListNode<K, V> down, Entry<K, V> entry) {
		SkipListNode<K, V> newNode = new SkipListNode<K, V>(entry);
		newNode.setBelow(down);
		newNode.setPrevious(prev);
		if (prev != null) {
			newNode.setNext(prev.getNext());
			newNode.getPrevious().setNext(newNode);
		}
		if (newNode.getNext() != null) {
			newNode.getNext().setPrevious(newNode);
		}
		if (down != null) {
			down.setAbove(newNode);
		}
		return newNode;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public V put(K key, V value) {
		SkipListNode<K, V> temp = lookUp(key);
		// Entry with the key already exists in the map
		if (!isSentinel(temp) && temp.getEntry().getKey().equals(key)) {
			V originalValue = temp.getEntry().getValue();
			while (temp != null) {
				((MapEntry<K, V>) temp.getEntry()).setValue(value);
				temp = temp.getAbove();
			}
			return originalValue;
		}
		// Use q to represent the new entry as we move to the level
		// "above" after inserting into the bottom-most list
		SkipListNode<K, V> q = null;
		int currentLevel = -1;
		do {
			currentLevel++;
			// Check if we need to add a new level to the top of the skip list
			if (currentLevel >= height) {
				// Increase the height of the skip list
				height++;
				// Create a pointer to the current "tail" of the topmost list
				SkipListNode<K, V> tail = start.getNext();
				// Insert a new sentinel start node above
				start = insertAfterAbove(null, start, null);
				// Insert a new sentinel tail node above
				insertAfterAbove(start, tail, null);
			}
			// Insert the new entry into current level of the list
			q = insertAfterAbove(temp, q, new MapEntry<K, V>(key, value));
			// Backtrack to the entry into current level of the list
			while (temp.getAbove() == null) {
				temp = temp.getPrevious();
			}
			temp = temp.getAbove();
		} while (coinToss.nextBoolean());
		size++;
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public V remove(K key) {
		SkipListNode<K, V> temp = lookUp(key);

		if (isSentinel(temp) || temp == null || temp.getEntry().getKey() != key) {
			return null;
		}
		SkipListNode<K, V> aboveTemp = temp;
		V value = temp.getEntry().getValue();

		while (aboveTemp != null) {
			aboveTemp = temp.above;
			temp.above = null;
			temp.below = null;
			temp.getPrevious().setNext(temp.getNext());
			temp.getNext().setPrevious(temp.getPrevious());
			temp = aboveTemp;
		}
		size--;
		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		EntryCollection set = new EntryCollection();
		SkipListNode<K, V> current = start;
		while (current.below != null) {
			current = current.below;
		}
		current = current.next;
		while (!isSentinel(current)) {
			set.add(current.getEntry());
			current = current.next;
		}
		return set;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("SkipListMap[");
		SkipListNode<K, V> cursor = start;
		while (cursor.below != null) {
			cursor = cursor.below;
		}
		cursor = cursor.next;
		while (cursor != null && !isSentinel(cursor) && cursor.getEntry().getKey() != null) {
			sb.append(cursor.getEntry().getKey());
			if (!isSentinel(cursor.next)) {
				sb.append(", ");
			}
			cursor = cursor.next;
		}
		sb.append("]");

		return sb.toString();
	}

//	// This method may be useful for testing or debugging.
//	// You may comment-out this method instead of testing it, since
//	// the full string will depend on the series of random coin flips
//	// and will not have deterministic expected results.
//	public String toFullString() {
//		StringBuilder sb = new StringBuilder("SkipListMap[\n");
//		SkipListNode<K, V> cursor = start;
//		SkipListNode<K, V> firstInList = start;
//		while (cursor != null) {
//			firstInList = cursor;
//			sb.append("-INF -> ");
//			cursor = cursor.next;
//			while (cursor != null && !isSentinel(cursor)) {
//				sb.append(cursor.getEntry().getKey() + " -> ");
//				cursor = cursor.next;
//			}
//			sb.append("+INF\n");
//			cursor = firstInList.below;
//		}
//		sb.append("]");
//		return sb.toString();
//	}

	/**
	 * SkipListNode is the data object of a SkipListMap
	 * 
	 * @author Dr. King
	 * @author Maggie Lin
	 *
	 * @param <K> Key of the entry stored in the SkipListNode
	 * @param <V> Value of the entry stored in the SkipListNode
	 */
	private static class SkipListNode<K, V> {

		/** Entry to be contained in the SkipListNode */
		private Entry<K, V> entry;

		/**
		 * the SkipListNode that is above the SkipListNode that holds the current entry
		 */
		private SkipListNode<K, V> above;

		/**
		 * the SkipListNode that is below the SkipListNode that holds the current entry
		 */
		private SkipListNode<K, V> below;

		/**
		 * the SkipListNode that is the node before the SkipListNode that holds the
		 * current entry
		 */
		private SkipListNode<K, V> prev;

		/**
		 * the SkipListNode that is the node next to the SkipListNode that holds the
		 * current entry
		 */
		private SkipListNode<K, V> next;

		/**
		 * Constructs a SkipListNode with the given entry and set all nodes around it to
		 * null
		 * 
		 * @param entry the entry to be contained in the current node
		 */
		public SkipListNode(Entry<K, V> entry) {
			setEntry(entry);
			setAbove(null);
			setBelow(null);
			setPrevious(null);
			setNext(null);
		}

		/**
		 * Return the SkipListNode above the current node
		 * 
		 * @return SkipListNode above the current node
		 */
		public SkipListNode<K, V> getAbove() {
			return above;
		}

		/**
		 * Return the entry in the current SkipListNode
		 * 
		 * @return the entry in the current SkipListNode
		 */
		public Entry<K, V> getEntry() {
			return entry;
		}

		/**
		 * Return the SkipListNode next to the current node
		 * 
		 * @return SkipListNode next to the current node
		 */
		public SkipListNode<K, V> getNext() {
			return next;
		}

		/**
		 * Return the SkipListNode before the current node
		 * 
		 * @return SkipListNode before the current node
		 */
		public SkipListNode<K, V> getPrevious() {
			return prev;
		}

		/**
		 * Set the above pointer of the current node to the given SkipListNode
		 * 
		 * @param up the SkipListNode to be set above the current node
		 */
		public void setAbove(SkipListNode<K, V> up) {
			this.above = up;
		}

		/**
		 * Set the below pointer of the current node to the given SkipListNode
		 * 
		 * @param down the SkipListNode to be set below the current node
		 */
		public void setBelow(SkipListNode<K, V> down) {
			this.below = down;
		}

		/**
		 * Set the entry of the current node to the given entry
		 * 
		 * @param entry the entry to be put in the current SkipListNode
		 */
		public void setEntry(Entry<K, V> entry) {
			this.entry = entry;
		}

		/**
		 * Set the next pointer of the current node to the given SkipListNode
		 * 
		 * @param next the SkipListNode to be set after the current node
		 */
		public void setNext(SkipListNode<K, V> next) {
			this.next = next;
		}

		/**
		 * Set the previous pointer of the current node to the given SkipListNode
		 * 
		 * @param prev the SkipListNode to be set before the current node
		 */
		public void setPrevious(SkipListNode<K, V> prev) {
			this.prev = prev;
		}
	}
}