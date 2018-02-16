package graphs.minCut;

import dataStructures.DisjointSet;
import graphs.Edge;
import graphs.Graph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Anurag Paul
 *         Date: 13/1/18
 */
public class KargerMinCut {

    private Graph initialGraph;
    private double numIters;
    private Logger logger = LoggerFactory.getLogger(KargerMinCut.class);

    public KargerMinCut(Graph graph){
        initialGraph = graph;
        int numVertices = graph.getNumVertices();
        numIters = Math.pow(numVertices, 2) * (Math.log(numVertices) / Math.log(2));
    }

    public double getNumIters() {
        return numIters;
    }

    private static class Worker implements Runnable {

        public static AtomicInteger count = new AtomicInteger(0);
        public static Logger logger = LoggerFactory.getLogger(Worker.class);

        private int minCut;
        private Graph graph;
        private Random random;
        private Semaphore semaphore;

        public Worker(Graph graph, Random random, Semaphore semaphore){
            this.graph = graph;
            this.random = random;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                minCut = generateMinCut();
            }catch (InterruptedException e){
                logger.error("Worker Interrupted", e);
                Thread.currentThread().interrupt();
            }finally {
                semaphore.release();
            }
        }

        int generateMinCut() {
            int numVertices = graph.getNumVertices(), numEdges = graph.getNumEdges();
            List<Edge> edges = new ArrayList<>();
            edges.addAll(graph.getAllEdges());

            DisjointSet disjointSet = new DisjointSet(numVertices);

            int vertices = numVertices;

            // Keep contracting vertices until there are only 2 vertices left.
            while (vertices > 2) {
                try {
                    int i = random.nextInt(numEdges);

                    // Find vertices of current edge
                    int vertex1 = disjointSet.find(edges.get(i).src - 1);
                    int vertex2 = disjointSet.find(edges.get(i).dst - 1);

                    // if the two vertices are different, contract the edge
                    if (vertex1 != vertex2) {
                        vertices--;
                        disjointSet.union(vertex1, vertex2);
                    }
                }catch (Exception e){
                    logger.error("", e);
                }
            }

            //Count the edges between the two vertices left in the graph
            int cutEdges = 0;
            for (int i=0; i<numEdges; i++)
            {
                int vertex1 = disjointSet.find(edges.get(i).src - 1);
                int vertex2 = disjointSet.find(edges.get(i).dst - 1);
                if (vertex1 != vertex2)
                    cutEdges++;
            }

            logger.debug("Count:{} minCut:{}", count.incrementAndGet(), cutEdges);
            return cutEdges;
        }

        public int getMinCut() {
            return minCut;
        }
    }

    public int searchMinCuts() {
        int minCut = Integer.MAX_VALUE;
        int poolSize = 2 * Runtime.getRuntime().availableProcessors() -1;

        List<Worker> workers = new ArrayList<>();

        ExecutorService es = Executors.newFixedThreadPool(poolSize);
        final Semaphore semaphore = new Semaphore(poolSize);

        Worker.count = new AtomicInteger(0);

        for(int i=0; i<numIters; i++) {
            Worker worker = new Worker(initialGraph, new Random(i), semaphore);
            workers.add(worker);
            es.submit(worker);
        }
        es.shutdown();
        while(!es.isTerminated());

        if(es.isTerminated()) {
            for (Runnable worker : workers) {
                minCut = Math.min(minCut, ((Worker) worker).getMinCut());
            }
        }
        return minCut;
    }
}

