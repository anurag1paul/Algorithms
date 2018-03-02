package npComplete;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Anurag Paul
 * Date: 1/3/18
 */
public class DynamicProgrammingTspTest {

    private String base_file = "data/tsp/";

    @Test
    public void basicTest1(){
        String file = base_file + "tsp_test1.txt";
        TravelingSalesmanProblem tsp = new DynamicProgrammingTsp(file, " ");
        Assert.assertEquals(10.24, tsp.getTour(), 0.01);
    }

    @Test
    public void basicTest2(){
        String file = base_file + "tsp_test2.txt";
        TravelingSalesmanProblem tsp = new DynamicProgrammingTsp(file, " ");
        Assert.assertEquals(12.36, tsp.getTour(), 0.01);
    }

    @Test
    public void basicTest3(){
        String file = base_file + "tsp_test3.txt";
        TravelingSalesmanProblem tsp = new DynamicProgrammingTsp(file, " ");
        Assert.assertEquals(14.00, tsp.getTour(), 0.01);
    }

    @Test
    public void advancedTest(){
        String file = base_file + "tsp_25.txt";
        TravelingSalesmanProblem tsp = new DynamicProgrammingTsp(file, " ");
        Assert.assertEquals(26442.00, tsp.getTour(), 1.0);
    }
}
