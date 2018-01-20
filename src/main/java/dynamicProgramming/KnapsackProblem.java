package dynamicProgramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.lang.Math.max;

/**
 * @author Anurag Paul
 *         Date: 21/1/18
 */
public class KnapsackProblem {

    private static final Logger logger = LoggerFactory.getLogger(KnapsackProblem.class);

    private long knapsackCapacity;
    private int numItems;
    private List<Item> items = new ArrayList<>();

    private static class Item {

        final long value;
        final int volume;

        public Item(int value, int volume) {
            this.value = value;
            this.volume = volume;
        }
    }

    public KnapsackProblem(String fileName) {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            String[] elements = line.split(" ");
            knapsackCapacity = Long.parseLong(elements[0]);
            numItems = Integer.parseInt(elements[1]);

            line = reader.readLine();
            while(line != null) {
                elements = line.split(" ");
                items.add(new Item(Integer.parseInt(elements[0]), Integer.parseInt(elements[1])));
                line = reader.readLine();
            }
        }catch (IOException e) {
            logger.error("File Read Error", e);
        }
    }

    public long execute(){
        Map<Long, Long> tracker = new HashMap<>();

        for(int i=0; i<numItems; i++) {
            Item item = items.get(i);

            for(long x=knapsackCapacity; x>0; x--) {
                if(x > item.volume) {
                    long previousValue = tracker.getOrDefault(x, 0L);
                    long newPossibleValue = tracker.getOrDefault(x - item.volume, 0L) + item.value;
                    if(previousValue < newPossibleValue)
                        tracker.put(x, newPossibleValue);
                }
            }
        }

        return tracker.get(knapsackCapacity);
    }
}
