package divideAndConquer.selection;

import org.junit.Assert;
import org.junit.Test;

import static divideAndConquer.selection.RandomizedSelection.getOrderStatistic;

/**
 * @author Anurag Paul
 *         Date: 13/1/18
 */
public class RandomizedSelectionTest {

    @Test
    public void basicTest(){
        for(int i=0; i<10000; i++) {
            int[] input1 = {10, 2, 3, 5, 8, 1, 4, 6, 9, 7};
            Assert.assertEquals(5, getOrderStatistic(input1, 5));
        }
    }
}
