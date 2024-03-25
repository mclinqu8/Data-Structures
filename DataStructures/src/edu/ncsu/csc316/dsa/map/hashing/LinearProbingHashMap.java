package edu.ncsu.csc316.dsa.map.hashing;

/**
 * The LinearProbingHashMap is implemented as a hash table that uses linear
 * probing for collision resolution.
 * 
 * The hash map uses a multiply-and-divide compression strategy for calculating
 * hash functions. The hash map ensures expected O(1) performance of (@link
 * Map#put), (@link Map#get), and (@link Map#remove).
 * 
 * The hash table resizes if the load factor exceeds 0.5.
 * 
 * The LinearProbingHashMap class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * Citing Help from the Textbooks. The code for several methods is based on the
 * algorithm listed under Linear Probing on page 427 in the course textbook
 * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
 * 
 * @author Dr. King
 * @author Maggie Lin
 *
 * @param <K> the type of keys stored in the hash map
 * @param <V> the type of values associated with keys in the hash map
 */
public class LinearProbingHashMap<K, V> extends AbstractHashMap<K, V> {

	/** Hash Table represented by an array of TableEntries */
	private TableEntry<K, V>[] table;

	/** Size of the Hash Table */
	private int size;

	/**
	 * Constructs a new linear probing hash map that uses natural ordering of keys
	 * when performing comparisons. The created hash table uses the
	 * {@link AbstractHashMap#DEFAULT_CAPACITY}
	 */
	public LinearProbingHashMap() {
		this(AbstractHashMap.DEFAULT_CAPACITY, false);
	}

	/**
	 * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
	 * natural ordering of keys when performing comparisons. The created hash table
	 * uses the {@link AbstractHashMap#DEFAULT_CAPACITY}
	 * 
	 * @param isTesting if true, the hash table uses a predictable series of random
	 *                  values for deterministic and repeatable testing
	 */
	public LinearProbingHashMap(boolean isTesting) {
		this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
	}

	/**
	 * Constructs a new linear probing hash map that uses natural ordering of keys
	 * when performing comparisons. The created hash table is initialized to have
	 * the provided capacity.
	 * 
	 * @param capacity the initial capacity of the hash table
	 */
	public LinearProbingHashMap(int capacity) {
		this(capacity, false);
	}

	/**
	 * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
	 * natural ordering of keys when performing comparisons. The created hash table
	 * is initialized to have the provided capacity.
	 * 
	 * @param capacity  the initial capacity of the hash table
	 * @param isTesting if true, the hash table uses a predictable series of random
	 *                  values for deterministic and repeatable testing
	 */
	public LinearProbingHashMap(int capacity, boolean isTesting) {
		super(capacity, isTesting);
		size = 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		EntryCollection collection = new EntryCollection();
		for (int i = 0; i < table.length; i++) {
			if (!isAvailable(i)) {
				collection.add(table[i]);
			}
		}
		return collection;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void createTable(int capacity) {
		table = (TableEntry<K, V>[]) new TableEntry[capacity];
		size = 0;
	}

	/**
	 * Returns true if the Hash table slot at the given index is free for a new
	 * entry
	 * 
	 * @param index the index of the hash table we are looking at to see if it is
	 *              free
	 * @return true if the table slot at the given index is available for a new
	 *         entry
	 */
	private boolean isAvailable(int index) {
		return table[index] == null || table[index].isDeleted();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public V bucketGet(int hash, K key) {
		int idx = findBucket(hash, key);
		if (idx < 0) {
			return null;
		}
		return table[idx].getValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public V bucketPut(int hash, K key, V value) {
		int idx = findBucket(hash, key);
		if (idx >= 0) {
			V answer = table[idx].getValue();
			table[idx].setValue(value);
			return answer;
		}
		table[-(idx + 1)] = new TableEntry<K, V>(key, value);
		size++;
		return null;
	}

	/**
	 * Returns a number that represents the index at which the key is located at
	 * 
	 * @param index the index in the Hash Table
	 * @param key   the key of the entry to locate
	 * @return the index of the bucket that contains the entry(if it exist) or a
	 *         number which represent the index where the entry should be stored
	 */
	private int findBucket(int index, K key) {
		int avail = -1;
		int idx = index;
		do {
			if (isAvailable(idx)) {
				if (avail == -1) {
					avail = idx;
				}
				if (table[idx] == null) {
					return -(avail + 1);
				}
			} else if (table[idx].getKey().equals(key)) {
				return idx;
			}
			idx = (idx + 1) % table.length;
		} while (idx != index);
		return -(avail + 1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public V bucketRemove(int hash, K key) {
		int idx = findBucket(hash, key);
		if (idx < 0) {
			return null;
		}
		V answer = table[idx].getValue();
		table[idx].setDeleted(true);
		size--;
		return answer;
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
	protected int capacity() {
		return table.length;
	}

	/**
	 * The data structure which represents a bucket in a Linear Hash Map
	 * 
	 * @author Dr. King
	 *
	 * @param <K> The key of the entry
	 * @param <V> The value of the entry
	 */
	private static class TableEntry<K, V> extends MapEntry<K, V> {

		/** Value to keep track of whether the table entry has been deleted */
		private boolean isDeleted;

		/**
		 * Constructor for a TableEntry data structure which represents a bucket in a
		 * Linear Hash Map
		 * 
		 * @param key   the key of the entry
		 * @param value the value of the entry
		 */
		public TableEntry(K key, V value) {
			super(key, value);
			setDeleted(false);
		}

		/**
		 * Returns the value of whether the entry has been deleted
		 * 
		 * @return true if the Table Entry has be deleted
		 */
		public boolean isDeleted() {
			return isDeleted;
		}

		/**
		 * Set the value that represented whether the current Table Entry has been
		 * deleted
		 * 
		 * @param deleted value which represents whether the Table entry has been
		 *                deleted
		 */
		public void setDeleted(boolean deleted) {
			isDeleted = deleted;
		}
	}
}