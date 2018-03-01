package utils;

import org.junit.Test;

import java.util.List;

/**
 * @author Anurag Paul
 * Date: 1/3/18
 */
public class CombinationsTest {

    @Test
    public void test(){
        for(List<Integer> c : Combinations.nChooseR(10,2)){
            System.out.println(c);
        }
    }
}
