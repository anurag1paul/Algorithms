package dynamicProgramming;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Anurag Paul
 *         Date: 20/1/18
 */
public class MaxWeightIndependentSetsTest {

    @Test
    public void basicTest() {
        String file = "data/mwisTest1.txt";
        MaxWeightIndependentSets mwis = new MaxWeightIndependentSets(file);
        Assert.assertEquals(2616, mwis.execute());
        byte[] expected = {0,1,0,1,0,1,0,1,0,1};
        byte[] output = mwis.getOutput();
        for(int i=0; i< expected.length; i++)
            Assert.assertEquals(expected[i], output[i]);
    }

    @Test
    public void basicTest2() {
        String file = "data/mwisTest2.txt";
        MaxWeightIndependentSets mwis = new MaxWeightIndependentSets(file);
        Assert.assertEquals(2617, mwis.execute());
        byte[] expected = {0,1,0,1,0,0,1,0,0,1};
        byte[] output = mwis.getOutput();
        for(int i=0; i< expected.length; i++)
            Assert.assertEquals(expected[i], output[i]);
    }

    @Test
    public void advancedTest() {
        String file = "data/mwis.txt";
        MaxWeightIndependentSets mwis = new MaxWeightIndependentSets(file);
        Assert.assertEquals(2955353732L, mwis.execute());
        int[] check = {1, 2, 3, 4, 17, 117, 517, 997};
        byte[] output = mwis.getOutput();
        for(int i : check)
            System.out.print(output[i-1]);
    }
}
