package divideAndConquer.selection;

import static java.lang.Math.random;
import static utils.utils.swap;

public class RandomizedSelection {

    public static int getOrderStatistic(int[] input, int orderStatistic) {

        int len = input.length;

        if(orderStatistic > len || orderStatistic <= 0)
            throw new IllegalArgumentException("Order Statistic must be lie between zero and length of the array");

        return rSelect(input, 0, len, orderStatistic);
    }

    private static int rSelect(int[] array, int start, int end, int orderStatistic) {

        int len = end - start;
        if(len == 1)
            return array[start];

        int pivotIndex = start + (int)(random() * len);

        array = swap(array, pivotIndex, start);

        int pivot = array[start];

        int pivotPosition = start + 1;
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

        if (pivotPosition == orderStatistic)
            return pivot;
        else if (pivotPosition > orderStatistic)
            return rSelect(array, start, pivotPosition - 1, orderStatistic);
        else
            return rSelect(array, pivotPosition, end, orderStatistic);
    }
}
