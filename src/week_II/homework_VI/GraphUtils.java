package week_II.homework_VI;

import java.util.*;

/*
 * SD2x Homework #6
 * Implemented by @bozzcode
 */
@SuppressWarnings("ALL")
public class GraphUtils {

	public static int minDistance(Graph graph, String src, String dest) {

		if (graph == null || src == null || dest == null || !graph.containsElement(src)
				|| !graph.containsElement(dest)) return -1;
		
		return new BreadthFirstSearch(graph).bfsDistance(graph.getNode(src), dest);
	}
	

	public static Set<String> nodesWithinDistance(Graph graph, String src, int distance) {

		if (graph == null || !graph.containsElement(src) || distance < 1) return null;

		Set<String> nodesWithin = new HashSet<>();

		if (graph.getNumNodes() == 1) return nodesWithin;

        Map<Node, Integer> distances = new HashMap<>();
        Queue<Node> toExplore = new LinkedList<>();
        Set<Node> marked = new HashSet<>();

        toExplore.add(graph.getNode(src));
		marked.add(graph.getNode(src));
		distances.put(graph.getNode(src), 0);

		while (!toExplore.isEmpty()){
		    Node current = toExplore.remove();
            for (Node node: graph.getNodeNeighbors(current)) {
                if (!marked.contains(node)) {
                    distances.put(node, distances.get(current) + 1);
                    if (distances.get(node) <= distance) nodesWithin.add(node.getElement());
                    marked.add(node);
                    toExplore.add(node);
                }
            }
        }
		return nodesWithin;
	}


	public static boolean isHamiltonianPath(Graph g, List<String> values) {

		if (g == null || values == null || values.isEmpty()) return false;

		String src = values.get(0);
		Set<String> marked = new HashSet<>();
		marked.add(src);

		if (!src.equals(values.get(values.size() - 1))) return false;

        for (int i = 0; i < values.size(); i++) {
            if (!g.containsElement(values.get(i))) return false;
            if (marked.contains(values.get(i)) && i != (values.size() - 1)) return false;
            if (!g.getNodeNeighbors(g.getNode(values.get(i - 1))).contains(g.getNode(values.get(i)))) return false;

            marked.add(values.get(i));
        }

        return marked.size() == g.getNumNodes();
	}
	
}
