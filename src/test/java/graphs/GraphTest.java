package graphs;

import org.junit.Test;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 23/6/17
 */
public class GraphTest {

    @Test
    public void test(){
        Map<Edge, Integer> map =  new HashMap<>();
        map.put(new Edge(1,1), 1);
        map.put(new Edge(1,1), 2);
        map.put(new Edge(1,2), 2);
        map.put(new Edge(2,1), 2);
        for(Map.Entry<Edge, Integer> entry: map.entrySet())
            System.out.println(entry);
    }


}
