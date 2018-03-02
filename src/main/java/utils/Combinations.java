package utils;

import java.util.*;

/**
 * @author Anurag Paul
 * Date: 1/3/18
 */
public class Combinations {

    public static Iterable<Set<Integer>> nChooseR(int n, int r) {
        Set<Integer> cur = new HashSet<>();
        List<Set<Integer>> output = new LinkedList<>();
        int[] nArray = new int[n];
        for(int i=0; i<n ;i++)
            nArray[i] = i;
        return createCombination(output, cur, nArray, 0, r);
    }

    private static List<Set<Integer>> createCombination(List<Set<Integer>> output, Set<Integer> cur, int[] nArray,
                                                        int index, int r) {
        if(cur.size() != r && index != nArray.length) {
            Set<Integer> newList = new HashSet<>(cur);
            newList.add(nArray[index]);
            output = createCombination(output, newList, nArray, index+1, r);
            output = createCombination(output, new HashSet<>(cur), nArray, index+1, r);
        }
        else if(cur.size() == r)
            output.add(cur);

        return output;
    }
}
