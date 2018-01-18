package divideAndConquer.selection;

import org.junit.Assert;
import org.junit.Test;

import static divideAndConquer.selection.DeterministicSelection.getOrderStatistic;
import static utils.utils.printArray;

/**
 * @author Anurag Paul
 *         Date: 13/1/18
 */
public class DeterministicSelectionTest {

    @Test
    public void basicTest(){
        int[] input = {10, 2, 3, 5, 8, 1, 4, 6, 9, 7};
        Assert.assertEquals(5, getOrderStatistic(input, 5));
        Assert.assertEquals(3, getOrderStatistic(input, 3));
    }

    @Test
    public void advancedTest(){

        int[] input = new int[50];

        for(int i = 0; i < input.length; i++)
            input[i] = (int) (Math.random() * 50);

        printArray(input);
        int expected = RandomizedSelection.getOrderStatistic(input, 3);
        int actual = getOrderStatistic(input, 3);

        Assert.assertEquals(expected, actual);
    }
}
