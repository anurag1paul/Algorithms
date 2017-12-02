package graphs.trees;

import graphs.WeightedGraph;
import org.junit.Test;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 23/6/17
 */
public class MinimumSpanningTreeTest {

    @Test
    public void test() {
        //https://upload.wikimedia.org/wikipedia/commons/thumb/8/87/Kruskal_Algorithm_6.svg/200px-Kruskal_Algorithm_6.svg.png
        WeightedGraph.Builder builder = WeightedGraph.Builder.newInstance(7, 11);
        builder.addEdge(0, 1, 7); //AB -7
        builder.addEdge(0, 3, 5); //AD - 5
        builder.addEdge(3, 1, 9); //DB - 9
        builder.addEdge(1, 2, 8); //BC -8
        builder.addEdge(3, 4, 15);//DE - 15
        builder.addEdge(4, 5, 8);//EF -8
        builder.addEdge(3, 5, 6);//DF -6
        builder.addEdge(5, 6, 11);//FG - 11
        builder.addEdge(6, 4, 9);//EG -9
        builder.addEdge(1, 4, 7);//BE - 7
        builder.addEdge(4, 2, 5);//EC- 5

        WeightedGraph graph = builder.build();

        MinimumSpanningTree tree = new MinimumSpanningTree(graph);
        System.out.println(tree.getWeight());

    }
}
