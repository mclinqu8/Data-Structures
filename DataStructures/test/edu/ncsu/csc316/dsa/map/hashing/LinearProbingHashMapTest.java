package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for LinearProbingHashMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a linear probing hash map data structure 
 *
 * @author Dr. King
 * @author Maggie Lin
 *
 */
public class LinearProbingHashMapTest {

	/** Testing Hash Map with given capacity */
    private Map<Integer, String> map;
    
    /** Testing Hash Map with default capacity */
    private Map<Integer, String> map2;
    
    /** Normal Hash Map with given capacity */
    private Map<Integer, String> map3;
    
    /** Normal Hash Map */
    private Map<Integer, String> map4;

    /**
     * Create a new instance of a linear probing hash map before each test case executes
     */     
    @Before
    public void setUp() {
        // Use the "true" flag to indicate we are testing.
        // Remember that (when testing) alpha = 1, beta = 1, and prime = 7
        // based on our AbstractHashMap constructor.
        // That means you can draw the hash table by hand
        // if you use integer keys, since Integer.hashCode() = the integer value, itself
        // Finally, apply compression. For example:
        // for key = 1: h(1) = ( (1 * 1 + 1) % 7) % 7 = 2
        // for key = 2: h(2) = ( (1 * 2 + 1) % 7) % 7 = 3
        // for key = 3: h(3) = ( (1 * 3 + 1) % 7) % 7 = 4
        // for key = 4: h(4) = ( (1 * 4 + 1) % 7) % 7 = 5
        // for key = 5: h(5) = ( (1 * 5 + 1) % 7) % 7 = 6
        // for key = 6: h(6) = ( (1 * 6 + 1) % 7) % 7 = 0
        // etc.
        map = new LinearProbingHashMap<Integer, String>(7, true);
        map2 = new LinearProbingHashMap<Integer, String>(true);
        map3 = new LinearProbingHashMap<Integer, String>(5);
        map4 = new LinearProbingHashMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));

        // Since our entrySet method returns the entries in the table
        // from left to right, we can use the entrySet to check
        // that our values are in the correct order in the hash table.
        // Alternatively, you could implement a toString() method if you
        // want to check that the exact index/map of each bucket is correct
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        
        
        assertNull(map.put(4, "string4"));
        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        assertEquals(4, (int)it.next().getKey()); // should be in a map in index 5
        
        assertNull(map.put(1, "string1"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(6, "string6"));
        assertNull(map.put(7, "string7")); // Collision with 1
        assertNull(map.put(8, "string8")); // Should have trigger a resize & Collision with 2
        assertEquals(8, map.size());
        
        it = map.entrySet().iterator();
        assertEquals(6, (int)it.next().getKey()); 
        assertEquals(7, (int)it.next().getKey());
        assertEquals(1, (int)it.next().getKey()); 
        assertEquals(2, (int)it.next().getKey());
        assertEquals(3, (int)it.next().getKey()); 
        assertEquals(4, (int)it.next().getKey());
        assertEquals(5, (int)it.next().getKey()); 
        assertEquals(8, (int)it.next().getKey());
        
        //other maps
        assertEquals(0, map2.size());
        assertTrue(map2.isEmpty());
        assertNull(map2.put(1, "string1"));
        assertNull(map2.put(2, "string2"));
        assertNull(map2.put(3, "string1"));
        assertNull(map2.put(4, "string2"));
        assertNull(map2.put(5, "string5"));
        assertNull(map2.put(6, "string6"));
        assertNull(map2.put(7, "string7")); // Collision with 1
        assertNull(map2.put(8, "string8")); // Collision with 2 and resize
        assertEquals(8, map2.size());
        assertFalse(map2.isEmpty());
        
        Iterator<Map.Entry<Integer, String>> it2 = map2.entrySet().iterator();
        assertEquals(6, (int)it2.next().getKey()); 
        assertEquals(7, (int)it2.next().getKey());
        assertEquals(1, (int)it2.next().getKey()); 
        assertEquals(2, (int)it2.next().getKey());
        assertEquals(3, (int)it2.next().getKey()); 
        assertEquals(4, (int)it2.next().getKey());
        assertEquals(5, (int)it2.next().getKey()); 
        assertEquals(8, (int)it2.next().getKey());
        
        assertEquals(0, map3.size());
        assertTrue(map3.isEmpty());
        assertNull(map3.put(1, "string1"));
        assertEquals(1, map3.size());
        assertFalse(map3.isEmpty());
        assertEquals("string1", map3.put(1, "stringone"));
        assertEquals(1, map3.size());
        assertFalse(map3.isEmpty());

        assertEquals(0, map4.size());
        assertTrue(map4.isEmpty());
        assertNull(map4.put(1, "string1"));
        assertEquals(1, map4.size());
        assertFalse(map4.isEmpty());
        assertEquals("string1", map4.put(1, "stringone"));
        assertEquals(1, map4.size());
        assertFalse(map4.isEmpty());
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals(1, map.size());
        assertEquals("string3", map.get(3)); // should be in a map in index 4
        assertNull(map.get(1)); 
        
        assertTrue(map2.isEmpty());
        assertNull(map2.put(3, "string3"));
        assertEquals(1, map2.size());
        assertEquals("string3", map2.get(3)); // should be in a map in index 4
        assertNull(map2.get(1)); 
        
        assertTrue(map3.isEmpty());
        assertNull(map3.put(3, "string3"));
        assertEquals(1, map3.size());
        assertEquals("string3", map3.get(3)); // should be in a map in index 4
        assertNull(map3.get(1)); 
        
        assertTrue(map4.isEmpty());
        assertNull(map4.put(3, "string3"));
        assertEquals(1, map4.size());
        assertEquals("string3", map4.get(3)); // should be in a map in index 4
        assertNull(map4.get(1));  
    }
    
    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals(1, map.size());
        assertEquals("string3", map.remove(3)); // should be in a map in index 4
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.remove(1)); 
        
        assertTrue(map2.isEmpty());
        assertNull(map2.put(3, "string3"));
        assertEquals(1, map2.size());
        assertEquals("string3", map2.remove(3)); // should be in a map in index 4
        assertEquals(0, map2.size());
        assertTrue(map2.isEmpty());
        assertNull(map2.remove(1)); 
        
        assertTrue(map3.isEmpty());
        assertNull(map3.put(3, "string3"));
        assertEquals(1, map3.size());
        assertEquals("string3", map3.remove(3)); // should be in a map in index 4
        assertEquals(0, map3.size());
        assertTrue(map3.isEmpty());
        assertNull(map3.remove(1)); 
        
        assertTrue(map4.isEmpty());
        assertNull(map4.put(3, "string3"));
        assertEquals(1, map4.size());
        assertEquals("string3", map4.remove(3)); // should be in a map in index 4
        assertEquals(0, map4.size());
        assertTrue(map4.isEmpty());
        assertNull(map4.remove(1)); 
    }
    
    /**
     * Test the output of the iterator() behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
    	assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        
        Iterator<Integer> it = map.iterator();
        it = map.iterator();
        assertEquals(3, (int)it.next());
        assertEquals(4, (int)it.next());
    }
    
    /**
     * Test the output of the entrySet() behavior
     */     
    @Test
    public void testEntrySet() {
    	assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();     
        it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey());
        assertEquals(4, (int)it.next().getKey());
    }
    
    /**
     * Test the output of the values() behavior
     */  
    @Test
    public void testValues() {
    	assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        
        Iterator<String> it = map.values().iterator();
        it = map.values().iterator();
        assertEquals("string3", it.next());
        assertEquals("string4", it.next());
    }
}