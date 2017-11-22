package divideAndConquer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 22/11/17
 * The purpose of this class is to count the number of inversions in an input array
 * By inversion, we mean that how many smaller elements than the current element
 * occur before the current element in the array
 */
public class CountingInversions {

    private final int[] input;

    public CountingInversions(int[] input){
        this.input = input;
    }

    public int getNumInversions(){
        Map<String, Object> inputMap = createInput(input, 0);
        Map<String, Object> output = sortAndCount(inputMap);
        return (int) output.get("count");
    }

    private Map<String, Object> createInput(int[] array, int count) {
        Map<String, Object> input = new HashMap<>();
        input.put("array", array);
        input.put("count", count);
        return input;
    }

    private Map<String, Object> sortAndCount(Map<String, Object> inputMap) {

        int[] input = (int[]) inputMap.get("array");

        if (input.length > 1) {
            int split = input.length / 2;
            int[] front, back;
            front = new int[split];
            back = new int[input.length - split];

            for(int i = 0, j=0, k=0; i < input.length; i++) {
                if(i < split) {
                    front[j] = input[i];
                    j++;
                }
                else {
                    back[k] = input[i];
                    k++;
                }
            }

            return merge(sortAndCount(createInput(front, 0)), sortAndCount(createInput(back, 0)));
        }
        else
            return inputMap;
    }

    private Map<String, Object> merge(Map<String, Object> aMap, Map<String, Object> bMap){

        int[] a = (int[]) aMap.get("array");
        int[] b = (int[]) bMap.get("array");

        int count = (int) aMap.get("count") + (int) bMap.get("count");

        int len_merge = a.length + b.length;
        int[] c = new int[len_merge];

        for(int k=0, i=0, j=0; k<len_merge; k++) {

            if(i < a.length && j < b.length) {

                if(a[i] < b[j]) {
                    c[k] = a[i];
                    i++;
                }
                else {
                    c[k] = b[j];
                    j++;
                    count += (a.length - i);
                }
            }
            else if (i >= a.length) {
                c[k] = b[j];
                j++;
            }
            else {
                c[k] = a[i];
                i++;
            }
        }

        return createInput(c, count);
    }
}
