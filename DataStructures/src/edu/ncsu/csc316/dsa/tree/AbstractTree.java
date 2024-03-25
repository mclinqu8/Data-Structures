package edu.ncsu.csc316.dsa.tree;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;
import edu.ncsu.csc316.dsa.queue.Queue;

/**
 * A skeletal implementation of the Tree abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the tree
 * abstract data type.
 * 
 * Citing Help from the Textbooks. The code for several traversal methods is
 * based on the Preorder, Postorder, and Breadthfirst traversal algorithm on
 * page 339 - 341 in the course textbook "Data Structures and Algorithms" by
 * Goodrich, Tamassia, Goldwasser.
 * 
 * @author Dr. King
 * @author Maggie Lin
 *
 * @param <E> the type of elements stored in the tree
 */
public abstract class AbstractTree<E> implements Tree<E> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isInternal(Position<E> p) {
		return numChildren(p) > 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isLeaf(Position<E> p) {
		return numChildren(p) == 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isRoot(Position<E> p) {
		return p == root();
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
	public E set(Position<E> p, E value) {
		E original = p.getElement();
		this.validate(p).setElement(value);
		return original;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<Position<E>> preOrder() {
		PositionCollection traversal = new PositionCollection();
		if (!isEmpty()) {
			preOrderHelper(root(), traversal);
		}
		return traversal;
	}

	/**
	 * Helper method for preOrder method, allows positions to be pre-orderly added
	 * to the Iterable collection of positions
	 * 
	 * @param p         the current position to add to the Iterable collection of
	 *                  positions
	 * @param traversal an Iterable collection of positions that represent the
	 *                  pre-order traversal of positions within the tree
	 */
	private void preOrderHelper(Position<E> p, PositionCollection traversal) {
		traversal.add(p);
		for (Position<E> c : children(p)) {
			preOrderHelper(c, traversal);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<Position<E>> postOrder() {
		PositionCollection traversal = new PositionCollection();
		if (!isEmpty()) {
			postOrderHelper(root(), traversal);
		}
		return traversal;
	}

	/**
	 * Helper method for postOrder method, allows positions to be post-orderly added
	 * to the Iterable collection of positions
	 * 
	 * @param p         the current position to add to the Iterable collection of
	 *                  positions
	 * @param traversal an Iterable collection of positions that represent the
	 *                  post-order traversal of positions within the tree
	 */
	private void postOrderHelper(Position<E> p, PositionCollection traversal) {
		for (Position<E> c : children(p)) {
			postOrderHelper(c, traversal);
		}
		traversal.add(p);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<Position<E>> levelOrder() {
		PositionCollection traversal = new PositionCollection();
		if (!isEmpty()) {
			Queue<Position<E>> temp = new ArrayBasedQueue<>();
			temp.enqueue(root());
			while (!temp.isEmpty()) {
				Position<E> p = temp.dequeue();
				traversal.add(p);
				for (Position<E> c : children(p)) {
					temp.enqueue(c);
				}
			}
		}
		return traversal;
	}

	/**
	 * Safely casts a Position, p, to be an AbstractTreeNode.
	 * 
	 * @param p the position to cast to a AbstractTreeNode
	 * @return a reference to the AbstractTreeNode
	 * @throws IllegalArgumentException if p is null, or if p is not a valid
	 *                                  AbstractTreeNode
	 */
	protected abstract AbstractTreeNode<E> validate(Position<E> p);

	protected abstract static class AbstractTreeNode<E> implements Position<E> {

		/** Element to be contained within the current AbstractTreeNode */
		private E element;

		/**
		 * Constructor for an AbstractTreeNode
		 * 
		 * @param element the element to be contained within the given AbstractTreeNode
		 */
		public AbstractTreeNode(E element) {
			setElement(element);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public E getElement() {
			return element;
		}

		/**
		 * Set the element of the current AbstractTreeNode to the given element
		 * 
		 * @param element the element to be stored within the given AbstractTreeNode
		 */
		public void setElement(E element) {
			this.element = element;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[\n");
		toStringHelper(sb, "", root());
		sb.append("]");
		return sb.toString();
	}

	/**
	 * The toString helper method to create a string representation of the tree
	 * 
	 * @param sb     the StringBuilder that will contain the string representation
	 *               of the tree
	 * @param indent the number of indents to add to the StringBuilder
	 * @param root   the root of the tree
	 */
	private void toStringHelper(StringBuilder sb, String indent, Position<E> root) {
		if (root == null) {
			return;
		}
		sb.append(indent).append(root.getElement()).append("\n");
		for (Position<E> child : children(root)) {
			toStringHelper(sb, indent + " ", child);
		}
	}

	/**
	 * PositionCollection implements the {@link Iterable} interface to allow
	 * traversing through the positions of the tree. PositionCollection does not
	 * allow removal operations
	 * 
	 * @author Dr. King
	 *
	 */
	protected class PositionCollection implements Iterable<Position<E>> {

		/** The list of position that can be iterated through */
		private List<Position<E>> list;

		/**
		 * Construct a new PositionCollection
		 */
		public PositionCollection() {
			list = new SinglyLinkedList<Position<E>>();
		}

		/**
		 * Add a position to the collection. Null positions or positions with null
		 * elements are not added to the collection
		 * 
		 * @param position the position to add to the collection
		 */
		public void add(Position<E> position) {
			if (position != null && position.getElement() != null) {
				list.addLast(position);
			}
		}

		/**
		 * Return an iterator for PositionCollection
		 * 
		 * @return an iterator for the collection of all the positions stored within the
		 *         tree
		 */
		public Iterator<Position<E>> iterator() {
			return new PositionSetIterator();
		}

		private class PositionSetIterator implements Iterator<Position<E>> {

			/** Iterator for positions */
			private Iterator<Position<E>> it;

			/**
			 * Set the iterator for positions to an iterator for PositionCollection
			 */
			public PositionSetIterator() {
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
			public Position<E> next() {
				return it.next();
			}

			/**
			 * The remove operation is unsupported for PositionCollection iterator
			 */
			@Override
			public void remove() {
				throw new UnsupportedOperationException("The remove operation is not supported yet.");
			}
		}
	}
}