package graphs.search.allSourceShortestPath;

import graphs.Graph;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Anurag Paul
 * Date: 26/2/18
 */
public class JohnsonsAlgorithmTest {

    @Test
    public void basicTest() {
        String file = "data/shortestPaths/test1.txt";
        Graph graph = Graph.Builder.newInstance(8).loadEdgesListGraphFromFile(file, " ", true).build();
        JohnsonsAlgorithm johnsonsAlgorithm = new JohnsonsAlgorithm(graph);

        Assert.assertEquals(-41, johnsonsAlgorithm.getShortestShortestPath(), 0.001);
    }

    @Test
    public void advancedTest() {
        String[] files = {"data/shortestPaths/g1.txt", "data/shortestPaths/g2.txt", "data/shortestPaths/g3.txt"};
        double shortestShortestPath = Double.MAX_VALUE;

        for(String file: files) {
            Graph graph = Graph.Builder.newInstance(1000).loadEdgesListGraphFromFile(file, " ", true).build();
            JohnsonsAlgorithm johnsonsAlgorithm = new JohnsonsAlgorithm(graph);

            try {
                double minPath = johnsonsAlgorithm.getShortestShortestPath();
                if(minPath < shortestShortestPath)
                    shortestShortestPath = minPath;
            }catch (RuntimeException e) {
                System.err.println(e.getMessage());
            }
        }

        Assert.assertEquals(-19.0, shortestShortestPath, 0.0001);
    }
}
