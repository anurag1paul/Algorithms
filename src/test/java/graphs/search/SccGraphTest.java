package graphs.search;

import graphs.Graph;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class SccGraphTest {

    private Graph getGraph() {
        Graph.Builder builder = Graph.Builder.newInstance(9);
        builder.addEdge(7,1, 0);
        builder.addEdge(4,7, 0);
        builder.addEdge(1,4, 0);

        builder.addEdge(9,7, 0);

        builder.addEdge(6,9, 0);
        builder.addEdge(3,6, 0);
        builder.addEdge(9,3, 0);

        builder.addEdge(8,6, 0);

        builder.addEdge(2,8, 0);
        builder.addEdge(5,2, 0);
        builder.addEdge(8,5, 0);

        return builder.build();
    }

    @Test
    public void onePassTest(){

        SccGraph sccGraph = new SccGraph(getGraph());

        sccGraph.dfsPass(false);
        int[] expected = {7,3,1,8,2,5,9,4,6};
        int[] actual = sccGraph.getFinishingTimes();

        for (int i = 0; i < actual.length; i++)
            Assert.assertEquals(expected[i], actual[i]);
    }

    @Test
    public void basicTest(){

        SccGraph sccGraph = new SccGraph(getGraph());

        sccGraph.execute();
        Map<Integer, Long> expected = new HashMap<>();
        expected.put(9, 3L);
        expected.put(6,3L);
        expected.put(4, 3L);

        for(Map.Entry<Integer, Long> entry: sccGraph.getAllScc().entrySet()){
            Assert.assertEquals(expected.get(entry.getKey()), entry.getValue());
        }
    }

    @Test
    public void advancedTest(){
        SccGraph sccGraph = new SccGraph("data/SCC.txt", " ", 875714);
        sccGraph.execute();
        Map<Integer, Long> allScc = sccGraph.getAllScc();
        for(Map.Entry<Integer, Long> entry: allScc.entrySet()){
            System.out.println(entry.getKey() + "-> " + entry.getValue());
        }

        List<Long> scc = new LinkedList<>();
        scc.addAll(allScc.values());
        scc.sort(Collections.reverseOrder());
        for(int i=0; i<5; i++)
            System.out.print(scc.get(i) + ",");
    }
}
