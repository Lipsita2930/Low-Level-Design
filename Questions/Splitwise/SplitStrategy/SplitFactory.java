package Questions.Splitwise.SplitStrategy;

public class SplitFactory {

    public static SplitStrategy createStrategy(SplitType type) {

        switch (type) {

            case EQUAL:
                return new EqualSplitStrategy();

            case EXACT:
                return new ExactSplitStrategy();

            case PERCENTAGE:
                return new PercentageSplitStrategy();

            default:
                throw new RuntimeException("Invalid split type");
        }
    }
}
