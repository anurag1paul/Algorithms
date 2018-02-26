package graphs.search.singleSourceShortestPath;

import graphs.Graph;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Anurag Paul
 *         Date: 13/1/18
 */
public class DjikstraAlgorithmTest {

    @Test
    public void basicTest() {
        Graph graph = Graph.Builder.newInstance(8)
                                   .loadAdjacencyListGraphFromFile("data/djikstraTest.txt", "\t", false)
                                   .build();
        DjikstraAlgorithm shortestPath = new DjikstraAlgorithm(graph);
        double[] paths = shortestPath.getShortestPaths(1);
        double[] expectedPaths = {0, 0, 1, 2, 3, 4, 4, 3, 2};

        for(int i=1; i <= 8; i++) {
            Assert.assertEquals(paths[i], expectedPaths[i], 0.01);
        }
    }

    @Test
    public void advancedTest() {
        Graph graph = Graph.Builder.newInstance(200)
                .loadAdjacencyListGraphFromFile("data/djikstraData.txt", "\t", false)
                .build();
        DjikstraAlgorithm shortestPath = new DjikstraAlgorithm(graph);
        double[] paths = shortestPath.getShortestPaths(1);
        int check[] = {7,37,59,82,99,115,133,165,188,197};
        int expected[] = {2599,2610,2947,2052,2367,2399,2029,2442,2505,3068};
        int i = 0;
        for(int w: check) {
            Assert.assertEquals(expected[i++], (int)(paths[w]));
        }
    }
}
