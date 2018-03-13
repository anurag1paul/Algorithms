package applications;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Anurag Paul
 * Date: 10/3/18
 */
public class TwoSATTest {

    private String base = "data/2sat/";

    @Test
    public void basicTest1() {
        String file = base + "test1.txt";
        TwoSAT twoSAT = new TwoSAT(file, " ");
        Assert.assertTrue(!twoSAT.isSatisfiable());
    }

    @Test
    public void basicTest2() {
        String file = base + "test2.txt";
        TwoSAT twoSAT = new TwoSAT(file, " ");
        Assert.assertTrue(twoSAT.isSatisfiable());
    }

    @Test
    public void advancedTest() {
        for(int i=1; i<=6; i++) {
            String file = base + "2sat" + i + ".txt";
            TwoSAT twoSAT = new TwoSAT(file, " ");
            System.out.println(i + "->" + twoSAT.isSatisfiable());
        }
    }
}
