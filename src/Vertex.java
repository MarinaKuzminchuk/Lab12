import java.util.HashMap;
import java.util.LinkedList;

public class Vertex {

	private String name;
	private int index;
	private int distance = Integer.MAX_VALUE;
	
	HashMap<Vertex, Integer> neighbors = new HashMap<>();
	LinkedList<Vertex> shortestPath = new LinkedList<>();
	
	/*
	 * Vertex constructor, sets its Name and Index
	 * of its Position in 'vertexes' of WeightedGraph
	 */
	public Vertex(String name, int index){

		this.name = name;
		this.index = index;
	}
	
	/*
	 * Method to add a Vertex as a neighbor of this Vertex with
	 * the value of the edge as distance in the HashMap neighbors
	 */
	public void addDestination(Vertex destination, int distance) {
		neighbors.put(destination, distance);
	}
	
	/*
	 * Method to set the Distance manually (?)
	 * from: https://www.baeldung.com/java-dijkstra
	 * I don't know what for yet, was copy-pasted
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/*
	 * Getters
	 */
	
	public int getDistance() {
		return distance;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public String getName() {
		return this.name;
	}
}
