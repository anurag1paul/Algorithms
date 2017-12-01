package divideAndConquer.sorting;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 27/11/17
 */
public class QuickSortTest {

    @Test
    public void sortTest(){
        QuickSort sorter = new QuickSort(QuickSort.PivotType.FIRST);

        int[] input1 = {1,3,2,6,4,8,3,9,2};
        check(sorter.sort(input1));

        int[] input2 = {1,3,2,6,4,8,3,9,2,12};
        check(sorter.sort(input2));

        int[] input3= {9,8,7,6,5,4,3,2,1};
        check(sorter.sort(input3));

        int[] input4 = {10};
        check(sorter.sort(input4));

        int[] input5 = {10,5};
        check(sorter.sort(input5));
    }

    private void check(int[] output) {
        for(int i=0; i<(output.length -1); i++)
            Assert.assertTrue(output[i] <= output[i+1]);
    }

    private void print(int[] array) {
        System.out.println();
        for(int e: array)
            System.out.print(e + ",");
        System.out.println();
    }

    @Test
    public void advancedTest(){
        int size = 10000;
        try(BufferedReader reader = new BufferedReader(new FileReader("data/QuickSort.txt"))) {
            String line = reader.readLine();
            int[] input1 = new int[size];
            int[] input2 = new int[size];
            int[] input3 = new int[size];
            int i = 0;
            while (line != null && i < size) {
                input1[i] = Integer.parseInt(line);
                input2[i] = input1[i];
                input3[i] = input1[i];
                i++;
                line = reader.readLine();
            }
            QuickSort sorter = new QuickSort(QuickSort.PivotType.FIRST);
            check(sorter.sort(input1));
            long count = sorter.getNumComparisons();
            System.out.println("NumComparisons:" + count);

            //LAST
            sorter.setType(QuickSort.PivotType.LAST);
            check(sorter.sort(input2));
            count = sorter.getNumComparisons();
            System.out.println("NumComparisons:" + count);

            //Median
            sorter.setType(QuickSort.PivotType.MEDIAN);
            check(sorter.sort(input3));
            count = sorter.getNumComparisons();
            System.out.println("NumComparisons:" + count);

        }catch (IOException e){
            System.err.println(e.toString());
        }
    }
}
