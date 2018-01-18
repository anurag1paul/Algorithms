package applications.scheduling;

/**
 * @author Anurag Paul
 *         Date: 18/1/18
 */
public class RatioScoreCalculator implements ScoreCalculator {

    @Override
    public double calculateScore(Job job) {
        return (double)job.weight/(double)job.length;
    }
}
