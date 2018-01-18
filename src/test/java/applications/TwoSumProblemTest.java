package applications;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Anurag Paul
 *         Date: 15/1/18
 */
public class TwoSumProblemTest {

    @Test
    public void basicTest() {
        String fileName = "data/prob-2sumTest.txt";
        long targetStart = 3;
        long targetEnd = 10;

        TwoSumProblem solver = new TwoSumProblem(fileName, targetStart, targetEnd);
        Assert.assertEquals(8, solver.getCountOfSatisfiedTargets());
    }

    @Test
    public void advancedTest() {
        String fileName = "data/prob-2sum.txt";
        long targetStart = -10000;
        long targetEnd = 10000;

        TwoSumProblem solver = new TwoSumProblem(fileName, targetStart, targetEnd);
        long count = solver.getCountOfSatisfiedTargets();
        Assert.assertEquals(427, count);
    }
}
