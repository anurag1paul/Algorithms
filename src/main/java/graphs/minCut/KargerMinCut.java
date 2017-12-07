package graphs.minCut;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class KargerMinCut {

    private Graph initialGraph;
    private double numIters;
    private Logger logger = LoggerFactory.getLogger(KargerMinCut.class);

    public KargerMinCut(Graph graph){
        initialGraph = graph;
        int nodes = graph.getNodes().size();
        numIters = Math.pow(nodes, 2) * Math.log(nodes);
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

        private int generateMinCut() {

            int nNodes = graph.getNodes().size() + 1;

            while(graph.getNodes().size() > 2) {

                List<Edge> edges = new LinkedList<>();
                edges.addAll(graph.getEdges());
                Edge collapseEdge = edges.get(random.nextInt(edges.size()));

                List<Integer> adjacentNodes = new ArrayList<>();
                edges.remove(collapseEdge);

                for(Edge edge: edges){
                    if((collapseEdge.src == edge.src && collapseEdge.dst != edge.dst) ||
                            (collapseEdge.dst == edge.src && collapseEdge.src != edge.dst))
                        adjacentNodes.add(edge.dst);
                    else if ((collapseEdge.src == edge.dst && collapseEdge.dst != edge.src)||
                            (collapseEdge.dst == edge.dst && collapseEdge.src != edge.src))
                        adjacentNodes.add(edge.src);
                }

                graph.addNode(nNodes++, adjacentNodes, true);
                graph.removeNode(collapseEdge.src);
                graph.removeNode(collapseEdge.dst);
            }
            logger.info("Counter:{} minCut: {}", count.incrementAndGet(), graph.getEdges().size());
            return graph.getEdges().size();
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
        final Semaphore semaphore = new Semaphore(2 * poolSize);

        Worker.count = new AtomicInteger(0);

        for(int i=0; i<numIters; i++) {
            Worker worker = new Worker(new Graph(initialGraph), new Random(i), semaphore);
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

