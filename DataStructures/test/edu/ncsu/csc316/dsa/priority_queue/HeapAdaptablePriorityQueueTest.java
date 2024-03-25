package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * Test class for HeapAdaptablePriorityQueue
 * Checks the expected outputs of the Adaptable Priorty Queue abstract
 * data type behaviors when using a min-heap data structure 
 *
 * @author Dr. King
 *
 */
public class HeapAdaptablePriorityQueueTest {

	/** Adaptable Heap PriorityQueue */
    private HeapAdaptablePriorityQueue<Integer, String> heap;
    
    /**
     * Create a new instance of a heap before each test case executes
     */   
    @Before
    public void setUp() {
        heap = new HeapAdaptablePriorityQueue<Integer, String>();
    }
    
    /**
     * Test the output of the replaceKey behavior
     */     
    @Test
    public void testReplaceKey() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(3, heap.size());
        
        heap.replaceKey(e2,  -5);
        assertEquals(-5, (int)heap.min().getKey());
        assertEquals("two", heap.min().getValue());
        assertEquals(3, heap.size());
        
        heap.replaceKey(e0,  -10);
        assertEquals(-10, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(3, heap.size());
        
        heap.replaceKey(e1,  0);
        heap.replaceKey(e2,  2);
        heap.replaceKey(e0,  1);
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(3, heap.size());
        
    }
    
    /**
     * Test the output of the replaceValue behavior
     */ 
    @Test
    public void testReplaceValue() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
       
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(2, heap.size());
        
        heap.replaceValue(e0,  "ZERO");
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("ZERO", heap.min().getValue());
        assertEquals(2, heap.size());
        
        assertEquals("one",  e1.getValue());
        heap.replaceValue(e1,  "ONE");
        assertEquals(0, (int)heap.min().getKey());
        heap.replaceKey(e0,  3);
        assertEquals("ONE", heap.min().getValue());
        

    }

    /**
     * Test the output of the remove behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());

        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(3, heap.size());
        
        heap.remove(e0);
        assertEquals(1, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(2, heap.size());
        
        heap.remove(e2);
        assertEquals(1, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(1, heap.size());
        
        heap.remove(e1);
        assertNull(heap.min());
        assertEquals(0, heap.size());
        assertTrue(heap.isEmpty());
    }
    
    /**
     * Test the output of the heap behavior when using arbitrary key objects to
     * represent priorities
     */     
    @Test
    public void testStudentHeap() {
        AdaptablePriorityQueue<Student, String> sHeap = new HeapAdaptablePriorityQueue<Student, String>(new StudentIDComparator());
        Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
        Student s2 = new Student("J", "S", 2, 1, 2, "js2");
        Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
        Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
        
        assertTrue(sHeap.isEmpty());
        assertEquals(0, sHeap.size());
        
        Entry<Student, String> e3 = sHeap.insert(s3, "three");
        Entry<Student, String> e2 = sHeap.insert(s2, "two");
        Entry<Student, String> e1 = sHeap.insert(s1, "one");
        assertEquals("one", sHeap.min().getValue());
        
        sHeap.replaceKey(e1, s4);
        assertEquals("two", sHeap.min().getValue());
        
        sHeap.replaceValue(e2, "TWO");
        assertEquals("TWO", sHeap.min().getValue());
        
        sHeap.remove(e3);
        sHeap.remove(e2);
        assertEquals(1, sHeap.size());
        assertEquals("one", sHeap.min().getValue());

    }
}