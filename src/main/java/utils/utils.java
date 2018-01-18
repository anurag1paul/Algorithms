package utils;

import java.util.List;

/**
 * @author Anurag Paul
 *         Date: 13/1/18
 */
public class utils {

    public static int[] swap(int[] array, int oldPos, int newPos) {

        if(oldPos != newPos) {
            array[newPos] = array[newPos] + array[oldPos];
            array[oldPos] = array[newPos] - array[oldPos];
            array[newPos] = array[newPos] - array[oldPos];
        }
        return array;
    }

    public static void printArray(int[] arr){
        System.out.println("Array is");
        for(int i: arr)
            System.out.print(i + ",");
        System.out.println();
    }

    public static void printList(List<Integer> arr){
        System.out.println("List is");
        for(Integer i: arr)
            System.out.print(i + ",");
        System.out.println();
    }
}
