package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singly-linked list is a linked-memory representation of the List abstract
 * data type. This list maintains a dummy/sentinel front node in the list to
 * help promote cleaner implementations of the list behaviors. This list also
 * maintains a reference to the tail/last node in the list at all times to
 * ensure O(1) worst-case cost for adding to the end of the list. Size is
 * maintained as a global field to allow for O(1) size() and isEmpty()
 * behaviors.
 * 
 * @author Dr. King
 * @author Maggie Lin
 *
 * @param <E> the type of elements stored in the list
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

	/** A reference to the dummy/sentinel node at the front of the list **/
	private LinkedListNode<E> front;

	/** A reference to the last/final node in the list **/
	private LinkedListNode<E> tail;

	/** The number of elements stored in the list **/
	private int size;

	/**
	 * Constructs an empty singly-linked list
	 */
	public SinglyLinkedList() {
		front = new LinkedListNode<E>(null);
		tail = null;
		size = 0;
	}

	/**
	 * Adds a new element at the index position in the SinglyLinkedList
	 * 
	 * @param index the index at which the new element should be added
	 * @param value the new element to add to the array-based list
	 * @throws IndexOutOfBoundsException if index is out of bounds
	 */
	public void add(int index, E value) throws IndexOutOfBoundsException {
		checkIndexForAdd(index);
		LinkedListNode<E> current = front.next;

		// Add to the beginning
		if (index == 0) {
			front.next = new LinkedListNode<E>(value, current);
		} else if (index == size) {
			current = tail;
			current.next = new LinkedListNode<E>(value, null);
			tail = current.next;
		} else {
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			current.next = new LinkedListNode<E>(value, current.next);
		}
		size++;
		if (size == 1) {
			tail = front.next;
		}

	}

	/**
	 * {@inheritDoc} For a singly-linked list, this behavior has O(1) worst-case
	 * runtime.
	 */
	@Override
	public E last() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("The list is empty");
		}
		return tail.getElement();
	}

	/**
	 * {@inheritDoc} For this singly-linked list, addLast(element) behavior has O(1)
	 * worst-case runtime.
	 */
	@Override
	public void addLast(E element) {
		add(size, element);

	}

	/**
	 * Returns the number of elements in the SinglyLinkedList
	 * 
	 * @return the number of elements in the SinglyLinkedList
	 */
	public int size() {
		return size;
	}

	/**
	 * Removes an element at the index position in the SinglyLinkedList
	 * 
	 * @param index the index of the element to be removed
	 * @return value that was removed
	 * @throws IndexOutOfBoundsException if index is out of bounds
	 */
	public E remove(int index) throws IndexOutOfBoundsException {
		checkIndex(index);
		LinkedListNode<E> current = front.next;
		E remElement = null;
		if (index == 0) {
			remElement = current.data;
			front.next = current.next;
		} else if (index == size - 1) {
			for (int i = 0; i < size - 2; i++) {
				current = current.next;
			}
			tail = current;
			remElement = current.getNext().data;
			current.next = null;
		} else {
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			remElement = current.getNext().data;
			current.setNext(current.next.next);
		}
		size--;
		return remElement;
	}

	/**
	 * Replaces the element at the index with new value
	 * 
	 * @param index   the index at which the element is going to replace
	 * @param element the new element to replace the old element
	 * @return the old value that got replaced
	 * @throws IndexOutOfBoundsException if index is out of bounds
	 */
	public E set(int index, E element) throws IndexOutOfBoundsException {
		checkIndex(index);
		LinkedListNode<E> current = front.next;
		E value = null;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		value = current.data;
		current.setElement(element);
		return value;
	}

	/**
	 * Get the element at the index position in the SinglyLinkedList
	 * 
	 * @param index the index of the element to get
	 * @return value at the index
	 * @throws IndexOutOfBoundsException if index is out of bounds
	 */
	public E get(int index) throws IndexOutOfBoundsException {
		checkIndex(index);
		LinkedListNode<E> current = front.next;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}

		return current.getElement();
	}

	/**
	 * LinkedListNode is the data object for the SinglyLinkedList
	 * 
	 * @author Dr.King
	 * @author Maggie Lin
	 *
	 * @param <E> the type of elements stored in the list
	 */
	private static class LinkedListNode<E> {

		/** Data stored in a LinkedListNode */
		private E data;

		/** Reference to another LinkedListNode */
		private LinkedListNode<E> next;

		/**
		 * Constructor for a LinkedListNode that sets the data to the given value and
		 * the reference for the next LinkedListNode to null
		 * 
		 * @param value data to be stored in the LinkedListNode
		 */
		public LinkedListNode(E value) {
			this(value, null);
		}

		/**
		 * Constructor for a LinkedListNode that sets the data to the given value and
		 * the reference the next LinkedListNode as the given LinkedListNode
		 * 
		 * @param value    the value to be stored in the LinkedListNode
		 * @param nextNode the next LinkedListNode on the SinglyLLinkedList
		 */
		public LinkedListNode(E value, LinkedListNode<E> nextNode) {
			data = value;
			next = nextNode;
		}

		/**
		 * Gets the next LinkedListNode after this current one
		 * 
		 * @return the next LinkedListNode on the SinglyLinkedList
		 */
		public LinkedListNode<E> getNext() {
			return next;
		}

		/**
		 * Return the data of the current LinkedListNode
		 * 
		 * @return the data of the current LinkedListNode
		 */
		public E getElement() {
			return data;
		}

		/**
		 * Set the reference of the next LinkedListNode to the LinkedListNode given
		 * 
		 * @param nextNode LinkedListNode that should be set as the reference to the
		 *                 next LinkedListNode
		 */
		public void setNext(LinkedListNode<E> nextNode) {
			next = nextNode;
		}

		/**
		 * Set the data the LinkedListNode is holding to the given value
		 * 
		 * @param value the value to change the current data in the LinkedListNode to
		 */
		public void setElement(E value) {
			data = value;
		}
	}

	/**
	 * {@inheritDoc} The Iterator for the SiglyLinkedList
	 */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	/**
	 * An Element Iterator that iterates through LinkedListNode
	 * 
	 * @author Dr. King
	 * @author Maggie Lin
	 *
	 */
	private class ElementIterator implements Iterator<E> {
		/**
		 * Keep track of the next node that will be processed
		 */
		private LinkedListNode<E> current;

		/**
		 * Keep track of the node that was processed on the last call to 'next'
		 */
		private LinkedListNode<E> previous;

		/**
		 * Keep track of the previous-previous node that was processed so that we can
		 * update 'next' links when removing
		 */
		private LinkedListNode<E> previousPrevious;

		/**
		 * Keep track of whether it's ok to remove an element (based on whether next()
		 * has been called immediately before remove())
		 */
		private boolean removeOK;

		/**
		 * Construct a new element iterator where the cursor is initialized to the
		 * beginning of the list.
		 */
		public ElementIterator() {
			current = front.next;
			previous = front;
			previousPrevious = front;
			removeOK = false;
		}

		/**
		 * {@inheritDoc} Return if the SinglyLinkedList still have a next LinkedListNode
		 */
		@Override
		public boolean hasNext() {
			return current != null;
		}

		/**
		 * {@inheritDoc} Returns where the current cursor is on and then moves forward
		 * 
		 * @throws NoSuchElementException if there is no following element
		 */
		@Override
		public E next() throws NoSuchElementException {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			removeOK = true;
			E result = current.data;
			previousPrevious = previous;
			previous = current;
			current = current.next;
			return result;
		}

		/**
		 * {@inheritDoc} Removes the LinkedListNode returned on the preceding call to
		 * the next() method
		 * 
		 * @throws IllegalStateException if there is nothing to remove
		 */
		@Override
		public void remove() throws IllegalStateException {
			if (!removeOK) {
				throw new IllegalStateException();
			}
			if (!hasNext()) {
				tail = previousPrevious;
			}
			previousPrevious.next = current;
			previous = previousPrevious;
			size--;

			removeOK = false;
		}
	}

}