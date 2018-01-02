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
    final private List<List<Edge>> adjacencyList;
    final private Set<Edge> edges;

    private Graph(Builder builder){
        numVertices = builder.numVertices;
        numEdges = builder.edges.size();
        adjacencyList = builder.adjacencyList;
        edges = builder.edges;
    }

    public int getNumEdges() {
        return numEdges;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public List<List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public void printGraph(){
        System.out.println(String.format("NumNodes:%d NumEdges:%d", numVertices, numEdges));
        for (int i = 0; i < adjacencyList.size(); i++) {
            List<Edge> edges = adjacencyList.get(i);
            System.out.println(i + " -> ");
            for(Edge edge : edges)
                System.out.print(edge);
        }
    }

    public static class Builder{

        int numVertices;
        List<List<Edge>> adjacencyList;
        Set<Edge> edges = new HashSet<>();

        public static Builder newInstance(int numVertices){
            return new Builder(numVertices);
        }

        public static Builder newInstance(String fileName, String sep, int numVertices){
            return new Builder(fileName, sep, numVertices);
        }

        private Builder(int numVertices){
            this.numVertices = numVertices;
            createAdjacencyList();
        }

        private Builder(String fileName, String sep, int numVertices) {
            this.numVertices = numVertices;
            createAdjacencyList();
            loadAdjacencyListGraphFromFile(fileName, sep);
        }

        private void createAdjacencyList() {
            adjacencyList = new ArrayList<>();
            for(int i=0; i<numVertices; i++){
                adjacencyList.add(new ArrayList<>());
            }
        }

        public void addEdge(int start, int end, int weight){
            Edge edge = new Edge(start, end, weight);
            adjacencyList.get(start-1).add(edge);
            adjacencyList.get(end-1).add(edge);
            edges.add(edge);
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
