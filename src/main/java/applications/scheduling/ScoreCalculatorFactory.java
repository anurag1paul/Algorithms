package applications.scheduling;

/**
 * @author Anurag Paul(anurag.paul@delhivery.com)
 *         Date: 18/1/18
 */
public class ScoreCalculatorFactory {

    public static enum ScoreType {

        DIFFERENCE_BASED("DIFFERENCE_BASED"),
        RATIO_BASED("RATIO_BASED");

        String name;

        ScoreType(String name) {
            this.name = name;
        }
    }

    public static ScoreCalculator getCalculator(ScoreType type) {
        ScoreCalculator calculator = null;

        switch (type) {
            case RATIO_BASED: calculator = new RatioScoreCalculator();
                break;
            case DIFFERENCE_BASED: calculator = new DifferenceScoreCalculator();
                break;
        }

        return calculator;
    }
}
