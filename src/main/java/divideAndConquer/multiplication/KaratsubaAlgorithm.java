package divideAndConquer.multiplication;

import java.math.BigInteger;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 17/11/17
 */
public class KaratsubaAlgorithm {

    /*
     * Algorithm Steps:
     * n  = max(len(x), len(y))
     * if x = (10^(n/2) * a) + b and y = (10^(n/2) * c) + d
     * x * y = (10^(n) * ac) + (10^(n/2) * (((a+b) * (c+d)) - ac -  bd) + bd
     * So just need to calculate ac, bd and (a+b) * (c+d) recursively
     */

    public static String multiply(String x, String y) {

        int len = 0;
        int lenX = x.length();
        int lenY = y.length();

        if(lenX == lenY)
            len = lenX;
        else if(lenX > lenY) {
            len = lenX;
            y = equalizeLength(y, lenX - lenY);
        }
        else{
            len = lenY;
            x = equalizeLength(x, lenY - lenX);
        }

        if(len == 0){
            return "0";
        }

        if (len == 1) {
            Integer output = Integer.parseInt(x) * Integer.parseInt(y);
            return output.toString();
        }

        int mid = len/2;
        int sh = len-mid;

        String a = x.substring(0, mid);
        String b = x.substring(mid, len);
        String c = y.substring(0, mid);
        String d = y.substring(mid, len);

        String ac = multiply(a, c);
        String bd = multiply(b, d);
        String middle = diff(multiply(add(a,b), add(c,d)), ac, bd);

        return add(expand(ac, 2*sh), expand(middle, sh), bd);
    }

    private static byte[] subArray(byte[] array, int start, int end) throws IllegalArgumentException {

        if(start > end)
            throw new IllegalArgumentException("start has to be less than end");

        int length = end - start;
        byte[] out = new byte[length];

        for(int i=0, j=start; j < end; i++, j++)
            out[i] = array[j];

        return out;
    }

    static String expand(String num, int len) {

        StringBuilder newNum = new StringBuilder();
        newNum.append(num);

        for(int i = 0; i< len; i++)
            newNum.append('0');

        return newNum.toString();
    }

    static String equalizeLength(String num, int diff) {

        StringBuilder out = new StringBuilder();

        for(int i = 0; i < diff; i++)
            out.append('0');

        out.append(num);
        return out.toString();
    }

    static String add(String largeNum, String... nums) {
        BigInteger output = new BigInteger(largeNum);

        for(String num : nums)
            output = output.add(new BigInteger(num));

        return output.toString();
    }

    static String diff(String largeNum, String... nums) {

        BigInteger output = new BigInteger(largeNum);

        for(String num : nums)
            output = output.subtract(new BigInteger(num));

        return output.toString();
    }
}
