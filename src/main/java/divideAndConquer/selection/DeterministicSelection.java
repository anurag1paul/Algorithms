package divideAndConquer.selection;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.random;
import static utils.utils.swap;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 1/12/17
 */
public class DeterministicSelection {

    public static int getOrderStatistic(int[] input, int orderStatistic) {

        int len = input.length;

        if(orderStatistic > len || orderStatistic <= 0)
            throw new IllegalArgumentException("Order Statistic must be lie between zero and length of the array");

        return dSelect(input, orderStatistic);
    }

    private static int dSelect(int[] array, int orderStatistic) {

        double len = array.length;

        if(len == 1)
            return array[0];

        int pivot = getPivot(array);

        ArrayList<Integer> smaller = new ArrayList<>();
        ArrayList<Integer> greater = new ArrayList<>();

        for (int element : array) {
            if (element < pivot)
                smaller.add(element);
            else if (element > pivot)
                greater.add(element);
        }
        int pivotPosition = smaller.size();

        if (pivotPosition == orderStatistic)
            return pivot;
        else if (pivotPosition > orderStatistic)
            return dSelect(getArray(smaller), orderStatistic);
        else
            return dSelect(getArray(greater), orderStatistic - pivotPosition);
    }

    private static int[] getArray(ArrayList<Integer> list) {
        int len = list.size();
        int[] array = new int[len];
        for(int i=0; i< len; i++){
            array[i] = list.get(i);
        }
        return array;
    }


    private static int getPivot(int[] array) {
        double length = array.length;
        int splits = (int) Math.ceil(length / 5.0);

        int[] medianArray = new int[splits];
        int[] split = new int[5];

        for(int i=0; i<splits; i++){
            for(int j=i*5, k=0; k<5 && j <length; j++, k++){
                split[k] = array[j];
            }
            Arrays.sort(split);
            medianArray[i] = split[2];
        }

        return dSelect(medianArray, (int) Math.floor(length/2.0));
    }
}
