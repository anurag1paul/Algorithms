package applications.scheduling;

import org.junit.Assert;
import org.junit.Test;

import static applications.scheduling.ScoreCalculatorFactory.ScoreType.DIFFERENCE_BASED;
import static applications.scheduling.ScoreCalculatorFactory.ScoreType.RATIO_BASED;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 18/1/18
 */
public class GreedyJobSchedulerTest {

    @Test
    public void basicTest(){
        String fileName = "data/scheduling_jobs_test.txt";
        String sep = " ";

        GreedyJobScheduler scheduler = new GreedyJobScheduler(DIFFERENCE_BASED, fileName, sep);
        Assert.assertEquals(68615, scheduler.createSchedule());

        scheduler = new GreedyJobScheduler(RATIO_BASED, fileName, sep);
        Assert.assertEquals(67247, scheduler.createSchedule());
    }

    @Test
    public void advancedTest(){
        String fileName = "data/scheduling_jobs.txt";
        String sep = " ";

        GreedyJobScheduler scheduler = new GreedyJobScheduler(DIFFERENCE_BASED, fileName, sep);
        Assert.assertEquals(69119377652L, scheduler.createSchedule());

        scheduler = new GreedyJobScheduler(RATIO_BASED, fileName, sep);
        Assert.assertEquals(67311454237L, scheduler.createSchedule());
    }
}
