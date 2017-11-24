package divideAndConquer;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 22/11/17
 */
public class CountingInversionsTest {

    @Test
    public void test(){

        long[] input1 = {1,2,3,4,5,6};
        check(input1, 0);

        long[] input2 = {6,5,4,3,2,1};
        check(input2, 15);

        long[] input3 = {1,3,5,2,4,6};
        check(input3, 3);

        long[] input4 = {1,5,3,2,4};
        check(input4, 4);

        long[] input5 = {5,4,3,2,1};
        check(input5, 10);

        long[] input6 = {1,6,3,2,4,5};
        check(input6, 5);

        long[] input7 = { 9, 12, 3, 1, 6, 8, 2, 5, 14, 13, 11, 7, 10, 4, 0};
        check(input7, 56);

        long[] input8 = { 37, 7, 2, 14, 35, 47, 10, 24, 44, 17, 34, 11, 16,
                         48, 1, 39, 6, 33, 43, 26, 40, 4, 28, 5, 38, 41, 42,
                         12, 13, 21, 29, 18, 3, 19, 0, 32, 46, 27, 31, 25, 15,
                         36, 20, 8, 9, 49, 22, 23, 30, 45};
        check(input8, 590);

        long[] input9 = { 4, 80, 70, 23, 9, 60, 68, 27, 66, 78, 12, 40, 52, 53,
                        44, 8, 49, 28, 18, 46, 21, 39, 51, 7, 87, 99, 69, 62,
                        84, 6, 79, 67, 14, 98, 83, 0, 96, 5, 82, 10, 26, 48,
                        3, 2, 15, 92, 11, 55, 63, 97, 43, 45, 81, 42, 95, 20,
                        25, 74, 24, 72, 91, 35, 86, 19, 75, 58, 71, 47, 76, 59,
                        64, 93, 17, 50, 56, 94, 90, 89, 32, 37, 34, 65, 1, 73,
                        41, 36, 57, 77, 30, 22, 13, 29, 38, 16, 88, 61, 31, 85, 33, 54};
        check(input9, 2372);
    }

    private void check(long[] input, long expected) {
        long count = new CountingInversions(input).getNumInversions();
        Assert.assertEquals(expected, count);
    }

    @Test
    public void advancedTest() {
        try(BufferedReader reader = new BufferedReader(new FileReader("data/IntegerArray.txt"))) {
            String line = reader.readLine();
            long[] input = new long[100000];
            int i = 0;
            while (line != null) {
                input[i++] = Long.parseLong(line);
                line = reader.readLine();
            }
            long count = new CountingInversions(input).getNumInversions();
            System.out.println("NumInversions:" + count);
        }catch (IOException e){
            System.err.println(e.toString());
        }
    }
}
