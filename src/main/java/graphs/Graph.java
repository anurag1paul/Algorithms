package graphs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 23/6/17
 */
public class Graph {

    private static Logger logger = LoggerFactory.getLogger(Graph.class);

    final private int numVertices;
    final private int numEdges;
    final private Map<Edge, Integer> edges;

    private Graph(Builder builder){
        numVertices = builder.numVertices;
        numEdges = builder.edges.size();
        edges = builder.edges;
    }

    public int getNumEdges() {
        return numEdges;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public Map<Edge, Integer> getEdges() {
        return edges;
    }

    public void printGraph(){
        System.out.println(String.format("NumNodes:%d NumEdges:%d", numVertices, numEdges));
        for(Edge edge: edges.keySet()){
            System.out.println(edge);
        }
    }

    public static class Builder{

        int numVertices;
        Map<Edge, Integer> edges = new HashMap<>();

        public static Builder newInstance(int numVertices){
            return new Builder(numVertices);
        }

        public static Builder newInstance(String fileName, String sep){
            return new Builder(fileName, sep);
        }

        private Builder(int numVertices){
            this.numVertices = numVertices;
        }

        private Builder(String fileName, String sep) {
            numVertices = 0;
            loadAdjacencyListGraphFromFile(fileName, sep);
        }

        public void addEdge(int start, int end, int weight){
            edges.put(new Edge(start, end), weight);
        }

        private void loadAdjacencyListGraphFromFile(String fileName, String sep) {

            try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
                String line = reader.readLine();

                while(line != null){
                    String[] elements = line.split(sep);
                    int startNode = Integer.parseInt(elements[0]);
                    for(int i=1; i< elements.length; i++) {
                        int endNode = Integer.parseInt(elements[i]);
                        addEdge(startNode, endNode, 0);
                    }
                    line = reader.readLine();
                    numVertices++;
                }
            }
            catch (IOException e) {
                logger.error("File Load Error", e);
            }
        }

        public Graph build(){
            return new Graph(this);
        }
    }
}
