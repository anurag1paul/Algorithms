package graphs.search.allSourceShortestPath;

import graphs.Graph;

/**
 * @author Anurag Paul
 * Date: 26/2/18
 */
public abstract class AbstractAllPairsShortestPath {

    protected final Graph graph;
    protected final int numVertices;
    protected double[][] shortestPaths;

    AbstractAllPairsShortestPath(Graph graph) {
        this.graph = graph;
        this.numVertices = graph.getNumVertices();
    }

    public double[][] getShortestPaths() {
        if(shortestPaths == null)
            calculateShortestPaths();

        return shortestPaths;
    }

    protected abstract void calculateShortestPaths();

    public double getShortestShortestPath() {
        double[][] shortestPaths = getShortestPaths();

        double shortestShortestPath = Double.MAX_VALUE;

        for (int src=1; src<=numVertices; src++)
            for (int dst=1; dst<=numVertices; dst++)
                if (shortestPaths[src][dst] < shortestShortestPath)
                    shortestShortestPath = shortestPaths[src][dst];

        return shortestShortestPath;
    }
}
