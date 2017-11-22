package divideAndConquer;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 14/11/17
 */
public class MergeSortTest {

    @Test
    public void sortTest(){
        int[] input1 = {1,3,2,6,4,8,3,9,2};
        check(input1);

        int[] input2 = {1,3,2,6,4,8,3,9,2,12};
        check(input2);

        int[] input3= {9,8,7,6,5,4,3,2,1};
        check(input3);

        int[] input4 = {10};
        check(input4);

        int[] input5 ={10,5};
        check(input5);
    }

    private void check(int[] input) {
        int[] output = MergeSort.sort(input);
        Assert.assertTrue(output.length == input.length);

        for(int i=0; i<(output.length -1); i++)
            Assert.assertTrue(output[i] <= output[i+1]);
    }
}
