package graphs.minCut;

import graphs.Graph;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Anurag Paul
 *         Date: 13/1/18
 */
public class KargerMinCutTest {

    @Test
    public void basicTest(){
        String base = "data/kargerMinCutTest";

        //test1
        Graph graph = Graph.Builder.newInstance(8)
                                    .loadAdjacencyListGraphFromFile(base + "1.txt", " ")
                                    .build();
        KargerMinCut cutGen = new KargerMinCut(graph);
        Assert.assertEquals(2, cutGen.searchMinCuts());

        //test2
        graph = Graph.Builder.newInstance(8)
                .loadAdjacencyListGraphFromFile(base + "2.txt", " ")
                .build();
        cutGen = new KargerMinCut(graph);
        Assert.assertEquals(2, cutGen.searchMinCuts());

        //test3
        graph = Graph.Builder.newInstance(8)
                .loadAdjacencyListGraphFromFile(base + "3.txt", " ")
                .build();
        cutGen = new KargerMinCut(graph);
        Assert.assertEquals(1, cutGen.searchMinCuts());

        //test4
        graph = Graph.Builder.newInstance(8)
                .loadAdjacencyListGraphFromFile(base + "4.txt", " ")
                .build();
        cutGen = new KargerMinCut(graph);
        Assert.assertEquals(1, cutGen.searchMinCuts());

        //test5
        graph = Graph.Builder.newInstance(40)
                .loadAdjacencyListGraphFromFile(base + "5.txt", " ")
                .build();
        cutGen = new KargerMinCut(graph);
        Assert.assertEquals(3, cutGen.searchMinCuts());
    }

    @Test
    public void advancedTest(){
        String file = "data/kargerMinCut.txt";

        //test1
        Graph graph = Graph.Builder.newInstance(200)
                .loadAdjacencyListGraphFromFile(file, "\t")
                .build();
        KargerMinCut cutGen = new KargerMinCut(graph);
        System.out.println(String.format("Number of cuts: %d in iterations %f",
                cutGen.searchMinCuts(), cutGen.getNumIters()));
    }
}
