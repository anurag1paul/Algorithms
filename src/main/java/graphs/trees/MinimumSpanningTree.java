package graphs.trees;

import graphs.Edge;
import graphs.Graph;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Anurag Paul
 *         Date: 19/1/18
 */
public abstract class MinimumSpanningTree {
    protected double weight;

    protected Graph graph;
    protected Set<Edge> edges = new HashSet<>();

    public double getWeight() {
        return weight;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public abstract double generateMST();
}
