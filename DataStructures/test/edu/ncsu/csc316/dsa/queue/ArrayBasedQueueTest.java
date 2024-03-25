package edu.ncsu.csc316.dsa.queue;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedQueue. Checks the expected outputs of the Queue
 * abstract data type behaviors when using a circular array-based data structure
 *
 * @author Dr. King
 *
 */
public class ArrayBasedQueueTest {

	/** ArrayBasedQueue */
	private Queue<String> queue;

	/**
	 * Create a new instance of a circular array-based queue before each test case
	 * executes
	 */
	@Before
	public void setUp() {
		queue = new ArrayBasedQueue<String>();
	}

	/**
	 * Test the output of the enqueue(e) behavior
	 */
	@Test
	public void testEnqueue() {
		assertEquals(0, queue.size());
		assertTrue(queue.isEmpty());

		queue.enqueue("one");
		assertEquals(1, queue.size());
		assertFalse(queue.isEmpty());

		// Valid enqueue
		// Double capacity
		queue.enqueue("two");
		assertEquals(2, queue.size());
		assertFalse(queue.isEmpty());

		queue.enqueue("three");
		assertEquals(3, queue.size());
		assertFalse(queue.isEmpty());
	}

	/**
	 * Test the output of the dequeue() behavior, including expected exceptions
	 */
	@Test
	public void testDequeue() {
		assertEquals(0, queue.size());
		try {
			queue.dequeue();
			fail("NoSuchElementException should have been thrown.");
		} catch (Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}

		// Valid enqueue
		queue.enqueue("one");
		queue.enqueue("two");
		queue.enqueue("three");
		
		//Valid dequeue
		queue.dequeue();
		assertEquals(2, queue.size());
		assertFalse(queue.isEmpty());
	}

	/**
	 * Test the output of the front() behavior, including expected exceptions
	 */
	@Test
	public void testFront() {
		assertEquals(0, queue.size());
		assertTrue(queue.isEmpty());
		try {
			queue.front();
			fail("NoSuchElementException should have been thrown.");
		} catch (Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}
		
		queue.enqueue("one");
		assertEquals("one", queue.front());
		assertFalse(queue.isEmpty());
		
		//Testing series of enqueue and dequeue
		queue.enqueue("two");
		queue.enqueue("three");
		queue.enqueue("four");
		queue.dequeue();
		queue.dequeue();
		assertEquals("three", queue.front());
		assertFalse(queue.isEmpty());
	}
	
	/**
	 * Test the output of enqueue(e) and dequeue() behavior when rear is before front
	 */
	@Test
	public void testRearBeforeFront() {
		assertEquals(0, queue.size());
		try {
			queue.dequeue();
			fail("NoSuchElementException should have been thrown.");
		} catch (Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}

		// Valid enqueue
		queue.enqueue("one");
		queue.enqueue("two");
		queue.enqueue("three");
		
		//Valid dequeue
		queue.dequeue();
		assertEquals("two", queue.front());
		assertEquals(2, queue.size());
		assertFalse(queue.isEmpty());
		
		//Enqueue without doubling array capacity -- rear is moved before front
		queue.enqueue("formerOne");
		assertEquals("two", queue.front());
		assertEquals(3, queue.size());
		assertFalse(queue.isEmpty());
		
		//Enqueue while doubling array capacity -- rear is before front
		queue.enqueue("four");
		assertEquals("two", queue.front());
		assertEquals(4, queue.size());
		assertFalse(queue.isEmpty());
	}

}