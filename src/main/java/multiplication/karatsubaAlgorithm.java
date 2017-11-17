package multiplication;

import java.math.BigInteger;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 17/11/17
 */
public class karatsubaAlgorithm {

    public static String multiply(String a, String b){
        StringBuilder x = new StringBuilder(a);
        StringBuilder y = new StringBuilder(b);

        return multiply(x, y).toString();
    }

    /*
     * Algorithm Steps:
     * n  = max(len(x), len(y))
     * if x = (10^(n/2) * a) + b and y = (10^(n/2) * c) + d
     * x * y = (10^(n) * ac) + (10^(n/2) * (((a+b) * (c+d)) - ac -  bd) + bd
     * So just need to calculate ac, bd and (a+b) * (c+d) recursively
     */

    private static StringBuilder multiply(StringBuilder x, StringBuilder y) {

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
            StringBuilder out = new StringBuilder();
            out.append('0');
            return out;
        }

        if (len == 1) {
            int output = Integer.parseInt(x.toString()) * Integer.parseInt(y.toString());
            StringBuilder out = new StringBuilder();
            out.append(output);
            return out;
        }

        int mid = len/2;
        int sh = len-mid;

        StringBuilder a = new StringBuilder(x.substring(0, mid));
        StringBuilder b = new StringBuilder(x.substring(mid, len));
        StringBuilder c = new StringBuilder(y.substring(0, mid));
        StringBuilder d = new StringBuilder(y.substring(mid, len));

        StringBuilder ac = multiply(a, c);
        StringBuilder bd = multiply(b, d);
        StringBuilder middle = diff(multiply(add(a,b), add(c,d)), ac, bd);

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

    private static StringBuilder expand(StringBuilder num, int len) {

        for(int i = 0; i< len; i++)
            num.append('0');
        return num;
    }

    private static StringBuilder equalizeLength(StringBuilder num, int diff) {

        StringBuilder out = new StringBuilder();

        for(int i = 0; i < diff; i++)
            out.append('0');

        out.append(num);
        return out;
    }

    static StringBuilder add(StringBuilder largeNum, StringBuilder... nums) {
        BigInteger output = new BigInteger(largeNum.toString());

        for(StringBuilder num : nums)
            output = output.add(new BigInteger(num.toString()));

        return new StringBuilder(output.toString());
    }

    static StringBuilder diff(StringBuilder largeNum, StringBuilder... nums) {

        BigInteger output = new BigInteger(largeNum.toString());

        for(StringBuilder num : nums)
            output = output.subtract(new BigInteger(num.toString()));

        return new StringBuilder(output.toString());
    }
}
