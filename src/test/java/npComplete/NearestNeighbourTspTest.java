package npComplete;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Anurag Paul
 * Date: 10/3/18
 */
public class NearestNeighbourTspTest {

    private String base_file = "data/tsp/";

    @Test
    public void basicTest1(){
        String file = base_file + "tsp_nn_test1.txt";
        TravelingSalesmanProblem tsp = new NearestNeighbourTsp(file, " ");
        Assert.assertEquals(15.2361, tsp.getTour(), 0.01);
    }

    @Test
    public void advancedTest(){
        String file = base_file + "nn.txt";
        TravelingSalesmanProblem tsp = new NearestNeighbourTsp(file, " ");
        Assert.assertEquals(1203406.00, tsp.getTour(), 1.0);
    }

}
