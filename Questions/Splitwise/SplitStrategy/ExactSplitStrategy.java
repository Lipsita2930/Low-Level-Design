package Questions.Splitwise.SplitStrategy;

import java.util.*;

import Questions.Splitwise.model.Split;

class ExactSplitStrategy  implements SplitStrategy {
    @Override
    public List<Split> calculateSplit(double totalAmount, List<String> userIds, List<Double> values) {
        List<Split> splits = new ArrayList<>();

        //validations
        
        for (int i = 0; i < userIds.size(); i++) {
            splits.add(new Split(userIds.get(i), values.get(i)));
        }
        return splits;
    }
}