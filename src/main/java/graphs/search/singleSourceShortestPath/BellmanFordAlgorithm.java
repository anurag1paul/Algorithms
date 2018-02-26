package graphs.search.singleSourceShortestPath;

import graphs.Edge;
import graphs.Graph;

import java.util.Set;

/**
 * @author Anurag Paul
 * Date: 23/2/18
 */
public class BellmanFordAlgorithm {

    private final Graph graph;
    private final int numVertices;
    private final Set<Edge> edges;

    public BellmanFordAlgorithm(Graph graph) {
        this.graph = graph;
        this.numVertices = graph.getNumVertices();
        this.edges = graph.getAllEdges();
    }

    public double[] getShortestPaths(int startNode) {

        double[] shortestPaths = new double[numVertices + 1];
        boolean anyUpdates;

        // initialisation
        for(int i=1; i <= numVertices; i++) {
            if(i!=startNode)
                shortestPaths[i] = Integer.MAX_VALUE;
        }

        // main algorithm
        for(int i=1; i <= numVertices; i++) {

            anyUpdates = false;

            for(Edge edge: edges) {
                double newShortestPath = shortestPaths[edge.src] + edge.getWeight();
                if(newShortestPath < shortestPaths[edge.dst]) {
                    shortestPaths[edge.dst] = newShortestPath;
                    anyUpdates = true;
                }
            }

            if(!anyUpdates)
                break;
            else if(i==numVertices)
                throw new RuntimeException("Found Negative Cycle");
        }

        return shortestPaths;
    }

}
