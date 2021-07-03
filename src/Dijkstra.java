import java.util.*;

public class Dijkstra {
    private static class Label implements Comparable<Label> {
        Integer labeledVertex;
        Integer predecessorVertex;
        int cost;
        public int compareTo(Label o) {
            return Integer.compare(cost, o.cost);
        }
    }

    // this method looks for the path with the lowest sum of edge weights in the graph between 2 vertexes,
    // taken from https://horstmann.com/htw/2021/info2/day18/Dijkstra.java
    public static List<String> cheapestPath(WeightedGraph graph, Integer start, Integer end) {
        Set<Integer> searched = new HashSet<>();
        Map<Integer, Label> labels = new HashMap<>();
        PriorityQueue<Label> labelQueue = new PriorityQueue<>();
        // Step 1
        Map<Integer, Integer> incidentEdges = graph.getNeighbourWithEdgeWeight(start);
        for (Map.Entry<Integer, Integer> e : incidentEdges.entrySet()) {
            Label l = new Label();
            l.cost = e.getValue();
            l.labeledVertex = e.getKey();
            l.predecessorVertex = start;
            labelQueue.add(l);
            labels.put(l.labeledVertex, l);
        }
        boolean done = false;
        while (!done) {
            // Step 2
            Label r = labelQueue.remove();
            Map<Integer, Integer> incidentEdgesR = graph.getNeighbourWithEdgeWeight(r.labeledVertex);
            for (Map.Entry<Integer, Integer> e : incidentEdgesR.entrySet()) {
                Integer neighbor = e.getKey();
                if (!searched.contains(neighbor)) {
                    if (!labels.containsKey(neighbor)) {
                        // neighbor unlabeled
                        Label l = new Label();
                        l.cost = r.cost + e.getValue();
                        l.labeledVertex = e.getKey();
                        l.predecessorVertex = r.labeledVertex;
                        labelQueue.add(l);
                        labels.put(l.labeledVertex, l);
                    } else {
                        Label neighborLabel = labels.get(neighbor);
                        if (r.cost + e.getValue() < neighborLabel.cost) {
                            neighborLabel.cost = r.cost + e.getValue();
                            neighborLabel.predecessorVertex = r.labeledVertex;
                        }
                    }
                }
            }
            searched.add(r.labeledVertex);
            if (r.labeledVertex.equals(end))
                done = true;
        }
        // Step 4
        LinkedList<String> result = new LinkedList<>();
        Integer current = end;
        result.addFirst(graph.getVertexName(current));
        while (!current.equals(start)) {
            current = labels.get(current).predecessorVertex;
            result.addFirst(graph.getVertexName(current));
        }
        return result;
    }
    // modified version of the cheapest path that uses weight 1 for all edges
    public static List<String> shortestPath(WeightedGraph graph, Integer start, Integer end) {
        Set<Integer> searched = new HashSet<>();
        Map<Integer, Label> labels = new HashMap<>();
        PriorityQueue<Label> labelQueue = new PriorityQueue<>();
        // Step 1
        Map<Integer, Integer> incidentEdges = graph.getNeighbourWithEdgeWeight(start);
        for (Map.Entry<Integer, Integer> e : incidentEdges.entrySet()) {
            Label l = new Label();
            l.cost = 1;
            l.labeledVertex = e.getKey();
            l.predecessorVertex = start;
            labelQueue.add(l);
            labels.put(l.labeledVertex, l);
        }
        boolean done = false;
        while (!done) {
            // Step 2
            Label r = labelQueue.remove();
            Map<Integer, Integer> incidentEdgesR = graph.getNeighbourWithEdgeWeight(r.labeledVertex);
            for (Map.Entry<Integer, Integer> e : incidentEdgesR.entrySet()) {
                Integer neighbor = e.getKey();
                if (!searched.contains(neighbor)) {
                    if (!labels.containsKey(neighbor)) {
                        // neighbor unlabeled
                        Label l = new Label();
                        l.cost = r.cost + 1;
                        l.labeledVertex = e.getKey();
                        l.predecessorVertex = r.labeledVertex;
                        labelQueue.add(l);
                        labels.put(l.labeledVertex, l);
                    } else {
                        Label neighborLabel = labels.get(neighbor);
                        if (r.cost + 1 < neighborLabel.cost) {
                            neighborLabel.cost = r.cost + 1;
                            neighborLabel.predecessorVertex = r.labeledVertex;
                        }
                    }
                }
            }
            searched.add(r.labeledVertex);
            if (r.labeledVertex.equals(end))
                done = true;
        }
        // Step 4
        LinkedList<String> result = new LinkedList<>();
        Integer current = end;
        result.addFirst(graph.getVertexName(current));
        while (!current.equals(start)) {
            current = labels.get(current).predecessorVertex;
            result.addFirst(graph.getVertexName(current));
        }
        return result;
    }
}
