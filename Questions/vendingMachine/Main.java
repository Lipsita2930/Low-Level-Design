package Questions.vendingMachine;

public class Main {

    public static void main(String[] args) throws Exception {

      VendingMachineContext vendingMachine = new VendingMachineContext();
      vendingMachine.clickCoinInsertedState(Coin.FIVE_RUPEES);
      vendingMachine.clickOnSelectProduct(102);
      
    }

    


    private static void fillUpInventory(VendingMachineContext vendingMachine) {

        for (int i = 0; i < 10; i++) {

          Item newItem = new Item();
          int codeNumber = 101 + i; 

          if (i >= 0 && i < 3) {
            newItem.setItemType(ItemType.COKE);
            newItem.setPrice(12);
          } else if (i >= 3 && i < 5) {
            newItem.setItemType(ItemType.PEPSI);
            newItem.setPrice(9);
          } else if (i >= 5 && i < 7) {
            newItem.setItemType(ItemType.JUICE);
            newItem.setPrice(13);
          } else if (i >= 7 && i < 10) {
            newItem.setItemType(ItemType.SODA);
            newItem.setPrice(7);
          }

          for (int j = 0; j < 5; j++) {
            vendingMachine.updateInventory(newItem, codeNumber);
          }
        }
      }


   
    
}
