package graphs.MinCut;

import graphs.MinCut.Graph;
import org.junit.Test;

public class GraphTest {

    @Test
    public void addEdgeTest(){
        Graph graph = new Graph("data/kargerMinCutTest1.txt");
        graph.printGraph();
    }
}
