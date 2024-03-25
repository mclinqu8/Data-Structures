package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * A skeletal implementation of the Map abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the map
 * abstract data type.
 * 
 * @author Dr. King
 * @author Maggie Lin
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {

	/**
	 * MapEntry implements the Entry abstract data type.
	 * 
	 * @author Dr. King
	 *
	 * @param <K> the type of key stored in the entry
	 * @param <V> the type of value stored in the entry
	 */
	protected static class MapEntry<K, V> implements Entry<K, V> {

		/** The key element of a map entry */
		private K key;

		/** The value element of a map entry */
		private V value;

		/**
		 * Constructs a MapEntry with a provided key and a provided value
		 * 
		 * @param key   the key to store in the entry
		 * @param value the value to store in the entry
		 */
		public MapEntry(K key, V value) {
			setKey(key);
			setValue(value);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public K getKey() {
			return key;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public V getValue() {
			return value;
		}

		/**
		 * Set the key of the entry to the provided key
		 * 
		 * @param key the key to store in the entry
		 */
		private void setKey(K key) {
			this.key = key;
		}

		/**
		 * Set the value of the entry to the provided value
		 * 
		 * @param value the value to store in the entry
		 */
		public void setValue(V value) {
			this.value = value;
		}

		/**
		 * {@inheritDoc}
		 */
		@SuppressWarnings("unchecked")
		@Override
		public int compareTo(Entry<K, V> o) {
			return ((Comparable<K>) this.key).compareTo(o.getKey());
		}
	}

	/**
	 * EntryCollection implements the {@link Iterable} interface to allow traversing
	 * through the entries stored in the map. EntryCollection does not allow removal
	 * operations
	 * 
	 * @author Dr. King
	 *
	 */
	protected class EntryCollection implements Iterable<Entry<K, V>> {

		/** The list of entries stored in the map */
		private List<Entry<K, V>> list;

		/** Constructs an empty list of entries */
		public EntryCollection() {
			list = new SinglyLinkedList<Entry<K, V>>();
		}

		/**
		 * Add an entry into the list of entries
		 * 
		 * @param entry an entry in the map
		 */
		public void add(Entry<K, V> entry) {
			list.addLast(entry);
		}

		/**
		 * Return an iterator of entries
		 * 
		 * @return return an iterator of entries
		 */
		public Iterator<Entry<K, V>> iterator() {
			return new EntryCollectionIterator();
		}

		/**
		 * EntryCollectionIterator implements the {@link Iterator} interface to allow
		 * traversing through the entries stored in the map
		 * 
		 * @author Dr. King
		 * @author Maggie Lin
		 * 
		 */
		private class EntryCollectionIterator implements Iterator<Entry<K, V>> {

			/** The list of entries stored in the map */
			private Iterator<Entry<K, V>> it;

			/** Constructs an iterator for entries */
			public EntryCollectionIterator() {
				it = list.iterator();
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public Entry<K, V> next() {
				return it.next();
			}

			/**
			 * Do not allow for remove operation
			 * 
			 * @throws UnsupportedOperationException when remove operation is called
			 */
			@Override
			public void remove() {
				throw new UnsupportedOperationException("The remove operation is not supported yet.");
			}
		}
	}

	/**
	 * KeyIterator implements the {@link Iterator} interface to allow traversing
	 * through the keys stored in the map
	 * 
	 * @author Dr. King
	 *
	 */
	protected class KeyIterator implements Iterator<K> {

		/** The entry iterator */
		private Iterator<Entry<K, V>> it;

		/** Constructs an iterator for entry key */
		public KeyIterator() {
			it = entrySet().iterator();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public K next() {
			return it.next().getKey();
		}

		/**
		 * Do not allow for remove operation
		 * 
		 * @throws UnsupportedOperationException when remove operation is called
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException("The remove operation is not supported yet.");
		}
	}

	/**
	 * ValueIterator implements the {@link Iterator} interface to allow traversing
	 * through the values stored in the map
	 * 
	 * @author Dr. King
	 *
	 */
	protected class ValueIterator implements Iterator<V> {

		/** The entry iterator */
		private Iterator<Entry<K, V>> it;

		/** Constructs an iterator for entry value */
		public ValueIterator() {
			it = entrySet().iterator();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public V next() {
			return it.next().getValue();
		}

		/**
		 * Do not allow for remove operation
		 * 
		 * @throws UnsupportedOperationException when remove operation is called
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException("The remove operation is not supported yet.");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<K> iterator() {
		return new KeyIterator();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<V> values() {
		return new ValueIterable();
	}

	private class ValueIterable implements Iterable<V> {

		/**
		 * Return an iterator for entry value
		 * 
		 * @return an iterator for entry value
		 */
		@Override
		public Iterator<V> iterator() {
			return new ValueIterator();
		}
	}

}