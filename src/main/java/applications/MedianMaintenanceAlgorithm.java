package applications;

import java.util.Comparator;
import java.util.PriorityQueue;

import static java.lang.Math.abs;

/**
 * @author Anurag Paul
 *         Date: 13/1/18
 */
public class MedianMaintenanceAlgorithm {

    private PriorityQueue<Integer> lowHeap = new PriorityQueue<>(Comparator.reverseOrder());
    private PriorityQueue<Integer> highHeap = new PriorityQueue<>();

    public MedianMaintenanceAlgorithm(){}

    public int getNewMean(int newNumber) {
        addNumber(newNumber);
        balanceHeaps();

        int lowHeapSize = lowHeap.size();
        int highHeapSize = highHeap.size();

        int median;

        if(lowHeapSize >= highHeapSize)
            median = lowHeap.peek();
        else
            median = highHeap.peek();

        return median;
    }

    private void addNumber(int newNumber){

        int maxLowHeap = lowHeap.peek() != null ? lowHeap.peek() : 0;
        int minHighHeap = highHeap.peek() != null ? highHeap.peek() : 0;

        if(newNumber <= maxLowHeap)
            lowHeap.offer(newNumber);
        else if (newNumber >= minHighHeap)
            highHeap.offer(newNumber);
        else
            lowHeap.offer(newNumber);
    }

    private void balanceHeaps() {
        int lowHeapSize = lowHeap.size();
        int highHeapSize = highHeap.size();

        int diffSize = abs(lowHeapSize-highHeapSize);

        while(diffSize > 1) {
            if(lowHeapSize > highHeapSize)
                highHeap.offer(lowHeap.poll());
            else
                lowHeap.offer(highHeap.poll());

            diffSize-=2;
        }
    }
}
