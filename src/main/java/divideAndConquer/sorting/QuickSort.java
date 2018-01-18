package divideAndConquer.sorting;

import static divideAndConquer.sorting.QuickSort.PivotType.FIRST;
import static utils.utils.swap;

/**
 * @author Anurag Paul
 *         Date: 13/1/18
 */
public class QuickSort {

    public static enum PivotType{
        FIRST, LAST, MEDIAN
    }

    private long numComparisons = 0;
    private PivotType type;

    public QuickSort(PivotType type){
        this.type = type;
    }

    public long getNumComparisons() {
        return numComparisons;
    }

    public int[] sort(int[] input) {
        numComparisons = 0;
        return sort(input, 0, input.length);
    }

    public void setType(PivotType type) {
        this.type = type;
    }

    private int[] sort(int[] array, int start, int end){

        int len = end - start;

        if(len <= 1)
            return array;

        numComparisons += (len-1);
        int pivotPosition = getPivotPosition(array, start, end);

        if(type != FIRST) {
            array = swap(array, pivotPosition, start);
        }

        int pivot = array[start];

        pivotPosition = start + 1;
        boolean seenGreater = false;
        for(int j = start + 1; j < end; j++){
            if(array[j] < pivot){
                if(seenGreater)
                    array = swap(array, j, pivotPosition);
                pivotPosition++;
            } else
                seenGreater= true;
        }
        array = swap(array, start, pivotPosition - 1);

        // sort left part
        array = sort(array, start, pivotPosition - 1);
        // sort right part
        array = sort(array, pivotPosition, end);
        return array;
    }

    private int getPivotPosition(int[] array, int start, int end) {
        int pivotPosition;

        switch (type) {
            case FIRST: pivotPosition = start;
                break;
            case LAST: pivotPosition = end -1;
                break;
            case MEDIAN: pivotPosition = getMedianPivotPosition(array, start, end);
                break;
            default: throw new IllegalArgumentException();
        }

        return pivotPosition;
    }

    private int getMedianPivotPosition(int[] array, int start, int end){
        int pivotPosition;

        int startVal = array[start];
        int endVal = array[end-1];
        int len = end-start;

        int factor = len%2==0?(len/2)-1:len/2;
        int midPos = start + factor;
        int midVal = array[midPos];

        int max = Math.max(Math.max(startVal, endVal), midVal);
        int min = Math.min(Math.min(startVal, endVal), midVal);

        if(startVal != max && startVal != min)
            pivotPosition = start;
        else if(endVal != max && endVal != min)
            pivotPosition = end-1;
        else
            pivotPosition = midPos;

        return pivotPosition;
    }
}
