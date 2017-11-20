package multiplication;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 17/11/17
 */
public class karatsubaAlgorithmTest {

    @Test
    public void basicTest(){
        Long num1 = 1234L;
        Long num2 = 5678L;
        Long expected = num1 * num2;

        Assert.assertEquals(expected.toString(), karatsubaAlgorithm.multiply(num1.toString(),num2.toString()));
    }

    @Test
    public void addTest(){
        String num1 = "78";
        String num2 = "12";
        Assert.assertEquals("90", karatsubaAlgorithm.add(num1, num2));
    }

    @Test
    public void diffTest(){
        String num1 = "78";
        String num2 = "12";
        String num3 = "10";
        Assert.assertEquals("56", karatsubaAlgorithm.diff(num1, num2, num3));
    }

    @Test
    public void advancedTest(){
        String num1 = "3141592653589793238462643383279502884197169399375105820974944592";
        String num2 = "2718281828459045235360287471352662497757247093699959574966967627";

        long start = System.nanoTime();
        String out = karatsubaAlgorithm.multiply(num1,num2);
        long end = System.nanoTime();
        System.out.println("Karatsuba String Time taken = " + (end -start) + "ns");

        BigInteger b1 = new BigInteger(num1);
        BigInteger b2 = new BigInteger(num2);
        start = System.nanoTime();
        BigInteger b3 = b1.multiply(b2);
        end = System.nanoTime();
        System.out.println("Big Integer Multiplication Time taken = " + (end - start) + "ns");
        Assert.assertEquals(b3.toString(), out);
    }
}
