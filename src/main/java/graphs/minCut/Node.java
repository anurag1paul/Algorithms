package graphs.minCut;

import java.util.List;
import java.util.Map;

public class Node {

    public final int node;
    public final Map<Integer, List<Edge>> connectedEdges;

    public Node(int node, Map<Integer, List<Edge>> connectedEdges){
        this.node = node;
        this.connectedEdges = connectedEdges;
    }

}
