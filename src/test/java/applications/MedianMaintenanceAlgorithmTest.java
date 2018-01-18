package applications;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 13/1/18
 */
public class MedianMaintenanceAlgorithmTest {

    private static Logger logger = LoggerFactory.getLogger(MedianMaintenanceAlgorithm.class);

    @Test
    public void test() {

        String[] fileNames = {"data/MedianTest1.txt", "data/MedianTest2.txt", "data/Median.txt"};

        int i = 0;
        for(String fileName : fileNames) {
            MedianMaintenanceAlgorithm algo = new MedianMaintenanceAlgorithm();
            List<Integer> medians = new LinkedList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line = reader.readLine();

                while (line != null) {
                    medians.add(algo.getNewMean(Integer.parseInt(line)));
                    line = reader.readLine();
                }

                int output = medians.stream().mapToInt(Integer::intValue).sum() % 10000;
                System.out.println(output);
                switch(i) {
                    case 0: Assert.assertEquals(142, output);
                            break;
                    case 1: Assert.assertEquals(9335, output);
                            break;
                }
                i++;
            } catch (IOException e) {
                logger.error("File Read Error", e);
            }
        }
    }
}
