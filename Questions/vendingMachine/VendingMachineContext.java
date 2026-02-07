package Questions.vendingMachine;

import java.util.List;

public class VendingMachineContext {


    private VendingMachineState currentState;
    private StateFactory stateFactory;
    private Inventory inventory;
    private List<Coin> coinList;
    private int selectProduct;
    

    public VendingMachineContext(){
        this.stateFactory = new StateFactory();
        this.inventory = new Inventory();
        this.currentState =  stateFactory.createState(StateType.IDLE);
    }


    public VendingMachineState getCurrentState() {
        return currentState;
    }


    public void setCurrentState(VendingMachineState currentState) {
        this.currentState = currentState;
    }


    public StateFactory getStateFactory() {
        return stateFactory;
    }


    public void setStateFactory(StateFactory stateFactory) {
        this.stateFactory = stateFactory;
    }


    public Inventory getInventory() {
        return inventory;
    }


    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }


    public List<Coin> getCoinList() {
        return coinList;
    }


    public void setCoinList(List<Coin> coinList) {
        this.coinList = coinList;
    }


    public int getSelectProduct() {
        return selectProduct;
    }


    public void setSelectProduct(int selectProduct) {
        this.selectProduct = selectProduct;
    }

    private void advanceState(){
        currentState.next(this);
        System.out.println("State changed to "+ currentState.getStateName());
    }
    

    //Step 1
    public void clickCoinInsertedState(Coin coin){

        if(currentState instanceof IDLE || currentState instanceof HasCoin){
            System.out.println("Coin Inserter with denomination "+coin.name()+" and worth"+coin.value);
            advanceState();
            coinList.add(coin);
        }
        else{
            System.out.println("Cannot insert coin in the state "+currentState.getStateName());
        }

    }

    // STEP 2 - SELECT THE PRODUCT

    public void clickOnSelectProduct(int code) throws Exception{

        if(currentState instanceof HasCoin){
            advanceState();
            selectProduct(code);
        }
        
    }

    private void selectProduct(int code) throws Exception{

       if(currentState instanceof SelectProduct){

        Item item = inventory.getSelectedProduct(code);

        if(item == null){
            setCurrentState(stateFactory.createState(StateType.OUTOFSTOCK));
            throw new Exception("Item already sold out");
        }

        int balance = getBalance();

        if(item.getPrice() > balance){
            System.out.println("Insufficient amount. Product price: " + item.getPrice() + ", paid: " + balance);
            return;
          }
        

        advanceState();
        dispenseItem(item, code);

        if (balance >= item.getPrice()) { 
            double change = balance - item.getPrice();
            System.out.println("Returning change: " + change);
        }

    }
 }



    public void dispenseItem(Item item, int code) throws Exception{

        if(currentState instanceof Dispense){

            inventory.removeItem(item, code);
            coinList.clear();
            this.selectProduct = 0;  

        }

    }


    public int getBalance() {
        int balance = 0;
        for (Coin coin : coinList) {
          balance += coin.value; // Sum up the coin values
        }
        return balance;
      }


      public void updateInventory(Item item, int codeNumber) {
        
        if (currentState instanceof IDLE) { 

          try {
            inventory.addItem(item, codeNumber); 

          } catch (Exception e) {
            System.out.println("Error updating inventory: " + e.getMessage());
          }
        } else {
          System.out.println("Inventory can only be updated in Idle state");
        }
      }



    
}
