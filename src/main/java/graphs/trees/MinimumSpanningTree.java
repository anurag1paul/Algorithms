package graphs.trees;

import dataStructures.DisjointSet;
import graphs.Edge;
import graphs.Graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 23/6/17
 */
public class MinimumSpanningTree extends Tree {

    private double weight;

    private Set<Edge> edges = new HashSet<>();

    public MinimumSpanningTree(Graph graph) {
        generateMST(this, graph);
    }

    public double getWeight() {
        return weight;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    private static void generateMST(MinimumSpanningTree mst, Graph graph) {
        DisjointSet disjointSet = new DisjointSet(graph.getNumVertices());

        //generate sorted array of edges
        PriorityQueue<Edge> sortedGraphEdges= new PriorityQueue<>(Comparator.comparing(Edge::getWeight));
        sortedGraphEdges.addAll(graph.getAllEdges());

        while(!sortedGraphEdges.isEmpty()){
            Edge edge = sortedGraphEdges.poll();

            int firstParent = disjointSet.find(edge.src - 1);
            int secondParent = disjointSet.find(edge.dst - 1);

            if(firstParent != secondParent){
                disjointSet.union(firstParent, secondParent);
                mst.weight += edge.getWeight();
                mst.edges.add(edge);
            }
        }
    }
}
