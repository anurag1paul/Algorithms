package sets;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 23/6/17
 */
public class DisjointSetTest {

    @Test
    public void test(){
        DisjointSet set = new DisjointSet(6);
        set.union(1,2);
        Assert.assertEquals(1, set.find(1));
        Assert.assertEquals(1, set.find(2));

        set.union(3,4);
        Assert.assertEquals(3, set.find(3));
        Assert.assertEquals(3, set.find(4));

        set.union(4,5);
        Assert.assertEquals(3, set.find(4));
        Assert.assertEquals(3, set.find(5));

        set.union(2,5);
        Assert.assertEquals(1, set.find(2));
        Assert.assertEquals(1, set.find(5));
    }
}
