package graphs.trees;

import graphs.Graph;
import sets.DisjointSet;

import java.awt.datatransfer.MimeTypeParseException;
import java.util.*;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 23/6/17
 */
public class MinimumSpanningTree extends Tree {

    private double weight;

    private Map<AbstractMap.SimpleImmutableEntry<Integer, Integer>, Integer> edges = new HashMap<>();

    public MinimumSpanningTree(Graph graph) {
        generateMST(this, graph);
    }

    public double getWeight() {
        return weight;
    }

    public Map<AbstractMap.SimpleImmutableEntry<Integer, Integer>, Integer> getEdges() {
        return edges;
    }

    private static void generateMST(MinimumSpanningTree mst, Graph graph) {
        DisjointSet disjointSet = new DisjointSet(graph.getNumVertices());

        //generate sorted array of edges
        PriorityQueue<Map.Entry<AbstractMap.SimpleImmutableEntry<Integer, Integer>, Integer>>
        sortedGraphEdges= new PriorityQueue<>(Comparator.comparing(Map.Entry::getValue));
        sortedGraphEdges.addAll(graph.getEdges().entrySet());

        while(!sortedGraphEdges.isEmpty()){
            Map.Entry<AbstractMap.SimpleImmutableEntry<Integer, Integer>, Integer> weightedEdge = sortedGraphEdges.poll();
            AbstractMap.SimpleImmutableEntry<Integer, Integer> edge = weightedEdge.getKey();

            int firstParent = disjointSet.find(edge.getKey());
            int secondParent = disjointSet.find(edge.getValue());

            if(firstParent != secondParent){
                disjointSet.union(firstParent, secondParent);
                mst.weight += weightedEdge.getValue();
                mst.edges.put(weightedEdge.getKey(), weightedEdge.getValue());
            }
        }
    }
}
