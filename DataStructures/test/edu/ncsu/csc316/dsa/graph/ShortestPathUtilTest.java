package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for ShortestPathUtil. Checks the expected outputs of the Dijkstra
 * and the ShortestPathTree method.
 *
 * @author Maggie Lin
 *
 */
public class ShortestPathUtilTest {

	/** Undirected Graph */
	private Graph<String, Highway> undirectedGraph;

	/**
	 * Creates a new instance of an AdjacencyMap graph before each test case
	 * executes
	 */
	@Before
	public void setUp() {
		undirectedGraph = new AdjacencyMapGraph<String, Highway>();
	}

	/**
	 * Test the output of the Dijkstra behavior and the ShortestPathTree behavior.
	 */
	@Test
	public void testDijkstraAndShortestPathTree() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");

		Edge<Highway> e1 = undirectedGraph.insertEdge(v1, v2, new Highway(5));
		Edge<Highway> e2 = undirectedGraph.insertEdge(v1, v3, new Highway(10));
		Edge<Highway> e3 = undirectedGraph.insertEdge(v1, v4, new Highway(15));
		Edge<Highway> e4 = undirectedGraph.insertEdge(v1, v5, new Highway(20));
		undirectedGraph.insertEdge(v2, v3, new Highway(25));
		undirectedGraph.insertEdge(v2, v4, new Highway(30));
		undirectedGraph.insertEdge(v2, v5, new Highway(35));
		undirectedGraph.insertEdge(v3, v4, new Highway(40));
		undirectedGraph.insertEdge(v3, v5, new Highway(45));
		undirectedGraph.insertEdge(v4, v5, new Highway(50));
		Map<Vertex<String>, Integer> dij = ShortestPathUtil.dijkstra(undirectedGraph, v1);
		Map<Vertex<String>, Edge<Highway>> result = ShortestPathUtil.shortestPathTree(undirectedGraph, v1, dij);
		assertNotNull(result);
		assertEquals(e1, result.get(v2));
		assertEquals(e2, result.get(v3));
		assertEquals(e3, result.get(v4));
		assertEquals(e4, result.get(v5));
	}

	/**
	 * Weighted class for testing. A Highway contains a field of length which acts
	 * as the weight of the object.
	 * 
	 * @author Maggie Lin
	 *
	 */
	public class Highway implements Weighted, Comparable<Highway> {

		/** Length of the highway */
		private int length;

		/**
		 * Set the length/weight of the Highway.
		 * 
		 * @param l length of the highway
		 */
		public Highway(int l) {
			this.length = l;
		}

		/**
		 * Get the length of the Highway
		 * 
		 * @return weight/length of the Highway object
		 */
		@Override
		public int getWeight() {
			return length;
		}

		/**
		 * Compare the weight of two highways
		 * 
		 * @param h Highway object to compare to the current Highway object
		 * @return an integer that represents whether the Highway's weight is more than,
		 *         less than, or equal to the current Highway weight
		 */
		@Override
		public int compareTo(Highway h) {
			return Integer.compare(length, h.getWeight());
		}
	}

}