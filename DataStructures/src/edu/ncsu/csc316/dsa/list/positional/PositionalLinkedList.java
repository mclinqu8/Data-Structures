package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * The Positional Linked List is implemented as a doubly-linked list data
 * structure to support efficient, O(1) worst-case Positional List abstract data
 * type behaviors.
 * 
 * Size is maintained as a global field to ensure O(1) worst-case runtime of
 * size() and isEmpty().
 * 
 * The PositionalLinkedList class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Maggie Lin
 *
 * @param <E> the type of elements stored in the positional list
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

	/** A dummy/sentinel node representing at the front of the list **/
	private PositionalNode<E> front;

	/** A dummy/sentinel node representing at the end/tail of the list **/
	private PositionalNode<E> tail;

	/** The number of elements in the list **/
	private int size;

	/**
	 * Constructs an empty positional linked list
	 */
	public PositionalLinkedList() {
		front = new PositionalNode<E>(null);
		tail = new PositionalNode<E>(null, null, front);
		front.setNext(tail);
		size = 0;
	}

	/**
	 * Adds element to the Linked list between the given nodes.
	 * 
	 * Citing Help from the Textbooks The code for this method is based on the
	 * ArrayList algorithm on page 278 in the course textbook "Data Structures and
	 * Algorithms" by Goodrich, Tamassia, Goldwasser.
	 * 
	 * @param element element to add
	 * @param next    the PositionalNode after the element
	 * @param prev    the PositionalNode before the element
	 * @return the new Position
	 */
	private Position<E> addBetween(E element, PositionalNode<E> next, PositionalNode<E> prev) {
		PositionalNode<E> newNode = new PositionalNode<E>(element, next, prev);
		prev.setNext(newNode);
		next.setPrevious(newNode);
		size++;
		return newNode;
	}

	/**
	 * Insert the element at the front of the linked list and returns its new
	 * Position.
	 * 
	 * Citing Help from the Textbooks The code for this method is based on the
	 * ArrayList algorithm on page 279 in the course textbook "Data Structures and
	 * Algorithms" by Goodrich, Tamassia, Goldwasser.
	 * 
	 * @param element element to add
	 * @return the new Position
	 */
	public Position<E> addFirst(E element) {
		return addBetween(element, front.getNext(), front);
	}

	/**
	 * Insert the element at the end of the linked list and returns its new
	 * Position.
	 * 
	 * Citing Help from the Textbooks The code for this method is based on the
	 * ArrayList algorithm on page 278 in the course textbook "Data Structures and
	 * Algorithms" by Goodrich, Tamassia, Goldwasser.
	 * 
	 * @param element element to add
	 * @return the new Position
	 */
	public Position<E> addLast(E element) {
		return addBetween(element, tail, tail.getPrevious());
	}

	/**
	 * Insert the element immediately before the given position and returns its new
	 * Position.
	 * 
	 * Citing Help from the Textbooks The code for this method is based on the
	 * ArrayList algorithm on page 278 in the course textbook "Data Structures and
	 * Algorithms" by Goodrich, Tamassia, Goldwasser.
	 * 
	 * @param p       position the element will be added before
	 * @param element element to add
	 * @return the new Position
	 */
	public Position<E> addBefore(Position<E> p, E element) {
		PositionalNode<E> node = validate(p);
		return addBetween(element, node, node.getPrevious());
	}

	/**
	 * Insert the element immediately after the given position and returns its new
	 * Position.
	 * 
	 * Citing Help from the Textbooks The code for this method is based on the
	 * ArrayList algorithm on page 278 in the course textbook "Data Structures and
	 * Algorithms" by Goodrich, Tamassia, Goldwasser.
	 * 
	 * @param p       position the element will be added after
	 * @param element element to add
	 * @return the new Position
	 */
	public Position<E> addAfter(Position<E> p, E element) {
		PositionalNode<E> node = validate(p);
		return addBetween(element, node.getNext(), node);
	}

	/**
	 * Return the Position after given Position.
	 * 
	 * Citing Help from the Textbooks The code for this method is based on the
	 * ArrayList algorithm on page 278 in the course textbook "Data Structures and
	 * Algorithms" by Goodrich, Tamassia, Goldwasser.
	 * 
	 * @param p current Position
	 * @return the Position after current Position
	 * @throws IllegalArgumentException if p is null, or if p is not a valid
	 *                                  PositionalNode
	 */
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		PositionalNode<E> node = validate(p);
		return position(node.getNext());
	}

	/**
	 * Return the Position before given Position.
	 * 
	 * Citing Help from the Textbooks The code for this method is based on the
	 * ArrayList algorithm on page 278 in the course textbook "Data Structures and
	 * Algorithms" by Goodrich, Tamassia, Goldwasser.
	 * 
	 * @param p current Position
	 * @return the Position before current Position
	 * @throws IllegalArgumentException if p is null, or if p is not a valid
	 *                                  PositionalNode
	 */
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		PositionalNode<E> node = validate(p);
		return position(node.getPrevious());
	}

	/**
	 * Return the given PositionalNode as a Position or null if it is a sentinel
	 * node.
	 * 
	 * Citing Help from the Textbooks The code for this method is based on the
	 * ArrayList algorithm on page 279 in the course textbook "Data Structures and
	 * Algorithms" by Goodrich, Tamassia, Goldwasser.
	 * 
	 * @param node given PositionalNode to check if it is not a sentinel node
	 * @return given PositionalNode or null if given PositionalNode is a sentinel
	 *         node
	 */
	private Position<E> position(PositionalNode<E> node) {
		if (node == front || node == tail) {
			return null;
		}
		return node;
	}

	/**
	 * Return the first Position in the linked list or null if linked list is empty.
	 * 
	 * Citing Help from the Textbooks The code for this method is based on the
	 * ArrayList algorithm on page 278 in the course textbook "Data Structures and
	 * Algorithms" by Goodrich, Tamassia, Goldwasser.
	 * 
	 * @return the first Position in the linked list or null if linked list is empty
	 */
	public Position<E> first() {
		return position(front.getNext());
	}

	/**
	 * Return the last Position in the linked list or null if linked list is empty.
	 * 
	 * Citing Help from the Textbooks The code for this method is based on the
	 * ArrayList algorithm on page 278 in the course textbook "Data Structures and
	 * Algorithms" by Goodrich, Tamassia, Goldwasser.
	 * 
	 * @return the last Position in the linked list or null if linked list is empty
	 */
	public Position<E> last() {
		return position(tail.getPrevious());
	}

	/**
	 * Return the number of elements in the PositionalLinkedList.
	 * 
	 * Citing Help from the Textbooks The code for this method is based on the
	 * ArrayList algorithm on page 278 in the course textbook "Data Structures and
	 * Algorithms" by Goodrich, Tamassia, Goldwasser.
	 * 
	 * @return the number of elements in the PositionalLinkedList
	 */
	public int size() {
		return size;
	}

	/**
	 * Return if the PositionalLinkedList is empty.
	 * 
	 * Citing Help from the Textbooks The code for this method is based on the
	 * ArrayList algorithm on page 278 in the course textbook "Data Structures and
	 * Algorithms" by Goodrich, Tamassia, Goldwasser.
	 * 
	 * @return if the PositionalLinkedList is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Removes the element stored at the given Position and returns it.
	 * 
	 * Citing Help from the Textbooks The code for this method is based on the
	 * ArrayList algorithm on page 278 in the course textbook "Data Structures and
	 * Algorithms" by Goodrich, Tamassia, Goldwasser.
	 * 
	 * @param p the Position to remove the element from
	 * @return the removed element
	 * @throws IllegalArgumentException if p is null, or if p is not a valid
	 *                                  PositionalNode
	 */
	public E remove(Position<E> p) throws IllegalArgumentException {
		PositionalNode<E> node = validate(p);
		PositionalNode<E> pred = node.getPrevious();
		PositionalNode<E> succ = node.getNext();
		pred.setNext(succ);
		succ.setPrevious(pred);
		size--;
		E value = node.getElement();
		node.setElement(null);
		node.setNext(null);
		node.setPrevious(null);
		return value;
	}

	/**
	 * Replaces the element stored at the given Position and returns the replaced
	 * element.
	 * 
	 * Citing Help from the Textbooks The code for this method is based on the
	 * ArrayList algorithm on page 278 in the course textbook "Data Structures and
	 * Algorithms" by Goodrich, Tamassia, Goldwasser.
	 * 
	 * @param p       the Position to replace the element
	 * @param element the new element to replace the old element
	 * @return the replaced element
	 * @throws IllegalArgumentException if p is null, or if p is not a valid
	 *                                  PositionalNode
	 */
	public E set(Position<E> p, E element) throws IllegalArgumentException {
		PositionalNode<E> node = validate(p);
		E value = node.getElement();
		node.setElement(element);
		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<Position<E>> positions() {
		return new PositionIterable();
	}

	/**
	 * Adapts the PositionIterator so that it can return Iterable object
	 *
	 * @author Dr. King
	 */
	private class PositionIterable implements Iterable<Position<E>> {
		/**
		 * {@inheritDoc}
		 */
		@Override
		public Iterator<Position<E>> iterator() {
			return new PositionIterator();
		}
	}

	/**
	 * Iterator for PositionalLinkedList
	 * 
	 * @author Dr. King
	 * @author Maggie Lin
	 */
	private class PositionIterator implements Iterator<Position<E>> {

		/** Position of the next element */
		private Position<E> current;

		/** Position of the last reported element */
		private Position<E> recent;

		/** If the element at the current position is removable */
		private boolean removeOK;

		/**
		 * Constructs a Position Iterator
		 */
		public PositionIterator() {
			current = first();
			removeOK = false;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasNext() {
			return current != null;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Position<E> next() throws NoSuchElementException {
			if (current == null) {
				throw new NoSuchElementException("nothing left");
			}
			removeOK = true;
			recent = current;
			current = after(current);
			return recent;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void remove() throws IllegalStateException {
			if (recent == null || !removeOK ) {
				throw new IllegalStateException("nothing to remove");
			}
			PositionalLinkedList.this.remove(recent);
			recent = null;
		}
	}

	/**
	 * Iterator for elements using PositionIterator
	 * 
	 * @author Dr. King
	 *
	 */
	private class ElementIterator implements Iterator<E> {
		/**
		 * Position of Iterator
		 */
		private Iterator<Position<E>> it;

		/**
		 * Construct a new element iterator.
		 */
		public ElementIterator() {
			it = new PositionIterator();
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
		public E next() {
			return it.next().getElement();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void remove() {
			it.remove();
		}
	}

	/**
	 * Safely casts a Position, p, to be a PositionalNode.
	 * 
	 * @param p the position to cast to a PositionalNode
	 * @return a reference to the PositionalNode
	 * @throws IllegalArgumentException if p is null, or if p is not a valid
	 *                                  PositionalNode
	 */
	private PositionalNode<E> validate(Position<E> p) {
		if (p instanceof PositionalNode) {
			return (PositionalNode<E>) p;
		}
		throw new IllegalArgumentException("Position is not a valid positional list node.");
	}

	/**
	 * PositionalNode is the data object for PositionalLinkedList
	 * 
	 * @author Dr. King
	 * @param <E> the type of elements stored in the list
	 */
	private static class PositionalNode<E> implements Position<E> {

		/** Data stored in a PositionalLinkedList */
		private E element;

		/** Reference to next PositionalNode */
		private PositionalNode<E> next;

		/** Reference to previous PositionalNode */
		private PositionalNode<E> previous;

		/**
		 * Constructor for a PositionalLinkedList that sets the data to the given value
		 * and the reference for the next PositionalNode and previous PositionalNode to
		 * null
		 * 
		 * @param value the data it be stored in the PositionalNode
		 */
		public PositionalNode(E value) {
			this(value, null);
		}

		/**
		 * Constructor for a PositionalLinkedList that sets the data to the given value
		 * and the reference for the next PositionalNode to the given PositionalNodes
		 * and the previous PositionalNode to null
		 * 
		 * @param value the data it be stored in the PositionalNode
		 * @param next  the next PositionalNode after the current PositionalNode
		 */
		public PositionalNode(E value, PositionalNode<E> next) {
			this(value, next, null);
		}

		/**
		 * Constructor for a PositionalLinkedList that sets the data to the given value
		 * and the reference for the next PositionalNode and previous PositionalNode to
		 * the given PositionalNodes
		 * 
		 * @param value the data it be stored in the PositionalNode
		 * @param next  the next PositionalNode after the current PositionalNode
		 * @param prev  the previous PositionalNode before the current PositionalNode
		 */
		public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
			setElement(value);
			setNext(next);
			setPrevious(prev);
		}

		/**
		 * Set the reference of the previous PositionalNode to the PositionalNode given
		 * 
		 * @param prev PositionalNode that should be set as the reference to the
		 *             previous PositionalNode
		 */
		public void setPrevious(PositionalNode<E> prev) {
			previous = prev;
		}

		/**
		 * Get the previous PositionalNode
		 * 
		 * @return previous PositionalNode
		 */
		public PositionalNode<E> getPrevious() {
			return previous;
		}

		/**
		 * Set the reference of the next PositionalNode to the PositionalNode given
		 * 
		 * @param next PositionalNode that should be set as the reference to the next
		 *             PositionalNode
		 */
		public void setNext(PositionalNode<E> next) {
			this.next = next;
		}

		/**
		 * Get the next PositionalNode
		 * 
		 * @return next PositionalNode
		 */
		public PositionalNode<E> getNext() {
			return next;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public E getElement() {
			return element;
		}

		/**
		 * Set the data the PositionalNode is holding to the given value
		 * 
		 * @param element the value to change the current data in the PositionalNode to
		 */
		public void setElement(E element) {
			this.element = element;
		}
	}

}