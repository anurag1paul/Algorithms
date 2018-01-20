package applications;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author Anurag Paul
 *         Date: 20/1/18
 */
public class HuffmanCodes {

    private static final Logger logger = LoggerFactory.getLogger(HuffmanCodes.class);

    private static class Node implements Comparable<Node>{
        private final int weight;
        private final String id;
        private Node left;
        private Node right;
        private Set<String> members = new HashSet<>();

        public Node(String id, int weight) {
            this.id = id;
            this.weight = weight;
            members.add(id);
        }

        public boolean isLeaf(){
            return (left==null) && (right==null);
        }

        @Override
        public int compareTo(Node node) {
            return Integer.compare(weight, node.weight);
        }

        public void setLeft(Node left) {
            this.left = left;
            members.addAll(left.members);
        }

        public void setRight(Node right) {
            this.right = right;
            members.addAll(right.members);
        }

        public int getWeight() {
            return weight;
        }

        public String getId() {
            return id;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }
    }

    private int totalNodes;
    private List<Node> data;
    private Node root;
    private String[] encodings;
    private List<Integer> encodingsSize;

    public HuffmanCodes(String fileName) {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line = reader.readLine();
            totalNodes = Integer.parseInt(line);
            data = new LinkedList<>();
            line = reader.readLine();
            Integer id = 0;
            while(line != null) {
                int weight = Integer.parseInt(line);
                data.add(new Node(id.toString(), weight));
                id++;
                line = reader.readLine();
            }
        }catch (IOException e) {
            logger.error("File Read Error", e);
        }
    }

    public void execute(){
        mergePhase();
        generateEncodings();
    }

    private void mergePhase(){
        Queue<Node> first = new LinkedList<>();
        Queue<Node> second = new LinkedList<>();
        Collections.sort(data);
        first.addAll(data);

        while(!first.isEmpty() || !second.isEmpty()) {
            if((first.size() + second.size()) > 1) {
                Node right = getMinNode(first, second);
                Node left = getMinNode(first, second);
                Node merged = new Node(left.getId() + "-" + right.getId(), left.getWeight() + right.getWeight());
                merged.setLeft(left);
                merged.setRight(right);
                second.offer(merged);
            }
            else
                root = getMinNode(first, second);
        }
    }

    private Node getMinNode(Queue<Node> first, Queue<Node> second) {
        if(first.isEmpty())
            return second.poll();
        else if(second.isEmpty())
            return first.poll();
        else {
            if(first.peek().getWeight() < second.peek().getWeight())
                return first.poll();
            else
                return second.poll();
        }

    }

    private void generateEncodings(){

        encodings = new String[totalNodes];
        encodingsSize = new LinkedList<>();

        for(int i=0; i <totalNodes; i++) {
            String id = Integer.toString(i);
            StringBuilder encodingBuilder = new StringBuilder();
            Node node = root;
            while(!node.getId().equals(id) && node.members.contains(id)){
                Node left = node.getLeft();
                Node right = node.getRight();
                if(left.members.contains(id)) {
                    encodingBuilder.append("0");
                    node = left;
                }else {
                    encodingBuilder.append("1");
                    node = right;
                }
            }
            encodings[i] = encodingBuilder.toString();
            encodingsSize.add(encodings[i].length());
        }
    }

    public String[] getEncodings() {
        return encodings;
    }

    public int getMaxEncoding(){
        return Collections.max(encodingsSize);
    }

    public int getMinEncoding(){
        return Collections.min(encodingsSize);
    }
}
