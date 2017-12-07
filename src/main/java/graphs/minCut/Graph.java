package graphs.minCut;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 2/12/17
 */
public class Graph {

    private static Logger logger = LoggerFactory.getLogger(Graph.class);

    private Map<Integer, Node> nodes = new HashMap<>();
    private Set<Edge> edges = new HashSet<>();

    public Graph(){}

    public Graph(String fileName) {
        loadGraphFromFile(fileName, " ");
    }

    public Graph(String fileName, String sep) {
        loadGraphFromFile(fileName, sep);
    }

    public Graph(Graph graph) {
        for(Node node: graph.nodes.values()) {
            int nodeInt = node.node;
            addNode(nodeInt, node.connectedEdges.keySet(), false);
        }
    }

    public void addNode(int nodeInt, Collection<Integer> adjacentNodes, boolean allowDuplicates) {

        Node node;
        if(nodes.containsKey(nodeInt))
            node = nodes.get(nodeInt);
        else
            node = new Node(nodeInt, new HashMap<>());

        for(Integer adjInt: adjacentNodes){

            Node adjNode =  nodes.getOrDefault(adjInt, new Node(adjInt, new HashMap<>()));

            if(!(adjNode.connectedEdges.containsKey(nodeInt))){
                Edge newEdge = new Edge(nodeInt, adjInt);
                node.connectedEdges.put(adjInt, new LinkedList<>());
                adjNode.connectedEdges.put(nodeInt, new LinkedList<>());
                node.connectedEdges.get(adjInt).add(newEdge);
                adjNode.connectedEdges.get(nodeInt).add(newEdge);
                edges.add(newEdge);
            }
            else if(allowDuplicates) {
                Edge newEdge = new Edge(nodeInt, adjInt);
                node.connectedEdges.get(adjInt).add(newEdge);
                adjNode.connectedEdges.get(nodeInt).add(newEdge);
                edges.add(newEdge);
            }
            else {
                node.connectedEdges.put(adjInt, new LinkedList<>());
                node.connectedEdges.get(adjInt).addAll(adjNode.connectedEdges.get(nodeInt));
            }

            nodes.put(adjInt, adjNode);
        }
        nodes.put(nodeInt, node);
    }

    public Map<Integer, Node> getNodes() {
        return nodes;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    private void loadGraphFromFile(String fileName, String sep) {

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line = reader.readLine();

            while(line != null){
                String[] elements = line.split(sep);
                int startNode = Integer.parseInt(elements[0]);
                List<Integer> adjacentNodes = new ArrayList<>();
                for(int i=1; i< elements.length; i++)
                    adjacentNodes.add(Integer.parseInt(elements[i]));
                addNode(startNode, adjacentNodes, false);
                line = reader.readLine();
            }
        }
        catch (IOException e) {
            logger.error("File Load Error", e);
        }
    }

    public void removeNode(int nodeInt) {
        Node node = nodes.get(nodeInt);
        for(Map.Entry<Integer, List<Edge>> entry: node.connectedEdges.entrySet()){
            edges.removeAll(entry.getValue());
            nodes.get(entry.getKey()).connectedEdges.remove(nodeInt);
        }
        nodes.remove(nodeInt);
    }

    public void printGraph(){
        System.out.println(String.format("NumNodes:%d NumEdges:%d", nodes.size(), edges.size()));
        for(Node node: nodes.values()){
            System.out.print(node.node + "->");
            for(List<Edge> edges : node.connectedEdges.values()){
                for(Edge edge :edges)
                    System.out.print(edge);
            }
            System.out.println();
        }
    }
}
