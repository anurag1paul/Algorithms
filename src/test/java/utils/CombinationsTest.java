package utils;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Anurag Paul
 * Date: 1/3/18
 */
public class CombinationsTest {

    @Test
    public void test(){
        for(Set<Integer> c : Combinations.nChooseR(24,11)){
            System.out.println(c);
        }
    }

    @Test
    public void newTest() {
        Set<Integer> a = new HashSet<>();
        a.add(1);
        Map<Set, Integer> map = new HashMap<>();
        map.put(a, 1);
        a = new HashSet<>();
        a.add(1);
        System.out.println(map.get(a));
    }
}
