package divideAndConquer;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 22/11/17
 */
public class CountingInversionsTest {

    @Test
    public void test(){

        int[] input1 = {1,2,3,4,5,6};
        int count1 = new CountingInversions(input1).getNumInversions();
        Assert.assertEquals(0, count1);

        int[] input2 = {6,5,4,3,2,1};
        int count2 = new CountingInversions(input2).getNumInversions();
        Assert.assertEquals(15, count2);

        int[] input3 = {1,3,5,2,4,6};
        int count3 = new CountingInversions(input3).getNumInversions();
        Assert.assertEquals(3, count3);
    }
}
