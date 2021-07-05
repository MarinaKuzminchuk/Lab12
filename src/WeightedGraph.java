import java.util.*;

public class WeightedGraph {

	// all possible names of the vertices in the graph
	private static String[] NAMES = {"Berlin", "Rome", "Munich", "Amsterdam", "Warsaw", "Tokyo", "Paris", "London", "Seoul", "Delhi"};
	// for Integer[][] matrix Java will automatically fill elements with "null" value if they don't exist
	// to remove an edge set the value of the element to "null"
	private Integer[][] adjacencyMatrix;
	private Integer vertCount;
	private Integer edgeCount;
	// list of all the vertices in a graph
	private List<Vertex> vertices = new ArrayList<>();
	
	public static void main(String[] args) {
		
		WeightedGraph wG = new WeightedGraph(6, 10, true); 

		
		//Prints Matrix to the console
		String r = "";    //String to display initial letters of vertexes in initial row
		String t = "";    //String used to print out individual rows of matrix
		
		//Printing first row
		for(int i = 0; i < wG.vertCount; i++) {
			 r = r + wG.getVertex(i).getName().charAt(0) + " ";
		}
		System.out.println(r);
		//Printing matrix
		for(int h = 0; h < wG.vertCount; h++) {
			for(int v = 0; v < wG.vertCount; v++) {
			t = t + wG.printEdge(h, v);
			if(v == wG.vertCount - 1) {
				System.out.println(t + wG.getVertex(h).getName());   //Print out name of Vertex at the end
				t = "";
			}
			}
		}
	
		
//		Old hard-coded Matrix
//		System.out.println("B R M A W");
//		System.out.println(wG.printEdge(0, 0) + wG.printEdge(0, 1) + wG.printEdge(0, 2) + wG.printEdge(0, 3) + wG.printEdge(0, 4) + wG.getVertex(0).getName());
//		System.out.println(wG.printEdge(1, 0) + wG.printEdge(1, 1) + wG.printEdge(1, 2) + wG.printEdge(1, 3) + wG.printEdge(1, 4) + wG.getVertex(1).getName());
//		System.out.println(wG.printEdge(2, 0) + wG.printEdge(2, 1) + wG.printEdge(2, 2) + wG.printEdge(2, 3) + wG.printEdge(2, 4) + wG.getVertex(2).getName());
//		System.out.println(wG.printEdge(3, 0) + wG.printEdge(3, 1) + wG.printEdge(3, 2) + wG.printEdge(3, 3) + wG.printEdge(3, 4) + wG.getVertex(3).getName());
//		System.out.println(wG.printEdge(4, 0) + wG.printEdge(4, 1) + wG.printEdge(4, 2) + wG.printEdge(4, 3) + wG.printEdge(4, 4) + wG.getVertex(4).getName());

		System.out.println(Dijkstra.cheapestPath(wG, 0, 3));
		System.out.println(Dijkstra.shortestPath(wG, 0, 3));
	}

	Map<Integer, Integer> getNeighbourWithEdgeWeight(int vertex) {
		Map<Integer, Integer> neighborWithEdgeWeight = new HashMap<>();
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			Integer edgeWeight = adjacencyMatrix[vertex][i];
			if (edgeWeight != null) {
				neighborWithEdgeWeight.put(i, edgeWeight);
			}
		}
		return neighborWithEdgeWeight;
	}

	/*
	 * Methods to build the Matrix
	 */
	
	/*
	 * Constructor initiates a Matrix, Vertexes and Edges
	 */
	public WeightedGraph(int verts, int edges, boolean random) {
		vertCount = verts;
		edgeCount = edges;
		adjacencyMatrix = new Integer[verts][verts];
		createVertices(verts);
		if(random) {
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
			Vertex v = new Vertex(NAMES[i], i);
			vertices.add(v);
		}
	}
	
	/*
	 * This method randomly creates up to 'amount' edges for randomized vertex connections
	 * if boolean random is set to true in the constructor
	 */
	public void createRandomEdges(int amount) {
		Random r = new Random();

		for(int i = 0; i < amount; i++) {
			int vertexFrom = r.nextInt(vertices.size());
			int vertexTo = r.nextInt(vertices.size());
			int weight = r.nextInt(10);
			
			//In order to avoid weights between one and the same vertex
			
			while(vertexFrom == vertexTo) {
				vertexTo = r.nextInt(vertices.size());
			}
			if(getEdge(vertexFrom, vertexTo) == null) {
				makeEdge(vertexFrom, vertexTo, weight);
			}
		}
	}
	
	/*
	 * Creates given Edges for a set Matrix if boolean random is set to false in the constructor
	 */
	public void createEdges() {
//		makeEdge(1, 0, 3);	//     0 1 2 3 4
//		makeEdge(2, 0, 1);	//   0 - - - - -
//		makeEdge(3, 0, 4);	//   1 3 - - - -
//		makeEdge(3, 1, 2);	//   2 1 - - 6 -
//		makeEdge(2, 3, 6);	//   3 4 2 - - -
//		makeEdge(4, 3, 1);	//   4 - - - 1 -
		makeEdge(0, 1, 4);
		makeEdge(0, 2, 2);
		makeEdge(1, 2, 3);
		makeEdge(1, 3, 5);
		makeEdge(2, 3, 8);
		makeEdge(2, 1, 1);
	}
	// get the name of the vertex by its index
	public String getVertexName(Integer index){
		return NAMES[index];
	}
	/*
	 * Getters and Setters
	 */
	public Vertex getVertex(int i) {
		return vertices.get(i);
	}
	
	public void makeEdge(int from, int to, int weight) {
		adjacencyMatrix[from][to] = weight;
	}
	
	public Integer getEdge(int from, int to) {
		return adjacencyMatrix[from][to];
	}
	
	private String printEdge(int from, int to) {
		if (adjacencyMatrix[from][to] == null) {
			return "- ";
		} else {
			return adjacencyMatrix[from][to] + " ";
		}
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
