import java.util.HashMap;
import java.util.Random;

public class WeightedGraph {

	int[][] adjacencyMatrix = new int[5][5];
	String[] names = {"Berlin", "Rome", "Munich", "Amsterdam", "Warsaw"};
	HashMap<Integer, Vertex> vertexes = new HashMap<>(); 
	
	public static void main(String[] args) {
		WeightedGraph wG = new WeightedGraph();
		wG.createVertices(5);
		wG.createRandomEdges(8);
		System.out.println(wG.getEdge(1,3) +" "+ wG.getVertexes(1).getName() +" "+ wG.getVertexes(3).getName() );
		System.out.println(wG.getEdge(3,1) +" "+ wG.getVertexes(1).getName() +" "+ wG.getVertexes(3).getName() );
		System.out.println("B" + " " + "R" + " " + "M" + " " + "A" + " " + "W");
		System.out.println(wG.getEdge(0,0) + " " + wG.getEdge(1,0) + " " + wG.getEdge(2,0) + " " + wG.getEdge(3,0) + " " + wG.getEdge(4,0) + " " +  wG.getVertexes(0).getName());
		System.out.println(wG.getEdge(0,1) + " " + wG.getEdge(1,1) + " " + wG.getEdge(2,1) + " " + wG.getEdge(3,1) + " " + wG.getEdge(4,1) + " " +  wG.getVertexes(1).getName());
		System.out.println(wG.getEdge(0,2) + " " + wG.getEdge(1,2) + " " + wG.getEdge(2,2) + " " + wG.getEdge(3,2) + " " + wG.getEdge(4,2) + " " +  wG.getVertexes(2).getName());
		System.out.println(wG.getEdge(0,3) + " " + wG.getEdge(1,3) + " " + wG.getEdge(2,3) + " " + wG.getEdge(3,3) + " " + wG.getEdge(4,3) + " " +  wG.getVertexes(3).getName());
		System.out.println(wG.getEdge(0,4) + " " + wG.getEdge(1,4) + " " + wG.getEdge(2,4) + " " + wG.getEdge(3,4) + " " + wG.getEdge(4,4) + " " +  wG.getVertexes(4).getName());

	}
	
	
	public WeightedGraph() {
		
	}
	public Vertex getVertexes(int i) {
		return vertexes.get(i);
	}
	public void makeEdge(int to, int from, int weight) {
		adjacencyMatrix[to][from] = weight;
	}
	public int getEdge(int to, int from) {
		return adjacencyMatrix[to][from];
	}
	public void createVertices(int amount) {
		for(int i = 0; i < amount; i++) {
			Vertex v = new Vertex();
			v.setName(names[i]);
			vertexes.put(i, v);
		}
	}
	public void createRandomEdges(int amount) {
		Random r = new Random();

		for(int i = 0; i <= amount; i++) {
			int rdm = r.nextInt(4);
			int rdm2 = r.nextInt(4);
			int weight = r.nextInt(10);
			while(rdm == rdm2) {
				rdm2 = r.nextInt(4);
			}
			if(getEdge(rdm, rdm2) == 0) {
				makeEdge(rdm, rdm2, weight);
			}
		}
	}
	
	
//	   0 1 2 3 4
//   0 0 0 0 0 0
//   1 3 0 0 0 0
//   2 1 0 6 0 0
//   3 4 2 0 0 0
//   4 0 0 0 1 0
}  
