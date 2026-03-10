package Questions.Splitwise.model;

public class Split {

    private String userId;
    private double amount;

    public Split(String userId, double amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }
    public double getAmount() {
        return amount;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    
    
}
