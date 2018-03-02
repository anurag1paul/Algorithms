package utils;

import java.util.*;

/**
 * @author Anurag Paul
 * Date: 1/3/18
 */
public class Combinations {

    public static Iterable<Set<Integer>> nChooseR(int n, int r) {
        Set<Integer> cur = new TreeSet<>();
        List<Set<Integer>> output = new LinkedList<>();
        int[] nArray = new int[n];
        for(int i=0; i<n ;i++)
            nArray[i] = i + 1;
        return createCombination(output, cur, nArray, 0, r);
    }

    private static List<Set<Integer>> createCombination(List<Set<Integer>> output, Set<Integer> cur, int[] nArray,
                                                        int index, int r) {
        if(cur.size() != r && index != nArray.length) {
            Set<Integer> newSet = new TreeSet<>(cur);
            newSet.add(nArray[index]);
            output = createCombination(output, newSet, nArray, index+1, r);
            output = createCombination(output, new HashSet<>(cur), nArray, index+1, r);
        }
        else if(cur.size() == r)
            output.add(cur);

        return output;
    }
}
