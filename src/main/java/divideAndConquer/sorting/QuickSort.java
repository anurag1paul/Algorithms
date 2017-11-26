package divideAndConquer.sorting;

import static divideAndConquer.sorting.QuickSort.PivotType.FIRST;

public class QuickSort {

    public static enum PivotType{
        FIRST, LAST, MEDIAN
    }

    private int numComparisons = 0;
    private PivotType type;

    public QuickSort(PivotType type){
        this.type = type;
    }

    public void setType(PivotType type) {
        this.type = type;
    }

    public int[] sort(int[] input, int len){

        if(len == 1)
            return input;


        return input;
    }

    private int getPivot(int[] array, int start, int end) {
        int pivot;

        switch (type) {
            case FIRST: pivot = array[start];
                break;
            case LAST: pivot = array[end -1];
                break;
            case MEDIAN: pivot = array[start];
                break;
            default: throw new IllegalArgumentException();
        }

        return pivot;
    }

    private int[] partition(int[] array, int start, int end) {
        numComparisons += (end - start);
        int pivot = getPivot(array, start, end);

        if(type != FIRST) {
            array = swap(array, pivot, start);
        }

        int pivotPosition = start + 1;
        boolean seenGreater = false;

        for(int j = start + 1; j < end; j++){
            if(array[j] < pivot){
                if(seenGreater)
                    array = swap(array, j, pivotPosition);
                pivotPosition += 1;
            } else
                seenGreater= true;
        }
        array = swap(array, start, pivotPosition-1);
        return array;
    }

    private int[] swap(int[] array, int oldPos, int newPos) {
        array[newPos] = array[newPos] + array[oldPos];
        array[oldPos] = array[newPos] - array[oldPos];
        array[newPos] = array[newPos] - array[oldPos];
        return array;
    }

}
