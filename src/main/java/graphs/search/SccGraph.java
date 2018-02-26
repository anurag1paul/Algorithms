package graphs.search;

import graphs.Edge;
import graphs.Graph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Anurag Paul
 *         Date: 7/12/17
 * Data Structure for implementing strongly connected connections
 */
public class SccGraph {

    private static final Logger logger = LoggerFactory.getLogger(SccGraph.class);

    private Graph graph;
    private int timer = 0;
    private int leader = 0;
    private int numVertices;
    private boolean[] explored;
    private Integer[] leaders;
    private int[] finishingTimes;

    public SccGraph(String file, String sep, int numVertices){

        this.numVertices = numVertices;
        graph = Graph.Builder.newInstance(numVertices).loadAdjacencyListGraphFromFile(file, sep, false).build();
    }

    public SccGraph(Graph graph) {
        this.graph = graph;
        this.numVertices = graph.getNumVertices();
    }

    private void initialise() {
        explored = new boolean[numVertices];
        leaders = new Integer[numVertices];
        finishingTimes = new int[numVertices];
        leader = 0;
    }

    public void execute(){
        logger.info("Started");
        dfsPass(false);
        logger.info("First Pass Over");
        updateGraph();
        logger.info("Graph Updated");
        dfsPass(true);
        logger.info("Finished");
    }

    private void updateGraph() {
        Graph.Builder builder = Graph.Builder.newInstance(numVertices);

        for(int i=1; i<=numVertices; i++) {
            for(Edge edge: graph.getEdges(i))
                builder.addEdge(finishingTimes[edge.src - 1], finishingTimes[edge.dst - 1], 0);
        }

        graph = builder.build();
    }

    public void dfsPass(boolean forward) {
        initialise();
        for(int i=numVertices; i>0; i--) {
            if(!explored[i-1]){
                leader = i;
                DFS(graph, i, forward);
            }
        }
    }

    private void DFS(Graph graph, int node, boolean forward) {
        explored[node-1] = true;
        leaders[node-1] = leader;

        for(Edge edge: graph.getEdges(node)){
            int dst = 0;
            if(forward && edge.src == node)
                dst = edge.dst;
            else if(!forward && edge.dst == node)
                dst = edge.src;

            if(dst > 0 && !explored[dst-1])
                DFS(graph, dst, forward);
        }

        timer++;
        finishingTimes[node-1] = timer;
    }

    public int[] getFinishingTimes() {
        return finishingTimes;
    }

    public Integer[] getLeaders() {
        return leaders;
    }

    public Map<Integer, Long> getAllScc(){
        return Arrays.stream(leaders).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
