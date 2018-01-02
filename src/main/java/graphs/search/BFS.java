package graphs.search;

import graphs.Edge;
import graphs.Graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 2/1/18
 */
public class BFS {

    public List<Integer> genericBFS(Graph graph, int startVertex){

        Queue<Integer> toBeVisited = new LinkedList<>();
        toBeVisited.offer(startVertex);

        List<Integer> explored = new LinkedList<>();
        explored.add(startVertex);

        while(!toBeVisited.isEmpty()) {

            int vertex = toBeVisited.poll();

            for(Edge edge: graph.getEdges(vertex)){

                int newVertex = edge.dst;

                if(!explored.contains(newVertex)) {
                    toBeVisited.offer(newVertex);
                    explored.add(newVertex);
                }
            }
        }

        return explored;
    }
}
