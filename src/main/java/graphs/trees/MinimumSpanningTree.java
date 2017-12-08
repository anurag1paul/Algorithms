package graphs.trees;

import graphs.Edge;
import graphs.Graph;
import sets.DisjointSet;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 23/6/17
 */
public class MinimumSpanningTree extends Tree {

    private double weight;

    private Map<Edge, Integer> edges = new HashMap<>();

    public MinimumSpanningTree(Graph graph) {
        generateMST(this, graph);
    }

    public double getWeight() {
        return weight;
    }

    public Map<Edge, Integer> getEdges() {
        return edges;
    }

    private static void generateMST(MinimumSpanningTree mst, Graph graph) {
        DisjointSet disjointSet = new DisjointSet(graph.getNumVertices());

        //generate sorted array of edges
        PriorityQueue<Map.Entry<Edge, Integer>>
                sortedGraphEdges= new PriorityQueue<>(Comparator.comparing(Map.Entry::getValue));
        sortedGraphEdges.addAll(graph.getEdges().entrySet());

        while(!sortedGraphEdges.isEmpty()){
            Map.Entry<Edge, Integer> weightedEdge = sortedGraphEdges.poll();
            Edge edge = weightedEdge.getKey();

            int firstParent = disjointSet.find(edge.src);
            int secondParent = disjointSet.find(edge.dst);

            if(firstParent != secondParent){
                disjointSet.union(firstParent, secondParent);
                mst.weight += weightedEdge.getValue();
                mst.edges.put(weightedEdge.getKey(), weightedEdge.getValue());
            }
        }
    }
}
