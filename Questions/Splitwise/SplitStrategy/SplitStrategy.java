package Questions.Splitwise.SplitStrategy;
import java.util.List;

import Questions.Splitwise.model.Split;

public interface SplitStrategy {
    List<Split> calculateSplit(double totalAmount, List<String> userIds, List<Double> values);
}