package graphs;

import org.junit.Test;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 23/6/17
 */
public class WeightedGraphTest {

    @Test
    public void test(){
        Map<AbstractMap.SimpleImmutableEntry<Integer,Integer>, Integer> map =  new HashMap<>();
        map.put(new AbstractMap.SimpleImmutableEntry<>(1,1), 1);
        map.put(new AbstractMap.SimpleImmutableEntry<>(1,1), 2);
        for(Map.Entry<AbstractMap.SimpleImmutableEntry<Integer,Integer>, Integer> entry: map.entrySet())
            System.out.println(entry);
    }
}
