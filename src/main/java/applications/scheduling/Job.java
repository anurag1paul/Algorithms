package applications.scheduling;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 18/1/18
 */
public class Job implements Comparable<Job>{

    public final int weight;
    public final int length;
    private double score;

    public Job(int weight, int length) {
        this.weight = weight;
        this.length = length;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    @Override
    public int compareTo(Job job) {
        int compare =  Double.compare(score, job.score);

        if(compare == 0)
            compare = Integer.compare(weight, job.weight);

        return compare;
    }
}
