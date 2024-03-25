package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Iterator;
import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * A Search Table map is an ordered (meaning entries are stored in a sorted
 * order based on the keys of the entries) contiguous-memory representation of
 * the Map abstract data type. This array-based map delegates to an existing
 * array-based list. To improve efficiency of lookUps, the search table map
 * implements binary search to locate entries in O(logn) worst-case runtime.
 * Insertions and deletions have O(n) worst-case runtime.
 * 
 * @author Dr. King
 * @author Maggie Lin
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class SearchTableMap<K extends Comparable<K>, V> extends AbstractOrderedMap<K, V> {

	/** Array-based list of entries */
	private ArrayBasedList<Entry<K, V>> list;

	/**
	 * Constructs a new SearchTableMap where keys of entries are compared based on
	 * their natural ordering based on {@link Comparable#compareTo}
	 */
	public SearchTableMap() {
		this(null);
	}

	/**
	 * Constructs a new SearchTableMap where keys of entries are compared based on a
	 * provided {@link Comparator}
	 * 
	 * @param compare a Comparator that defines comparisons rules for keys in the
	 *                map
	 */
	public SearchTableMap(Comparator<K> compare) {
		super(compare);
		list = new ArrayBasedList<Entry<K, V>>();
	}

	/**
	 * Look up where the entry is located in the map based on the key
	 * 
	 * @param key the key of the entry to look for in the map
	 * @return a negative number that represent where the new entry should go in the
	 *         map
	 */
	private int lookUp(K key) {
		if (list.isEmpty()) {
			return -1;
		}
		return binarySearchHelper(0, list.size() - 1, key);
	}

	/**
	 * Use binary search to look for the index at where the new entry should be
	 * inserted
	 * 
	 * @param min the minimum index of the entry map
	 * @param max the maximum index of the entry map
	 * @param key the key of the entry to search for in the map
	 * @return a negative number that represents where the entry should be inserted
	 */
	private int binarySearchHelper(int min, int max, K key) {
		if (min > max) {
			return -1 * (min + 1);
		}
		int mid = (max + min) / 2;
		if (list.get(mid).getKey().equals(key)) {
			return mid;
		} else if (super.compare(list.get(mid).getKey(), key) > 0) {
			return binarySearchHelper(min, mid - 1, key);
		} else {
			return binarySearchHelper(mid + 1, max, key);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public V get(K key) {
		int index = lookUp(key);
		if (index < 0) {
			return null;
		} else {
			return list.get(index).getValue();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		EntryCollection set = new EntryCollection();
		for (Entry<K, V> entry : list) {
			set.add(entry);
		}
		return set;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public V put(K key, V value) {
		int index = lookUp(key);
		if (index >= 0) {
			V oldVal = list.get(index).getValue();
			list.set(index, new MapEntry<K, V>(key, value));
			return oldVal;
		} else {
			list.add(-(index + 1), new MapEntry<K, V>(key, value));
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public V remove(K key) {
		int index = lookUp(key);
		if (index < 0) {
			return null;
		} else {
			V valueRemoved = list.get(index).getValue();
			list.remove(index);
			return valueRemoved;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("SearchTableMap[");
		Iterator<Entry<K, V>> it = list.iterator();
		while (it.hasNext()) {
			sb.append(it.next().getKey());
			if (it.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}