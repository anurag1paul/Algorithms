package graphs.search.allSourceShortestPath;

import graphs.Edge;
import graphs.Graph;
import graphs.search.singleSourceShortestPath.BellmanFordAlgorithm;
import graphs.search.singleSourceShortestPath.DjikstraAlgorithm;

/**
 * @author Anurag Paul
 * Date: 26/2/18
 */
public class JohnsonsAlgorithm extends AbstractAllPairsShortestPath {

    public JohnsonsAlgorithm(Graph graph) {
        super(graph);
    }

    protected void calculateShortestPaths() {
        shortestPaths = new double[numVertices+1][numVertices+1];

        // first add a dummy node to the graph with zero edge weights
        Graph.Builder modifiedGraph = Graph.Builder.newInstance(numVertices + 1);
        for(Edge edge: graph.getAllEdges()){
            modifiedGraph.addEdge(edge);
        }
        for(int i=1; i<=numVertices; i++)
            modifiedGraph.addEdge(numVertices+1, i, 0);

        // Use Bellman Ford Algorithm to calculate weights
        BellmanFordAlgorithm bellmanFord = new BellmanFordAlgorithm(modifiedGraph.build());
        double[] weights = bellmanFord.getShortestPaths(numVertices+1);

        // now create another modified graph with adjusted edge lengths
        modifiedGraph = Graph.Builder.newInstance(numVertices);
        for(Edge edge: graph.getAllEdges()){
            double newWeight = edge.getWeight() + weights[edge.src] - weights[edge.dst];
            modifiedGraph.addEdge(edge.src, edge.dst, newWeight);
        }

        DjikstraAlgorithm djikstraAlgorithm = new DjikstraAlgorithm(modifiedGraph.build());

        //now run Djikstra n times to calculate shortest paths
        for(int src=1; src<=numVertices; src++){
            double[] modShortestPaths = djikstraAlgorithm.getShortestPaths(src);

            for (int dst=1; dst<=numVertices; dst++){
                shortestPaths[src][dst] = modShortestPaths[dst] - weights[src] + weights[dst];
            }
        }
    }
}
