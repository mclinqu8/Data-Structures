package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * A skeletal implementation of the Binary Tree abstract data type. This class
 * provides implementation for common methods that can be implemented the same
 * no matter what specific type of concrete data structure is used to implement
 * the binary tree abstract data type.
 * 
 * Citing Help from the Textbooks. The code for several traversal methods is
 * based on the Inorder traversal algorithm on page 341 in the course
 * textbook "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
 * 
 * @author Dr. King
 * @author Maggie Lin
 *
 * @param <E> the type of elements stored in the binary tree
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<Position<E>> inOrder() {
		PositionCollection traversal = new PositionCollection();
		if (!isEmpty()) {
			inOrderHelper(root(), traversal);
		}
		return traversal;
	}

	/**
	 * Helper method for inOrder method, allows positions to be in-orderly added to
	 * the Iterable collection of positions
	 * 
	 * @param p         the current position to add to the Iterable collection of
	 *                  positions
	 * @param traversal an Iterable collection of positions that represent the
	 *                  in-order traversal of positions within the tree
	 */
	private void inOrderHelper(Position<E> p, PositionCollection traversal) {
		if (left(p) != null) {
			inOrderHelper(left(p), traversal);
		}
		traversal.add(p);
		if (right(p) != null) {
			inOrderHelper(right(p), traversal);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int numChildren(Position<E> p) {
		int count = 0;
		AbstractTreeNode<E> node = validate(p);
		if (left(node) != null) {
			count++;
		}
		if (right(node) != null) {
			count++;
		}
		return count;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Position<E> sibling(Position<E> p) {
		Position<E> parent = this.parent(p);
		if (parent == null) {
			return null;
		}
		if (p == left(parent)) {
			return right(parent);
		} else {
			return left(parent);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<Position<E>> children(Position<E> p) {
		AbstractTreeNode<E> node = validate(p);
		PositionCollection childrenCollection = new PositionCollection();
		if (left(node) != null) {
			childrenCollection.add(left(node));
		}
		if (right(node) != null) {
			childrenCollection.add(right(node));
		}
		return childrenCollection;
	}
}