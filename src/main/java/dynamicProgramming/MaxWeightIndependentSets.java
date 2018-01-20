package dynamicProgramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static sun.swing.MenuItemLayoutHelper.max;

/**
 * @author Anurag Paul
 *         Date: 20/1/18
 */
public class MaxWeightIndependentSets {

    private static Logger logger = LoggerFactory.getLogger(MaxWeightIndependentSets.class);

    private int numNodes;
    private long[] data;
    private byte[] output;
    private long weight = 0;

    public MaxWeightIndependentSets(String fileName) {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();

            numNodes = Integer.parseInt(line);
            line = reader.readLine();
            data = new long[numNodes];

            int i = 0;
            while(line!=null) {
                data[i++] = Long.parseLong(line);
                line = reader.readLine();
            }
        }catch (IOException e) {
            logger.error("File Read Error", e);
        }
    }

    public long execute(){
        long[] tracker = new long[numNodes + 1];
        tracker[0] = 0;
        tracker[1] = data[0];

        for(int i=2; i<= numNodes; i++) {
            tracker[i] = Math.max(tracker[i-1], tracker[i-2] + data[i-1]);
        }

        int i = numNodes;
        weight = tracker[i];

        output = new byte[numNodes];

        while(i>=1) {

            long option1 = tracker[i-1];
            long option2 = 0;

            if(i == 1)
                option2 = data[0];
            else
                option2 = tracker[i-2] + data[i-1];

            if(option1 >= option2)
                i--;
            else {
                output[i-1] = 1;
                i -= 2;
            }
        }

        return weight;
    }

    public byte[] getOutput() {
        return output;
    }
}
