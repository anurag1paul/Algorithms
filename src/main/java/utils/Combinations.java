package utils;

import java.util.*;

/**
 * @author Anurag Paul
 * Date: 1/3/18
 */
public class Combinations {

    public static Iterable<List<Integer>> nChooseR(int n, int r) {
        List<Integer> cur = new ArrayList<>();
        List<List<Integer>> output = new LinkedList<>();
        int[] nArray = new int[n];
        for(int i=0; i<n ;i++)
            nArray[i] = i+1;
        return createCombination(output, cur, nArray, 0, r);
    }

    private static List<List<Integer>> createCombination(List<List<Integer>> output, List<Integer> cur, int[] nArray, int index, int r) {
        if(cur.size() != r && index != nArray.length) {
            List<Integer> newList = new ArrayList<>(cur);
            newList.add(nArray[index]);
            output = createCombination(output, newList, nArray, index+1, r);
            output = createCombination(output, new ArrayList<>(cur), nArray, index+1, r);
        }
        else if(cur.size() == r)
            output.add(cur);

        return output;
    }
}
