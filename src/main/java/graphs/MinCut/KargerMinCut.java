package graphs.MinCut;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.List;

public class KargerMinCut {

    private Graph initialGraph;
    private double numIters;
    private Logger logger = LoggerFactory.getLogger(KargerMinCut.class);

    public KargerMinCut(Graph graph){
        initialGraph = graph;
        int nodes = graph.getNodes().size();
        numIters = Math.pow(nodes, 2) * Math.log(nodes);
    }

    public double getNumIters() {
        return numIters;
    }

    public int generateMinCut(Random random) {

        Graph graph = new Graph(initialGraph);
        int nNodes = graph.getNodes().size() + 1;

        while(graph.getNodes().size() > 2) {

            List<Edge> edges = new LinkedList<>();
            edges.addAll(graph.getEdges());
            Edge collapseEdge = edges.get(random.nextInt(edges.size()));

            List<Integer> adjacentNodes = new ArrayList<>();
            edges.remove(collapseEdge);

            for(Edge edge: edges){
                if((collapseEdge.src == edge.src && collapseEdge.dst != edge.dst) ||
                        (collapseEdge.dst == edge.src && collapseEdge.src != edge.dst))
                    adjacentNodes.add(edge.dst);
                else if ((collapseEdge.src == edge.dst && collapseEdge.dst != edge.src)||
                            (collapseEdge.dst == edge.dst && collapseEdge.src != edge.src))
                    adjacentNodes.add(edge.src);
            }

            graph.addNode(nNodes++, adjacentNodes, true);
            graph.removeNode(collapseEdge.src);
            graph.removeNode(collapseEdge.dst);
        }

        return graph.getEdges().size();
    }

    public int searchMinCuts() {
        int minCut = Integer.MAX_VALUE;
        for(int i=0; i<numIters; i++) {
            minCut = Math.min(minCut, generateMinCut(new Random(i)));
            logger.info("Iter:{} MinCut:{}", i, minCut);
        }
        return minCut;
    }
}
