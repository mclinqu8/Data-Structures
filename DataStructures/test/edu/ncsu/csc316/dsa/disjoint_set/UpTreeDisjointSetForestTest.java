package edu.ncsu.csc316.dsa.disjoint_set;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for UpTreeDisjointSetForest
 * Checks the expected outputs of the Disjoint Set abstract data type 
 * behaviors when using an up-tree data structure
 *
 * @author Dr. King
 * @author Maggie Lin
 */
public class UpTreeDisjointSetForestTest {

	/** Disjoint Set Forest of Strings */
    private DisjointSetForest<String> set;

    /**
     * Create a new instance of a up-tree forest before each test case executes
     */     
    @Before
    public void setUp() {
        set = new UpTreeDisjointSetForest<>();
    }
    
    /**
     * Test the output of the makeSet behavior
     */ 
    @Test
    public void testMakeSet() {
        Position<String> one = set.makeSet("one");
        assertEquals("one", one.getElement());
        Position<String> two = set.makeSet("two");
        assertEquals("two", two.getElement());
        
    }

    /**
     * Test the output of the union-find behaviors
     */     
    @Test
    public void testUnionFind() {
        Position<String> one = set.makeSet("one");
        Position<String> two = set.makeSet("two");
        Position<String> three = set.makeSet("three");
        Position<String> four = set.makeSet("four");
        Position<String> five = set.makeSet("five");
        Position<String> six = set.makeSet("six");
        Position<String> seven = set.makeSet("seven");
        Position<String> eight = set.makeSet("eight");
        Position<String> nine = set.makeSet("nine");
        Position<String> ten = set.makeSet("ten");
        
        assertEquals(one, set.find("one"));
        // One <- Two
        set.union(two, one);
        // One <- Two, Three
        set.union(one, three);
        assertEquals(one, set.find("one"));
        assertEquals(one, set.find("two"));
        assertEquals(one, set.find("three"));
        // Four <- Five
        set.union(five, four);
        assertEquals(four, set.find("four"));
        assertEquals(four, set.find("five"));
        // Six <- Seven
        set.union(seven, six);
        assertEquals(six, set.find("six"));
        assertEquals(six, set.find("seven"));
        // Eight <- Nine
        set.union(nine, eight);
        assertEquals(eight, set.find("eight"));
        assertEquals(eight, set.find("nine"));
        // Four <- Five, Six, Seven
        set.union(six, four);
        assertEquals(four, set.find("four"));
        assertEquals(four, set.find("five"));
        assertEquals(four, set.find("six"));
        assertEquals(four, set.find("seven"));
        // One <- Two, Three, Eight, Nine
        set.union(nine, one);
        assertEquals(one, set.find("one"));
        assertEquals(one, set.find("two"));
        assertEquals(one, set.find("three"));
        assertEquals(eight, set.find("eight"));
        assertEquals(one, set.find("nine"));
        set.union(eight, one);
        assertEquals(one, set.find("eight"));
        // One <- Two, Three, Eight, Nine, Ten
        set.union(ten, one);
        assertEquals(one, set.find("ten"));
        // One <- Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten
        set.union(four, one);
        assertEquals(one, set.find("one"));
        assertEquals(one, set.find("two"));
        assertEquals(one, set.find("three"));
        assertEquals(one, set.find("four"));
        assertEquals(one, set.find("five"));
        assertEquals(one, set.find("six"));
        assertEquals(one, set.find("seven"));
        assertEquals(one, set.find("eight"));
        assertEquals(one, set.find("nine"));
        assertEquals(one, set.find("ten"));        
    }
}