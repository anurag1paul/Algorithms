package graphs.search;

import graphs.Graph;
import org.junit.Test;

import java.util.List;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 2/1/18
 */
public class BFSTest {

    @Test
    public void basicTest(){
        Graph.Builder builder = Graph.Builder.newInstance(6);
        builder.addEdge(6, 1, 0);
        builder.addEdge(6, 2, 0);
        builder.addEdge(1, 3, 0);
        builder.addEdge(2, 3, 0);
        builder.addEdge(2, 4, 0);
        builder.addEdge(3, 4, 0);
        builder.addEdge(3, 5, 0);
        builder.addEdge(4, 5, 0);
        Graph graph = builder.build();

        BFS bfs = new BFS();
        List<Integer> explored =  bfs.genericBFS(graph, 6);
        for(Integer v :explored)
            System.out.print(v + ",");
    }
}
