package graphs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public Set<Edge> getAllEdges() {
        return edges;
    }

    public List<Edge> getEdges(int vertex) {
        return adjacencyList.get(vertex-1);
    }

    public void printGraph(){
        System.out.println(String.format("NumNodes:%d NumEdges:%d", numVertices, numEdges));
        for (int i = 0; i < adjacencyList.size(); i++) {
            List<Edge> edges = adjacencyList.get(i);
            System.out.print((i+1) + " -> ");
            for(Edge edge : edges)
                System.out.print(edge);
            System.out.println();
        }
    }

    public static class Builder{

        private int numVertices;
        private List<List<Edge>> adjacencyList;
        private Set<Edge> edges = new HashSet<>();

        public static Builder newInstance(int numVertices){
            return new Builder(numVertices);
        }

        private Builder(int numVertices){
            this.numVertices = numVertices;
            createAdjacencyList();
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

        public Builder loadAdjacencyListGraphFromFile(String fileName, String sep) {

            try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
                String line = reader.readLine();

                while(line != null){
                    String[] elements = line.split(sep);
                    int startNode = Integer.parseInt(elements[0]);
                    for(int i=1; i< elements.length; i++) {
                        String[] weightedEdge = elements[i].split(",");
                        int endNode = Integer.parseInt(weightedEdge[0]);
                        int weight = weightedEdge.length > 1 ? Integer.parseInt(weightedEdge[1]) : 0;
                        addEdge(startNode, endNode, weight);
                    }
                    line = reader.readLine();
                }
            }
            catch (IOException e) {
                logger.error("File Load Error", e);
                System.exit(1);
            }

            return this;
        }

        public Graph build(){
            return new Graph(this);
        }
    }
}
