package edu.ncsu.csc316.dsa.set;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * Test class for HashSet
 * Checks the expected outputs of the Set abstract data type behaviors when using
 * a hash table data structure 
 *
 * @author Dr. King
 * @author Maggie Lin
 *
 */
public class HashSetTest {

	/** Hash Set of Integers */
    private Set<Integer> set;
    
    /** Hash Set of Integers with predictable Hash */
    private Set<Integer> testSet;
    
    /**
     * Create new instances of a hash-based set before each test case executes
     */ 
    @Before
    public void setUp() {
        // Will use a "production" hash map with random alpha, beta values
        set = new HashSet<Integer>();
        
        // Will use our hash map for testing, which uses alpha=1, beta=1, prime=7
        testSet = new HashSet<Integer>(true);
    }

    /**
     * Test the output of the add behavior
     */      
    @Test
    public void testAdd() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        
        set.add(5);
        assertEquals(1, set.size());
        assertFalse(set.isEmpty());
        
        //Duplicate value
        set.add(5);
        assertEquals(1, set.size());
        
        //Test set
        assertTrue(testSet.isEmpty());
        assertEquals(0, testSet.size());
        
        testSet.add(5);
        assertEquals(1, testSet.size());
        assertFalse(testSet.isEmpty());
        
        //Duplicate value
        testSet.add(5);
        assertEquals(1, testSet.size());
    }

    /**
     * Test the output of the contains behavior
     */ 
    @Test
    public void testContains() {
    	assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());

        assertTrue(set.contains(5));
        assertTrue(set.contains(25));
        assertFalse(set.contains(11));
        
        //Test Set
        assertTrue(testSet.isEmpty());
        assertEquals(0, testSet.size());
        testSet.add(5);
        testSet.add(10);
        testSet.add(15);
        testSet.add(20);
        testSet.add(25);
        assertEquals(5, testSet.size());

        assertTrue(testSet.contains(5));
        assertTrue(testSet.contains(25));
        assertFalse(testSet.contains(11));
    }

    /**
     * Test the output of the remove behavior
     */ 
    @Test
    public void testRemove() {
    	assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        assertEquals(5, (int)set.remove(5));
        assertEquals(4, set.size());
        
        //Test Set
        assertTrue(testSet.isEmpty());
        assertEquals(0, testSet.size());
        testSet.add(5);
        testSet.add(10);
        testSet.add(15);
        testSet.add(20);
        testSet.add(25);
        assertEquals(5, testSet.size());

        assertTrue(testSet.contains(5));
        assertTrue(testSet.contains(25));
        assertFalse(testSet.contains(11));
    }

    /**
     * Test the output of the retainAll behavior
     */     
    @Test
    public void testRetainAll() {       
    	assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(10);
        other.add(20);
        other.add(30);
        
        set.retainAll(other);
        assertEquals(2, set.size());
        
        //Test Set
        assertTrue(testSet.isEmpty());
        assertEquals(0, testSet.size());
        testSet.add(5);
        testSet.add(10);
        testSet.add(15);
        testSet.add(20);
        testSet.add(25);
        assertEquals(5, testSet.size());
        
        Set<Integer> otherTest = new TreeSet<Integer>();
        otherTest.add(10);
        otherTest.add(20);
        otherTest.add(30);
        
        testSet.retainAll(otherTest);
        assertEquals(2, testSet.size());
    }


    /**
     * Test the output of the removeAll behavior
     */    
    @Test
    public void testRemoveAll() {
    	assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(10);
        other.add(20);
        other.add(30);
        
        set.removeAll(other);
        assertEquals(3, set.size());
        
        //Test Set
        assertTrue(testSet.isEmpty());
        assertEquals(0, testSet.size());
        testSet.add(5);
        testSet.add(10);
        testSet.add(15);
        testSet.add(20);
        testSet.add(25);
        assertEquals(5, testSet.size());
        
        Set<Integer> otherTest = new TreeSet<Integer>();
        otherTest.add(10);
        otherTest.add(20);
        otherTest.add(30);
        
        testSet.removeAll(other);
        assertEquals(3, testSet.size());
        
        
    }

    /**
     * Test the output of the addAll behavior
     */     
    @Test
    public void testAddAll() {
    	assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(30);
        other.add(40);
        other.add(50);
        
        set.addAll(other);
        assertEquals(8, set.size());
        
        //Test Set
        assertTrue(testSet.isEmpty());
        assertEquals(0, testSet.size());
        testSet.add(5);
        testSet.add(10);
        testSet.add(15);
        testSet.add(20);
        testSet.add(25);
        assertEquals(5, testSet.size());
        
        Set<Integer> otherTest = new TreeSet<Integer>();
        otherTest.add(30);
        otherTest.add(40);
        otherTest.add(50);
        
        testSet.addAll(otherTest);
        assertEquals(8, testSet.size());
    }

    /**
     * Test the output of the iterator behavior
     */ 
    @Test
    public void testIterator() {
        // Since our hash map uses random numbers, it can
        // be difficult to test that our hash set iterator returns
        // values in a certain order.
        // We could approach this problem with a few different options:
        // (1) instead of checking the specific order of entries
        //      visited by the iterator, we could save each
        //      iterator.next() into an array, then check that the 
        //      array *contains* the entry, regardless of its exact location
        //
        // (2) implement an isTesting flag for HashSet, similar to testSet in the setUp method. 
        //     This is the more appropriate option since we can control the
        //     entire execution and know exactly which values should appear
        //     in the set and in the correct expected order from using the iterator 
        //
        // Revisit your test cases for HashMap iterator -- those tests can be adapted
        //     to work here, too, since you have already worked out the details
        //     of where entries with certain keys will be stored and in what order
        //     they will be stored
        assertTrue(testSet.isEmpty());
        testSet.add(3);
        testSet.add(5);
        testSet.add(2);
        testSet.add(4);
        testSet.add(1);
        testSet.add(6);
        assertEquals(6, testSet.size());
        assertFalse(testSet.isEmpty());
        
        Iterator<Integer> it = testSet.iterator();
        assertTrue(it.hasNext());
        assertEquals(6, (int)it.next()); // should be index 0
        assertEquals(1, (int)it.next()); // should be index 2
        assertEquals(2, (int)it.next()); // should be index 3
        assertEquals(3, (int)it.next()); // should be index 4
        assertEquals(4, (int)it.next()); // should be index 5
        assertEquals(5, (int)it.next()); // should be index 6   
        assertFalse(it.hasNext());
        
        //Regular Set
        assertTrue(set.isEmpty());
        set.add(3);
        set.add(5);
        set.add(2);
        set.add(4);
        set.add(1);
        set.add(6);
        assertEquals(6, set.size());
        assertFalse(set.isEmpty());
        
        Iterator<Integer> itSet = set.iterator();
        ArrayBasedList<Integer> setValue = new ArrayBasedList<Integer>();
        boolean contains = false;
        assertTrue(itSet.hasNext());
        setValue.addLast(itSet.next());
        setValue.addLast(itSet.next());
        setValue.addLast(itSet.next());
        setValue.addLast(itSet.next());
        setValue.addLast(itSet.next());
        setValue.addLast(itSet.next());
        for(int i = 0; i < setValue.size(); i++) {
        	if (setValue.get(i) == 3) {
        		contains = true;
        	}
        }
        assertTrue(contains);
        assertFalse(it.hasNext());
    }
}