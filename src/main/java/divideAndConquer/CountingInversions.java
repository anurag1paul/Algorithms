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

    private final long[] input;

    public CountingInversions(long[] input){
        this.input = input;
    }

    public long getNumInversions(){
        Map<String, Object> inputMap = createInput(input, 0);
        Map<String, Object> output = sortAndCount(inputMap);
        return (long) output.get("count");
    }

    private Map<String, Object> createInput(long[] array, long count) {
        Map<String, Object> input = new HashMap<>();
        input.put("array", array);
        input.put("count", count);
        return input;
    }

    private Map<String, Object> sortAndCount(Map<String, Object> inputMap) {

        long[] input = (long[]) inputMap.get("array");

        if (input.length > 1) {
            int split = input.length / 2;
            long[] front, back;
            front = new long[split];
            back = new long[input.length - split];

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

        long[] a = (long[]) aMap.get("array");
        long[] b = (long[]) bMap.get("array");

        long count = (long) aMap.get("count") + (long) bMap.get("count");

        int len_merge = a.length + b.length;
        long[] c = new long[len_merge];

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
