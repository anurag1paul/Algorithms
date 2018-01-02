package graphs.minCut;

import graphs.Graph;
import org.junit.Assert;
import org.junit.Test;

public class KargerMinCutTest {

    @Test
    public void basicTest(){
        String base = "data/kargerMinCutTest";

        //test1
        Graph graph = Graph.Builder.newInstance(base + "1.txt", " ", 8).build();
        KargerMinCut cutGen = new KargerMinCut(graph);
        Assert.assertEquals(2, cutGen.searchMinCuts());

        //test2
        cutGen = new KargerMinCut(Graph.Builder.newInstance(base + "2.txt", " ", 8).build());
        Assert.assertEquals(2, cutGen.searchMinCuts());

        //test3
        cutGen = new KargerMinCut(Graph.Builder.newInstance(base + "3.txt", " ", 8).build());
        Assert.assertEquals(1, cutGen.searchMinCuts());

        //test4
        cutGen = new KargerMinCut(Graph.Builder.newInstance(base + "4.txt", " ", 8).build());
        Assert.assertEquals(1, cutGen.searchMinCuts());

        //test5
        cutGen = new KargerMinCut(Graph.Builder.newInstance(base + "5.txt", " ", 40).build());
        Assert.assertEquals(3, cutGen.searchMinCuts());
    }

    @Test
    public void advancedTest(){
        String file = "data/kargerMinCut.txt";

        //test1
        KargerMinCut cutGen = new KargerMinCut(Graph.Builder.newInstance(file, "\t", 200).build());
        System.out.println(String.format("Number of cuts: %d in iterations %f",
                cutGen.searchMinCuts(), cutGen.getNumIters()));
    }
}
