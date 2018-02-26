package graphs.trees.clustering;

import graphs.Graph;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Anurag Paul
 *         Date: 20/1/18
 */
public class SimpleClusteringTest {

    @Test
    public void basicTest(){
        Graph graph = Graph.Builder.newInstance(12).loadEdgesListGraphFromFile("data/clustering1_test.txt", " ", false).build();
        SimpleClustering clustering = new SimpleClustering(graph);
        double spacing = clustering.cluster(4);
        Assert.assertEquals(99.0, spacing, 0.0001);
    }

    @Test
    public void advancedTest(){
        Graph graph = Graph.Builder.newInstance(500).loadEdgesListGraphFromFile("data/clustering1.txt", " ", false).build();
        SimpleClustering clustering = new SimpleClustering(graph);
        double spacing = clustering.cluster(4);
        Assert.assertEquals(106.0, spacing, 0.0001);
    }
}
