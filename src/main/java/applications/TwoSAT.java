package applications;

import graphs.Graph;
import graphs.search.SccGraph;
import npComplete.TravelingSalesmanProblem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Anurag Paul
 * Date: 10/3/18
 */
public class TwoSAT {

    private static Logger logger = LoggerFactory.getLogger(TwoSAT.class);

    private int numVariables;
    private Graph implicationGraph;

    public TwoSAT(String fileName, String sep) {

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            numVariables = Integer.parseInt(line);
            Graph.Builder builder = Graph.Builder.newInstance(2 * numVariables);
            line = reader.readLine();
            int var1, var2, notVar1, notVar2;

            while(line != null){
                String[] elements = line.split(sep);
                var1 = Integer.parseInt(elements[0]);
                var2 = Integer.parseInt(elements[1]);
                if(var1 > 0)
                    notVar1 = var1 + numVariables;
                else {
                    notVar1 = -1 * var1;
                    var1 = notVar1 + numVariables;
                }
                if(var2 > 0)
                    notVar2 = var2 + numVariables;
                else {
                    notVar2 = -1 * var2;
                    var2 = notVar2 + numVariables;
                }
                builder.addEdge(notVar1, var2, 0);
                builder.addEdge(notVar2, var1, 0);
                line = reader.readLine();
            }

            implicationGraph = builder.build();
        }catch (IOException e) {
            logger.error("File Load Error", e);
            System.exit(1);
        }
    }

    public boolean isSatisfiable() {
        SccGraph scc = new SccGraph(implicationGraph);
        scc.execute();
        int[] leaders = scc.getLeaders();

        boolean isSatisfiable = true;

        for(int i=1; i<=numVariables; i++) {
            if(leaders[i-1] == leaders[i + numVariables -1]) {
                isSatisfiable = false;
                break;
            }
        }

        return isSatisfiable;
    }
}
