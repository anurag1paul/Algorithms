package graphs.trees;

import graphs.Graph;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Anurag Paul
 *         Date: 18/1/18
 */
public class PrimsMSTAlgoTest {

    @Test
    public void test() {
        //https://upload.wikimedia.org/wikipedia/commons/thumb/8/87/Kruskal_Algorithm_6.svg/200px-Kruskal_Algorithm_6.svg.png
        Graph.Builder builder = Graph.Builder.newInstance(7);
        builder.addEdge(1, 2, 7); //AB -7
        builder.addEdge(1, 4, 5); //AD - 5
        builder.addEdge(4, 2, 9); //DB - 9
        builder.addEdge(2, 3, 8); //BC -8
        builder.addEdge(4, 5, 15);//DE - 15
        builder.addEdge(5, 6, 8);//EF -8
        builder.addEdge(4, 6, 6);//DF -6
        builder.addEdge(6, 7, 11);//FG - 11
        builder.addEdge(7, 5, 9);//EG -9
        builder.addEdge(2, 5, 7);//BE - 7
        builder.addEdge(5, 3, 5);//EC- 5

        Graph graph = builder.build();

        MinimumSpanningTree tree = new PrimsMSTAlgo(graph);
        tree.generateMST();
        Assert.assertEquals(39, tree.getWeight(), 0.00001);
    }

    @Test
    public void advancedTest(){
        String fileName = "data/edges.txt";
        String sep = " ";
        int numVertices = 500;
        int numEdges = 2184;

        Graph graph = Graph.Builder.newInstance(numVertices).loadEdgesListGraphFromFile(fileName, sep, false).build();
        MinimumSpanningTree tree = new PrimsMSTAlgo(graph);
        double weight = tree.generateMST();
        Assert.assertEquals(-3612829.0, weight, 0.00001);
    }
}
