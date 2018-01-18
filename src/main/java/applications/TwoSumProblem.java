package applications;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Anurag Paul
 *         Date: 15/1/18
 * To compute the number of target values t in the given interval (inclusive)
 * such that there are distinct numbers x,y in the input file that satisfy x+y=t.
 */
public class TwoSumProblem {

    private static final Logger logger = LoggerFactory.getLogger(TwoSumProblem.class);

    private HashSet<Long> data = new HashSet<>();
    private long targetStart;
    private long targetStop;

    public TwoSumProblem(String fileName, long targetStart, long targetStop) {
        loadDataFromFile(fileName);
        this.targetStart = targetStart;
        this.targetStop = targetStop;
    }

    private void loadDataFromFile(String fileName) {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line = reader.readLine();

            while(line != null){
                data.add(Long.parseLong(line));
                line = reader.readLine();
            }
        }
        catch (IOException e) {
            logger.error("File Load Error", e);
            System.exit(1);
        }
    }

    /**
     * Checks if there is a distinct x,y pair in the data that satisfies x + y = t
     * @return count of targets satisfied
     */
    public long getCountOfSatisfiedTargets() {

        int poolSize = 2 * Runtime.getRuntime().availableProcessors() -1;

        ExecutorService es = Executors.newFixedThreadPool(poolSize);
        final Semaphore semaphore = new Semaphore(poolSize);

        Worker.count = new AtomicLong(0);

        for(long t = targetStart; t<=targetStop; t++) {
            Worker worker = new Worker(t, data, semaphore);
            es.submit(worker);
        }

        es.shutdown();
        while(!es.isTerminated());

        return Worker.count.get();
    }

    static class Worker implements Runnable {

        private static AtomicLong count = new AtomicLong(0);
        private final long target;
        private final HashSet<Long> data;
        private final Semaphore semaphore;

        Worker(long target, HashSet<Long> data, Semaphore semaphore) {
            this.target = target;
            this.data = data;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {

            try {
                semaphore.acquire();
                for(long x: data) {
                    long y = target - x;
                    if(y != x && data.contains(y)) {
                        count.incrementAndGet();
                        break;
                    }
                }
            }catch (InterruptedException e){
                logger.error("Worker Interrupted", e);
                Thread.currentThread().interrupt();
            }finally {
                semaphore.release();
            }
        }
    }
}
