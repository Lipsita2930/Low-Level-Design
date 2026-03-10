package Questions.Splitwise.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Questions.Splitwise.SplitStrategy.SplitFactory;
import Questions.Splitwise.SplitStrategy.SplitType;
import Questions.Splitwise.simplifier.*;;

public class Group {

    public static int nextGroupId = 0;
    public String groupId;
    public String name;
    public List<User> members; //observers
    public Map<String, Expense> groupExpenses; // Group's own expense book
    public Map<String, Map<String, Double>> groupBalances; // memberId -> {otherMemberId -> balance}

    public Group(String name) {
        this.groupId = "group" + (++nextGroupId);
        this.name = name;
        this.members = new ArrayList<>();
        this.groupExpenses = new HashMap<>();
        this.groupBalances = new HashMap<>();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public Map<String, Expense> getGroupExpenses() {
        return groupExpenses;
    }

    public void setGroupExpenses(Map<String, Expense> groupExpenses) {
        this.groupExpenses = groupExpenses;
    }

    public Map<String, Map<String, Double>> getGroupBalances() {
        return groupBalances;
    }

    public void setGroupBalances(Map<String, Map<String, Double>> groupBalances) {
        this.groupBalances = groupBalances;
    }



    //1. addMember to the group
    public void addMember(User user){
        members.add(user);
        groupBalances.put(user.getUserId(), new HashMap<>());
    }

    //2.removemember from group
    public boolean removemember(String userId){
        
       
        if(!canLeaveGroup(userId)){
            System.out.println("\nUser not allowed to leave group without clearing expenses");
            return false;
        }

        members.removeIf(user -> user.getUserId().equals(userId));
        groupBalances.remove(userId);

        for (Map.Entry<String, Map<String, Double>> memberBalance : groupBalances.entrySet()) {
            memberBalance.getValue().remove(userId);
        }

        return true;

    }

    public boolean isMember(String userId) {
        return groupBalances.containsKey(userId);
    }

    //3.check if member can leave the group
    public boolean canLeaveGroup(String userId){

        if (!isMember(userId)) {
            throw new RuntimeException("user is not a part of this group");
        }

        Map<String, Double> userBalanceSheet = groupBalances.get(userId);
        for (Map.Entry<String, Double> balance : userBalanceSheet.entrySet()) {
            if (Math.abs(balance.getValue()) > 0.01) {
                return false;
            }
        }

        return true;

    }

    public void notifyMembers(String message) {
        for (User observer : members) {
            observer.update(message);
        }
    }


    //4. add Expense
    public boolean addExpense(String description, double amount, String paidByUserId,
        List<String> involvedUsers, SplitType splitType) {
        return addExpense(description, amount, paidByUserId, involvedUsers, splitType, new ArrayList<>());
    }

    public boolean addExpense(String description, double amount, String paidByUserId,
                   List<String> involvedUsers, SplitType splitType, 
                   List<Double> splitValues){

                     
        if (!isMember(paidByUserId)) {
            throw new RuntimeException("user is not a part of this group");
        }

        for (String userId : involvedUsers) {
            if (!isMember(userId)) {
                throw new RuntimeException("involvedUsers are not a part of this group");
            }
        }
        

        List<Split> splits = SplitFactory.createStrategy(splitType).calculateSplit(amount, involvedUsers, splitValues);

        Expense expense = new Expense(description, paidByUserId, amount, groupId, splits);
        groupExpenses.put(expense.getExpenseId(), expense);

        for (Split split : splits) {
            if (!split.getUserId().equals(paidByUserId)) {
                updateGroupBalance(paidByUserId, split.getUserId(), split.getAmount());
            }
        }

        System.out.println("\n=========== Sending Notifications ====================");
        String paidByName = getUserByuserId(paidByUserId).getUserName();
        notifyMembers("New expense added: " + description + " (Rs " + amount + ")");

        return true;

    }

    private User getUserByuserId(String userId){
        for(User user : members){
            if(user.getUserId().equals(userId)){
               return user;
            }
        }
        return null;
    }

    public void updateGroupBalance(String fromUserId, String toUserId, double amount) {

        groupBalances.get(fromUserId).put(toUserId, groupBalances.get(fromUserId).getOrDefault(toUserId, 0.0) + amount);
        groupBalances.get(toUserId).put(fromUserId, groupBalances.get(toUserId).getOrDefault(fromUserId, 0.0) - amount);
        
        // Remove if balance becomes zero
        if (Math.abs(groupBalances.get(fromUserId).get(toUserId)) < 0.01) {
            groupBalances.get(fromUserId).remove(toUserId);
        }
        if (Math.abs(groupBalances.get(toUserId).get(fromUserId)) < 0.01) {
            groupBalances.get(toUserId).remove(fromUserId);
        }
    }


    public void settleIndividualPayment(String fromUserId, String toUserId, double amount) {
        User fromUser = getUserByuserId(fromUserId);
        User toUser = getUserByuserId(toUserId);
        
        if (fromUser != null && toUser != null) {
            fromUser.updateBalance(toUserId, amount);
            toUser.updateBalance(fromUserId, -amount);
            
            System.out.println(fromUser.getUserName() + " settled Rs" + amount + " with " + toUser.getUserName());
        }
    }

    

    public void simplifyGroupDebts() {
        Map<String, Map<String, Double>> simplifiedBalances = DebtSimplifier.simplifyDebts(groupBalances);
        groupBalances = simplifiedBalances;
    
        System.out.println("\nDebts have been simplified for group: " + name);
    }
    



    
}
