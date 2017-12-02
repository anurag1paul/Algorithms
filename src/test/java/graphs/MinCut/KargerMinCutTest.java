package graphs.MinCut;

import org.junit.Assert;
import org.junit.Test;

public class KargerMinCutTest {

    @Test
    public void basicTest(){
        String base = "data/kargerMinCutTest";

        //test1
        KargerMinCut cutGen = new KargerMinCut(new Graph(base + "1.txt"));
        Assert.assertEquals(2, cutGen.searchMinCuts());

        //test2
        cutGen = new KargerMinCut(new Graph(base + "2.txt"));
        Assert.assertEquals(2, cutGen.searchMinCuts());

        //test3
        cutGen = new KargerMinCut(new Graph(base + "3.txt"));
        Assert.assertEquals(1, cutGen.searchMinCuts());

        //test4
        cutGen = new KargerMinCut(new Graph(base + "4.txt"));
        Assert.assertEquals(1, cutGen.searchMinCuts());

        //test5
        cutGen = new KargerMinCut(new Graph(base + "5.txt"));
        Assert.assertEquals(3, cutGen.searchMinCuts());
    }

    @Test
    public void advancedTest(){
        String file = "data/kargerMinCut.txt";

        //test1
        KargerMinCut cutGen = new KargerMinCut(new Graph(file, "\t"));
        System.out.println(String.format("Number of cuts: %d in iterations %f",
                cutGen.searchMinCuts(), cutGen.getNumIters()));
    }
}
