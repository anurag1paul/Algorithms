package graphs.search;

import graphs.Edge;
import graphs.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Anurag Paul
 *         Date: 2/1/18
 */
public class ConnectedComponents {

    private final Graph graph;
    private final int numVertices;

    public ConnectedComponents(Graph graph) {
        this.graph = graph;
        this.numVertices = graph.getNumVertices();
    }

    public int getNumConnectedComponents(){

        Queue<Integer> toBeVisited = new LinkedList<>();
        boolean[] explored = new boolean[numVertices + 1];
        int count = 0;

        for(int i=1 ; i<=numVertices; i++) {
            if(!explored[i]) {
                toBeVisited.offer(i);
                count++;
            }

            while (!toBeVisited.isEmpty()) {

                int vertex = toBeVisited.poll();

                for (Edge edge : graph.getEdges(vertex)) {
                    int newVertex = edge.dst;
                    if(vertex == edge.dst)
                        newVertex = edge.src;

                    if (!explored[newVertex]) {
                        toBeVisited.offer(newVertex);
                        explored[newVertex] = true;
                    }
                }
            }
        }

        return count;
    }
}
