package dynamicProgramming;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Anurag Paul
 *         Date: 21/1/18
 */
public class KnapsackProblemTest {

    @Test
    public void basicTest() {
        String fileName = "data/knapsackTest1.txt";
        KnapsackProblem kp = new KnapsackProblem(fileName);
        Assert.assertEquals(147, kp.execute());
    }

    @Test
    public void advancedTest1() {
        String fileName = "data/knapsack1.txt";
        KnapsackProblem kp = new KnapsackProblem(fileName);
        Assert.assertEquals(2493893, kp.execute());
    }

    @Test
    public void advancedTest2() {
        String fileName = "data/knapsack_big.txt";
        KnapsackProblem kp = new KnapsackProblem(fileName);
        Assert.assertEquals(4243395, kp.execute());
    }
}
