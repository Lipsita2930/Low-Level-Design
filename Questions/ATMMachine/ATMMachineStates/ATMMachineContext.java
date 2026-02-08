package Questions.ATMMachine.ATMMachineStates;

import java.util.Map;

import Questions.ATMMachine.Account;
import Questions.ATMMachine.CASHTYPE;
import Questions.ATMMachine.Card;
import Questions.ATMMachine.Inventory;
import Questions.ATMMachine.TransactionType;

public class ATMMachineContext {

    private ATMMachineState curentState;
    private Card currentCard;
    private Account currentAccount;
    private TransactionType selectedOperation;
    private Inventory atmInventory;
    private ATMStateFactory atmStateFactory;
    private Map<String, Account> accounts;
    

    public ATMMachineState getCurentState() {
        return curentState;
    }
    public void setCurentState(ATMMachineState curentState) {
        this.curentState = curentState;
    }
    public Card getCurrentCard() {
        return currentCard;
    }
    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }
    public Account getCurrentAccount() {
        return currentAccount;
    }
    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }
    public TransactionType getSelectedOperation() {
        return selectedOperation;
    }
    public void setSelectedOperation(TransactionType selectedOperation) {
        this.selectedOperation = selectedOperation;
    }
    public Inventory getAtmInventory() {
        return atmInventory;
    }
    public void setAtmInventory(Inventory atmInventory) {
        this.atmInventory = atmInventory;
    }
    public ATMStateFactory getAtmStateFactory() {
        return atmStateFactory;
    }
    public void setAtmStateFactory(ATMStateFactory atmStateFactory) {
        this.atmStateFactory = atmStateFactory;
    }
    public Map<String, Account> getAccounts() {
        return accounts;
    }
    public void setAccounts(Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account){
        this.accounts.put(account.getAccountNumber(), account);
    }

    public void removeAccount(Account account){
        this.accounts.remove(account.getAccountNumber());
    }

    public void advanceState(){
        this.curentState.next(this);
    }

    public void insertCard(Card card){

        if(curentState instanceof IDLEState){
            this.currentCard = card;
            advanceState();
        }
        else{
            System.out.println("Cannot insert card "+ curentState.getStateName());
        }

    }

    public void enterPin(int pin){

        if(curentState instanceof HasCardState){

            if(this.currentCard.isValidPin(pin)){
                System.out.println("Validated the entered pin");
                this.setCurrentAccount(accounts.get(currentCard.getAccountNumber()));
                advanceState();
            }
            else{
                System.out.println("Invalid Pin entered");
            }
        }
        else{
            System.out.println("Cannot enter pin "+ curentState.getStateName());
        }
    }

    public void selectOperation(TransactionType type){

        if(curentState instanceof SelectOperationState){
            this.selectedOperation = type;
            advanceState();
        }
        else{
            System.out.println("Cannot Select Operation "+ curentState.getStateName());
        }

    }

    public void performTransaction(int amount){

        if(curentState instanceof TransactionState){

            try{

                if(selectedOperation == TransactionType.CHECK_BALANCE){
                    double balance = currentAccount.getBalance();
                    System.out.println("Balance = "+ balance);
                }
                else if(selectedOperation == TransactionType.WITHDRAW){
                    performWithdraw(amount);
                    advanceState();
                }

            }
            catch(Exception e){
                System.out.println("Transaction Failed");
            }
          
        }
        else{
            System.out.println("Cannot Perform Transaction "+ curentState.getStateName());
        }

    }

    private void performWithdraw(int amount)throws Exception{

        if(!atmInventory.hasSufficientCash(amount)){
            throw new Exception("Insufficient cash in ATM");
        }

        boolean isWithdrawPossible = currentAccount.withdraw(amount);
        if(!isWithdrawPossible){
            throw new Exception("Insufficient balance in Account");

        }

        Map<CASHTYPE, Integer> dispensedCash = atmInventory.dispenseCash((int) amount);

        for (Map.Entry<CASHTYPE, Integer> entry : dispensedCash.entrySet()) {
        System.out.println(entry.getValue() + " x $" + entry.getKey().value);
        }

    }

    public void returnCard() {
        if (curentState instanceof HasCardState
            || curentState instanceof SelectOperationState
            || curentState instanceof TransactionState) {
          System.out.println("Card returned to customer");
          resetATM();
        } else {
          System.out.println("No card to return in " + curentState.getStateName());
        }
      }

      private void resetATM() {
        this.currentCard = null;
        this.currentAccount = null;
        this.selectedOperation = null;
        this.curentState = atmStateFactory.createATMState(StateType.IdleState);
      }

      public void cancelTransaction() {

        if (curentState instanceof TransactionState || curentState instanceof TransactionState) {
          System.out.println("Transaction cancelled");
          returnCard();
        }

         else {
          System.out.println("No transaction to cancel in " + curentState.getStateName());
        }
      }
   
    


    

    
}
