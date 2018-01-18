package graphs.trees;

import dataStructures.DisjointSet;
import graphs.Edge;
import graphs.Graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author Anurag Paul
 *         Date: 23/6/17
 */
public class KruskalsMSTAlgo extends MinimumSpanningTree {

    public KruskalsMSTAlgo(Graph graph) {
        this.graph = graph;
    }

    @Override
    public double generateMST() {
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
                weight += edge.getWeight();
                edges.add(edge);
            }
        }

        return weight;
    }
}
