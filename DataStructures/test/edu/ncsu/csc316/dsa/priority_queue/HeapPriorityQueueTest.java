package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Test class for HeapPriorityQueue
 * Checks the expected outputs of the Priority Queue abstract data type behaviors when using
 * a min-heap data structure 
 *
 * @author Dr. King
 * @author Maggie Lin
 *
 */
public class HeapPriorityQueueTest {

	/** Priority Heap Queue */
    private PriorityQueue<Integer, String> heap;
    
    /**
     * Create a new instance of a heap before each test case executes
     */     
    @Before
    public void setUp() {
        heap = new HeapPriorityQueue<Integer, String>();
    }
    
    /**
     * Test the output of the insert(k,v) behavior
     */     
    @Test
    public void testInsert() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        heap.insert(8, "eight");
        assertEquals(1, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(8, (int)heap.min().getKey());
        
        //New minimum
        heap.insert(4, "four");
        assertEquals(2, heap.size());
    }
    
    /**
     * Test the output of the min behavior
     */ 
    @Test
    public void testMin() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        assertNull(heap.min());
        
        heap.insert(8, "eight");
        assertEquals(1, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(8, (int)heap.min().getKey());
        
        //New minimum
        heap.insert(4, "four");
        assertEquals(2, heap.size());
        assertEquals(4, (int)heap.min().getKey());
        
        heap.insert(1, "one");
        assertEquals(3, heap.size());
        assertEquals(1, (int)heap.min().getKey());
        
    }
    
    /**
     * Test the output of the deleteMin behavior
     */     
    @Test 
    public void deleteMin() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        assertNull(heap.deleteMin());
        
        heap.insert(8, "eight");
        heap.insert(4, "four");        
        heap.insert(1, "one");
        assertEquals(3, heap.size());
        heap.deleteMin();
        assertEquals(4, (int)heap.min().getKey());
        heap.deleteMin();
        assertEquals(8, (int)heap.min().getKey());
        heap.deleteMin();
        assertNull(heap.min());
        assertNull(heap.deleteMin());
        
    }
    
    /**
     * Test the output of the heap behavior when using arbitrary key objects to
     * represent priorities
     */ 
    @Test
    public void testStudentHeap() {
        PriorityQueue<Student, String> sHeap = new HeapPriorityQueue<Student, String>(new StudentIDComparator());
        Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
        Student s2 = new Student("J", "S", 2, 1, 2, "js2");
        Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
        Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
        Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
        
        assertTrue(sHeap.isEmpty());
        assertEquals(0, sHeap.size());
        
        sHeap.insert(s5, "five");
        sHeap.insert(s4, "four");
        sHeap.insert(s3, "three");
        sHeap.insert(s2, "two");
        assertEquals("two", sHeap.min().getValue());
        sHeap.insert(s1, "one");
        assertEquals("one", sHeap.min().getValue());
        
        sHeap.deleteMin();
        assertEquals("two", sHeap.min().getValue());
        sHeap.deleteMin();
        assertEquals("three", sHeap.min().getValue());
        sHeap.deleteMin();
        assertEquals("four", sHeap.min().getValue());
        sHeap.deleteMin();
        assertEquals("five", sHeap.min().getValue());
        sHeap.deleteMin();
        assertNull(sHeap.min());
        assertNull(sHeap.deleteMin());
    }
}