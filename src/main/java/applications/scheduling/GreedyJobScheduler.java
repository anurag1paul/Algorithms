package applications.scheduling;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 18/1/18
 */
public class GreedyJobScheduler {

    private static Logger logger = LoggerFactory.getLogger(GreedyJobScheduler.class);

    private PriorityQueue<Job> jobs;
    private ScoreCalculator calculator;

    private long objectiveFunction;
    private List<Job> schedule;

    public GreedyJobScheduler(ScoreCalculatorFactory.ScoreType type, String filename, String sep) {
        calculator = ScoreCalculatorFactory.getCalculator(type);
        loadProblemFromFile(filename, sep);
    }

    private void loadProblemFromFile(String fileName, String sep){
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            int nJobs = Integer.parseInt(line);

            jobs = new PriorityQueue<>(nJobs, Comparator.reverseOrder());

            line = reader.readLine();

            for(int i=0; i<nJobs; i++) {

                String[] elements = line.split(sep);
                int weight = Integer.parseInt(elements[0]);
                int length = Integer.parseInt(elements[1]);

                Job job = new Job(weight,length);
                job.setScore(calculator.calculateScore(job));
                jobs.offer(job);

                line = reader.readLine();
            }
        }catch (IOException e) {
            logger.error("File Reading Error", e);
        }
    }

    public long createSchedule(){
        long timer = 0;

        objectiveFunction = 0;
        schedule = new LinkedList<>();

        while(!jobs.isEmpty()) {
            Job job = jobs.poll();
            timer += job.length;
            objectiveFunction += job.weight * timer;
        }

        return objectiveFunction;
    }
}
