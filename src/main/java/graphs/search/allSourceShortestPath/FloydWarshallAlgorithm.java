package graphs.search.allSourceShortestPath;

import graphs.Edge;
import graphs.Graph;

/**
 * @author Anurag Paul
 * Date: 26/2/18
 */
public class FloydWarshallAlgorithm extends AbstractAllPairsShortestPath {

    public FloydWarshallAlgorithm(Graph graph) {
        super(graph);
    }

    @Override
    protected void calculateShortestPaths() {

        shortestPaths = new double[numVertices+1][numVertices+1];

        //initialisation
        for (int src=1; src<=numVertices; src++)
            for(int dst=1; dst<=numVertices; dst++)
                if(src != dst)
                    shortestPaths[src][dst] = Double.MAX_VALUE;

        for (Edge edge: graph.getAllEdges())
            shortestPaths[edge.src][edge.dst] = edge.getWeight();


        // budget refers to number of vertices allowed between src and dst
        for (int budget = 1; budget <= numVertices; budget++) {
            for (int src = 1; src <= numVertices; src++) {
                for (int dst = 1; dst <= numVertices; dst++) {
                    double newShortestPath = shortestPaths[src][budget] + shortestPaths[budget][dst];
                    if(newShortestPath < shortestPaths[src][dst])
                        shortestPaths[src][dst] = newShortestPath;
                }
            }
        }

        for(int i=1; i<=numVertices; i++) {
            if(shortestPaths[i][i] < 0)
                throw new RuntimeException("Found Negative Cycle");
        }
    }
}
