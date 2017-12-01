package divideAndConquer.selection;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 1/12/17
 */
public class DeterministicSelection {

    public static int getOrderStatistic(int[] input, int orderStatistic) {

        int len = input.length;

        if(orderStatistic > len || orderStatistic <= 0)
            throw new IllegalArgumentException("Order Statistic must be lie between zero and length of the array");

        return dSelect(input, input.length, orderStatistic);
    }

    private static int dSelect(int[] array, double len, int orderStatistic) {

        if(len == 1)
            return array[0];

        int pivot = getPivot(array);

        ArrayList<Integer> smaller = new ArrayList<>();
        ArrayList<Integer> greater = new ArrayList<>();

        int equalCount = 0;

        for (int element : array) {
            if (element < pivot)
                smaller.add(element);
            else if (element > pivot)
                greater.add(element);
            else
                equalCount++;
        }
        int pivotPosition = smaller.size() + 1;

        if (pivotPosition <= orderStatistic && orderStatistic <= (pivotPosition + equalCount))
            return pivot;
        else if (pivotPosition > orderStatistic)
            return dSelect(getArray(smaller), smaller.size(), orderStatistic);
        else
            return dSelect(getArray(greater), greater.size(), orderStatistic - pivotPosition);
    }

    private static int[] getArray(ArrayList<Integer> list) {
        int len = list.size();
        int[] array = new int[len];
        for(int i = 0; i < len; i++){
            array[i] = list.get(i);
        }
        return array;
    }

    private static int getPivot(int[] array) {
        double length = array.length;
        int splits = (int) Math.ceil(length / 5.0);

        int[] medianArray = new int[splits];

        for(int i=0; i<splits; i++){
            ArrayList<Integer> split = new ArrayList<>();

            for(int j=i*5, k=0; k < 5 && j < length; j++, k++){
                split.add(array[j]);
            }

            Collections.sort(split);
            medianArray[i] = split.get(split.size()/2);
        }

        return dSelect(medianArray, medianArray.length, (int) Math.floor(medianArray.length/2.0));
    }
}
