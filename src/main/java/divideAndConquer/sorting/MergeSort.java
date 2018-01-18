package divideAndConquer.sorting;

/**
 * @author Anurag Paul
 *         Date: 14/11/17
 *         Runtime: O(n log(n))
 */
public class MergeSort {

    private static int[] merge(int[] a, int[] b){

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

        return c;
    }

    public static int[] sort(int[] input) {
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

            return merge(sort(front), sort(back));
        }
        else
            return input;
    }
}
