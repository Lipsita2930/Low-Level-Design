package Questions.Splitwise.model;

import java.util.HashMap;
import java.util.Map;

public class User {


    public static int nextUserId = 0;
    private String userId;
    private String userName;
    private String email;
    private String phone;
    Map<String, Double> balance;


    public User(String userName, String email, String phone){
        this.userId = "user" + (++nextUserId);
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.balance = new HashMap<>();
    }

    
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Map<String, Double> getBalance() {
        return balance;
    }
    public void setBalance(Map<String, Double> balance) {
        this.balance = balance;
    }

    // whenever a expense is added we add the balance to the user balance sheet
    public void updateBalance(String otherUserId, double amount){
        balance.putIfAbsent(otherUserId, balance.getOrDefault(userId, 0.0)+amount);

        if (Math.abs(balance.get(otherUserId)) < 0.01) {
            balance.remove(otherUserId);
        }
    }


    // as user is the observer, if a expense is added then will notify -- here we can have mail, any other notification
    public void update(String msg){
        System.out.println(msg);
    }

    //how much amount the using is owed
    public double getTotalOwed() {
        double total = 0;
        for (Map.Entry<String, Double> balance : balance.entrySet()) {
            if (balance.getValue() < 0) {
                total += Math.abs(balance.getValue());
            }
        }
        return total;
    }

    //how much the user is owing
    public double getTotalOwing() {
        double total = 0;
        for (Map.Entry<String, Double> balance : balance.entrySet()) {
            if (balance.getValue() > 0) {
                total += balance.getValue();
            }
        }
        return total;
    }


    
}
