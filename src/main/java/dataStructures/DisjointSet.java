package dataStructures;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 23/6/17
 */
public class DisjointSet {

    private static Logger logger = LoggerFactory.getLogger(DisjointSet.class);

    private int[][] relationships;

    public DisjointSet(int size){
        relationships = new int[size][2]; //0->parent, 1-> rank

        //initialise relationships
        for(int i = 0; i < size; i++){
            relationships[i][0] = i;
            relationships[i][1] = 0;
        }
    }

    public void union(int first, int second){
        int firstParent = find(first);
        int secondParent = find(second);

        int firstParentRank = relationships[firstParent][1];
        int secondParentRank = relationships[secondParent][1];

        if(firstParentRank == secondParentRank) { //same rank
            relationships[firstParent][1] += 1;
            relationships[secondParent][0] = firstParent;
        }else if(firstParentRank > secondParentRank){
            relationships[secondParent][0] = firstParent;
        } else
            relationships[firstParent][0] = secondParent;
    }

    public int find(int member){
        int parent = relationships[member][0];
        if(parent == member)
            return member;
        else
            return find(parent);
    }
}
