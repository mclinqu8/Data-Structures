package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for GraphTraversalUtil. Checks the expected outputs of the
 * BreadthFirstSearch method and the DepthFirstSearch method.
 *
 * @author Maggie Lin
 *
 */
public class GraphTraversalUtilTest {

	/** Undirected Graph */
	private Graph<String, Integer> undirectedGraph;

	/**
	 * Create a new instance of an AdjacencyMap graph before each test case executes
	 */
	@Before
	public void setUp() {
		undirectedGraph = new AdjacencyListGraph<String, Integer>();
	}

	/**
	 * Test the output of the BreadthFirstSearch() behavior
	 */
	@Test
	public void testBreadthFirstSearch() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");

		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
		Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
		Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
		undirectedGraph.insertEdge(v2, v3, 25);
		undirectedGraph.insertEdge(v2, v4, 30);
		undirectedGraph.insertEdge(v2, v5, 35);
		undirectedGraph.insertEdge(v3, v4, 40);
		undirectedGraph.insertEdge(v3, v5, 45);
		undirectedGraph.insertEdge(v4, v5, 50);
		Map<Vertex<String>, Edge<Integer>> result = GraphTraversalUtil.breadthFirstSearch(undirectedGraph, v1);
		assertNotNull(result);
		assertEquals(e1, result.get(v2));
		assertEquals(e2, result.get(v3));
		assertEquals(e3, result.get(v4));
		assertEquals(e4, result.get(v5));
	}

	/**
	 * Test the output of the DepthFirstSearch() behavior
	 */
	@Test
	public void testDepthFirstSearch() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");

		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		undirectedGraph.insertEdge(v1, v3, 10);
		undirectedGraph.insertEdge(v1, v4, 15);
		undirectedGraph.insertEdge(v1, v5, 20);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v2, v3, 25);
		undirectedGraph.insertEdge(v2, v4, 30);
		undirectedGraph.insertEdge(v2, v5, 35);
		Edge<Integer> e3 = undirectedGraph.insertEdge(v3, v4, 40);
		undirectedGraph.insertEdge(v3, v5, 45);
		Edge<Integer> e4 = undirectedGraph.insertEdge(v4, v5, 50);
		Map<Vertex<String>, Edge<Integer>> result = GraphTraversalUtil.depthFirstSearch(undirectedGraph, v1);
		assertNotNull(result);
		assertEquals(e1, result.get(v2));
		assertEquals(e2, result.get(v3));
		assertEquals(e3, result.get(v4));
		assertEquals(e4, result.get(v5));
	}

}