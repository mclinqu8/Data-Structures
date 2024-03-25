package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * An AdjacencyMapGraph is an implementation of the (@link Graph) abstract data
 * type. AdjacencyMapGraph maintains a list of vertices in the graph and a list
 * of edges in the graph. In addition, AdjacencyMapGraph vertices each maintain
 * maps of incoming and outgoing edges to improve efficiency.
 * 
 * The AdjacencyMapGraph class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Maggie Lin
 *
 * @param <V> the type of data in the vertices in the graph
 * @param <E> the type of data in the edges in the graph
 */
public class AdjacencyMapGraph<V, E> extends AbstractGraph<V, E> {

	/** List of vertices */
    private PositionalList<Vertex<V>> vertexList;
    
    /** List of edges */
    private PositionalList<Edge<E>> edgeList;
    
    /**
     * Creates a new undirected adjacency map graph
     */
    public AdjacencyMapGraph() {
        this(false);
    }

    /**
     * Creates a new adjacency map graph
     * 
     * @param directed if true, the graph is directed; if false, the graph is
     *                 undirected
     */    
    public AdjacencyMapGraph(boolean directed) {
        super(directed);
        vertexList = new PositionalLinkedList<Vertex<V>>();
        edgeList = new PositionalLinkedList<Edge<E>>();
    }
    
    /**
	 * {@inheritDoc}
	 */
    @Override
    public int numVertices() {
        return vertexList.size();
    }

    /**
	 * {@inheritDoc}
	 */
    @Override
    public Iterable<Vertex<V>> vertices() {
        return vertexList;
    }

    /**
	 * {@inheritDoc}
	 */
    @Override
    public int numEdges() {
        return edgeList.size();
    }

    /**
	 * {@inheritDoc}
	 */
    @Override
    public Iterable<Edge<E>> edges() {
        return edgeList;
    }

    /**
	 * {@inheritDoc}
	 */
    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        return validate(vertex1).getOutgoing().get(vertex2);
    }

    /**
	 * {@inheritDoc}
	 */
    @Override
    public int outDegree(Vertex<V> vertex) {
        return validate(vertex).getOutgoing().size();
    }

    /**
	 * {@inheritDoc}
	 */
    @Override
    public int inDegree(Vertex<V> vertex) {
        return validate(vertex).getIncoming().size();
    }

    /**
	 * {@inheritDoc}
	 */
    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return validate(vertex).getOutgoing().values();
    }

    /**
	 * {@inheritDoc}
	 */
    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return validate(vertex).getIncoming().values();
    }

    /**
	 * {@inheritDoc}
	 */
    @Override
    public Vertex<V> insertVertex(V vertexData) {
        AMVertex vertex = new AMVertex(vertexData, isDirected());
        Position<Vertex<V>> pos = vertexList.addLast(vertex);
        vertex.setPosition(pos);
        return vertex;
    }

    /**
	 * {@inheritDoc}
	 */
    @Override
    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData) {
    	if (getEdge(v1, v2) == null) {
			AMVertex origin = validate(v1);
			AMVertex destination = validate(v2);
			GraphEdge edge = new GraphEdge(edgeData, origin, destination);
			Position<Edge<E>> pos = edgeList.addLast(edge);
			edge.setPosition(pos);
			origin.getOutgoing().put(v2, edge);
			destination.getIncoming().put(v1, edge);
			return edge;
    	} else {
    		throw new IllegalArgumentException("Edge exists.");
    	}
    }

    /**
	 * {@inheritDoc}
	 */
    @Override
    public void removeVertex(Vertex<V> vertex) {
        AMVertex v = validate(vertex);
        for (Edge<E> e : v.getOutgoing().values()) {
            removeEdge(e);
        }
        for (Edge<E> e : v.getIncoming().values()) {
            removeEdge(e);
        }
        vertexList.remove(v.getPosition());
    }

    /**
	 * {@inheritDoc}
	 */
    @Override
    public void removeEdge(Edge<E> edge) {
        GraphEdge e = validate(edge);
        Vertex<V>[] ends = e.getEndpoints();
        AMVertex origin = validate(ends[0]);
        AMVertex dest = validate(ends[1]);
        origin.getOutgoing().remove(dest);
        dest.getIncoming().remove(origin);
        edgeList.remove(e.getPosition());
    }
    
    /**
     * Represents a vertex in an AdjacencyMapGraph
     * 
     * @author Dr. King
     *
     */
    private class AMVertex extends GraphVertex {

        /**
         * A map of outgoing edges -- opposite vertex, edge to reach opposite vertex
         */
        private Map<Vertex<V>, Edge<E>> outgoing;

        /**
         * A map of incoming edges -- opposite vertex, edge to reach opposite vertex
         */
        private Map<Vertex<V>, Edge<E>> incoming;

        /**
         * Creates a new adjacency map vertex.
         * 
         * @param data       the data to store in the vertex
         * @param isDirected if true, the vertex belongs to a directed graph; if false,
         *                   the vertex belongs to an undirected graph
         */
        public AMVertex(V data, boolean isDirected) {
            super(data);
            outgoing = new LinearProbingHashMap<Vertex<V>, Edge<E>>();
            if (isDirected) {
                incoming = new LinearProbingHashMap<>();
            } else {
                incoming = outgoing;
            }
        }

        /**
         * Returns a map of outgoingEdges from the vertex. For an undirected graph,
         * returns the same as getIncoming()
         * 
         * @return a map of outgoing edges from the vertex
         */
        public Map<Vertex<V>, Edge<E>> getOutgoing() {
            return outgoing;
        }

        /**
         * Returns a map of incomingEdges to the vertex. For an undirected graph,
         * returns the same as getOutgoing()
         * 
         * @return a map of incoming edges to the vertex
         */
        public Map<Vertex<V>, Edge<E>> getIncoming() {
            return incoming;
        }
    }

    /**
     * Safely casts a Vertex to an adjacency map vertex
     * 
     * @param v vertex to safely cast to an adjacency map vertex
     * @return an adjacency map vertex representation of the given Vertex
     * @throws IllegalArgumentException if the vertex is not a valid adjacency map
     *                                  vertex
     */
    private AMVertex validate(Vertex<V> v) {
        if (!(v instanceof AdjacencyMapGraph.AMVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency map vertex.");
        }
        return (AMVertex) v;
    }
}