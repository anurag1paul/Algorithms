package npComplete;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Combinations;

import java.util.*;

/**
 * @author Anurag Paul
 * Date: 1/3/18
 */
public class DynamicProgrammingTsp extends TravelingSalesmanProblem {

    private static Logger logger = LoggerFactory.getLogger(DynamicProgrammingTsp.class);

    public DynamicProgrammingTsp(String filename, String sep) {
        super(filename, sep);
    }

    @Override
    public double getTour() {
        double tourLength;

        Map<AbstractMap.SimpleEntry<Set<Integer>, Integer>, Double> oldMap = new HashMap<>();
        Set<Integer> baseSet = new HashSet<>();
        baseSet.add(0);
        oldMap.put(new AbstractMap.SimpleEntry<>(baseSet, 0), 0.0);

        double minValue, newValue;

        for(int m = 2; m <= numCities; m++){

            logger.info("Currently working with m = {}", m);

            Map<AbstractMap.SimpleEntry<Set<Integer>, Integer>, Double> newMap = new HashMap<>();

            for(Set<Integer> set: Combinations.nChooseR(numCities, m)){

                if(set.contains(0)) {

                    for(int j: set) {

                        if(j!=0) {
                            minValue = Double.MAX_VALUE;
                            for(int k : set) {
                                Set<Integer> keySet = new HashSet<>(set);
                                keySet.remove(j);
                                newValue = oldMap.getOrDefault(new AbstractMap.SimpleEntry<>(keySet, k), (double) Integer.MAX_VALUE);
                                newValue += matrix[k][j];
                                minValue = Double.min(minValue, newValue);
                            }
                            newMap.put(new AbstractMap.SimpleEntry<>(set, j), minValue);
                        }
                    }
                }
            }

            oldMap = newMap;
        }

        for(int j=1; j<numCities; j++) {
            baseSet.add(j);
        }

        tourLength = Double.MAX_VALUE;
        for(int j=1; j<numCities; j++) {
            newValue = oldMap.get(new AbstractMap.SimpleEntry<>(baseSet, j)) + matrix[j][0];
            tourLength = Double.min(tourLength, newValue);
        }

        return tourLength;
    }
}
