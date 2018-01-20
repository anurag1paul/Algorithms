package applications;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Anurag Paul
 *         Date: 20/1/18
 */
public class HuffmanCodesTest {

    @Test
    public void basicTest1() {
        String fileName = "data/huffmanTest1.txt";
        HuffmanCodes codes = new HuffmanCodes(fileName);
        codes.execute();
        Assert.assertEquals(2, codes.getMinEncoding());
        Assert.assertEquals(5, codes.getMaxEncoding());
    }

    @Test
    public void basicTest2() {
        String fileName = "data/huffmanTest2.txt";
        HuffmanCodes codes = new HuffmanCodes(fileName);
        codes.execute();
        Assert.assertEquals(3, codes.getMinEncoding());
        Assert.assertEquals(6, codes.getMaxEncoding());
    }

    @Test
    public void advancedTest() {
        String fileName = "data/huffman.txt";
        HuffmanCodes codes = new HuffmanCodes(fileName);
        codes.execute();
        Assert.assertEquals(9, codes.getMinEncoding());
        Assert.assertEquals(19, codes.getMaxEncoding());
    }
}
