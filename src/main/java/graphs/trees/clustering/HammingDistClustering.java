package graphs.trees.clustering;

import graphs.Graph;
import graphs.search.ConnectedComponents;
import graphs.search.SccGraph;
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
public class HammingDistClustering {

    private static final Logger logger = LoggerFactory.getLogger(HammingDistClustering.class);
    private final int maxDistance;
    private int numVertices;
    private int numBits;
    private Map<String, Integer> data = new HashMap<>();
    private Graph graph;

    public HammingDistClustering(String fileName, int maxDistance){

        this.maxDistance = maxDistance;

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line = reader.readLine();
            String[] elements = line.split(" ");
            numVertices = Integer.parseInt(elements[0]);
            numBits = Integer.parseInt(elements[1]);

            line = reader.readLine();
            int i=1;
            while (line != null) {
                line  = line.replace(" ", "");
                if(!data.containsKey(line))
                    data.put(line, i++);
                line = reader.readLine();
            }

            //update numVertices
            numVertices = data.size();
        }catch (IOException e) {
            logger.error("File Read Error", e);
        }

        createGraph();
    }

    private void createGraph(){

        Graph.Builder builder = Graph.Builder.newInstance(numVertices);
        for(Map.Entry<String, Integer> vertex : data.entrySet()){

            String bitVertex = vertex.getKey();
            int vertIndex = vertex.getValue();

            for(int i=0; i<numBits; i++) {
                StringBuilder dst1 = new StringBuilder();
                dst1.append(bitVertex);
                dst1 = update(dst1, bitVertex, i);

                String dst1Str = dst1.toString();
                if(data.containsKey(dst1Str))
                    builder.addEdge(vertIndex, data.get(dst1Str), 1);

                for(int j=0; j<numBits; j++) {
                    StringBuilder dst2 = new StringBuilder();
                    dst2.append(bitVertex);
                    if(j!=i){
                        dst2 = update(dst2, bitVertex, i);
                        dst2 = update(dst2, bitVertex, j);
                        String dst2Str = dst2.toString();
                        if(data.containsKey(dst2Str))
                            builder.addEdge(vertIndex, data.get(dst2Str), 2);
                    }
                }
            }
        }
        this.graph = builder.build();
    }

    private StringBuilder update(StringBuilder builder, String text, int pos) {
        String update = "0";
        if(text.charAt(pos) == '0')
            update = "1";
        builder.replace(pos, pos+1, update);
        return builder;
    }

    public int getNumClusters(){
        ConnectedComponents cc = new ConnectedComponents(graph);
        return cc.getNumConnectedComponents();
    }
}
