package Questions.Splitwise.model;

import java.util.List;

public class Expense {

    public static int nextExpenseId;

    private String expenseId;
    private String description;
    private String paidByUserID;
    private double amount;
    private String groupId;
    List<Split> Splits; // User involved in the split and by how much amount

    public Expense( String description, String paidByUserID, double amount, String groupId,
            List<Split> splits) {
        this.expenseId = "expense" + (++nextExpenseId);
        this.description = description;
        this.paidByUserID = paidByUserID;
        this.amount = amount;
        this.groupId = groupId;
        Splits = splits;
    }
    public String getExpenseId() {
        return expenseId;
    }
    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPaidByUserID() {
        return paidByUserID;
    }
    public void setPaidByUserID(String paidByUserID) {
        this.paidByUserID = paidByUserID;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getGroupId() {
        return groupId;
    }
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public List<Split> getSplits() {
        return Splits;
    }
    public void setSplits(List<Split> splits) {
        Splits = splits;
    }


    
}
