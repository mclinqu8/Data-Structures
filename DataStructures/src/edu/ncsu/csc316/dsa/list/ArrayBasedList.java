package edu.ncsu.csc316.dsa.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An array-based list is a contiguous-memory representation of the List
 * abstract data type. This array-based list dynamically resizes to ensure O(1)
 * amortized cost for adding to the end of the list. Size is maintained as a
 * global field to allow for O(1) size() and isEmpty() behaviors.
 * 
 * @author Dr. King
 * @author Maggie Lin
 *
 * @param <E> the type of elements stored in the list
 */
public class ArrayBasedList<E> extends AbstractList<E> {

	/**
	 * The initial capacity of the list if the client does not provide a capacity
	 * when constructing an instance of the array-based list
	 **/
	private final static int DEFAULT_CAPACITY = 0;

	/** The array in which elements will be stored **/
	private E[] data;

	/** The number of elements stored in the array-based list data structure **/
	private int size;

	/**
	 * Constructs a new instance of an array-based list data structure with the
	 * default initial capacity of the internal array
	 */
	public ArrayBasedList() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Constructs a new instance of an array-based list data structure with the
	 * provided initial capacity
	 * 
	 * @param capacity the initial capacity of the internal array used to store the
	 *                 list elements
	 */
	@SuppressWarnings("unchecked")
	public ArrayBasedList(int capacity) {
		data = (E[]) (new Object[capacity]);
		size = 0;
	}

	/**
	 * Adds a new element at the index position in the array-based list
	 * 
	 * Citing Help from the Textbooks The code for this method is based on the
	 * ArrayList algorithm on page 261 in the course textbook "Data Structures and
	 * Algorithms" by Goodrich, Tamassia, Goldwasser.
	 * 
	 * @param index the index at which the new element should be added
	 * @param value the new element to add to the array-based list
	 */
	public void add(int index, E value) throws IndexOutOfBoundsException {
		// Check if index is out of bound
		checkIndexForAdd(index);
		// Check to see if array size is too small
		ensureCapacity(this.size + 1);
		// Shift values one to the right
		for (int i = this.size - 1; i >= index; i--) {
			data[i + 1] = data[i];
		}
		// Insert new value
		data[index] = value;
		size++;

	}

	/**
	 * Get the element at the index position in the array-based list
	 * 
	 * @param index the index of the element to get
	 * @return value at the index
	 */
	public E get(int index) throws IndexOutOfBoundsException {
		// Check if index is out of bound
		checkIndex(index);
		return data[index];
	}

	/**
	 * Removes an element at the index position in the array-based list
	 * 
	 * Citing Help from the Textbooks The code for this method is based on the
	 * ArrayList algorithm on page 261 in the course textbook "Data Structures and
	 * Algorithms" by Goodrich, Tamassia, Goldwasser.
	 * 
	 * @param index the index of the element to be removed
	 * @return value that was removed
	 */
	public E remove(int index) throws IndexOutOfBoundsException {

		// Check if index is out of bound
		checkIndex(index);
		E removedValue = data[index];

		for (int i = index; i < this.size - 1; i++) {
			data[i] = data[i + 1];
		}
		data[size - 1] = null;
		size--;

		return removedValue;
	}

	/**
	 * Replaces the element at the index with new value
	 * 
	 * Citing Help from the Textbooks The code for this method is based on the
	 * ArrayList algorithm on page 261 in the course textbook "Data Structures and
	 * Algorithms" by Goodrich, Tamassia, Goldwasser.
	 * 
	 * @param index the index at which the element is going to replace
	 * @param value the new element to replace the old element
	 * @return the old value that got replaced
	 */
	public E set(int index, E value) throws IndexOutOfBoundsException {
		// Check if index is out of bound
		checkIndex(index);
		// Set data at index to value
		E oldData = data[index];
		data[index] = value;
		return oldData;
	}

	/**
	 * Returns the number of elements in the ArrayBasedList
	 * 
	 * @return the number of elements in the ArrayBasedList
	 */
	public int size() {
		return size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	/**
	 * To ensure amortized O(1) cost for adding to the end of the array-based list,
	 * use the doubling strategy on each resize. Here, we add +1 after doubling to
	 * handle the special case where the initial capacity is 0 (otherwise, 0*2 would
	 * still produce a capacity of 0).
	 * 
	 * @param minCapacity the minimum capacity that must be supported by the
	 *                    internal array
	 */
	private void ensureCapacity(int minCapacity) {
		int oldCapacity = data.length;
		if (minCapacity > oldCapacity) {
			int newCapacity = oldCapacity * 2 + 1;
			if (newCapacity < minCapacity) {
				newCapacity = minCapacity;
			}
			data = Arrays.copyOf(data, newCapacity);
		}
	}

	private class ElementIterator implements Iterator<E> {
		/** Position of the iterator */
		private int position;

		/** Whether it is OK to remove current value */
		private boolean removeOK;

		/**
		 * Construct a new element iterator where the cursor is initialized to the
		 * beginning of the list.
		 */
		public ElementIterator() {
			position = 0;
			removeOK = false;
		}

		/**
		 * Test whether the iterator has a next object.
		 * 
		 * Citing Help from the Textbooks The code for this method is based on the
		 * ArrayIterator algorithm on page 285 in the course textbook "Data Structures
		 * and Algorithms" by Goodrich, Tamassia, Goldwasser.
		 * 
		 * @return true if there are further objects, false otherwise
		 */
		@Override
		public boolean hasNext() {
			return position < size;
		}

		/**
		 * Returns the next object in the iterator.
		 * 
		 * Citing Help from the Textbooks The code for this method is based on the
		 * ArrayIterator algorithm on page 285 in the course textbook "Data Structures
		 * and Algorithms" by Goodrich, Tamassia, Goldwasser.
		 * 
		 * @return next object
		 * @throws NoSuchElementException if there are no further elements
		 */
		@Override
		public E next() throws NoSuchElementException {
			if (position == size) {
				throw new NoSuchElementException();
			}
			removeOK = true;
			return data[position++];
		}

		/**
		 * Removes the element returned by most recent call to next.
		 * 
		 * Citing Help from the Textbooks The code for this method is based on the
		 * ArrayIterator algorithm on page 285 in the course textbook "Data Structures
		 * and Algorithms" by Goodrich, Tamassia, Goldwasser.
		 * 
		 * @throws IllegalStateException if next has not yet been called
		 * @throws IllegalStateException if remove was already called since recent next
		 */
		@Override
		public void remove() throws IllegalStateException {
			if (!removeOK) {
				throw new IllegalStateException();
			}
			ArrayBasedList.this.remove(position - 1);
			position--;
			removeOK = false;
		}
	}
}