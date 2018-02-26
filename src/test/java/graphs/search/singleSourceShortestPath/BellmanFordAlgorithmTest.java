package graphs.search.singleSourceShortestPath;

import graphs.Graph;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Anurag Paul
 * Date: 23/2/18
 */
public class BellmanFordAlgorithmTest {

    @Test
    public void test(){
        String file = "data/shortestPaths/test1.txt";
        Graph graph = Graph.Builder.newInstance(8).loadEdgesListGraphFromFile(file, " ", true).build();
        BellmanFordAlgorithm bellmanFord = new BellmanFordAlgorithm(graph);
        double[] shortestPaths = bellmanFord.getShortestPaths(1);
        double shortestShortestPath = Double.MAX_VALUE;
        for(double path: shortestPaths)
            if(path < shortestShortestPath)
                shortestShortestPath = path;

        Assert.assertEquals(-41, shortestShortestPath, 0.001);
    }
}
