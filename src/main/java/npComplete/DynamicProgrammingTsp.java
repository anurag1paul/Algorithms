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
        Map<String, Map<Integer, Double>> oldMap = new HashMap<>();
        Set<Integer> baseSet = new TreeSet<>();
        baseSet.add(0);
        Map<Integer, Double> baseValue = new HashMap<>();
        baseValue.put(0,0.0);
        oldMap.put(setToString(baseSet), baseValue);

        double minValue, newValue;

        for(int m = 2; m <= numCities; m++){

            logger.info("Currently working with m = {}", m);

            Map<String, Map<Integer, Double>> newMap = new HashMap<>();

            for(Set<Integer> set: Combinations.nChooseR(numCities-1, m-1)){
                set.add(0);
                Set<Integer> keySet = new HashSet<>(set);
                String setString = setToString(set);
                Map<Integer, Double> setInternalMap = new HashMap<>();
                for(int j: set) {
                    if(j!=0) {
                        minValue = Double.MAX_VALUE;
                        keySet.remove(j);
                        String keySetString = setToString(keySet);
                        Map<Integer, Double> internalMap = oldMap.get(keySetString);
                        if(internalMap != null) {
                            for (int k : set) {
                                newValue = internalMap.getOrDefault(k, (double) Integer.MAX_VALUE);
                                newValue += matrix[k][j];
                                minValue = Double.min(minValue, newValue);
                            }
                        }
                        keySet.add(j);
                        setInternalMap.put(j, minValue);
                    }
                }
                newMap.put(setString, setInternalMap);
            }

            oldMap = newMap;
        }

        for(int j=1; j<numCities; j++) {
            baseSet.add(j);
        }

        tourLength = Double.MAX_VALUE;
        String baseSetString = setToString(baseSet);
        Map<Integer, Double> internalMap = oldMap.get(baseSetString);
        for(int j=1; j<numCities; j++) {
            newValue = internalMap.get(j) + matrix[j][0];
            tourLength = Double.min(tourLength, newValue);
        }

        return tourLength;
    }

    private String setToString(Set<Integer> set) {
        StringBuilder builder = new StringBuilder();
        for(Integer e: set)
            builder.append(e).append(",");
        return builder.toString();
    }
}
