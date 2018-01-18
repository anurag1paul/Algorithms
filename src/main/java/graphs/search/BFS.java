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
public class BFS {

    public List<Integer> genericBFS(Graph graph, int startVertex){

        Queue<Integer> toBeVisited = new LinkedList<>();
        toBeVisited.offer(startVertex);

        boolean[] explored = new boolean[graph.getNumVertices()];
        List<Integer> exploredList = new ArrayList<>();

        while(!toBeVisited.isEmpty()) {

            int vertex = toBeVisited.poll();

            for(Edge edge: graph.getEdges(vertex)){

                int newVertex = edge.dst;

                if(!explored[newVertex]) {
                    toBeVisited.offer(newVertex);
                    explored[newVertex] = true;
                    exploredList.add(newVertex);
                }
            }
        }

        return exploredList;
    }
}
