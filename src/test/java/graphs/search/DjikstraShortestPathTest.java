package graphs.search;

import graphs.Graph;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 13/1/18
 */
public class DjikstraShortestPathTest {

    @Test
    public void basicTest() {
        Graph graph = Graph.Builder.newInstance(8)
                                   .loadAdjacencyListGraphFromFile("data/djikstraTest.txt", "\t")
                                   .build();
        DjikstraShortestPath shortestPath = new DjikstraShortestPath(graph);
        double[] paths = shortestPath.getShortestPaths(1);
        double[] expectedPaths = {0, 0, 1, 2, 3, 4, 4, 3, 2};

        for(int i=1; i <= 8; i++) {
            Assert.assertEquals(paths[i], expectedPaths[i], 0.01);
        }
    }

    @Test
    public void advancedTest() {
        Graph graph = Graph.Builder.newInstance(200)
                .loadAdjacencyListGraphFromFile("data/djikstraData.txt", "\t")
                .build();
        DjikstraShortestPath shortestPath = new DjikstraShortestPath(graph);
        double[] paths = shortestPath.getShortestPaths(1);
        System.out.println(paths.length);
        int check[] = {7,37,59,82,99,115,133,165,188,197};
        for(int w: check) {
            System.out.print((int)(paths[w]) + ",");
        }
    }
}
