package edu.ncsu.csc316.dsa.queue;

import java.util.NoSuchElementException;

/**
 * The Array-based Queue is implemented as a circular array-based data structure
 * to support efficient, O(1) worst-case Queue abstract data type behaviors. The
 * internal array dynamically resizes using the doubling strategy to ensure O(1)
 * amortized cost for adding to the queue.
 * 
 * @author Dr. King
 * @author Maggie Lin
 *
 * @param <E> the type of elements stored in the queue
 */
public class ArrayBasedQueue<E> extends AbstractQueue<E> {

	/**
	 * Internal array to store the data within the queue
	 */
	private E[] data;

	/**
	 * A reference to the index of the first element in the queue
	 */
	private int front;

	/**
	 * A reference to the index immediately after the last element in the queue
	 */
	private int rear;

	/**
	 * The number of elements stored in the queue
	 */
	private int size;

	/**
	 * The initial default capacity of the internal array that stores the data
	 */
	private static final int DEFAULT_CAPACITY = 0;

	/**
	 * Constructs a new array-based queue with the given initialCapcity for the
	 * array
	 * 
	 * @param initialCapacity the initial capacity to use when creating the initial
	 *                        array
	 */
	@SuppressWarnings("unchecked")
	public ArrayBasedQueue(int initialCapacity) {
		data = (E[]) (new Object[initialCapacity]);
		size = 0;
	}

	/**
	 * Constructs a new array-based queue with the default initial capacity for the
	 * array
	 */
	public ArrayBasedQueue() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * To ensure amortized O(1) cost for adding to the array-based queue, use the
	 * doubling strategy on each resize. Here, we add +1 after doubling to handle
	 * the special case where the initial capacity is 0 (otherwise, 0*2 would still
	 * produce a capacity of 0).
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
			@SuppressWarnings("unchecked")
			E[] newData = (E[]) (new Object[newCapacity]);
			if (oldCapacity == 0) {
				front = 0;
				rear = front;
				data = newData;
			} else if (rear == front) {
				int index = 0;
				for (int i = front; i < data.length; i++) {
					newData[index] = data[i];
					index++;
				}
				for (int j = 0; j < rear; j++) {
					newData[index] = data[j];
					index++;
				}
				data = newData;
				front = 0;
				rear = index;
			} else {
				int index = 0;
				for (int i = front; i < rear; i++) {
					newData[index] = data[i];
					index++;
				}
				data = newData;
				front = 0;
				rear = index;
			}

		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void enqueue(E element) {
		ensureCapacity(this.size + 1);

		if (rear == data.length) {
			rear = 0;
		}
		data[rear] = element;
		rear++;
		size++;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E dequeue() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		if (front == data.length) {
			front = 0;
		}
		E element = data[front];
		data[front] = null;
		front++;
		size--;
		return element;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E front() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return data[front];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return size;
	}
}