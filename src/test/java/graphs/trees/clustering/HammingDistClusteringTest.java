package graphs.trees.clustering;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Anurag Paul
 *         Date: 20/1/18
 */
public class HammingDistClusteringTest {

    @Test
    public void advancedTest(){
        String fileName = "data/clustering_big.txt";
        HammingDistClustering clustering = new HammingDistClustering(fileName);
        Assert.assertEquals(6118, clustering.getNumClusters());
    }
}
