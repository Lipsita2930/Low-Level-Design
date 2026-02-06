package Questions.vendingMachine;

public class VendingMachineContext {


    private StateType currentState;
    private StateFactory stateFactory;
    private Inventory inventory;

    public VendingMachineContext(){
        this.currentState = StateType.IDLE;
        stateFactory = new StateFactory();
        this.inventory = new Inventory();
    }
    
}
