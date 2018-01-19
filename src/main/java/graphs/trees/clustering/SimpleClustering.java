package graphs.trees.clustering;

import dataStructures.DisjointSet;
import graphs.Edge;
import graphs.Graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author Anurag Paul
 *         Date: 20/1/18
 */
public class SimpleClustering {

    private final int numVertices;
    private final Graph graph;

    public SimpleClustering(Graph graph) {
        this.graph = graph;
        this.numVertices = graph.getNumVertices();
    }

    public double cluster(int numClusters) {
        DisjointSet disjointSet = new DisjointSet(graph.getNumVertices());

        //generate sorted array of edges
        PriorityQueue<Edge> sortedGraphEdges= new PriorityQueue<>(Comparator.comparing(Edge::getWeight));
        sortedGraphEdges.addAll(graph.getAllEdges());

        Set<Integer> clusterParents = new HashSet<>();
        for(int i=0; i<numVertices; i++)
            clusterParents.add(i);

        double spacing = Double.MAX_VALUE;

        while(!sortedGraphEdges.isEmpty()){
            Edge edge = sortedGraphEdges.poll();

            int firstParent = disjointSet.find(edge.src - 1);
            int secondParent = disjointSet.find(edge.dst - 1);

            if(firstParent != secondParent){
                if(clusterParents.size() > numClusters) {
                    int absorbed = disjointSet.union(firstParent, secondParent);
                    clusterParents.remove(absorbed);
                }
                else if(spacing > edge.getWeight())
                    spacing = edge.getWeight();
            }
        }

        return spacing;
    }
}
