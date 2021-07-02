import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class WeightedGraph {

	/*
	 * The adjacencyMatrix HAS to use doubles, as we need to
	 * set weights to infinite if they are not present. 0 only
	 * tells us, that this is the same vertex (which cannot be visited anyways)
	 * https://www.tutorialspoint.com/weighted-graph-representation-in-data-structure
	 */
	
	//int[][] adjacencyMatrix;
	double[][] adjacencyMatrix;
	
	//value of infinity
	double inf = Double.POSITIVE_INFINITY;
	
	String[] names = {"Berlin", "Rome", "Munich", "Amsterdam", "Warsaw"};
	
	//List of all the Vertexes
	ArrayList<Vertex> vertexes = new ArrayList<>();
	
	public static void main(String[] args) {
		
		WeightedGraph wG = new WeightedGraph(5, 12, false);

		//Prints Matrix to the console
		
		System.out.println(" B " + " " + " R " + " " + " M " + " " + " A " + " " + " W ");
		System.out.println(wG.printEdge(0,0) + wG.printEdge(1,0) + wG.printEdge(2,0) + wG.printEdge(3,0) + wG.printEdge(4,0) + wG.getVertex(0).getName());
		System.out.println(wG.printEdge(0,1) + wG.printEdge(1,1) + wG.printEdge(2,1) + wG.printEdge(3,1) + wG.printEdge(4,1) + wG.getVertex(1).getName());
		System.out.println(wG.printEdge(0,2) + wG.printEdge(1,2) + wG.printEdge(2,2) + wG.printEdge(3,2) + wG.printEdge(4,2) + wG.getVertex(2).getName());
		System.out.println(wG.printEdge(0,3) + wG.printEdge(1,3) + wG.printEdge(2,3) + wG.printEdge(3,3) + wG.printEdge(4,3) + wG.getVertex(3).getName());
		System.out.println(wG.printEdge(0,4) + wG.printEdge(1,4) + wG.printEdge(2,4) + wG.printEdge(3,4) + wG.printEdge(4,4) + wG.getVertex(4).getName());
	}
	
	
	
	/*
	 * Methods to build the Matrix
	 */
	
	/*
	 * Constructor initiates a Matrix, Vertecies and Edges
	 */
	public WeightedGraph(int verts, int edges, boolean random) {
		adjacencyMatrix = new double[verts][verts];
		createVertices(verts);
		if(random == true) {
			createRandomEdges(edges);
		} else {
			createEdges();
		}
	}
	
	/*
	 * This method creates all Vertices and adds them to a ArrayList. 
	 * The Index in the ArrayList is relative to its location in the Matrix
	 */
	public void createVertices(int amount) {
		for(int i = 0; i < amount; i++) {
			Vertex v = new Vertex(names[i], i);	
			vertexes.add(v);
		}
	}
	
	/*
	 * This method randomly creates up to 'amount' edges for randomized vertex connections
	 * if boolean random is set to true in the constructor
	 */
	public void createRandomEdges(int amount) {
		Random r = new Random();

		for(int i = 0; i <= amount; i++) {
			int rdm = r.nextInt(4);
			int rdm2 = r.nextInt(4);
			int weight = r.nextInt(10);
			
			//In order to avoid weights between one and the same vertex
			
			while(rdm == rdm2) {
				rdm2 = r.nextInt(4);
			}
			if(getEdge(rdm, rdm2) == 0.0) {
				makeEdge(rdm, rdm2, weight);
			}
		}
		setToInfinity();
	}
	
	/*
	 * Creates given Edges for a set Matrix if boolean random is set to false in the constructor
	 */
	public void createEdges() {
		makeEdge(0,1,3);	//     0 1 2 3 4
		makeEdge(0,2,1);	//   0 0 I I I I
		makeEdge(0,3,4);	//   1 3 0 I I I
		makeEdge(1,3,2);	//   2 1 I 0 6 I
		makeEdge(3,2,6);	//   3 4 2 I 0 I
		makeEdge(3,4,1);	//   4 I I I 1 0
		setToInfinity();	// 	The built Matrix
	}
	
	/*
	 * This method sets all weights that are not present to Infinity
	 */
	public void setToInfinity() {
		for(int i = 0; i < vertexes.size(); i++) {
			for(int j = 0; j < vertexes.size(); j++) {
				if(getEdge(i,j) == 0.0 && i != j) {
					adjacencyMatrix[i][j] = inf;
				}
			}
		}
	}
	/*
	 * Getters and Setters
	 */
	public Vertex getVertex(int i) {
		return vertexes.get(i);
	}
	
	public void makeEdge(int to, int from, double weight) {
		adjacencyMatrix[to][from] = weight;
	}
	
	public double getEdge(int to, int from) {
		return adjacencyMatrix[to][from];
	}
	
	private String printEdge(int to, int from) {
		String p = adjacencyMatrix[to][from] + "";
		String line = "";
		if(p.equals("Infinity")) {
			line = "Inf ";
		}else {
			line = adjacencyMatrix[to][from] + " ";
		}
		return line;
	}
	
	/*
	 * Started to build the Algorithm. Obviously doesn't work yet
	 */
	
	
	/*
	 * Returns the Vertex with the lowest Distance 
	 * 
	 * (Not sure here, how does this method know 
	 * whether or not there actually is a connection
	 * from a specific Vertex to this exact one? Doesn't
	 * it just return any Vertex with the lowest Distance
	 * out of all of them? What if there is no edge between them?)
	 */
	public Vertex getLowestDistanceVertex(HashSet<Vertex> v) {
		int max = Integer.MAX_VALUE;
		Vertex small = new Vertex("small", 0);
		for(Vertex vertex: v) {
			if(vertex.getDistance() < max) {
				max = vertex.getDistance();
				small = vertex;
			}
		}
		return small;
	}
	/*
	 * Calculates the shortest Path to EACH Vertex from the source
	 * 
	 * (Still unfinished)
	 */
	public void calculateShortestPathFromSource(Vertex source) {
		source.setDistance(0);
		HashSet<Vertex> settledVertexes = new HashSet<>();
		HashSet<Vertex> unsettledVertexes = new HashSet<>();
		unsettledVertexes.add(source);
		while(unsettledVertexes.size() != 0) {
			Vertex currentVertex = getLowestDistanceVertex(unsettledVertexes);
			unsettledVertexes.remove(currentVertex);
			//for()
		}
	}
}  
