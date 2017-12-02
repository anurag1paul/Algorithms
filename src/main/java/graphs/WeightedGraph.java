package graphs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 23/6/17
 */
public class WeightedGraph {

    private static Logger logger = LoggerFactory.getLogger(WeightedGraph.class);

    final private int numVertices;
    final private int numEdges;
    final private Map<AbstractMap.SimpleImmutableEntry<Integer, Integer>, Integer> edges;

    private WeightedGraph(Builder builder){
        numVertices = builder.numVertices;
        numEdges = builder.numEdges;
        edges = builder.edges;
    }

    public int getNumEdges() {
        return numEdges;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public Map<AbstractMap.SimpleImmutableEntry<Integer, Integer>, Integer> getEdges() {
        return edges;
    }

    public static class Builder{

        int numVertices;
        int numEdges;
        Map<AbstractMap.SimpleImmutableEntry<Integer, Integer>, Integer> edges = new HashMap<>();

        public static Builder newInstance(int numVertices, int numEdges){
            return new Builder(numVertices, numEdges);
        }

        private Builder(int numVertices, int numEdges){
            this.numVertices = numVertices;
            this.numEdges = numEdges;
        }

        public void addEdge(int start, int end, int weight){
            if(start <= numVertices && end <= numVertices && edges.size() < numEdges)
                edges.put(new AbstractMap.SimpleImmutableEntry<>(start, end), weight);
            else
                logger.warn("Edge not added: start:{} end:{}", start, end);
        }

        public WeightedGraph build(){
            if(edges.size() == numEdges)
                return new WeightedGraph(this);
            else
                throw new IllegalStateException("Edges added are less than numEdges");
        }
    }
}
