package graphs.search;

import graphs.Edge;
import graphs.Graph;

import java.util.*;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 13/1/18
 */
public class DjikstraShortestPath {

    private Graph graph;
    private int numVertices;

    public DjikstraShortestPath(Graph graph) {
        this.graph = graph;
        this.numVertices = graph.getNumVertices();
    }

    public double[] getShortestPaths(int startNode) {

        double[] shortestLengths = new double[numVertices + 1];
        boolean[] visited = new boolean[numVertices + 1];
        Set<Integer> visitedSet = new HashSet<>();

        visited[startNode] = true;
        shortestLengths[startNode] = 0;
        visitedSet.add(startNode);

        PriorityQueue<Map.Entry<Integer, Double>> toBeVisited =
                new PriorityQueue<>((t1, t2) -> t1.getValue() < t2.getValue()? -1:1);
        Map<Integer, Map.Entry<Integer, Double>> callback = new HashMap<>();

        double distance, oldDistance;

        while(visitedSet.size() != numVertices) {

            for(Integer v: visitedSet) {
                for(Edge edge: graph.getEdges(v)) {
                    if(edge.src == v && !visited[edge.dst]) {

                        distance = shortestLengths[v] + edge.getWeight();

                        if(callback.containsKey(edge.dst)) {
                            oldDistance = callback.get(edge.dst).getValue();
                            if(distance < oldDistance) {
                                toBeVisited.remove(callback.get(edge.dst));
                            }else
                                continue;
                        }

                        Map.Entry<Integer, Double> newEntry = new AbstractMap.SimpleEntry<>(edge.dst, distance);
                        callback.put(edge.dst, newEntry);
                        toBeVisited.offer(newEntry);
                    }
                }
            }

            Map.Entry<Integer, Double> nextVisit = toBeVisited.poll();
            int nextNode = nextVisit.getKey();
            visited[nextNode] = true;
            visitedSet.add(nextNode);
            shortestLengths[nextNode] = nextVisit.getValue();
        }

        return shortestLengths;
    }
}
