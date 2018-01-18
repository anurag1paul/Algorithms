package graphs.trees;

import graphs.Edge;
import graphs.Graph;

import java.util.*;

/**
 * @author Anurag Paul
 *         Date: 18/1/18
 */
public class PrimsMSTAlgo extends MinimumSpanningTree {

    private int numVertices;

    public PrimsMSTAlgo(Graph graph) {
        this.graph = graph;
        numVertices = graph.getNumVertices();
    }

    @Override
    public double generateMST() {
        int startNode = new Random().nextInt(numVertices);

        boolean[] visited = new boolean[numVertices + 1];
        Set<Integer> visitedSet = new HashSet<>();

        visited[startNode] = true;
        visitedSet.add(startNode);

        PriorityQueue<Map.Entry<Integer, Edge>> toBeVisited =
                new PriorityQueue<>((t1, t2) -> t1.getValue().getWeight() < t2.getValue().getWeight() ? -1 : 1);
        Map<Integer, Map.Entry<Integer, Edge>> callback = new HashMap<>();

        double distance, oldDistance;

        int latestAddedNode = startNode, otherNode;

        while(visitedSet.size() != numVertices) {

            for(Edge edge: graph.getEdges(latestAddedNode)) {
                if(edge.src == latestAddedNode)
                    otherNode = edge.dst;
                else
                    otherNode = edge.src;

                if(!visited[otherNode]) {

                    distance = edge.getWeight();

                    if(callback.containsKey(otherNode)) {
                        oldDistance = callback.get(otherNode).getValue().getWeight();
                        if(distance < oldDistance) {
                            toBeVisited.remove(callback.get(otherNode));
                        }else
                            continue;
                    }

                    Map.Entry<Integer, Edge> newEntry = new AbstractMap.SimpleEntry<>(otherNode, edge);
                    callback.put(otherNode, newEntry);
                    toBeVisited.offer(newEntry);
                }
            }

            Map.Entry<Integer, Edge> nextVisit = toBeVisited.poll();
            latestAddedNode = nextVisit.getKey();
            visited[latestAddedNode] = true;
            visitedSet.add(latestAddedNode);
            weight += nextVisit.getValue().getWeight();
            edges.add(nextVisit.getValue());
        }

        return weight;
    }
}
