package Questions.ATMMachine;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private Map<CASHTYPE, Integer> cashInventory;

    public Inventory() {
        cashInventory = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory(){
        cashInventory.put(CASHTYPE.BILL_100, 10);
        cashInventory.put(CASHTYPE.BILL_50, 10);
        cashInventory.put(CASHTYPE.BILL_20, 20);
        cashInventory.put(CASHTYPE.BILL_10, 30);
        cashInventory.put(CASHTYPE.BILL_5, 20);
        cashInventory.put(CASHTYPE.BILL_1, 50);
    }

    public void addCash(CASHTYPE type, int count){
        cashInventory.put(type, cashInventory.get(type) + count);
    }

    public double getTotalCash(){

        int total = 0;

        for (Map.Entry<CASHTYPE, Integer> entry : cashInventory.entrySet()) {
            total += entry.getKey().value * entry.getValue();
          }
          return total;

    }

    public boolean hasSufficientCash(int amount){
        return getTotalCash() >= amount;
    }


    // Cash Withdrawl
    public Map<CASHTYPE, Integer> dispenseCash(int amount) {
        
        if (!hasSufficientCash(amount)) {
          return null;
        }

        Map<CASHTYPE, Integer> dispensedCash = new HashMap<>();
        int remainingAmount = amount;
       
        for (CASHTYPE cashType : CASHTYPE.values()) {

          int count = Math.min(remainingAmount / cashType.value, cashInventory.get(cashType));
          if (count > 0) {
            dispensedCash.put(cashType, count);
            remainingAmount -= count * cashType.value;
            cashInventory.put(cashType, cashInventory.get(cashType) - count);
          }
        }
       

        if (remainingAmount > 0) {
          for (Map.Entry<CASHTYPE, Integer> entry : dispensedCash.entrySet()) {
            cashInventory.put(entry.getKey(),
                cashInventory.get(entry.getKey()) + entry.getValue());
          }
          return null;
        }
        
        return dispensedCash;
    }
    
    
}
