package utils;

public class utils {

    public static int[] swap(int[] array, int oldPos, int newPos) {

        if(oldPos != newPos) {
            array[newPos] = array[newPos] + array[oldPos];
            array[oldPos] = array[newPos] - array[oldPos];
            array[newPos] = array[newPos] - array[oldPos];
        }
        return array;
    }
}
